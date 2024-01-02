import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.Random;

class Ghost {
    @Getter
    private int x;
    @Getter
    private int y;
    private Direction direction;

    private Maze maze;
    @Getter
    private CellType[][] mazeArray;
    //  @Getter@Setter
    //  private int count;


    //private GameManager gm;
    public Ghost(CellType[][] mazeArray) {
        maze = new Maze();
        this.mazeArray = mazeArray;
        this.x = 14;  // Начальные координаты пакмана
        this.y = 14;
        this.direction = Direction.UP;  // Начальное направление
        //  count = 0;
        //this.angle = 45;  // Начальный угол для анимации открытой челюсти
    }

//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void move() {
        if (atWall()) {
            // Если призрак стоит у стены, сделаем случайный поворот
            randomTurn();
        } else {
            // Если призрак не у стены, двигаемся как пакман
            moveLikePacman();
        }
    }

    private void moveLikePacman() {
        // Логика движения призрака в зависимости от текущего направления
        switch (direction) {
            case UP:
                if (y > 0 && mazeArray[y - 1][x] != CellType.WALL) {
                    y--;  // Перемещение вверх, если нет стены
                }
                break;
            case DOWN:
                if (y < maze.getROWS() - 1 && mazeArray[y + 1][x] != CellType.WALL) {
                    y++;  // Перемещение вниз, если нет стены
                }
                break;
            case LEFT:
                if (x > 0 && mazeArray[y][x - 1] != CellType.WALL) {
                    x--;  // Перемещение влево, если нет стены
                }
                break;
            case RIGHT:
                if (x < maze.getCOLUMNS() - 1 && mazeArray[y][x + 1] != CellType.WALL) {
                    x++;  // Перемещение вправо, если нет стены
                }
                break;
        }
    }

    private boolean atWall() {
        // Проверяем, находится ли призрак у стены
        switch (direction) {
            case UP:
                return y > 0 && mazeArray[y - 1][x] == CellType.WALL;
            case DOWN:
                return y < maze.getROWS() - 1 && mazeArray[y + 1][x] == CellType.WALL;
            case LEFT:
                return x > 0 && mazeArray[y][x - 1] == CellType.WALL;
            case RIGHT:
                return x < maze.getCOLUMNS() - 1 && mazeArray[y][x + 1] == CellType.WALL;
            default:
                return false;
        }
    }

    private void randomTurn() {
        Random random = new Random();
        int randomDirection = random.nextInt(4);

        // Пока не выбрано новое направление, выбираем случайное направление, отличное от текущего
        while (randomDirection == direction.ordinal()) {
            randomDirection = random.nextInt(4);
        }

        // Устанавливаем новое направление
        direction = Direction.values()[randomDirection];
    }


    public void drawGhost(GraphicsContext gc, Color color){
//        gc.setFill(color);
//        gc.fillArc(x * maze.getCELL_SIZE(), y * maze.getCELL_SIZE(),
//                maze.getCELL_SIZE(), maze.getCELL_SIZE(), 0, 360, javafx.scene.shape.ArcType.ROUND);
        double centerX = x * maze.getCELL_SIZE() + 0.5 * maze.getCELL_SIZE();
        double centerY = y * maze.getCELL_SIZE() + 0.5 * maze.getCELL_SIZE();
        double headRadius = 0.4 * maze.getCELL_SIZE(); // Радиус головы

        // Рисуем круглую голову призрака
        gc.setFill(color);
        gc.fillOval(centerX - headRadius, centerY - headRadius, 2 * headRadius, 2 * headRadius);

        // Рисуем три холма внизу
        double hillWidth = 0.3 * maze.getCELL_SIZE(); // Ширина холма
        double hillHeight = 0.15 * maze.getCELL_SIZE(); // Высота холма

        gc.fillRoundRect(centerX - 0.5 * hillWidth, centerY + 0.5 * headRadius, hillWidth, hillHeight, 10, 10);
        gc.fillRoundRect(centerX - 1.5 * hillWidth, centerY + 0.5 * headRadius, hillWidth, hillHeight, 10, 10);
        gc.fillRoundRect(centerX + 0.5 * hillWidth, centerY + 0.5 * headRadius, hillWidth, hillHeight, 10, 10);


    }
}
