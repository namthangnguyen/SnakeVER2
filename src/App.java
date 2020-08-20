import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class App extends JFrame {
    GameScreen gameScreen;

    public App() {
        setSize(637, 460);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Java program on close;

        gameScreen = new GameScreen();
        add(gameScreen);
        this.addKeyListener(new HandlerKeyEvent());

        setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
    }

    private class HandlerKeyEvent implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameScreen.isPlaying = !gameScreen.isPlaying;
                if (gameScreen.isGameOver) {
                    gameScreen.isGameOver = false;
                    gameScreen.resetGame();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) gameScreen.getSnake().setVector(Helper.Direction.GO_UP);
            if (e.getKeyCode() == KeyEvent.VK_DOWN) gameScreen.getSnake().setVector(Helper.Direction.GO_DOWN);
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) gameScreen.getSnake().setVector(Helper.Direction.GO_RIGHT);
            if (e.getKeyCode() == KeyEvent.VK_LEFT) gameScreen.getSnake().setVector(Helper.Direction.GO_LEFT);
        }

        @Override
        public void keyReleased(KeyEvent e) { }
    }
}
