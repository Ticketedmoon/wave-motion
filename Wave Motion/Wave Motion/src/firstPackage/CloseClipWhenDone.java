package firstPackage;

import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class CloseClipWhenDone implements LineListener
{
	@Override public void update(LineEvent event)
    {
        if (event.getType().equals(LineEvent.Type.STOP))
        {
            Line soundClip = event.getLine();
            soundClip.close();
             
            //Just to prove that it is called...
            System.out.println("Done playing: Harder Better Faster Stronger (8-bit)");
            System.exit(0);
        }
    }
}
