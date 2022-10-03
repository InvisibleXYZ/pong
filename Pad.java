import java.awt.*;

public class Pad implements Drawable{

    private final int width, height;
    private int x, y;

    public Pad(int x, int y) {
        this.x = x;
        this.y = y;

        width = 30;
        height = 100;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void moveUp() {
        this.y -= 50;
        if (y < 0) y = 0;
    }

    public void moveDown() {
        this.y += 50;
        if (y > 400) y = 400;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
