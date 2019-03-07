import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Renderer extends JPanel {
	
	int unitSize;
	Workshop workshop;
	ArrayList<Color> colors;
	
    public Renderer(int unitSize, Workshop ws) {
		super();
		this.unitSize = unitSize;
		this.workshop = ws;
        colors = new ArrayList<Color>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.PINK);
        colors.add(Color.MAGENTA);
        
	    JFrame frame = new JFrame("Workers");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(this);
	    frame.setSize((Workshop.width+1)*unitSize,(Workshop.height+1)*unitSize);  
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;	    
		g2d.clearRect(0, 0, Workshop.width*unitSize,Workshop.height*unitSize);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(workshop.ramp.x*unitSize, workshop.ramp.y*unitSize, workshop.ramp.width*unitSize, workshop.ramp.height*unitSize);
		for (int i=0; i< workshop.shelves.size(); i++) {
			Shelf s = workshop.shelves.get(i);
			g2d.setColor(colors.get(s.color));
			g2d.fillRect(s.x*unitSize, s.y*unitSize, s.width*unitSize, s.height*unitSize);
		}
		for (int i=0; i<workshop.workers.size(); i++) {
			Worker w = workshop.workers.get(i);
			g2d.setColor(w.color);
			
			int allstart = (int)((0.5)*unitSize);
			
			int frontsize = (int)((0.2)*unitSize);
			int midsize = (int)((0.4)*unitSize);
			int backsize = (int)((0.6)*unitSize);
			
			int frontdist = (int)((0.3)*unitSize);
			int backdist = (int)((-0.4)*unitSize);
			
			//
			int xdelta = Direction.getx(w.direction); 
			int ydelta = Direction.gety(w.direction); 
			
			fillCenteredRect(g2d, w.x*unitSize+allstart+(xdelta*backdist), w.y*unitSize+allstart+(ydelta*backdist), backsize, backsize);
			fillCenteredRect(g2d, w.x*unitSize+allstart, w.y*unitSize+allstart, midsize, midsize);
			fillCenteredRect(g2d, w.x*unitSize+allstart+(xdelta*frontdist), w.y*unitSize+allstart+(ydelta*frontdist), frontsize, frontsize);
		}
		for (int i=0; i< workshop.boxes.size(); i++) {
			Box b = workshop.boxes.get(i);
			
			int allstart = (int)((0.5)*unitSize);
			
			g2d.setColor(Color.BLACK);
			int outsize = (int)((0.8)*unitSize);
			fillCenteredRect(g2d, b.x*unitSize+allstart, b.y*unitSize+allstart, outsize, outsize);
			g2d.setColor(colors.get(b.color));
			int insize = (int)((0.5)*unitSize);
			fillCenteredRect(g2d, b.x*unitSize+allstart, b.y*unitSize+allstart, insize, insize);
			//System.out.println("x "+b.x+" y "+b.y+" outstart "+outstart+" outsize "+outsize+" instart "+instart+" insize "+insize);
		}

	}
	
	private void fillCenteredRect(Graphics g, int x, int y, int width, int height) {
		int halfwidth = (int)((0.5)*width);
		int halfheight = (int)((0.5)*height);
		g.fillRect(x-halfwidth, y-halfheight, width, height);
	}
	
    public void update(Graphics g)
    {
        paint( g ) ;
    }

 }
