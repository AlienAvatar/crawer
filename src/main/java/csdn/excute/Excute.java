package csdn.excute;

import csdn.database.JDBCMethod;
import csdn.htmlbrowser.HTTPReponse;
import csdn.htmlparse.ParseHtml;
import csdn.model.CSDNBean;
import huxiu.database.JDBC;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.SQLException;
import java.util.List;

public class Excute {
    public static void main(String[] args) {

//        String test = "<a strategy=\"recommend\" href=\"http://blog.csdn.net/linsongbin1/article/details/79410156\" target=\"_blank\"> 索引表和ES的一点点思考 </a>";
//        String test2 = "<a>所属分类 <em> 数据库 </em> 不感兴趣</a>";
//        test = test2 + test;
//        Document document = Jsoup.parse(test);
//        Elements list = document.getElementsByTag("a");
//        for(Element bean : list) {
//            String str = bean.select("h2").attr("href");
//            System.out.println(bean.attr("href"));
//        }
        String html = HTTPReponse.getHtml("https://www.csdn.net/");
        List<String> urls = ParseHtml.ParseHtml(html);
        JDBCMethod jdbcMethod = new JDBCMethod();
        if (urls != null) {
            String newHtml = null;
            for (String url : urls) {
                newHtml = HTTPReponse.getHtml(url);
                if(newHtml != null && newHtml.length() > 0) {
                    CSDNBean csdnBean = ParseHtml.ParseCsdn(newHtml);
                    if(csdnBean != null) {
                        try {
                            jdbcMethod.insert(csdnBean);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
