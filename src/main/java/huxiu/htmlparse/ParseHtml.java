package huxiu.htmlparse;

import huxiu.httpbrower.HttpReponse;
import huxiu.model.HuxiuBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParseHtml {
    public static HuxiuBean parseHtml(){
        String html = HttpReponse.getHtml("https://www.huxiu.com/article/235501.html");

        if(html != null){
            //将html解析成DOM结构
            Document document = Jsoup.parse(html);
            String content = document.select(".main").get(0).text();
            String author = document.select(".article-author").get(0).text();
            String title = document.select(".article-author").get(0).text();
            String comment = document.select(".article-pl").get(0).text();
            //截取了10000个字
            content = content.substring(0,9999);
            HuxiuBean huxiuBean = new HuxiuBean();
            huxiuBean.setContent(content);
            huxiuBean.setAuthor(author);
            huxiuBean.setTitle(title);
            huxiuBean.setComment(comment);

            String article = "题目：" + title + " \n 作者：" + author + " \n 文章：" + content + " \n 评论：" + comment;
            System.out.println(article);
            return huxiuBean;
        }
        return null;

    }


}
