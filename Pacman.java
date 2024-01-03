import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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
    private final Maze maze;
    @Getter
    private final CellType[][] mazeArray;
    private final IntegerProperty count = new SimpleIntegerProperty(0);
    public Pacman(CellType[][] mazeArray) {
        maze = new Maze();
        this.mazeArray = mazeArray;
        this.x = 1;
        this.y = 1;
        this.initialX = x;
        this.initialY = y;
        this.direction = Direction.RIGHT;
    }

    public void move() {
        switch (direction) {
            case UP:
                if (y > 0 && mazeArray[y - 1][x] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS || mazeArray[y][x] == CellType.BOOSTER){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                    }
                    y--;
                }
                break;
            case DOWN:
                if (y < maze.getROWS() - 1 && mazeArray[y + 1][x] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS || mazeArray[y][x] == CellType.BOOSTER){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                    }
                    y++;
                }
                break;
            case LEFT:
                if (x > 0 && mazeArray[y][x - 1] != CellType.WALL) {
                    if(mazeArray[y][x] == CellType.POINTS || mazeArray[y][x] == CellType.BOOSTER ){
                        mazeArray[y][x] = CellType.EMPTY;
                        setCount(getCount() + 1);
                    }
                    x--;
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
                    x++;
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
            case UP -> drawPacmanUp(gc);
            case DOWN -> drawPacmanDown(gc);
            case LEFT -> drawPacmanLeft(gc);
            case RIGHT -> drawPacmanRight(gc);
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
    public IntegerProperty countProperty() {
        return count;
    }
    public int getCount() {
        return count.get();
    }
    public void setCount(int value) {
        count.set(value);
    }
}
