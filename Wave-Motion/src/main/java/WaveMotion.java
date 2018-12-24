import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class WaveMotion implements ActionListener, KeyListener{

	public JFrame frame;
	public RenderPanel renderPanel;
	public MenuPanel menuPanel;
	public Timer timer = new Timer(15, this);
	// Set up the transition speed methods with timer object rather than the i counter in RenderPanel.java.
	
	public WaveMotion() throws IOException
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		renderPanel = new RenderPanel();
		renderPanel.setFocusable(true);
		renderPanel.addKeyListener(this);
		
		frame = new JFrame("Wave Motion -- Created by Shane");
		frame.setSize(500, 420);
		frame.setResizable(false);
		frame.setLocation(dim.width/2 - frame.getWidth()/2, dim.height/2 - frame.getHeight()/2 );
		
		// place the panel on the frame / window.
		// Figure out how to change the program windows Icon.
		// Set up JLabels for Menu page
		
		// Image on menu screen with text.
		Image image = ImageIO.read(getClass().getClassLoader().getResource("images/redShield(1).png"));
        JLabel backgroundImage = new JLabel(new ImageIcon(image));

		// Menu screen Panel here:
		menuPanel = new MenuPanel();
		menuPanel.addKeyListener(this);
		
		// Add stuff here.
		menuPanel.add(backgroundImage);
		
		frame.add(menuPanel);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// Change the Icon of the program.
		// "../../../Images/rainbow.png"
		frame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/rainbow.png")).getImage());
		
		try 
		{
			Thread.sleep(5000);
		}
		catch(InterruptedException e)
		{
		    System.out.println("got interrupted!");
		}
		
		// remove menu panel
		frame.remove(menuPanel);
		
		// Add Wave Motion (renderPanel)
		frame.add(renderPanel);
		frame.setVisible(true);
		
		renderPanel.requestFocusInWindow();
		timer.start();
		
	}
	
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		
		// Close the program.
		if(keyCode == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
			System.out.println("Program closed.");
		}
		
		// If up arrow key is pushed, decrease speed of transition.
		else if(keyCode == KeyEvent.VK_UP)
		{
			if(renderPanel.i < 13)
			{
				renderPanel.i++;
				System.out.println("Colour transition speed increased: " + renderPanel.i);
			}
			
			else
				System.out.println("Maximum colour transition speed reached.");
		}
		
		// If down arrow key is pushed, decrease speed of transition.
		// Easy enough to do.
		else if(keyCode == KeyEvent.VK_DOWN)
		{
			if(renderPanel.i > 0)
			{
				renderPanel.i--;
				System.out.println("Colour transition speed decreased: " + renderPanel.i);
			}
			
			else
				System.out.println("Minimum colour transition speed reached.");
		}
		
		else
			e.consume();
		
	}
	
	// Required when implementing actionListener.
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    
	public void actionPerformed(ActionEvent arg0) 
	{
		renderPanel.repaint();
	}
	
	public void playMusic()
	{
		try
		{
			InputStream file = getClass().getResourceAsStream("music/HBFS.wav");

			// Add buffer for mark/reset support
			InputStream bufferedIn = new BufferedInputStream(file);

			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(bufferedIn));
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			clip.start();
			clip.addLineListener(new CloseClipWhenDone());
			Thread.sleep(clip.getMicrosecondLength());
		}
		
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
		
	public static void main(String [] args)
	{
		// Menu & wave Motion GUI.
		try
		{
			WaveMotion waveMotion = new WaveMotion();
			waveMotion.playMusic();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}