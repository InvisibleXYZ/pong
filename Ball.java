import java.awt.*;

public class Ball extends Thread implements Drawable {

    private final int width, height;
    public boolean isMove;
    private int x, y;
    private int vx, vy;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        width = 10;
        height = 10;

        vx = ((int) (Math.random() * 2) == 0) ? -10 : 10;
        vy = ((int) (Math.random() * 2) == 0) ? -4 : 4;

        start();
    }

    private void move() {
        x += vx;
        y += vy;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (isMove) {
                move();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void beat() {
        vx = -vx;
    }

    public void beatCorner() {
        vy = -vy;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void startPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
