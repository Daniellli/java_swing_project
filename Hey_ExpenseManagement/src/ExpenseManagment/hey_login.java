package ExpenseManagment;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.text.html.CSS;

public class hey_login extends JFrame implements ActionListener {

    JTabbedPane tpane;
    JPanel panel1, panel2;
    Container cntr = getContentPane();

    JPasswordField t2;
    JTextField t1;
    JLabel l1, l2, l3;
    String status;

    public hey_login() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 13);

        //        JPanel panel2=new JPanel(new BoxLayout(cntr, ALLBITS))
        l1 = new JLabel("User");
        l2 = new JLabel("Password");
        l3 = new JLabel("Login");
        t1 = new JTextField("", 30);
        t2 = new JPasswordField(10);
        JButton b1 = new JButton("Login");
        JButton b2 = new JButton("Back");

        setTitle("Login");
        // center screen
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width
                - getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height
                - getHeight()) / 2);

        panel1 = new JPanel();//主面板
        GridBagConstraints constraints = new GridBagConstraints();//布局约束或布局的光标   
        GridBagLayout layout = new GridBagLayout();//布局

        l3.setFont(new Font("Arial", Font.BOLD, 50));
//        l3.setForeground(new java.awt.Color(250, 150, 0));
        l3.setForeground(Color.white);
        l3.setBounds(135, 20, 200, 30);

//        JPanel test = new JPanel(null);
//        test.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 150, 250)));
//        panel1.setBounds(250, 130, 600, 500);
//        test.add(panel1);
        panel2 = new JPanel();
        panel2.add(l3);
        cntr.setLayout(null);

        panel2.setBounds(150, 10, 400, 70);
        panel1.setBounds(235, 180, 200, 200);
        cntr.add(panel2);
        // cntr.add(test);//top container ==JFrame
        cntr.add(panel1);

        ImageIcon ico = new ImageIcon(getClass().getResource("/photo/IMG_5088.jpg"));
        JLabel picture = new JLabel(ico);
        picture.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
        this.getLayeredPane().add(picture, new Integer(Integer.MIN_VALUE));
        JPanel p = (JPanel) this.getContentPane();
        p.setOpaque(false);
        add(new JLabel(""));
//        setSize(ico.getIconWidth(), ico.getIconHeight());
        setOpacity(1);
        panel1.setOpaque(false);//设置为不透明为false
        panel2.setOpaque(false);
        
        constraints.insets = new Insets(2, 2, 2, 2);
        panel1.setLayout(layout);

        l1.setFont(labelFont);
//         l1.setForeground(new java.awt.Color(0, 110, 250));
        l1.setForeground(Color.white);
        constraints.ipadx = 2;//填充X坐标
        constraints.ipady = 2;//填充Y坐标
        constraints.gridx = 0;//设置X坐标
        constraints.gridy = 0;//设置Y坐标
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(l1, constraints);
        panel1.add(l1);

        t1.setFont(labelFont);
        constraints.ipadx = 2;
        constraints.ipady = 2;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(t1, constraints);
        panel1.add(t1);

        l2.setFont(labelFont);
//        l2.setForeground(new java.awt.Color(0, 110, 250));
       l2.setForeground(Color.white);
        constraints.ipadx = 2;
        constraints.ipady = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(l2, constraints);
        panel1.add(l2);

        // setup Password input field in display area
        t2.setEchoChar('*');
        constraints.ipadx = 2;
        constraints.ipady = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;
        layout.setConstraints(t2, constraints);
        panel1.add(t2);

        // setup Login button in display area
        b1.setFont(buttonFont);
        constraints.gridx = 0;
        constraints.gridy = 2;
        layout.setConstraints(b1, constraints);
        panel1.add(b1);
        b1.addActionListener(this);

        //setup Back Button in display area
        b2.setFont(buttonFont);
        constraints.gridx = 1;
        constraints.gridy = 2;
        layout.setConstraints(b2, constraints);
        panel1.add(b2);
        panel1.setSize(200, 150);
        b2.addActionListener(this);
//        panel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 200, 250)));

//
        setSize(700, 500);

        setDefaultCloseOperation(EXIT_ON_CLOSE);//设置窗口关闭按钮可用
        setVisible(true);
        setLocationRelativeTo(null);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String t = e.getActionCommand();
        if (t.equals("Back")) {
            new Hey_AdminPanel();
            this.dispose();
        } else//连接数据库
        {
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement";
            String userName = "sa";
            String userPwd = "123456";
            try {
                Class.forName(driverName);
                Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);

                PreparedStatement ps = conn.prepareStatement("select * from users where user_name = ? and user_pwd = ? and user_type=?");
                ps.setString(1, t1.getText());
                ps.setString(2, t2.getText());
                ps.setString(3, status);
                ResultSet rs = ps.executeQuery();

                if (rs.next())//如果没有检索到元组表示账号或用户名错误，反之登入成功
                {
                    if (status.equals("Admin"))//检查身份是Admin还是user,分别进入admin_section and users_section
                    {
                        Hey_adminSection cc = new Hey_adminSection(t1.getText());

                        this.dispose();
                    } else//enter user section
                    {
                        Hey_userSection cc = new Hey_userSection(t1.getText());
//                                 cc.user_name=;
                        this.dispose();
                    }

                } else//登入失败
                {
                    JOptionPane.showMessageDialog(null, "Your password or account number is wrong.");
                    t1.setText("");
                    t2.setText("");
                }
                ps.close();
                conn.close();

            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }
}
