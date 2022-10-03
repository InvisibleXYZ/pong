import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public
class Main
        extends Frame
        implements Runnable, KeyListener {

    private final Ball ball;
    private final Pad pad1;
    private final Pad pad2;
    private final Set<Integer> key_pressed = new HashSet<>();

    public Main() {
        this.setSize(640, 480);
        this.setBackground(Color.BLACK);
        this.setTitle("Pong");
        this.setVisible(true);

        ball = new Ball(getWidth()/2, getHeight()/2);
        pad1 = new Pad(50, 200);
        pad2 = new Pad(560, 200);

        this.addKeyListener(this);

        new Thread(this).start();

    }

    public static void main(String[] args) {
        new Main();
    }

    public static boolean intersect(Pad pad, Ball ball) {
        return  ball.getX() < pad.getX() + pad.getWidth() && ball.getX() + ball.getWidth() > pad.getX() &&
                ball.getY() < pad.getY() + pad.getHeight() && ball.getY() + ball.getHeight() > pad.getY();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            repaint();
            if (intersect(pad1, ball) || intersect(pad2, ball)) {
                ball.beat();
            }
            if (ball.getY() < 30 || ball.getY() > 460) {
                ball.beatCorner();
            }
            if (ball.getX() > 630 || ball.getX() < 0) {
                ball.isMove = false;
                ball.startPosition(320, 240);
                pad1.startPosition(50, 200);
                pad2.startPosition(560, 200);
            }
            try {
                Thread.sleep(1000 / 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        ball.draw(g);
        pad1.draw(g);
        pad2.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {//T
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key_pressed.add(e.getKeyCode());
        for (int key : key_pressed) {
            switch (key) {
                case 87 -> pad1.moveUp();
                case 83 -> pad1.moveDown();
                case 73 -> pad2.moveUp();
                case 75 -> pad2.moveDown();
                case 0x20 -> ball.isMove = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key_pressed.remove(e.getKeyCode());
    }

}

