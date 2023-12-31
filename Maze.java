import lombok.Getter;

// Класс PixelMaze представляет лабиринт в виде двумерного массива с клетками размером 16x16 пикселей
public class Maze {
    @Getter
    private int CELL_SIZE;
    @Getter
    private int ROWS;
    @Getter
    private int COLUMNS;
    @Getter
    private CellType[][] maze;

    public Maze() {
        CELL_SIZE = 16;
        ROWS = 31;
        COLUMNS = 28;
        maze = new CellType[ROWS][COLUMNS];
        initializeMaze();


    }

    private void initializeMaze() {
        // Заполняем лабиринт случайными значениями из перечисления CellType
        classicMaze();
        // Отражаем левую половину в правую
        mirrorLeftToRight();
    }

    private CellType getRandomCellType() {
        // Генерация случайного типа ячейки
        double randomValue = Math.random();
        if (randomValue < 0.3) {
            return CellType.WALL;
        } else {
            return CellType.EMPTY;
        }
    }

    private void mirrorLeftToRight() {
        // Зеркальное отражение и вставка в правую половину
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS / 2; j++) {
                maze[i][COLUMNS / 2 + j] = maze[i][(COLUMNS / 2) - 1 - j];
            }
        }
    }

    private void classicMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < COLUMNS / 2; j++) {
                maze[i][j] = CellType.EMPTY;
                switch (i) {
                    case (0):
                    case (30):
                        maze[i][j] = CellType.WALL;
                        break;
                    case (1):
                        //  maze[i][j] = CellType.EMPTY;
                        maze[i][0] = CellType.WALL;
                        maze[i][COLUMNS / 2 - 1] = CellType.WALL;
                        break;
                    case (2):
                    case (3):
                    case (4):
                    case (21):
                    case (22):
                        maze[i][0] = CellType.WALL;
                        maze[i][2] = CellType.WALL;
                        maze[i][3] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][9] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
                    case (5):
                        //                    default:
                    case (29):
                        maze[i][0] = CellType.WALL;
                        break;
                    case (6):
                    case (7):
                        maze[i][0] = CellType.WALL;
                        maze[i][2] = CellType.WALL;
                        maze[i][3] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][12] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
                    case (8):
                    case (26):
                        maze[i][0] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
                    case (9):
                        maze[i][0] = CellType.WALL;
                        maze[i][1] = CellType.WALL;
                        maze[i][2] = CellType.WALL;
                        maze[i][3] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][9] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
                    case (10):
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][9] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
                    case (11):
                    case (17):
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        break;
                    case (12):
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][12] = CellType.WALL;
                        break;
                    case (13):
                    case (15):
                        maze[i][0] = CellType.WALL;
                        maze[i][1] = CellType.WALL;
                        maze[i][2] = CellType.WALL;
                        maze[i][3] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        break;
                    case (14):
                        maze[i][10] = CellType.WALL;
                        break;
                    case (16):
                    case (18):
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][12] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
                    case (19):
                        maze[i][0] = CellType.WALL;
                        maze[i][1] = CellType.WALL;
                        maze[i][2] = CellType.WALL;
                        maze[i][3] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][12] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
                    case (20):
                        maze[i][0] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
                    case (23):
                        maze[i][0] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                        break;
                    case (24):
                    case (25):
                        maze[i][0] = CellType.WALL;
                        maze[i][1] = CellType.WALL;
                        maze[i][2] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][12] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
                    case(27):
                    case(28):
                        maze[i][0] = CellType.WALL;
                        maze[i][2] = CellType.WALL;
                        maze[i][3] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                        maze[i][6] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][9] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                        break;
//
//                        maze[i][j] = CellType.EMPTY;
//                        break;
                }

            }
        }

    }


}
