package com.wavemotion.launcher;

import com.wavemotion.panels.MenuPanel;
import com.wavemotion.panels.RenderPanel;
import com.wavemotion.utils.SoundManagerUtil;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class WaveMotion implements ActionListener, KeyListener {

    private static final String LOADING_IMAGE_PATH = "images/red-shield.png";
    private static final String APPLICATION_ICON_PATH = "images/rainbow.png";
    private static final String APPLICATION_TITLE = "Wave Motion -- Created by Shane";
    private static final String PATH_TO_MUSIC_FILE = "/music/HBFS.wav";

    private static final Logger log = Logger.getLogger(WaveMotion.class.getName());

    public JFrame frame;
    public RenderPanel renderPanel;
    public MenuPanel menuPanel;
    public final Timer timer = new Timer(15, this);

    public void launch() throws IOException {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        renderPanel = new RenderPanel();
        renderPanel.setFocusable(true);
        renderPanel.addKeyListener(this);

        frame = new JFrame(APPLICATION_TITLE);
        frame.setSize(500, 420);
        frame.setResizable(false);
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);

        showLoadingScreen();

        frame.remove(menuPanel);
        frame.add(renderPanel);
        frame.setVisible(true);
        renderPanel.requestFocusInWindow();
        timer.start();
    }

    private void showLoadingScreen() throws IOException {
        URL loadingImageUrl = getClass().getClassLoader().getResource(LOADING_IMAGE_PATH);
        URL applicationIconUrl = getClass().getClassLoader().getResource(APPLICATION_ICON_PATH);
        if (loadingImageUrl != null && applicationIconUrl != null) {
            Image image = ImageIO.read(loadingImageUrl);
            JLabel backgroundImage = new JLabel(new ImageIcon(image));

            menuPanel = new MenuPanel();
            menuPanel.addKeyListener(this);
            menuPanel.add(backgroundImage);

            frame.add(menuPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setIconImage(new ImageIcon(applicationIconUrl).getImage());
            sleepForSeconds(5);
        } else {
            throw new IllegalStateException("Loading Image could not be found");
        }
    }

    private void sleepForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log.info("got interrupted!");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
            log.info("Program closed.");
        } else if (keyCode == KeyEvent.VK_UP) {
            if (renderPanel.i < 13) {
                renderPanel.i++;
                log.info("Colour transition speed increased: " + renderPanel.i);
            } else {
                log.info("Maximum colour transition speed reached.");
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (renderPanel.i > 0) {
                renderPanel.i--;
                log.info("Colour transition speed decreased: " + renderPanel.i);
            } else
                log.info("Minimum colour transition speed reached.");
        } else {
            e.consume();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void actionPerformed(ActionEvent arg0) {
        renderPanel.repaint();
    }

    public static void main(String[] args) {
        try {
            WaveMotion application = new WaveMotion();
            application.launch();
            SoundManagerUtil.playMusic(PATH_TO_MUSIC_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
