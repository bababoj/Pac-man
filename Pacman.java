import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

class Pacman {
    @Getter@Setter
    private int x;
    @Getter@Setter
    private int y;
    @Getter@Setter
    private int initialX;
    @Getter@Setter
    private int initialY;

    private Direction direction;
    private int angle;

    private Maze maze;
    @Getter
    private CellType[][] mazeArray;
  //  @Getter@Setter
  //  private int count;

    private final IntegerProperty count = new SimpleIntegerProperty(0);


    //private GameManager gm;
    public Pacman(CellType[][] mazeArray) {
        maze = new Maze();
        this.mazeArray = mazeArray;
        this.x = 1;  // Начальные координаты пакмана
        this.y = 1;
        this.initialX = x;
        this.initialY = y;
        this.direction = Direction.RIGHT;  // Начальное направление пакмана
        angle = 45;
      //  count = 0;
        //this.angle = 45;  // Начальный угол для анимации открытой челюсти
    }

    public void move() {
        // Логика движения пакмана в зависимости от текущего направления
        switch (direction) {
            case UP:
                if (y > 0 && mazeArray[y - 1][x] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS || mazeArray[y][x] == CellType.BOOSTER){
                        mazeArray[y][x] = CellType.EMPTY;
                      //  count++;
                        setCount(getCount() + 1);
                    }
                    y--;  // Перемещение вверх, если нет стены
                }
                break;
            case DOWN:
                if (y < maze.getROWS() - 1 && mazeArray[y + 1][x] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS || mazeArray[y][x] == CellType.BOOSTER){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                    }
                    y++;  // Перемещение вниз, если нет стены
                }
                break;
            case LEFT:
                if (x > 0 && mazeArray[y][x - 1] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS || mazeArray[y][x] == CellType.BOOSTER ){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                    }
                    x--;  // Перемещение влево, если нет стены
                    if(x == 0 && y == 14){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                        x = maze.getCOLUMNS() - 1;
                    }

                }
                break;
            case RIGHT:
                if (x < maze.getCOLUMNS() - 1 && mazeArray[y][x + 1] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS || mazeArray[y][x] == CellType.BOOSTER){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                    }
                    x++;  // Перемещение вправо, если нет стены
                    if(x == maze.getCOLUMNS() - 1 && y == 14){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                        x = 0;
                    }
                }
                break;
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void drawPacman(GraphicsContext gc) {
        switch (direction) {
            case UP:
               drawPacmanUp(gc);
                break;
            case DOWN:
                drawPacmanDown(gc);
                break;
            case LEFT:
                drawPacmanLeft(gc);
                break;
            case RIGHT:
               drawPacmanRight(gc);
                break;
        }
    }

    public void drawPacmanRight(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillArc(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(),
                maze.getCELL_SIZE(), maze.getCELL_SIZE(), 45, 270, javafx.scene.shape.ArcType.ROUND);

    }

    public void drawPacmanUp(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillArc(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(),
                maze.getCELL_SIZE(), maze.getCELL_SIZE(), 45, -270, javafx.scene.shape.ArcType.ROUND);


    }
    public void drawPacmanLeft(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillArc(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(),
                maze.getCELL_SIZE(), maze.getCELL_SIZE(), -135, 270, javafx.scene.shape.ArcType.ROUND);

    }
    public void drawPacmanDown(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillArc(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(),
                maze.getCELL_SIZE(), maze.getCELL_SIZE(), -45, 270, javafx.scene.shape.ArcType.ROUND);

    }
//    public enum Direction {
//        UP, DOWN, LEFT, RIGHT
//    }
    public IntegerProperty countProperty() {
        return count;
    }
    public int getCount() {
        return count.get();
    }

    // Метод для установки значения счетчика
    public void setCount(int value) {
        count.set(value);
    }
}
