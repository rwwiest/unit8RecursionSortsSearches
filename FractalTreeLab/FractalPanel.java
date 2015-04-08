//********************************************************************

import java.awt.*;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class FractalPanel extends JPanel
{
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 1000;
    //private final int THE_ANGLE = 2;
    private final double SQ = Math.sqrt(3.0) / 6;

    //     private final int TOPX = 200, TOPY = 20;
    //     private final int LEFTX = 60, LEFTY = 300;
    //     private final int RIGHTX = 340, RIGHTY = 300;

    private int current; //current order
    //-----------------------------------------------------------------
    //  Sets the initial fractal order to the value specified.
    //-----------------------------------------------------------------
    public FractalPanel (int currentOrder)
    {
        current = currentOrder;
        setBackground (Color.black);
        setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

    //-----------------------------------------------------------------
    //  Draws the fractal recursively. Base case is an order of 1 for
    //  which a simple straight line is drawn. Otherwise three
    //  intermediate points are computed, and each line segment is
    //  drawn as a fractal
    //-----------------------------------------------------------------
    public void drawFractal (int order, int x1, int y1,int x5,int y5,double angle,
    Graphics page, String dir)
    {
        int deltaX, deltaY, x2, y2, x3, y3, x4, y4;
        //MY CODE STARTS HERE
        // if order is one then draw the first line
        // otherwise create two more lines one third of the way the initial line and
        // draw two lines that are 80% the length that end one x coordinate to the right and left
        // then do the same thing
        if (order == 1)
            page.drawLine (x1, y1, x5, y5);
        else
        {
            page.drawLine (x1,y1,x5,y5);
            deltaX = x5 - x1;  // distance between end points
            deltaY = y5 - y1;
            double distance = Math.sqrt((deltaX*deltaX)+(deltaY *deltaY));//DISTANCE FORMULA

            double totalAngle = (angle*.2) +angle;//THE_ANGLE;
         
            double cosValue = Math.cos(Math.toRadians(totalAngle));
            double sinValue = Math.sin(Math.toRadians(totalAngle));
            int xNewRight=0;
            int yNewRight=0;
            int xNewLeft=0;
            int yNewLeft=0;
            if (dir == "up")
            {
                xNewRight = x5 + (int)(.95*distance*sinValue); 
                yNewRight = y5 - (int)(distance *.95 * cosValue);

                xNewLeft = x5 - (int)(.95*distance*sinValue); 
                yNewLeft = y5 - (int)(distance *.95 * cosValue);
            }
            if (dir == "right")
            {
                xNewRight = x5 + (int)(.95*distance*sinValue); 
                yNewRight = y5 + (int)(distance *.95 * cosValue);

                xNewLeft = x5 + (int)(.95*distance*sinValue); 
                yNewLeft = y5 - (int)(distance *.95 * cosValue);
            }
            if (dir == "left")
            {
                xNewRight = x5 - (int)(.95*distance*sinValue); 
                yNewRight = y5 + (int)(distance *.95 * cosValue);

                xNewLeft = x5 - (int)(.95*distance*sinValue); 
                yNewLeft = y5 - (int)(distance *.95 * cosValue);
            }
            if (dir == "down")
            {
                xNewRight = x5 + (int)(.95*distance*sinValue); 
                yNewRight = y5 + (int)(distance *.95 * cosValue);

                xNewLeft = x5 - (int)(.95*distance*sinValue); 
                yNewLeft = y5 + (int)(distance *.95 * cosValue);
            }
            page.drawLine (x5, y5, xNewRight, yNewRight);
            page.drawLine (x5, y5 , xNewLeft, yNewLeft);
            drawFractal (order-1, x5, y5, xNewRight, yNewRight,totalAngle, page,dir);
            drawFractal (order-1, x5, y5 , xNewLeft, yNewLeft,totalAngle, page,dir);
        }
    }

    //-----------------------------------------------------------------
    //  Performs the initial calls to the drawFractal method.
    //-----------------------------------------------------------------
    public void paintComponent (Graphics page)
    {
        super.paintComponent (page);
        page.setColor (Color.green);
        drawFractal (current,500,1000,500,900,5,page,"up");
        drawFractal (current,500,500,600,500,5,page,"right");
        drawFractal (current,500,500,400,500,5,page,"left");
        drawFractal (current,500,0,500,100,5,page,"down");
        drawFractal (current,0,500,100,500,5,page,"right");
        drawFractal (current,1000,500,900,500,5,page,"left");
    }

    //-----------------------------------------------------------------
    //  Sets the fractal order to the value specified.
    //-----------------------------------------------------------------
    public void setOrder (int order)
    {
        current = order;
    }

    //-----------------------------------------------------------------
    //  Returns the current order.
    //-----------------------------------------------------------------
    public int getOrder ()
    {
        return current;
    }
}
