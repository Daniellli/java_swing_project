package ExpenseManagment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Locale.*;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Hey_userSection extends JFrame implements ActionListener {

    JPanel p1, p2, pa, pb, pc, pd, pe, pf;
    JLabel lb1, lb2, lb3, lb4, lb5;
    JLabel lba, lbb, lbc, lbd;
    JButton bt, bt1, bt2;
    JButton bta, btb, btc;
    JTabbedPane tp;

    JTextField jt1, jt2, jt3;
    JTextField jta, jtb, jtc, jtd;
    JComboBox jc1;

    int Max, Min;
    String user_name;
    int Cat_id;

    JTable table;
    JScrollPane sPane;//可滚动的pane
    Vector<String> row;
    Vector<Vector> data;

    public static void main(String[] args) {
           Hey_userSection obj = new Hey_userSection("Daniel");
    }

    public void connectDB(String t) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement;";
            Properties prop = new Properties();
            prop.put("user", "sa");
            prop.put("password", "123456");
            Connection con = DriverManager.getConnection(URL, prop);

            PreparedStatement ps = con.prepareStatement("select * from expense where user_name = ?");
            ps.setString(1, user_name);
            ResultSet rs = ps.executeQuery();
            ResultSet rs1 = null;//存放从category表中取出的数据
            row = new Vector();

            while (rs.next()) {
                row = new Vector();
                if (t.equals("secondPane"))//if second Pane is chosen
                {
                    row.add(rs.getInt("record_id") + "");
                    //从category表中，找出expense Type这一列值
                    ps = con.prepareStatement("select cat_name from category where cat_id=?");
                    ps.setInt(1, rs.getInt("cat_id"));
                    rs1 = ps.executeQuery();
                    if (rs1.next())//找到 expense Type
                    {
                        row.add(rs1.getString("cat_name") + "");
                    }
                    row.add(rs.getDate("expense_date") + "");
                    row.add(rs.getInt("price") + "");
                    row.add(rs.getString("remarks") + "");
                } else if (t.equals("fourthPane"))//fourth pane is chosen
                {
                    row.add(rs.getDate("expense_date") + "");
                    //从category表中，找出expense Type这一列值
                    ps = con.prepareStatement("select cat_name from category where cat_id=?");
                    ps.setInt(1, rs.getInt("cat_id"));
                    rs1 = ps.executeQuery();
                    if (rs1.next())//找到 expense Type
                    {
                        row.add(rs1.getString("cat_name") + "");//expense type
                    }
                    row.add(rs.getInt("price") + "");
                }
                data.add(row);
            }
            //数据库连接不关闭，关闭的话当检索到的数据为空会报错，所以关闭
            if (rs != null) {
                rs.close();
            }
            if (rs1 != null) {
                rs1.close();
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void tableOfDatabase(String t) {
        pb.removeAll();
        pf.removeAll();
        pb.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        Vector<String> head = new Vector<>();
        if (t.equals("secondPane"))// if second pane is chosen 
        {
            head.add("ID");
            head.add("Expense Type");
            head.add("Date");
            head.add("Price");
            head.add("Remarks");
            data = new Vector<Vector>();
            connectDB("secondPane");
            table = new JTable(data, head);//数据，表头
            sPane = new JScrollPane(table);//将table添加到pane中
            pb.add(sPane);
        } else //fourth pane is chosen
        {
            head.add("Date");
            head.add("Expense Type");
            head.add("Price");
            data = new Vector<Vector>();
            connectDB("fourthPane");
            table = new JTable(data, head);//数据，表头
            sPane = new JScrollPane(table);//将table添加到pane中
            pf.add(sPane);
        }

    }

    public Hey_userSection(String name) {
//标题
        {
            user_name = name;
            p1 = new JPanel();
            p1.setLayout(null);
            p1.setBounds(0, 0, 700, 40);

            lb1 = new JLabel("User Section");
            lb1.setFont(new Font("Arial", Font.BOLD, 30));
            lb1.setBounds(0, 0, 200, 30);
            p1.add(lb1);

            bt = new JButton();

            bt = new JButton("Log out");
            bt.setFont(new Font("华文行楷", Font.BOLD, 30));
            bt.setBounds(500, 0, 200, 40);
            bt.addActionListener(this);

            p1.add(bt);

            add(p1);
        }
//琛ㄥご 锛�
        {
            p2 = new JPanel(null);
            p2.setBounds(0, 0, 700, 480);
            pa = new JPanel();
            pb = new JPanel();
            pc = new JPanel();
            pd = new JPanel();
            pe = new JPanel();
            pf = new JPanel();

            tp = new JTabbedPane(JTabbedPane.TOP);
            tp.addTab("Add Expense", pa);
            tp.addTab("View Expense", pb);
            tp.addTab("Edit Expense", pc);
            tp.addTab("report", pf);
            tp.setBounds(0, 50, 695, 415);
            tp.addChangeListener(new ChangeListener() {//pb的表格，当切换选项卡时，触发事件，生成表格
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (tp.getSelectedComponent() == pb) {
                        tableOfDatabase("secondPane");
                    } else if (tp.getSelectedComponent() == pf)//切换到第4个选项卡，report，显示所有expense的数据
                    {
                        tableOfDatabase("fourthPane");
                    }
                }
            });

            p2.add(tp);
            add(p2);
        }
//JTabbedPane的pa界面
        {
            pa.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
            pd.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 200, 255)));
            pd.setBounds(100, 50, 450, 300);

            lb2 = new JLabel("Expense Type");
            lb2.setBounds(100, 40, 100, 20);

            jc1 = new JComboBox();//
            addItemOfExpenseType();//添加expense Type到这里面,

            jc1.setBounds(220, 40, 150, 20);
            lb3 = new JLabel("ID");
            lb3.setBounds(100, 90, 100, 20);
            jt1 = new JTextField(10);
            jt1.setBounds(220, 90, 150, 20);
            lb4 = new JLabel("Price");
            lb4.setBounds(100, 140, 100, 20);
            jt2 = new JTextField(10);
            jt2.setBounds(220, 140, 150, 20);
            lb5 = new JLabel("Remarks");
            lb5.setBounds(100, 190, 100, 20);
            jt3 = new JTextField(10);
            jt3.setBounds(220, 190, 150, 20);
            bt1 = new JButton("Add");
            bt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ex) {
                    if (ex.getActionCommand().equals("Add")) {
                        //add the expense to database 
                        if (jt1.getText().length() == 0 || jt2.getText().length() == 0 || jt3.getText().length() == 0) {
                            JOptionPane.showMessageDialog(null, "data can not be empty!");
                        } else {

                            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                            String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement";
                            String userName = "sa";
                            String userPwd = "123456";

                            try {
                                Class.forName(driverName);
                                Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);

                                PreparedStatement ps = conn.prepareStatement("select * from category where cat_name=?");
                                ps.setString(1, jc1.getSelectedItem().toString());
                                ResultSet rs = ps.executeQuery();
                                if (rs.next())//取出这个expense type的约束区间
                                {
                                    Max = rs.getInt("max_limit");
                                    Min = rs.getInt("min_limit");
                                    Cat_id = rs.getInt("cat_id");
                                }

                                if (Integer.valueOf(jt2.getText()) > Max || Integer.valueOf(jt2.getText()) < Min)//超过最大限制或小于最小限制
                                {
                                    JOptionPane.showMessageDialog(null, "the price  outweigh the limit ");
                                } else//满足category表中的约束区间，添加数据到expense表中
                                {
                                    Calendar cc = Calendar.getInstance();//获取当前日期
//                                        (c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DATE))
                                    Date now = new Date(cc.get(Calendar.YEAR) - 1900, cc.get(Calendar.MONTH), cc.get(Calendar.DATE));
                                    ps = conn.prepareStatement("insert into expense (user_name,record_id,cat_id,price,remarks,expense_date) values(?,?,?,?,?,? )");
                                    ps.setString(1, user_name);
                                    ps.setInt(2, Integer.valueOf(jt1.getText()));
                                    ps.setInt(3, Cat_id);
                                    ps.setFloat(4, Float.valueOf(jt2.getText()));
                                    ps.setString(5, jt3.getText());
                                    ps.setDate(6, now);
                                    if (ps.executeUpdate() != 0)//受影响行数不等于0
                                    {
                                        JOptionPane.showMessageDialog(null, "inserted.");
                                    }
                                    jt1.setText("");
                                    jt2.setText("");
                                    jt3.setText("");

                                    rs.close();
                                    ps.close();
                                    conn.close();
                                }

                            } catch (ClassNotFoundException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                        }
                    }
                }
            });
            bt1.setBounds(60, 240, 150, 40);
            bt2 = new JButton("Back");
            bt2.addActionListener(this);
