import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Point> location;
    private int vector;

    private Point snakeHead;
    boolean updateAfterChangeVt = false; // tránh bug điều hướng quá nhanh

    public Snake() {
        location = new ArrayList<>();
        location.add(new Point(3, 1));
        location.add(new Point(2, 1));
        location.add(new Point(1, 1));
        vector = Helper.Direction.GO_RIGHT;

        snakeHead = location.get(0);
    }

    public void setVector(int v) {
        if (vector != -v && updateAfterChangeVt) {
            vector = v;
            updateAfterChangeVt = false;
        }
    }

    public ArrayList<Point> getLocation() {
        return location;
    }

    public boolean eatWorm(Worm w) {
        if (snakeHead.x == w.getLocation().x && snakeHead.y == w.getLocation().y) {
            location.add(new Point(location.get(location.size() - 1).x, location.get(location.size() - 1).y));
            return true;
        }
        return false;
    }

    public boolean hitItself() {
        for (int i = 2; i < location.size(); i++) {
            if (snakeHead.x == location.get(i).x && snakeHead.y == location.get(i).y) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        for (int i = location.size() - 1; i > 0; i--) {
            location.get(i).x = location.get(i - 1).x;
            location.get(i).y = location.get(i - 1).y;
        }

        if (vector == Helper.Direction.GO_UP) snakeHead.y--;
        if (vector == Helper.Direction.GO_DOWN) snakeHead.y++;
        if (vector == Helper.Direction.GO_RIGHT) snakeHead.x++;
        if (vector == Helper.Direction.GO_LEFT) snakeHead.x--;

        // rắn đi xuyên màn hình
        if (snakeHead.x < 0) snakeHead.x = Helper.GRID_X - 1;
        if (snakeHead.x > Helper.GRID_X - 1) snakeHead.x = 0;
        if (snakeHead.y < 0) snakeHead.y = Helper.GRID_Y - 1;
        if (snakeHead.y > Helper.GRID_Y - 1) snakeHead.y = 0;

        updateAfterChangeVt = true;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        for (int i = 1; i < location.size(); i++)
            g.fillRect(location.get(i).x*20 + Helper.Graphics.PADDING, location.get(i).y*20 + Helper.Graphics.PADDING, 18, 18);
        g.setColor(Color.cyan);
        g.fillRect(snakeHead.x*20 + Helper.Graphics.PADDING, snakeHead.y*20 + Helper.Graphics.PADDING, 18, 18);
    }

}
