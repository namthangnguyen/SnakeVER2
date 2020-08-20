import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements Runnable {
    private Snake snake;
    private Worm worm;
    private Thread thread;
    private int point;

    static boolean isPlaying = false;
    static boolean isGameOver = false;

    public GameScreen() {
        snake = new Snake();
        System.out.println(snake);
        worm = new Worm(snake, 100); // Pass the snake to avoid it
        point = 0;

        thread = new Thread(this);
        thread.start();
    }

    public Snake getSnake() {
        return snake;
    }

    public void resetGame() {
        snake = new Snake();
        worm = new Worm(snake, 100);
        point = 0;
    }

    public void paintBackground(Graphics g) {
        int width = Helper.Graphics.WIDTH;
        int height = Helper.Graphics.HEIGHT;
        int padding = Helper.Graphics.PADDING;
        g.setColor(Color.black);
        g.fillRect(0, 0, width + padding*2, height + padding*2);
        g.setColor(Color.orange);
        for (int i = 0; i < 4; i++) {
            g.drawRect(i, i, width + padding*2 - 2*i, height + padding*2 - 2*i);
        }
    }

    @Override
    public void paint(Graphics g) {
        paintBackground(g);
        snake.draw(g);
        worm.draw(g);

        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(16.0f));
        g.drawString("Point: " + point, 10, 25);
        if (!isPlaying) {
            g.setFont(g.getFont().deriveFont(18.0f));
            g.drawString("PRESS SPACE TO PLAY GAME!", 165, 200);
        }
        if (isGameOver) {
            g.setFont(g.getFont().deriveFont(36.0f));
            g.drawString("GAME OVER!", 190, 120);
        }
    }

    @Override
    public void run() {
        long time = 0;
        while (true) {
            if (isPlaying) {
                if (System.currentTimeMillis() - time > 100) {
                    snake.run();
                    if (snake.eatWorm(worm)) {
                        point += worm.getNutri();
                        worm = new Worm(snake, 100);
                    }
                    if (snake.hitItself()) {
                        isGameOver = true;
                        isPlaying = false;
                    }
                    time = System.currentTimeMillis();
                }
            }
            repaint(); // recall paint()
            try {
                thread.sleep(30);
            } catch (InterruptedException e) {}
        }
    }
}
