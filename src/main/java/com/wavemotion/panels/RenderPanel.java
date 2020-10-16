package com.wavemotion.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class RenderPanel extends JPanel {

    private int r = Math.abs(new Random().nextInt(256));
    private int g = Math.abs(new Random().nextInt(256));
    private int b = Math.abs(new Random().nextInt(256));
    public int i = 3;

    private boolean currentIsGreater = false;
    private boolean rIsGreater = false;
    private boolean bIsGreater = false;

    protected void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);
        gfx.setColor(new Color(r, g, b));
        gfx.fillRect(0, 0, 500, 420);

        shuffleColourR();
        shuffleColourB();
        shuffleColourG();
    }

    private void shuffleColourG() {
        if (currentIsGreater && g >= 10 + i) {
            g -= i;
        } else if (!currentIsGreater && g < 245 - i) {
            g += i;
        } else {
            currentIsGreater = !currentIsGreater;
        }
    }

    private void shuffleColourB() {
        if (bIsGreater && b >= 10) {
            b -= 2;
        } else if (!bIsGreater && b < 245) {
            b += 1;
        } else {
            bIsGreater = !bIsGreater;
        }
    }

    private void shuffleColourR() {
        if (rIsGreater && r >= 10) {
            r -= 2;
        } else if (!rIsGreater && r < 245) {
            r += 2;
        } else {
            rIsGreater = !rIsGreater;
        }
    }

}
