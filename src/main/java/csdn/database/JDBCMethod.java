package csdn.database;

import csdn.model.CSDNBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCMethod {
    private static Connection conn = null;
    public JDBCMethod(){
        conn = JDBCDatabase.getConnection();
    }

//    private static final String sql = "insert into csdn(title,author,content,`date`,`type`,readNum,isDelete) values(?,?,?,?,?,?,?)";

    public void insert(CSDNBean csdnBean) throws SQLException {
        if(csdnBean != null) {
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                String sql = "insert into csdn(title,author,content,date,type,readNum,isDelete) values (?,?,?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                //parameterIndex表示第一个值
                pst.setString(1, csdnBean.getTitle());
                pst.setString(2, csdnBean.getAuthor());
                pst.setString(3, csdnBean.getContent());
                pst.setString(4, csdnBean.getDate());
                pst.setString(5, csdnBean.getType());
                pst.setString(6, csdnBean.getReadNum());
                pst.setInt(7, csdnBean.getIsDelete());
                pst.executeUpdate();
                System.out.println("插入成功");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("插入失败");
            }
        }else
        {
            PreparedStatement pst = null;
            ResultSet rs = null;
            JDBCDatabase.closeConnection(rs,pst,conn);
        }
    }

    public static void main(String[] args) throws SQLException {
        CSDNBean csdnBean = new CSDNBean();
        JDBCMethod jdbcMethod = new JDBCMethod();
        csdnBean.setTitle("一位高级Java程序员教你如何轻松拿到阿里、京东、腾讯的offer");
        csdnBean.setAuthor("uxiad7442kmy1x86dtm3");
        csdnBean.setContent("11");
        csdnBean.setDate("cs");
        csdnBean.setType("cs");
        csdnBean.setReadNum("232");
        csdnBean.setIsDelete(0);
        jdbcMethod.insert(csdnBean);
    }
}
