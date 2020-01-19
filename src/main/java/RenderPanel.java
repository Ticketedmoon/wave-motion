import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

	private static int cur = Math.abs(new Random().nextInt(256));
	private static int x =  Math.abs(new Random().nextInt(256));
	private static int y =  Math.abs(new Random().nextInt(256));
	public int i = 3;
	
	private boolean CurisGreater = false;
	private boolean XisGreater = false;
	private boolean YisGreater = false;
	
	protected void paintComponent(Graphics gfx) {
		super.paintComponent(gfx);
		gfx.setColor(new Color(x,cur,y));
		gfx.fillRect(0, 0, 500, 420);
		
		// X Value subset.
		if (XisGreater && x >= 10)
			x-=2;
		else if(!XisGreater && x < 245)
			x+=2;
		else
			XisGreater = !XisGreater;
		
		// Y 
		if (YisGreater && y >= 10)
			y-=2;
		else if(!YisGreater && y < 245)
			y+=1;
		else
			YisGreater = !YisGreater;
		
		// If statements for Cur counter.
		if (CurisGreater && cur >= 10+i)
			cur-=i;
		else if(!CurisGreater && cur < 245-i)
			cur+=i;
		else
			CurisGreater = !CurisGreater;
		
	}
		
}
