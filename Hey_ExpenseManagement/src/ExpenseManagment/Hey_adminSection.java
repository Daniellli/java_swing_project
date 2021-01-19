package ExpenseManagment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale.*;
import java.util.Properties;
import java.util.Vector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class Hey_adminSection extends JFrame implements ActionListener {

    JPanel p1, p2, pa, pb, pc, pd, pe;
    JLabel lb1, lb2, lb3, lb4, lb5;
    JLabel lba, lbb, lbc, lbd;
    JButton bt, bt1, bt2;
    JButton bta, btb, btc;
    JTabbedPane tp;
    JTable tb;
    JTextField jt1, jt2, jt3, jt4;
    JTextField jta, jtb, jtc, jtd;
    //table
    JTable table;
    JScrollPane sPane;//可滚动的pane
    Vector<String> row;
    Vector<Vector> data;
String user_name;
    public Hey_adminSection( String name) {
//标题  ；
        {
            user_name=name;
            
            p1 = new JPanel();
            p1.setLayout(null);
            p1.setBounds(0, 0, 700, 40);

            lb1 = new JLabel("Admin Section");
            lb1.setFont(new Font("华文行楷", Font.BOLD, 30));
            lb1.setBounds(0, 0, 200, 20);
            p1.add(lb1);
              
            bt = new JButton();

            bt = new JButton("Log out");
            bt.setFont(new Font("Arial", Font.BOLD, 30));
            bt.setBounds(500, 0, 200, 40);
            bt.addActionListener(this);//添加事件触发器
            p1.add(bt);
         

            add(p1);
        }
//表头 ；
        {
            p2 = new JPanel(null);
            p2.setBounds(0, 0, 700, 480);
            pa = new JPanel();
            pb = new JPanel();
            pc = new JPanel();
            pd = new JPanel();
            pe = new JPanel();

            tp = new JTabbedPane(JTabbedPane.TOP);
            tp.addTab("Add Category", pa);
            tp.addTab("View Category", pb);
            tp.addTab("Edit Category", pc);
            tp.setBounds(0, 50, 695, 415);
            tableOfDatabase();
            tp.addChangeListener(new javax.swing.event.ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                    if (tp.getSelectedComponent() == pb) {
//                        pb = new JPanel();
                        tableOfDatabase();
                    }
                }
            });

            p2.add(tp);
            add(p2);
               
//            p1.setOpaque(false);
//            p2.setOpaque(false);

        }
//JTabbedPane的pa的内表格；
        {
            pa.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
            //new java.awt.Color(255, 0, 255), it is used to set the color(red,green,blue)
            pd.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 205, 255)));
            pd.setBounds(100, 50, 450, 300);

            lb2 = new JLabel("Name");
            lb2.setBounds(100, 40, 100, 20);
            jt1 = new JTextField(10);
            jt1.setBounds(220, 40, 150, 20);
            lb3 = new JLabel("Minimum Limit");
            lb3.setBounds(100, 90, 100, 20);
            jt2 = new JTextField(10);
            jt2.setBounds(220, 90, 150, 20);
            lb4 = new JLabel("Maximum Limit");
            lb4.setBounds(100, 140, 100, 20);
            jt3 = new JTextField(10);
            jt3.setBounds(220, 140, 150, 20);
            lb5 = new JLabel("ID");
            lb5.setBounds(100, 190, 100, 20);
            jt4 = new JTextField(10);
            jt4.setBounds(220, 190, 150, 20);
            bt1 = new JButton("Add");
            bt1.setBounds(60, 240, 150, 40);
            bt1.addActionListener(this);
            bt1.setActionCommand("Add_add");

            bt2 = new JButton("Back");
            bt2.setBounds(240, 240, 150, 40);
            bt2.addActionListener(this);
            bt2.setActionCommand("Add_back");

            pd.add(lb2);
            pd.add(jt1);
            pd.add(lb3);
            pd.add(jt2);
            pd.add(lb4);
            pd.add(jt3);
            pd.add(lb5);
            pd.add(jt4);
            pd.add(bt1);
            pd.add(bt2);
            pd.add(createLine1());
            pd.setLayout(null);
            pa.add(pd);
            pa.setLayout(null);
        }
//JTabbedPane的pb的内表格
        {
//            pb.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
//            Vector<String> head = new Vector<>();
//            head.add("ID");
//            head.add("Name");
//            head.add("Min Value");
//            head.add("Max Value");
//            data=new Vector<Vector>();
//            
//            connectDB();
//           table = new JTable(data,head);//数据，表头
//            sPane = new JScrollPane(table);//将table添加到pane中
//              pb.add(sPane);

        }
//JTabbedPane的pc的内表格
        {
            pc.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
            pe.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 200, 255)));//设置边框颜色
            pe.setBounds(100, 50, 450, 300);

            lbd = new JLabel("ID");
            lbd.setBounds(80, 30, 40, 20);
            jtd = new JTextField(10);
            jtd.setBounds(140, 30, 150, 20);
            btc = new JButton("Load");
            btc.setBounds(320, 20, 100, 40);
            btc.addActionListener(this);
            btc.setActionCommand("Edit_load");

            lba = new JLabel("Name");
            lba.setBounds(100, 80, 100, 20);
            jta = new JTextField(10);
            jta.setBounds(220, 80, 150, 20);
            lbb = new JLabel("Minimum Limit");
            lbb.setBounds(100, 130, 100, 20);
            jtb = new JTextField(10);
            jtb.setBounds(220, 130, 150, 20);
            lbc = new JLabel("Maximum Limit");
            lbc.setBounds(100, 180, 100, 20);
            jtc = new JTextField(10);
            jtc.setBounds(220, 180, 150, 20);
            bta = new JButton("Update");
            bta.setBounds(60, 240, 150, 40);
            bta.addActionListener(this);
            bta.setActionCommand("Edit_update");

            btb = new JButton("Back");
            btb.setBounds(240, 240, 150, 40);
            btb.addActionListener(this);
            btb.setActionCommand("Edit_back");

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
        
