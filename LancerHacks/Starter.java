import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Starter{
    private static String fileName = "nba_team_per.txt";
    private static double max = 28.6;
    public static void readFile() throws IOException{
        JFrame f = new JFrame("PER Graphic and Betting Analysis Percentages");
        int frameWidth = 1050;
        int frameHeight = 800;
        f.setSize(frameWidth, frameHeight);
        int[] xCoords = new int[]{10, 190, 370, 550, 730, 910,
                                  10, 190, 370, 550, 730, 910,
                                  10, 190, 370, 550, 730, 910,
                                  10, 190, 370, 550, 730, 910,
                                  10, 190, 370, 550, 730, 910};
        int[] yCoords = new int[]{10, 10, 10, 10, 10, 10,
                                  130, 130, 130, 130, 130, 130,
                                  250, 250, 250, 250, 250, 250,
                                  370, 370, 370, 370, 370, 370,
                                  490, 490, 490, 490, 490, 490};
        Color[] all_colors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.ORANGE,
                                         Color.YELLOW, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, 
                                         Color.GREEN, Color.YELLOW, Color.RED, Color.BLACK, Color.RED, 
                                         Color.BLUE, Color.RED, Color.MAGENTA, Color.YELLOW, Color.ORANGE, 
                                         Color.MAGENTA, Color.RED, Color.BLUE, Color.RED, Color.RED,
                                         Color.RED, Color.BLACK, Color.BLUE, Color.YELLOW, Color.BLUE};
        ArrayList<Circle> circles = new ArrayList<Circle>();
        try{
            Scanner in = new Scanner(new File(fileName));
            String line = "";
            int counter = 0;
            while(in.hasNextLine()){
                line = in.nextLine();
                line = line.trim();
                String[] splitLine = line.split(":");
                String teamName = splitLine[0];
                double per = Double.parseDouble(splitLine[1]);
                double angle = (per/max)*360.0;
                Circle c = new Circle(xCoords[counter], yCoords[counter], 75, angle, all_colors[counter], teamName);
                f.add(c);
                f.setVisible(true);
                counter++;
            }
        }
        catch(IOException e){
            System.out.println("There was a problem reading in the file: " + e);
        }
        f.add(new Text("*All values are compared to LeBron James' effectiveness rating of " + max + 
                       " (the highest in the league) and displayed in the charts as a percentage of the value.", 10, 770));
        f.setVisible(true);
        f.add(new Text("To read this graphic, compare the percentage displayed in both the number value and " + 
                       "corresponding pi-chart to the basic median odds of successful betting (50%).", 10, 670));                      
        f.setVisible(true);
        f.add(new Text("Values greater than 50% indicate higher odds of successful bets while lower values indicate " + 
                       "lower odds of placing successful bets.", 10, 690));                      
        f.setVisible(true);
    }
}
