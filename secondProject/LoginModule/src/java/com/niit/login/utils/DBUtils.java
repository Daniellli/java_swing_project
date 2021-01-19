/*Written By Nitesh*/
package com.niit.login.utils;
import java.sql.*;
import com.niit.login.beans.Users;
public class DBUtils {
    public static Connection getConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost\\XSC_SQLSERVER;databaseName=Master;user=sa;password=niit1234");
            return con;
        }
        catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static void closeConnection(Connection con, PreparedStatement stat, ResultSet rs){
        try{
            if(con!=null)
                con.close();
            if(stat!=null)
                stat.close();
            if(rs!=null)
                rs.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public static Users getUser(String loginId){
        Users usr = null;
        Connection con=null;
        PreparedStatement stat=null;
        ResultSet rs=null;
        try{
            con = getConnection();
            stat = con.prepareStatement("select * from Users where Login_ID=?");
            stat.setString(1, loginId);
            rs = stat.executeQuery();
            if(rs.next()){
                usr = new Users(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            closeConnection(con, stat, rs);
        }
        return usr;
    }
    public static boolean verifyUser(String loginId){
        Connection con=null;
        PreparedStatement stat=null;
        ResultSet rs=null;
        boolean exists = false;
        try{
            con = getConnection();
            stat = con.prepareStatement("select * from Users where Login_ID=?");
            stat.setString(1, loginId);
            rs = stat.executeQuery();
            if(rs.next()){
                exists = true;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            closeConnection(con, stat, rs);
        }
        return exists;
    }
    public static boolean registerUsers(Users usr){
        try{
            Connection con = getConnection();
            PreparedStatement stat = con.prepareStatement("insert into Users values(?, ?, ?, ?, ?, ?, ?)");
            stat.setString(1, usr.getLoginId());
            stat.setString(2, usr.getUserName());
            stat.setString(3, usr.getUserType());
            stat.setString(4, usr.getUserPhone());
            stat.setString(5, usr.getPassword());
            stat.setString(6, usr.getSecurityQuestion());
            stat.setString(7, usr.getSecurityAnswer());
            int ret = stat.executeUpdate();
            closeConnection(con, stat, null);
            if(ret>0)
                return true;
            else
                return false;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static String getSecurityQuestion(String loginId){
        Connection con=null;
        PreparedStatement stat=null;
        ResultSet rs=null;
        String securityQuestion=null;
        try{
            con = getConnection();
            stat = con.prepareStatement("select Security_Question from Users where Login_ID=?");
            stat.setString(1, loginId);
            rs = stat.executeQuery();
            if(rs.next()){
                securityQuestion = rs.getString(1);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            closeConnection(con, stat, rs);
        }
        return securityQuestion;
    }
    public static String getSecurityAnswer(String loginId, String securityQuestion){
        Connection con=null;
        PreparedStatement stat=null;
        ResultSet rs=null;
        String securityAnswer = null;
        try{
            con = getConnection();
            stat = con.prepareStatement("select Security_Answer from Users where Login_ID=? and Security_Question=?");
            stat.setString(1, loginId);
            stat.setString(2, securityQuestion);
            rs = stat.executeQuery();
            if(rs.next()){
                securityAnswer = rs.getString(1);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            closeConnection(con, stat, rs);
        }
        return securityAnswer;
    }
    public static boolean resetPassword(String loginId, String securityQuestion, String securityAnswer, String password){
        try{
            Connection con = getConnection();
            PreparedStatement stat = con.prepareStatement("update Users set Password=? where Login_ID=? and Security_Question=? "
                    + "and Security_Answer=?");
            stat.setString(1, password.trim());
            stat.setString(2, loginId.trim());
            stat.setString(3, securityQuestion.trim());
            stat.setString(4, securityAnswer.trim());
            int ret = stat.executeUpdate();
            closeConnection(con, stat, null);
            if(ret>0)
                return true;
            else
                return false;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean authenticate(String loginId, String password, String userType){
        Connection con=null;
        PreparedStatement stat=null;
        ResultSet rs=null;
        boolean exists = false;
        try{
            con = getConnection();
            stat = con.prepareStatement("select * from Users where Login_ID=? and Password=? and User_Type=?");
            stat.setString(1, loginId);
            stat.setString(2, password);
            stat.setString(3, userType);
            rs = stat.executeQuery();
            if(rs.next()){
                exists = true;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            closeConnection(con, stat, rs);
        }
        return exists;
    }
}