//            bt2.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent ex) {
//                        if(ex.getActionCommand().equals("Back"))
//                        {
//                               hey_login cc=new hey_login();
//                               cc.status="User";
//                               super.dispose();//这个实现不了，所以改用实现抽象类的方法
//                               
//                        }
//                    }
//            });
            bt2.setBounds(240, 240, 150, 40);
            pd.add(lb2);
            pd.add(jc1);
            pd.add(jt1);
            pd.add(lb3);
            pd.add(jt2);
            pd.add(lb4);
            pd.add(jt3);
            pd.add(lb5);
            pd.add(jt3);
            pd.add(bt1);
            pd.add(bt2);
            pd.add(createLine1());
            pd.setLayout(null);
            pa.add(pd);
            pa.setLayout(null);
        }
        //第二个选项卡pb的内容
        {
            pb.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        }
//JTabbedPane的pc选项卡
        {
            pc.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
            pe.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 200, 255)));
            pe.setBounds(100, 50, 450, 300);

            lbd = new JLabel("ID");
            lbd.setBounds(80, 30, 40, 20);
            jtd = new JTextField(10);
            jtd.setBounds(140, 30, 150, 20);
            btc = new JButton("Load");
            btc.setBounds(320, 20, 100, 40);
            lba = new JLabel("Expense Type");
            lba.setBounds(100, 80, 100, 20);
            jta = new JTextField(10);
            jta.setBounds(220, 80, 150, 20);
            lbb = new JLabel("Price");
            lbb.setBounds(100, 130, 100, 20);
            jtb = new JTextField(10);
            jtb.setBounds(220, 130, 150, 20);
            lbc = new JLabel("Remarks");
            lbc.setBounds(100, 180, 100, 20);
            jtc = new JTextField(10);
            jtc.setBounds(220, 180, 150, 20);
            bta = new JButton("Update");
            bta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("Update")) {
                        if (jta.getText().length() == 0 || jtc.getText().length() == 0 || jtb.getText().length() == 0 || jtd.getText().length() == 0) {
                            JOptionPane.showMessageDialog(null, "can not be empty.");
                        } else {
                            try {
                                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement;";
                                Properties prop = new Properties();
                                prop.put("user", "sa");
                                prop.put("password", "123456");
                                Connection con = DriverManager.getConnection(URL, prop);

//                                   int Max,Min;
//    String user_name;
//    int Cat_id;
                                PreparedStatement ps = con.prepareStatement("select * from category where cat_name = ?");
                                ps.setString(1, jta.getText());
                                ResultSet rs = ps.executeQuery();
                                if (rs.next())//这种expense type ，管理员有定义,可以更新数据
                                {
                                    Max = rs.getInt("max_limit");
                                    Min = rs.getInt("min_limit");
                                    Cat_id = rs.getInt("cat_id");//expense_typde 对应的catrgory 表中的cat_id
                                    if (Integer.valueOf(jtb.getText()) > Max || Integer.valueOf(jtb.getText()) < Min) {
                                        JOptionPane.showMessageDialog(null, "your price is not in the range allowed .");

                                    } else//价格也合法 
                                    {
                                        System.out.println(".actionPerformed()");
                                        ps = con.prepareStatement("update expense set cat_id =?,price=?,remarks=? where record_id = ?");
                                        ps.setInt(1, Cat_id);
                                        ps.setInt(2, Integer.valueOf(jtb.getText()));
                                        ps.setString(3, jtc.getText());
                                        ps.setInt(4, Integer.valueOf(jtd.getText()));
                                        if (ps.executeUpdate() != 0)//updated successfully
                                        {
                                            JOptionPane.showMessageDialog(null, "Updated");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "can not be updated .please check it over and update again");
                                        }

                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "this Expense Type is wrong");
                                }
                                rs.close();
                                ps.close();
                                con.close();
                                jta.setText("");
                                jtb.setText("");
                                jtc.setText("");
                                jtd.setText("");
                            } catch (ClassNotFoundException ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage());
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage());
                            }

                        }
                    }
                }
            });
            bta.setBounds(60, 240, 150, 40);
            btb = new JButton("Back");
            btb.addActionListener(this);
            btb.setActionCommand("Edit_Back");
            btb.setBounds(240, 240, 150, 40);
            btc.addActionListener(this);

            pe.add(lba);
            pe.add(lbb);
            pe.add(lbc);
            pe.add(lbd);
            pe.add(jta);
            pe.add(jtb);
            pe.add(jtc);
            pe.add(jtd);
            pe.add(bta);
            pe.add(btb);
            pe.add(btc);
            pe.add(createLine2());
            pe.add(createLine3());
            pc.add(pe);
            pe.setLayout(null);
            pc.setLayout(null);
        }