//        pa.setOpaque(false);
//        pb.setOpaque(false);
//        pc.setOpaque(false);
//        tp.setOpaque(false);
        
        //set out the image of backGround
//      ImageIcon   ico = new ImageIcon(getClass().getResource("/photo/IMG_5158_1.jpg"));
//       JLabel  picture = new JLabel(ico);
//        picture.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
//        this.getLayeredPane().add(picture, new Integer(Integer.MIN_VALUE));
//        JPanel p = (JPanel) this.getContentPane();
//        p.setOpaque(false);
//        add(new JLabel(""));
//        setSize(ico.getIconWidth(), ico.getIconHeight());
        
        
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        //限制JFrame的大小，使其不能伸缩变化
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(700,500);
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


    public void actionPerformed(ActionEvent y) {
        //connet database
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement";
        String userName = "sa";
        String userPwd = "123456";
        Connection conn = null;

        try {

            if (y.getActionCommand().equals("Add_add")) {
                if (jt1.getText().length() == 0 || jt2.getText().length() == 0 || jt3.getText().length() == 0 || jt4.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "the dates must not be empty!");
                } else {
                    Class.forName(driverName);
                    conn = DriverManager.getConnection(dbURL, userName, userPwd);
                    PreparedStatement stat = conn.prepareStatement("insert into category (cat_id,cat_name,min_limit,max_limit)values(?,?,?,?)");
                    stat.setInt(1, Integer.parseInt(this.jt4.getText()));
                    stat.setString(2, jt1.getText());
                    stat.setInt(3, Integer.parseInt(jt2.getText()));
                    stat.setInt(4, Integer.parseInt(jt3.getText()));
                    if (stat.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "Category Added");
                        jt1.setText("");
                        jt2.setText("");
                        jt3.setText("");
                        jt4.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Check the errors");
                    }
                    //   stat.close();
                    //  conn.close();

                    tp.setSelectedIndex(1);
               
                }
            } else if (y.getActionCommand().equals("Add_back")) {
                new Hey_AdminPanel();
                this.dispose();
            } else if (y.getActionCommand().equals("Edit_load")) {
                //get the message from database according to the entered ID number
                Class.forName(driverName);
                conn = DriverManager.getConnection(dbURL, userName, userPwd);
                PreparedStatement ps = conn.prepareStatement("select * from category where cat_id= ?");
                ps.setString(1, jtd.getText());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    jta.setText(rs.getString("cat_name"));
                    jtb.setText(String.valueOf(rs.getInt("min_limit")));
                    jtc.setText(String.valueOf(rs.getInt("max_limit")));
                } else {
                    JOptionPane.showMessageDialog(null, "Check the errors");
                }
                rs.close();
                ps.close();
                conn.close();

            } else if (y.getActionCommand().equals("Edit_update")) {
                //get the message from the textfield for changing the category table
                Class.forName(driverName);
                conn = DriverManager.getConnection(dbURL, userName, userPwd);
                PreparedStatement ps = conn.prepareStatement("update category set cat_name=?,min_limit=?,max_limit=?  where cat_id=?");
                ps.setString(1, jta.getText());
                ps.setString(2, jtb.getText());
                ps.setString(3, jtc.getText());
                ps.setString(4, jtd.getText());

                if (ps.executeUpdate() != 0) {
                    JOptionPane.showMessageDialog(null, "updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Check the errors");
                }
                jta.setText("");
                jtb.setText("");
                jtc.setText("");
                jtd.setText("");

                ps.close();
                conn.close();

            } else if (y.getActionCommand().equals("Edit_back")) {
                new Hey_AdminPanel();
                this.dispose();
            } else if (y.getActionCommand().equals("Log out")) {
                new Hey_AdminPanel();
                this.dispose();
            }
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
    }

    public void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=ExpenseManagement;";
            Properties prop = new Properties();
            prop.put("user", "sa");
            prop.put("password", "123456");
            Connection con = DriverManager.getConnection(URL, prop);

            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from category");
            while (rs.next()) {
                row = new Vector();
                row.add(rs.getInt(1) + "");
                row.add(rs.getString(2) + "");
                row.add(rs.getInt(4) + "");
                row.add(rs.getInt(3) + "");
                data.add(row);
            }
            rs.close();
            stat.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void tableOfDatabase() {
         pb.removeAll();
         
        pb.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        Vector<String> head = new Vector<>();
        head.add("ID");
        head.add("Name");
        head.add("Min Value");
        head.add("Max Value");
        data = new Vector<Vector>();
        connectDB();
        table = new JTable(data, head);//数据，表头
        sPane = new JScrollPane(table);//将table添加到pane中
       
        pb.add(sPane);
    }
}
