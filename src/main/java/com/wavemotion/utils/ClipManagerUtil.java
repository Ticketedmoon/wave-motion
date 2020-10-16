package com.wavemotion.utils;

import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class ClipManagerUtil implements LineListener {
    public void update(LineEvent event) {
        if (event.getType().equals(LineEvent.Type.STOP)) {
            Line soundClip = event.getLine();
            soundClip.close();
            System.exit(0);
        }
    }
}
