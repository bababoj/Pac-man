import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

class Ghost {
    @Getter@Setter
    private int x;
    @Getter@Setter
    private int y;
    @Setter@Getter
    private Direction direction;
    private final Maze maze;
    @Getter
    private final CellType[][] mazeArray;
    @Setter@Getter
    private boolean vulnerable = false;
    public Ghost(CellType[][] mazeArray) {
        maze = new Maze();
        this.mazeArray = mazeArray;
        this.x = 13;
        this.y = 12;
        this.direction = Direction.UP;
        resetPosition();
    }
    public void resetPosition() {
        x = 13;
        y = 12;
    }
    public void move() {
        if (atWall()) {
            randomTurn();
        } else {
            moveLikePacman();
        }
    }

    private void moveLikePacman() {
        switch (direction) {
            case UP:
                if (y > 0 && mazeArray[y - 1][x] != CellType.WALL) {
                    y--;
                }
                break;
            case DOWN:
                if (y < maze.getROWS() - 1 && mazeArray[y + 1][x] != CellType.WALL) {
                    y++;
                }
                break;
            case LEFT:
                if (x > 0 && mazeArray[y][x - 1] != CellType.WALL) {
                    x--;
                    if(y == 13 && x == 14){
                        y = 12;
                    }
                }
                break;
            case RIGHT:
                if (x < maze.getCOLUMNS() - 1 && mazeArray[y][x + 1] != CellType.WALL) {
                    x++;
                }
                break;
        }
    }

    private boolean atWall() {
        return switch (direction) {
            case UP -> y > 0 && mazeArray[y - 1][x] == CellType.WALL;
            case DOWN -> y < maze.getROWS() - 1 && mazeArray[y + 1][x] == CellType.WALL;
            case LEFT -> x > 0 && mazeArray[y][x - 1] == CellType.WALL;
            case RIGHT -> x < maze.getCOLUMNS() - 1 && mazeArray[y][x + 1] == CellType.WALL;
        };
    }

    private void randomTurn() {
        Random random = new Random();
        int randomDirection = random.nextInt(4);

        while (randomDirection == direction.ordinal()) {
            randomDirection = random.nextInt(4);
        }
        direction = Direction.values()[randomDirection];
    }


    public void drawGhost(GraphicsContext gc, Color color){

        double centerX = x * maze.getCELL_SIZE() + 0.5 * maze.getCELL_SIZE();
        double centerY = y * maze.getCELL_SIZE() + 0.5 * maze.getCELL_SIZE();
        double headRadius = 0.4 * maze.getCELL_SIZE();
        if (vulnerable) {
            gc.setFill(Color.WHITE);
        } else {
            gc.setFill(color);
        }
        gc.fillOval(centerX - headRadius, centerY - headRadius, 2 * headRadius, 2 * headRadius);

        double hillWidth = 0.3 * maze.getCELL_SIZE();
        double hillHeight = 0.15 * maze.getCELL_SIZE();

        gc.fillRoundRect(centerX - 0.5 * hillWidth, centerY + 0.5 * headRadius, hillWidth, hillHeight, 10, 10);
        gc.fillRoundRect(centerX - 1.5 * hillWidth, centerY + 0.5 * headRadius, hillWidth, hillHeight, 10, 10);
        gc.fillRoundRect(centerX + 0.5 * hillWidth, centerY + 0.5 * headRadius, hillWidth, hillHeight, 10, 10);
    }
}
