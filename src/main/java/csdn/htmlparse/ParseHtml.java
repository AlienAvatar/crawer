package csdn.htmlparse;

import csdn.database.JDBCMethod;
import csdn.htmlbrowser.HTTPReponse;
import csdn.model.CSDNBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseHtml {

    public static List<String> ParseHtml(String html) {
        List<String> urls = new ArrayList<String>();
        Set<String> filter = new HashSet<String>();
        Document doc = Jsoup.parse(html);

        Element list = doc.getElementById("feedlist_id");
        Elements links = list.select(".list_con").select(".title").select(".csdn-tracking-statistics");
        for (Element link : links) {
            String url = link.getElementsByTag("a").attr("href");
            String linkText = link.text();
            if (url.contains("http://blog.csdn.net/") && url.contains("article")) {
                filter.add(url);//过滤
                System.out.println("标题:" + linkText + " \n链接url: " + url);
            }
        }
        urls.addAll(filter);
        return urls;
    }

    public static CSDNBean ParseCsdn(String html) {
        List<CSDNBean> list = new ArrayList<CSDNBean>();

        Document doc = Jsoup.parse(html);

        String title = doc.select(".csdn_top").text();
        String date = doc.select(".artical_tag").select(".time").text();
        String readNum = doc.select(".right_bar").select(".txt").text();
        String author = doc.getElementById("uid").text();
        String content = doc.select("#article_content").select(".htmledit_views").text();
        String type = doc.select(".artical_tag").select(".original").text();

        readNum = subNumber(readNum);
        CSDNBean CSDNBean = new CSDNBean();
        CSDNBean.setTitle(title);
        CSDNBean.setDate(date);
        CSDNBean.setReadNum(readNum);
        CSDNBean.setAuthor(author);
        CSDNBean.setContent(content);
        CSDNBean.setType(type);
        CSDNBean.setIsDelete(0);
        return CSDNBean;
    }

    public static void main(String[] args) {
        String html = HTTPReponse.getHtml("http://blog.csdn.net/bKMk01MZ3w/article/details/79597592");
//        List<CSDNBean> list = ParseCsdn(html);
//        for (CSDNBean bean : list){
//            JDBCMethod.insert(bean);
//        }

    }

    //截取数字
    public static String subNumber(String str) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.group();
        }
        return "null";
    }
}
