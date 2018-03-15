package excute;

import database.JDBC;
import htmlparse.ParseHtml;
import model.HuxiuBean;

public class Excute {
    public static void main(String[] args) {
        HuxiuBean huxiuBean = ParseHtml.parseHtml();
        JDBC jdbc = new JDBC();
        jdbc.insert(huxiuBean);
    }
}
