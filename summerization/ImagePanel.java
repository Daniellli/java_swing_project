/*Developed by Nitesh*/
/*This is a custom JPanel class ImagePanel to display background image*/
package alarm;
import javax.swing.*;
import java.awt.*;
public class ImagePanel extends JPanel{
    private Image img;
    public ImagePanel(Image img) {
        this.img = img;
        //to get the height and width of image
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        //set the size of Panel according to the size of image
        super.setPreferredSize(size);
        super.setMinimumSize(size);
        super.setMaximumSize(size);
        super.setSize(size);
        super.setLayout(null);
        //super.setLayout(layout);
    }
    //To draw image on the Panel
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
