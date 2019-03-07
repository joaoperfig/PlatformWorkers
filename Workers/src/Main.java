import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Workshop ws = new Workshop();
		System.out.println(" "+BinaryEncoding.decode6(BinaryEncoding.encode6(34)));
	    Renderer rend = new Renderer(15, ws);
	    //System.out.println("tick");
	    try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	    for (int i=0; i<40000; i++) {
		    try {
		    	TimeUnit.MILLISECONDS.sleep(20);
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
