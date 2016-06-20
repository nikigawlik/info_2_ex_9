package exercise_9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * Code partially copied from https://docs.oracle.com/javase/tutorial/uiswing/painting/step1.html
 *
 */

class MyPanel extends JPanel {
	
	//unneccesary custom class, could have used java.awt.Point instead
	private class Point{
		public int x;
		public int y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		/*
		 * calculates the middle point between this point and the provided point.
		 */
		public Point midPoint(Point otherPoint)
		{
			return new Point((x + otherPoint.x) / 2, (y + otherPoint.y) / 2);
		}
		
		public String toString()
		{
			return "(" + x + "," + y + ")";
		}
	}

	private static final long serialVersionUID = 1L;
	private final boolean FILLED = true;

	public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Text
        g.drawString("sierpinski triangle",10,20);
        
        int w = this.getWidth()-64;
        int h = this.getHeight()-64;
        w = Math.min(w, h);

        /*
         * calculate the points of the outer triangle relative to 
         * the panel size
         * 0.86603 = sqrt(3)/2 
         * This makes the triangle equilateral (-> pythagorean theorem)
         */
        Point p1 = new Point(32+w/2, 32+w-(int)(w*0.86603));
        Point p2 = new Point(32, 32+w);
        Point p3 = new Point(32+w, 32+w);
        
        //start the recursion which draws the triangle
        drawSierpinskiTriangle(g, p1, p2, p3, 8);
        
    }
    
    /*
     * draws a sierpinski triangle through three points
     */
    private void drawSierpinskiTriangle(Graphics g, Point p1, Point p2, Point p3, int numberOfIterations)
    {
    	//draw outer triangle
    	drawTriangle(g, p1, p2, p3, FILLED);
    	//draw inner structure, start recursion
        drawInnerSierpinskiTriangle(g, p1, p2, p3, numberOfIterations);
    }
    
    /*
     * recursive method
     * draws the inner triangles of a sub-triangle in a 
     * sierpinski triangle
     */
    private void drawInnerSierpinskiTriangle(Graphics g, Point p1, Point p2, Point p3, int numberOfIterations)
    {
    	//termination
    	if(numberOfIterations < 0)
    		return;

    	//rainbow colors through the iterations
		g.setColor(Color.getHSBColor((numberOfIterations%8)/8.0F, 1.0F, 1.0F));
    	
		//calculate the inner triangle points
    	Point p12 = p1.midPoint(p2);
    	Point p23 = p2.midPoint(p3);
    	Point p31 = p3.midPoint(p1);
    	
    	//draw inner triangle
    	drawTriangle(g, p12, p23, p31, FILLED);

    	//do the same for the 3 sub-triangles (recursion)
    	drawInnerSierpinskiTriangle(g, p1, p12, p31, numberOfIterations - 1);
    	drawInnerSierpinskiTriangle(g, p12, p2, p23, numberOfIterations - 1);
    	drawInnerSierpinskiTriangle(g, p31, p23, p3, numberOfIterations - 1);
    }

    /*
     * draws a triangle
     */
	private void drawTriangle(Graphics g, Point p1, Point p2, Point p3, boolean filled) {
		
		int[] xs = {p1.x, p2.x, p3.x};
		int[] ys = {p1.y, p2.y, p3.y};
		
		if(filled)
			g.fillPolygon(xs, ys, 3);
		else
			g.drawPolygon(xs, ys, 3);
	}  
	
}
