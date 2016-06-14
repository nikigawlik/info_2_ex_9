package exercise_9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class MyPanel extends JPanel {
	
	private class Point{
		public int x;
		public int y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public Point midPoint(Point otherPoint)
		{
			return new Point((x + otherPoint.x) / 2, (y + otherPoint.y) / 2);
		}
		
		public String toString()
		{
			return "(" + x + "," + y + ")";
		}
	}

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
        
//        int mx = 500;
//        int my = 600;
//        int r = 500;
//        boolean flipped = false;
        
        int w = this.getWidth()-64;
        int h = this.getHeight()-64;
        w = Math.min(w, h);

        Point p1 = new Point(32+w/2, 32+w-(int)(w*0.86603));
        Point p2 = new Point(32, 32+w);
        Point p3 = new Point(32+w, 32+w);
        
        
        drawSierpinskiTriangle(g, p1, p2, p3, 8);
        
    }
    
    private void drawSierpinskiTriangle(Graphics g, Point p1, Point p2, Point p3, int numberOfIterations)
    {
    	drawTriangle(g, p1, p2, p3);
        drawInnerSierpinskiTriangle(g, p1, p2, p3, numberOfIterations);
    }
    
    private void drawInnerSierpinskiTriangle(Graphics g, Point p1, Point p2, Point p3, int numberOfIterations)
    {
    	if(numberOfIterations < 0)
    		return;
    	

		g.setColor(Color.getHSBColor((numberOfIterations%8)/8.0F, 1.0F, 1.0F));
    	
    	Point p12 = p1.midPoint(p2);
    	Point p23 = p2.midPoint(p3);
    	Point p31 = p3.midPoint(p1);
    	
    	drawTriangle(g, p12, p23, p31);

    	drawInnerSierpinskiTriangle(g, p1, p12, p31, numberOfIterations - 1);
    	drawInnerSierpinskiTriangle(g, p12, p2, p23, numberOfIterations - 1);
    	drawInnerSierpinskiTriangle(g, p31, p23, p3, numberOfIterations - 1);
    }

	private void drawTriangle(Graphics g, Point p1, Point p2, Point p3) {
//		g.drawLine(p1.x, p1.y, p2.x, p2.y);
//		g.drawLine(p2.x, p2.y, p3.x, p3.y);
//		g.drawLine(p3.x, p3.y, p1.x, p1.y);
		
		int[] xs = {p1.x, p2.x, p3.x};
		int[] ys = {p1.y, p2.y, p3.y};
		
		g.fillPolygon(xs, ys, 3);
	}  
	
}
