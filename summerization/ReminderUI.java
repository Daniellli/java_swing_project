/*Developed by Nitesh*/
/*This is the UI of alarm to be displayed*/
package alarm;
import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;
import sun.audio.*;
public class ReminderUI extends JFrame implements java.awt.event.WindowListener{
    //ImagePanel to display the background image and to hold other components
    ImagePanel mainPanel;
    //To stop the alarm music being played
    Task task;
    JLabel imgClock, welcomeUser, welcomeMessage, welcomeDetails;
    public ReminderUI(String user, String message, String details, Task task){
        super("Medi Care Reminder");
        this.task = task;
        //To get the background image
        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/background.jpg"));
        //To create the ImagePanel using the background image
        this.mainPanel = new ImagePanel(img);
        //Box Layout has been used to arrange elements across Y AXIS
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel,BoxLayout.Y_AXIS));
        //To get the clock icon
        Icon ico = new ImageIcon(getClass().getResource("/Clock.png"));
        //To set the RED border around the Panel
        this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        //To add a line of gap in between 2 elements
        this.mainPanel.add(Box.createVerticalGlue());
        
        this.imgClock = new JLabel(ico);
        this.imgClock.setAlignmentX(CENTER_ALIGNMENT);
        this.imgClock.setAlignmentY(CENTER_ALIGNMENT);
        this.mainPanel.add(this.imgClock);
        //To add a line of gap in between 2 elements
        this.mainPanel.add(Box.createVerticalGlue());
        //To add welcome user message after the clock logo
        this.welcomeUser = new JLabel("\"Hello " + user + "\"");
        this.welcomeUser.setFont(new Font("Times New Roman",Font.BOLD,24));
        this.welcomeUser.setForeground(Color.red);
        this.welcomeUser.setAlignmentX(CENTER_ALIGNMENT);
        this.welcomeUser.setAlignmentY(CENTER_ALIGNMENT);
        this.mainPanel.add(this.welcomeUser);
        //To add a line of gap in between 2 elements
        this.mainPanel.add(Box.createVerticalGlue());
        //To add alarm message after the welcome user message
        this.welcomeMessage = new JLabel("\"Your "+ message +" is due\"");
        this.welcomeMessage.setFont(new Font("Times New Roman",Font.BOLD,18));
        this.welcomeMessage.setForeground(Color.BLACK);
        this.welcomeMessage.setAlignmentX(CENTER_ALIGNMENT);
        this.welcomeMessage.setAlignmentY(CENTER_ALIGNMENT);
        this.mainPanel.add(this.welcomeMessage);
        //To add a line of gap in between 2 elements
        this.mainPanel.add(Box.createVerticalGlue());
        //To add alarm details after the alarm message
        this.welcomeDetails = new JLabel(details);
        this.welcomeDetails.setFont(new Font("Times New Roman",Font.BOLD,18));
        this.welcomeDetails.setForeground(Color.BLUE);
        this.welcomeDetails.setAlignmentX(CENTER_ALIGNMENT);
        this.welcomeDetails.setAlignmentY(CENTER_ALIGNMENT);
        this.mainPanel.add(this.welcomeDetails);
        //To add a line of gap in between 2 elements
        this.mainPanel.add(Box.createVerticalGlue());
        //add main panel on the frame
        super.add(this.mainPanel);
        super.setIconImage(new ImageIcon(getClass().getResource("/Clock.png")).getImage());
        super.addWindowListener(this);
        super.setSize(450, 320);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }
    @Override
    public void windowOpened(WindowEvent e) {
    }
    //Stop the alarm music when the alarm UI frame is closed
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing alarm");
        AudioPlayer.player.stop(task.audios);
        this.dispose();
    }
    @Override
    public void windowClosed(WindowEvent e) {
    }
    @Override
    public void windowIconified(WindowEvent e) {
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    @Override
    public void windowActivated(WindowEvent e) {
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
