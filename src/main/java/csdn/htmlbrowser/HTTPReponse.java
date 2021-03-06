package csdn.htmlbrowser;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HTTPReponse {

    public static String getHtml(String url) {
        if (url != null && url.length() > 0) {
            String html = null;
            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpClientParams.setCookiePolicy(httpClient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);
            HttpGet httpGet = new HttpGet(url);
            //设置超时处理
            RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).
                    setSocketTimeout(3000).build();
            // user-agent
            httpGet.setHeader("user-agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            httpGet.setConfig(config);
            try {
                // 执行请求获取html的文档
                CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = httpResponse.getEntity();
                    html = EntityUtils.toString(entity, "utf-8");
                }
                httpClient.close();
                httpResponse.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return html;
        }
       return null;
    }


}
