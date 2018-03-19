package huxiu.httpbrower;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/***
 * 设置代理访问，发送http请求，得到返回页面
 */
public class HttpReponse{
    public static String getHtml(String url){
        String html = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

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
                html = EntityUtils.toString(entity,"utf-8");
            }
            httpClient.close();
            httpResponse.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return html;
    }
}
