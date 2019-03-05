import javax.swing.JComponent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.ArrayList;
public class Circle extends JComponent{
    Ellipse2D e;
    Arc2D a;
    int x, y, r;
    String team, displayPercent;
    Color color; 
    double angle, percent, per;
    public Circle(int xPos, int yPos, int radius, double deg, Color c, String t){
        x = xPos;
        y = 10+yPos;
        r = radius;
        team = t;
        color = c;
        angle = deg;
        e = new Ellipse2D.Double(x, y, r, r);
        a = new Arc2D.Double(x, y, r, r, 180, angle, Arc2D.PIE);
        setDisplayPercent();
        setDisplayVal();
        setColor();
    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.draw(e);
        g2.setColor(Color.WHITE);
        g2.fill(e);
        g2.setColor(color);
        g2.fill(a);
        g2.setColor(Color.BLACK);
        g2.drawString(team, x, y-5);
        g2.drawString(displayPercent, x+15, y+25);
        if(color.equals(Color.BLACK)||color.equals(Color.BLUE)){
            g2.setColor(Color.WHITE);
        }
        g2.drawString(per+" PER", x+10, y+60);
    }
    private void setColor(){
        if(percent>=51){color = new Color(34, 160, 20); }
        else if(percent<=49){color = Color.RED;}
        else{color = Color.BLACK;}
    }
    private void setDisplayPercent(){
        percent = angle/360;
        percent = (double)Math.round(percent*10000)/100;
        displayPercent = percent + "%";
        if(displayPercent.length()!=6){
            displayPercent = percent + "0%";
        }
    }
    private void setDisplayVal(){
        per = (angle/260)*28.6;
        per = (double)Math.round(per*10)/10;
    }
}
