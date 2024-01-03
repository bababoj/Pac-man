import lombok.Getter;
public class Maze {
    @Getter
    private final int CELL_SIZE;
    @Getter
    private final int ROWS;
    @Getter
    private final int COLUMNS;
    @Getter
    private final CellType[][] maze;

    public Maze() {
        CELL_SIZE = 16;
        ROWS = 31;
        COLUMNS = 28;
        maze = new CellType[ROWS][COLUMNS];
        initializeMaze();
    }

    private void initializeMaze() {
        classicMaze();
        mirrorLeftToRight();
    }

    private void mirrorLeftToRight() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS / 2; j++) {
                maze[i][COLUMNS / 2 + j] = maze[i][(COLUMNS / 2) - 1 - j];
            }
        }
    }

    private void classicMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < COLUMNS / 2; j++) {
                maze[i][j] = CellType.POINTS;
                switch (i) {
                    case (0), (30) -> maze[i][j] = CellType.WALL;
                    case (1) -> {
                        maze[i][0] = CellType.WALL;
                        maze[i][COLUMNS / 2 - 1] = CellType.WALL;
                    }
                    case (3), (2), (4), (21), (22) -> {
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
                    }
                    case (5), (29) -> maze[i][0] = CellType.WALL;
                    case (6), (7) -> {
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
                    }
                    case (8), (26) -> {
                        maze[i][0] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                    }
                    case (9) -> {
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
                    }
                    case (10) -> {
                        maze[i][0] = CellType.EMPTY;
                        maze[i][1] = CellType.EMPTY;
                        maze[i][2] = CellType.EMPTY;
                        maze[i][3] = CellType.EMPTY;
                        maze[i][4] = CellType.EMPTY;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][9] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                    }
                    case (11), (17) -> {
                        maze[i][0] = CellType.EMPTY;
                        maze[i][1] = CellType.EMPTY;
                        maze[i][2] = CellType.EMPTY;
                        maze[i][3] = CellType.EMPTY;
                        maze[i][4] = CellType.EMPTY;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                    }
                    case (12) -> {
                        maze[i][0] = CellType.EMPTY;
                        maze[i][1] = CellType.EMPTY;
                        maze[i][2] = CellType.EMPTY;
                        maze[i][3] = CellType.EMPTY;
                        maze[i][4] = CellType.EMPTY;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][12] = CellType.WALL;
                        maze[i][13] = CellType.EMPTY;
                    }
                    case (13), (15) -> {
                        maze[i][11] = CellType.EMPTY;
                        maze[i][12] = CellType.EMPTY;
                        maze[i][13] = CellType.EMPTY;
                        maze[i][14] = CellType.EMPTY;
                        maze[i][15] = CellType.EMPTY;
                        maze[i][16] = CellType.EMPTY;
                        maze[i][0] = CellType.WALL;
                        maze[i][1] = CellType.WALL;
                        maze[i][2] = CellType.WALL;
                        maze[i][3] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                    }
                    case (14) -> {
                        maze[i][11] = CellType.EMPTY;
                        maze[i][12] = CellType.EMPTY;
                        maze[i][13] = CellType.EMPTY;
                        maze[i][14] = CellType.EMPTY;
                        maze[i][15] = CellType.EMPTY;
                        maze[i][16] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                    }
                    case (16), (18) -> {
                        maze[i][0] = CellType.EMPTY;
                        maze[i][1] = CellType.EMPTY;
                        maze[i][2] = CellType.EMPTY;
                        maze[i][3] = CellType.EMPTY;
                        maze[i][4] = CellType.EMPTY;
                        maze[i][5] = CellType.WALL;
                        maze[i][7] = CellType.WALL;
                        maze[i][8] = CellType.WALL;
                        maze[i][10] = CellType.WALL;
                        maze[i][11] = CellType.WALL;
                        maze[i][12] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                    }
                    case (19) -> {
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
                    }
                    case (20) -> {
                        maze[i][0] = CellType.WALL;
                        maze[i][13] = CellType.WALL;
                    }
                    case (23) -> {
                        maze[i][0] = CellType.WALL;
                        maze[i][4] = CellType.WALL;
                        maze[i][5] = CellType.WALL;
                    }
                    case (24), (25) -> {
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
                    }
                    case (27), (28) -> {
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
                    }
                }

            }
        }

    }


}
