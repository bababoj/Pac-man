import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Booster {
    private final int x;
    private final int y;
    private boolean active;

    private final Maze maze;

    public Booster(int x, int y) {
        this.x = x;
        this.y = y;
        this.active = true;
        maze = new Maze();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public void drawBooster(GraphicsContext gc) {
        if (active) {
                gc.setFill(Color.BLACK);
                gc.fillRect(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(), maze.getCELL_SIZE(), maze.getCELL_SIZE());

                gc.setFill(Color.WHITE);
                double circleSize = 6;
                double centerX = x * maze.getCELL_SIZE() + (maze.getCELL_SIZE() - circleSize) / 2;
                double centerY = y * maze.getCELL_SIZE() + (maze.getCELL_SIZE() - circleSize) / 2;
                gc.fillOval(centerX, centerY, circleSize, circleSize);
        }
    }
}
