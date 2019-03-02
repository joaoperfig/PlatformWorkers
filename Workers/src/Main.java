import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Workshop ws = new Workshop();

	    Renderer rend = new Renderer(15, ws);
	    //System.out.println("tick");
	    for (int i=0; i<40000; i++) {
		    try {
		    	TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    ws.update();
		    rend.revalidate();
		    rend.repaint();
		    //System.out.println("tock");
	    }
	}
}
