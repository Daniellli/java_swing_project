package ExpenseManagment;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.geometry.HorizontalDirection;
import javafx.scene.paint.Color;
import javax.swing.*;

public class Hey_ChangePassword extends JFrame implements ActionListener {

    JPanel jp5;
    JPasswordField tf1, tf2, tf3;
    JTextField tf4;
    String status = "Admin";
    JLabel lb1;

    public Hey_ChangePassword() {

        Font font1 = new Font("Arial", Font.BOLD, 40);//字体
        Font font2 = new Font("Arial", Font.BOLD, 17);
        Font font3 = new Font("Arial", Font.BOLD, 20);

        JPanel jp1 = new JPanel(new GridBagLayout());//面板，网格布局，4行2列
        GridBagConstraints cst = new GridBagConstraints();//GridBagLayOut的约束
        cst.insets = new Insets(2, 2, 2, 2);

        JPanel jp2 = new JPanel();//打酱油的，用来填充空间的
        JPanel jp3 = new JPanel();//打酱油的，用来填充空间的
        JPanel jp4 = new JPanel();//打酱油的，用来填充空间的
        jp5 = new JPanel();
        lb1 = new JLabel("<html><u>Change Password</u></html>", (int) CENTER_ALIGNMENT);//label 居中
//        lb1.setForeground(new java.awt.Color(200,160, 0));
lb1.setForeground(java.awt.Color.white);
//        lb1.setForeground(new java.awt.Color(TOP_ALIGNMENT, TOP_ALIGNMENT, TOP_ALIGNMENT));

        JLabel lb2 = new JLabel("Old Password", (int) CENTER_ALIGNMENT);
//        lb2.setForeground(new java.awt.Color(0,150,250));
        JLabel lb3 = new JLabel("New Password", (int) CENTER_ALIGNMENT);
//        lb3.setForeground(new java.awt.Color(0,150,250));
        JLabel lb4 = new JLabel("Comfirm Password", (int) CENTER_ALIGNMENT);
//        lb4.setForeground(new java.awt.Color(0,150,250));
        JLabel lb5 = new JLabel("Name", (int) CENTER_ALIGNMENT);
//        lb5.setForeground(new java.awt.Color(0,150,250));
        lb2.setForeground(java.awt.Color.white);
        lb3.setForeground(java.awt.Color.white);
        lb4.setForeground(java.awt.Color.white);
        lb5.setForeground(java.awt.Color.white);
        

        lb1.setFont(font1);//setup font
        lb2.setFont(font2);
        lb3.setFont(font2);
        lb4.setFont(font2);
        lb5.setFont(font2);

        JButton b1 = new JButton("Update");
        JButton b2 = new JButton("Login");
//        b1.setForeground(java.awt.Color.red);
//        b2.setForeground(java.awt.Color.red);
        b1.addActionListener(this);
        b2.addActionListener(this);

        b1.setFont(font3);
        b2.setFont(font3);

        tf1 = new JPasswordField("");
        tf2 = new JPasswordField("");
        tf3 = new JPasswordField("");
        tf4 = new JTextField("");

        //所有控件添加到主面板jp1中，按顺序，总的4行2列
        cst.gridx = 0;
        cst.gridy = 0;
        jp1.add(lb5, cst);

        cst.gridx = 1;
        cst.gridy = 0;
        cst.ipadx = 130;
        jp1.add(tf4, cst);

        cst.gridx = 0;
        cst.gridy = 1;
        jp1.add(lb2, cst);

        cst.gridx = 1;
        cst.gridy = 1;
        cst.ipadx = 130;
        jp1.add(tf1, cst);

        cst.gridx = 0;
        cst.gridy = 2;
        jp1.add(lb3, cst);

        cst.gridx = 1;
        cst.gridy = 2;
        cst.ipadx = 130;
        jp1.add(tf2, cst);

        cst.gridx = 0;
        cst.gridy = 3;
        jp1.add(lb4, cst);

        cst.gridx = 1;
        cst.gridy = 3;
        cst.ipadx = 130;
        jp1.add(tf3, cst);

        jp5.add(b1);
        jp5.add(b2);
//        jp1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 50, 100)));
        JPanel test = new JPanel(null);
        jp1.setBounds(50, 50, 560, 300);
        jp1.setOpaque(false);
        test.add(jp1);
//        test.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, s2, new java.awt.Color(0, 50, 150)));
        setVisible(true);
        test.setOpaque(false);
   
        jp5.setOpaque(false);
        ImageIcon ico = new ImageIcon(getClass().getResource("/photo/hey (3).jpg"));
        JLabel picture = new JLabel(ico);
        picture.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
        this.getLayeredPane().add(picture, new Integer(Integer.MIN_VALUE));
        JPanel p = (JPanel) this.getContentPane();
        p.setOpaque(false);
        add(new JLabel(""));
        setSize(ico.getIconWidth(), ico.getIconHeight());
     setSize(700, 500);
        setLayout(new BorderLayout());//设置窗口border布局
        add(lb1, BorderLayout.NORTH);//标题
//        add(jp2, BorderLayout.WEST);//填充空间
//        add(jp3, BorderLayout.EAST);//填充空间
        add(jp5, BorderLayout.SOUTH);//填充空间
        add(test, BorderLayout.CENTER);//主面板

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭
        setLocationRelativeTo(null);
        System.out.println(this.getHeight()+" width: "+ this.getWidth());
    }



    @Override
    public void actionPerformed(ActionEvent x) {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement";
        String userName = "sa";
        String userPwd = "123456";

        try {
            if (x.getActionCommand().equals("Update")) {

                if (tf1.getPassword().length == 0 || tf2.getPassword().length == 0 || tf3.getPassword().length == 0 || tf4.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Date can not be empty!");
                } else {
                    if (!String.valueOf(tf2.getPassword()).equals(String.valueOf(tf3.getPassword())))//如果新密码和旧密码不相同，则取消操作
                    {
                        JOptionPane.showMessageDialog(null, "your new password is different with your confirm password,please check it out and input again");
                    } else//新密码和确认密码一样，准备修改密码
                    {
                        Class.forName(driverName);
                        Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);
                        PreparedStatement st = conn.prepareStatement("update users set user_pwd= ? where user_name = ? and user_pwd = ? and user_type=?");
                        st.setString(1, String.valueOf(tf2.getPassword()));
                        st.setString(2, tf4.getText());
                        st.setString(3, String.valueOf(tf1.getPassword()));
                        st.setString(4, status);
                        if (!st.execute()) {
                            JOptionPane.showMessageDialog(null, "password's be changed successfully!");
                            new Hey_AdminPanel();
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "fail to change it ,please try again");

                        }
                        st.close();
                        conn.close();
                    }

                }
            } else if (x.getActionCommand().equals("Login")) {
                hey_login ll = new hey_login();
                ll.status = status;
                ll.l3.setText(ll.status + " Login");
                this.dispose();
            }

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }

    }

}