//JTabbedPane的pf-report选项卡
        {
            pf.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        }
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        //闄愬埗JFrame鐨勫ぇ灏忥紝浣垮叾涓嶈兘浼哥缉鍙樺寲
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    static JComponent createLine1() {
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setPreferredSize(new Dimension(250, 1));
        line.setBounds(0, 220, 450, 300);

        return line;
    }

    static JComponent createLine2() {
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setPreferredSize(new Dimension(250, 1));
        line.setBounds(0, 70, 450, 300);

        return line;
    }

    static JComponent createLine3() {
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setPreferredSize(new Dimension(250, 1));
        line.setBounds(0, 220, 450, 300);

        return line;
    }

    public void addItemOfExpenseType() {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement";
        String userName = "sa";
        String userPwd = "123456";

        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select cat_name from category");
            while (rs.next()) {
                jc1.addItem(rs.getString("cat_name"));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back") || e.getActionCommand().equals("Log out") || e.getActionCommand().equals("Edit_back"))//点击back或者log out，返回登入界面
        {
            hey_login cc = new hey_login();
            cc.status = "User";
            cc.l3.setText(cc.status + " Login");
            super.dispose();
        } else if (e.getActionCommand().equals("Load")) {
            if (jtd.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "please input id number");// didn't input any id number
            } else//ID栏不为空，可以加载
            {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement;";
                    Properties prop = new Properties();
                    prop.put("user", "sa");
                    prop.put("password", "123456");
                    Connection con = DriverManager.getConnection(URL, prop);

                    ResultSet rs1 = null;
                    PreparedStatement ps = con.prepareStatement("select * from expense where record_id=? and user_name = ?");
                    ps.setInt(1, Integer.valueOf(jtd.getText()));
                    ps.setString(2, user_name);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next())//find the date
                    {
                        //get the date of expense Type
                        ps = con.prepareStatement("select cat_name from category where cat_id = ?");
                        ps.setInt(1, rs.getInt("cat_id"));
                        rs1 = ps.executeQuery();
                        if (rs1.next()) {
                            jta.setText(rs1.getString("cat_name") + "");
                        }

                        jtb.setText(rs.getInt("price") + "");
                        jtc.setText(rs.getString("remarks") + "");
                    } else {
                        JOptionPane.showMessageDialog(null, "can not find the date identifed by the id number, " + jtd.getText());
                    }
                    rs1.close();
                    rs.close();
                    ps.close();
                    con.close();

                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }

    }

}
