package Interface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;

public class KeyEventManager implements KeyListener {

    private static Queue<KeyEvent> _queue = new LinkedList<>();

    public KeyEventManager() {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        synchronized (_queue) {
            _queue.offer(e);
        }
    }

    public static int Start() {
        var frame = new JFrame("Key capture");
        KeyEventManager menu = new KeyEventManager();
        frame.addKeyListener(menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        while (true) {
            KeyEvent event = null;
            synchronized (_queue) {
                if (!_queue.isEmpty()) {
                    event = _queue.poll();
                }
            }
            if (event != null) {
                return event.getKeyCode();
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}