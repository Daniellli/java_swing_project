package ExpenseManagment;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.spi.DirStateFactory;
import javax.swing.*;

public class Hey_registration extends JFrame implements ActionListener {

    JPanel jp1, jp2;
    JLabel lb1, lb2, lb3, lb4, lb5, lb6;
    JTextField tf1, tf3, tf4, tf5;
    JPasswordField tf2;
    JButton jb1, jb2;
    String status;
    Pattern emailPattern;
    Pattern telepPattern;
    Pattern pwdPattern;

    public Hey_registration() {
        {
            emailPattern = Pattern.compile("\\w+\\x40\\w+\\x2e\\w+");//email正则表达式
            telepPattern = Pattern.compile("[0-9]{11}");//email正则表达式
            pwdPattern = Pattern.compile("[a-z[A-Z]0-9]{10,}");//email正则表达式

            jp1 = new JPanel();
            jp1.setBounds(0, 0, 700, 40);
//            jp1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
            lb1 = new JLabel("Registration Page For User");
            lb1.setForeground(new java.awt.Color(0,150,250));
            lb1.setFont(new Font("Arial", Font.BOLD, 30));
            jp1.add(lb1);
            add(jp1);

        }
        {
            Font f=new Font("arial",Font.BOLD,14);
            jp2 = new JPanel();
            jp2.setBounds(100, 50, 500, 360);
//            jp2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
            add(jp2);
            lb2 = new JLabel("Name");
            lb2.setFont(f);
            lb2.setBounds(100, 50, 100, 30);
            tf1 = new JTextField();
            tf1.setBounds(250, 50, 150, 30);
            lb3 = new JLabel("Password");
            lb3.setBounds(100, 100, 100, 30);
             lb3.setFont(f);
            tf2 = new JPasswordField();
            tf2.setBounds(250, 100, 150, 30);
            lb4 = new JLabel("Email Id");
            lb4.setBounds(100, 150, 100, 30);
            lb4.setFont(f);
            tf3 = new JTextField();
            tf3.setBounds(250, 150, 150, 30);
            lb5 = new JLabel("Contact No");
            lb5.setBounds(100, 200, 100, 30);
            lb5.setFont(f);
            tf4 = new JTextField();
            tf4.setBounds(250, 200, 150, 30);
            lb2.setForeground(Color.white);
            lb3.setForeground(Color.white);
            lb4.setForeground(Color.white);
            lb5.setForeground(Color.white);
            
            //ID不用，users用user_name 唯一标识
//            lb6=new JLabel("ID");
//            lb6.setBounds(100, 250, 100, 30);
//            tf5=new JTextField();
//            tf5.setBounds(250, 250, 150, 30);
            jb1 = new JButton("Register");
            jb1.setBounds(80, 300, 150, 30);
            jb1.addActionListener(this);
            jb1.setActionCommand("Register");

            jb2 = new JButton("Back");
            jb2.setBounds(280, 300, 150, 30);
            jb2.addActionListener(this);
            jb2.setActionCommand("Back");
            jp2.add(lb2);
            jp2.add(tf1);
            jp2.add(lb3);
            jp2.add(tf2);
            jp2.add(lb4);
            jp2.add(tf3);
            jp2.add(lb5);
            jp2.add(tf4);
//            jp2.add(lb6);
//            jp2.add(tf5);
            jp2.add(jb1);
            jp2.add(jb2);

            jp2.setLayout(null);
        }
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        ImageIcon ico = new ImageIcon(getClass().getResource("/photo/hey (2).jpg"));
        JLabel picture = new JLabel(ico);
        picture.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
        this.getLayeredPane().add(picture, new Integer(Integer.MIN_VALUE));
        JPanel p = (JPanel) this.getContentPane();
        p.setOpaque(false);
        add(new JLabel(""));
        setSize(ico.getIconWidth(), ico.getIconHeight());

        setLayout(null);
        setSize(700, 500);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    public void actionPerformed(ActionEvent g) {
        Matcher m = pwdPattern.matcher(tf2.getText());
        Matcher te = telepPattern.matcher(tf4.getText());
        Matcher email = emailPattern.matcher(tf3.getText());
        if (g.getActionCommand().equals("Back"))//back
        {
            Hey_AdminPanel cc = new Hey_AdminPanel();
            this.dispose();
        } else if (g.getActionCommand().equals("Register"))//REgister
        {
            if (tf1.getText().length() == 0 || tf2.getText().length() == 0 || tf3.getText().length() == 0 || tf4.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "date can not be empty.");
            } else if (m.matches() == false) {
                JOptionPane.showMessageDialog(null, "your password must include letters and digits and the number must be more than 10");
            } else if (te.matches() == false) {
                JOptionPane.showMessageDialog(null, "your telephone number must be 11");
            } else if (email.matches() == false) {
                JOptionPane.showMessageDialog(null, "your Email number is wrong ,please check again.");
            } else//数据合法可以注册
            {
                String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement";
                String userName = "sa";
                String userPwd = "123456";
                PreparedStatement ps = null;
                DirStateFactory.Result rs = null;
                Statement sm = null;
                try {
                    Class.forName(driverName);
                    Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
                    String ss;
                    ss = ("insert into users (user_name,user_pwd,email_id,contact_no,user_type)values(?,?,?,?,?)");
                    ps = dbConn.prepareStatement(ss);
                    ps.setString(1, tf1.getText());
                    ps.setString(2, tf2.getText());
                    ps.setString(3, tf3.getText());
                    ps.setString(4, tf4.getText());
                    ps.setString(5, status);
                    if (!ps.execute())//
                    {
                        JOptionPane.showMessageDialog(null, "Registrated successfully");
                        new Hey_AdminPanel();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "failed");
                    }
                    ps.close();
                    dbConn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print(e.getMessage());
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }

    }
}
