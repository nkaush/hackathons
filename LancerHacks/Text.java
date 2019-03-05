import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Text extends JComponent
{
    String display;
    int x, y;
    public Text(String s, int x, int y){
        display = s;
        this.x = x;
        this.y = y;
    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString(display, x, y);
    }
}