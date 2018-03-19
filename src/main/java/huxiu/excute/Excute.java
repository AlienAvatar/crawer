package huxiu.excute;

import huxiu.database.JDBC;
import huxiu.htmlparse.ParseHtml;
import huxiu.model.HuxiuBean;

public class Excute {
    public static void main(String[] args) {
        HuxiuBean huxiuBean = ParseHtml.parseHtml();
        JDBC jdbc = new JDBC();
        jdbc.insert(huxiuBean);
    }
}
