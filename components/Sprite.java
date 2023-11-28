package components;

import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage image = null;
    private int x = -1, y = -1;

    public Sprite(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
