package com.wavemotion.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManagerUtil {
    public static void playMusic(String pathToMusicFile) {
        try {
            InputStream file = SoundManagerUtil.class.getResourceAsStream(pathToMusicFile);
            InputStream bufferedIn = new BufferedInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(bufferedIn));
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
            clip.addLineListener(new ClipManagerUtil());
            Thread.sleep(clip.getMicrosecondLength());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
