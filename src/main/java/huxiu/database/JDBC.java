package huxiu.database;

import huxiu.model.HuxiuBean;

import java.sql.*;

public class JDBC {
    //加入useunicode=true&characterEncoding=utf8 解决mysql的乱码问题
    private static final String url = "jdbc:mysql://localhost:3306/crawer?useunicode=true&characterEncoding=utf8";       //数据库地址
    private static final String username = "root";      //数据库用户名
    private static final String password = "123";        //数据库密码
    private static final String driver = "com.mysql.jdbc.Driver";       //mysql驱动
    Connection conn = null;
    public JDBC(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insert(HuxiuBean huxiuBean) {
        //???? 表示通配符
        String sql = "insert into huxiu(title,author,content,`comment`) values (?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            //parameterIndex表示第一个值
            pst.setString(1,huxiuBean.getTitle());
            pst.setString(2,huxiuBean.getAuthor());
            pst.setString(3,huxiuBean.getContent());
            pst.setString(4,huxiuBean.getComment());
            pst.executeUpdate();
            System.out.println("插入成功");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("插入失败");
        }
    }

    public static void main(String[] args) {
        JDBC jdbc = new JDBC();
        HuxiuBean huxiuBean = new HuxiuBean();
        huxiuBean.setTitle("测试");
        huxiuBean.setAuthor("测试人员");
        huxiuBean.setContent("测试测试测试正文");
        huxiuBean.setComment("测试评论");
        jdbc.insert(huxiuBean);

    }
}
