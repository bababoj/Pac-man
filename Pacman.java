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
    private int x;
    private int y;
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
                    if(mazeArray[y][x] == CellType.POINTS){
                        mazeArray[y][x] = CellType.EMPTY;
                      //  count++;
                        setCount(getCount() + 1);
                    }
                    y--;  // Перемещение вверх, если нет стены
                }
                // angle = 90;
                //  angle = 90;  // Установка угла анимации для направления вверх
                break;
            case DOWN:
                if (y < maze.getROWS() - 1 && mazeArray[y + 1][x] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                    }
                    y++;  // Перемещение вниз, если нет стены
                }
                //angle = 270;
                //angle = 270;  // Установка угла анимации для направления вниз
                break;
            case LEFT:
                if (x > 0 && mazeArray[y][x - 1] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                    }
                    x--;  // Перемещение влево, если нет стены
                    if(x == 0 && y == 14){
                        x = maze.getCOLUMNS() - 1;
                    }
                    //angle = 180;
                }
                break;
            case RIGHT:
                if (x < maze.getCOLUMNS() - 1 && mazeArray[y][x + 1] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                    }
                    x++;  // Перемещение вправо, если нет стены
                    if(x == maze.getCOLUMNS() - 1 && y == 14){
                        x = 0;
                    }
                    //angle = 45;
                }
                break;
        }
        System.out.println(count);
        //updateMouthDegree();
        // angle = (direction == Direction.RIGHT) ? mouthOpenAngle : (direction == Direction.LEFT) ? 180 : angle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getAngle() {
        return angle;
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
//        Timeline timeline = new Timeline();
//        // Создаем KeyFrame для первого состояния (fillArc)
//        // Создаем KeyFrame для первого состояния (fillArc)
//       KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(2), event -> {
//            gc.setFill(Color.YELLOW);
//            gc.fillArc(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(),
//                    maze.getCELL_SIZE(), maze.getCELL_SIZE(), 45, 360 - 2 * 45, ArcType.ROUND);
//        });
//
//// Создаем KeyFrame для второго состояния (fillOval)
//        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), event -> {
//            gc.setFill(Color.YELLOW);
//            gc.fillArc(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(),
//                    maze.getCELL_SIZE(), maze.getCELL_SIZE(), 30, 360 - 2 * 30, ArcType.ROUND);
//        });
//
//        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(2), event -> {
//            gc.setFill(Color.YELLOW);
//            gc.fillOval(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(),
//                    maze.getCELL_SIZE(), maze.getCELL_SIZE());
//        });
//
//        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(2), event -> {
//            gc.setFill(Color.YELLOW);
//            gc.fillArc(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(),
//                    maze.getCELL_SIZE(), maze.getCELL_SIZE(), 30, 360 - 2 * 30, ArcType.ROUND);
//        });
//
//
//        // Добавляем KeyFrame в анимацию
//        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4);
//
//        // Устанавливаем количество повторений (в данном случае 1, чтобы анимация выполнилась один раз)
//        timeline.setCycleCount(Timeline.INDEFINITE);
//
//        // Воспроизводим анимацию
//        timeline.play();
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
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
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
