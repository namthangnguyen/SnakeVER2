import java.awt.*;
import java.util.Random;

public class Worm {
    private Point location;
    private int nutri;

    public Worm(Snake s, int n) {
        nutri = n;
        Random r = new Random();
        do {
            location = new Point(r.nextInt(Helper.GRID_X - 1), r.nextInt(Helper.GRID_Y - 1));
        } while (checkWormOnSnake(s));
    }

    public int getNutri() {
        return nutri;
    }

    public Point getLocation() {
        return location;
    }

    private boolean checkWormOnSnake(Snake s) {
        for (int i = 0; i < s.getLocation().size() - 1; i++) {
            if (location.x == s.getLocation().get(i).x && location.y == s.getLocation().get(i).y)
                return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(location.x*20 + Helper.Graphics.PADDING, location.y*20 + Helper.Graphics.PADDING, 18, 18);
    }
}
