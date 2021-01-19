package ExpenseManagment;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.Set;
import javax.swing.*;

public class Hey_AdminPanel extends JFrame implements ItemListener {

    JPanel jp1;
    JLabel lb1;//标题；
    JLabel lb2;//图片；
    JLabel lb3;
    JLabel lb4;
    JComboBox b1;
    JComboBox b2;

    public Hey_AdminPanel() {

        jp1 = new JPanel();
        lb1 = new JLabel();
        lb2 = new JLabel();
        lb3 = new JLabel();
        lb4 = new JLabel();

        b1 = new JComboBox();
        b2 = new JComboBox();
        //  标题的布局
        lb1 = new JLabel("Expense Management");
        lb1.setForeground(Color.white);
        lb1.setFont(new Font("Arial", Font.BOLD, 30));
        lb1.setBounds(200, 0, 350, 50);
        //图片的布局
        ImageIcon aa = new ImageIcon(getClass().getResource("/photo/IMG_5157_2.jpg"));
        lb2 = new JLabel(aa);
        lb2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 255)));//图片框的边框以及颜色；
        lb2.setBounds(40, 100, 300, 300);

        //You Are文本的布局
        lb3 = new JLabel("You Are:");
        lb3.setFont(new Font("Arial", 1, 24));
        lb3.setBounds(360, 200, 150, 20);
        lb3.setForeground(Color.white);
        //选择是用户还是管理员界面的组合框布局
        b1 = new JComboBox();
        b1.addItem("Administrator");
        b1.setBounds(530, 200, 150, 20);
        b1.addItem("User");
        b1.addItemListener(this);
        b1.setActionCommand("role");//设置命令名字

        //Click Here For 的布局
        lb4 = new JLabel("Click Here For:");
        lb4.setFont(new Font("Arial", 1, 24));
        lb4.setBounds(360, 250, 180, 20);
        lb4.setForeground(Color.white);
        //选择布局
        b2 = new JComboBox();
        b2.addItem(null);
        b2.addItem("Login");
        b2.addItem("Register");
        b2.addItem("Forgot Password");
        b2.setBounds(530, 250, 150, 20);
        b2.addItemListener(this);
        b1.setActionCommand("way");//设置命令名字

        jp1.add(lb1);
        jp1.add(lb2);
        jp1.add(lb3);
        jp1.add(lb4);
        jp1.add(b1);
        jp1.add(b2);
        jp1.add(createLine());
        jp1.setOpaque(false);

        ImageIcon ico = new ImageIcon(getClass().getResource("/photo/IMG_5157_1.jpg"));
        JLabel picture = new JLabel(ico);
        picture.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
        this.getLayeredPane().add(picture , new Integer(Integer.MIN_VALUE));
        JPanel p = (JPanel) this.getContentPane();
        p.setOpaque(false);
        
//        add(new JLabel(""));
//        setSize(ico.getIconWidth(), ico.getIconHeight());
        jp1.setLayout(null);
        add(jp1);
        setSize(700, 500);
        setVisible(true);
        //限制JFrame的大小，使其不能伸缩变化
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    //画一条水平线
    static JComponent createLine() {
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setPreferredSize(new Dimension(250, 1));
        line.setBounds(0, 50, 700, 10);

        return line;
    }

    //主方法
    public static void main(String[] args) {
        Hey_AdminPanel obj = new Hey_AdminPanel();
    }

    @Override
    public void itemStateChanged(ItemEvent ex) {
        if (ex.getItem().equals(b1.getSelectedItem()) && b2.getSelectedItem() != null) {
            comboselect((String) b1.getSelectedItem(), (String) b2.getSelectedItem());
        } else if (ex.getItem().equals(b2.getSelectedItem()) && b1.getSelectedItem() != null) {
            comboselect((String) b1.getSelectedItem(), (String) b2.getSelectedItem());
        }
    }

    //选中了两个选项后，处理选择的选项需要跳到哪个界面的方法
    public void comboselect(String xlk1Choise, String xlk2Choise) {

        if ((xlk1Choise.equals("Administrator")) && (xlk2Choise.equals("Login"))) {
            hey_login ll = new hey_login();
            ll.status = "Admin";
            ll.l3.setText(ll.status + " login");
            this.dispose();
        } else if ((xlk1Choise.equals("Administrator")) && (xlk2Choise.equals("Register"))) {
            Hey_registration cc = new Hey_registration();
            cc.status = "Admin";
            cc.lb1.setText("Registration Page For " + cc.status);
            this.dispose();
        } else if ((xlk1Choise.equals("Administrator")) && (xlk2Choise.equals("Forgot Password"))) {
            Hey_ChangePassword cc = new Hey_ChangePassword();
            cc.status = "Admin";
            this.dispose();
        } else if ((xlk1Choise.equals("User")) && (xlk2Choise.equals("Login"))) {
            hey_login ll = new hey_login();
            ll.status = "User";
            ll.l3.setText(ll.status + " Login");
            this.dispose();
        } else if ((xlk1Choise.equals("User")) && (xlk2Choise.equals("Register"))) {
            Hey_registration cc = new Hey_registration();
            cc.status = "User";
            cc.lb1.setText("Registration Page For " + cc.status);
            this.dispose();
        } else if ((xlk1Choise.equals("User")) && (xlk2Choise.equals("Forgot Password"))) {
            Hey_ChangePassword cc = new Hey_ChangePassword();
            cc.status = "User";
            this.dispose();
        }
    }

}
