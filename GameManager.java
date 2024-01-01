
import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

public class GameManager extends Application {
    private static int CELL_SIZE;
    private static int ROWS;
    private static int COLUMNS;
    private Pacman pacman;
    private  Maze maze;

    private Timeline pacmanAnimation;
    @Getter@Setter
    private  CellType[][] mazeArray;
    private CellType[][] finalMaze;

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {
        // Создаем лабиринт
        maze = new Maze();
        mazeArray = maze.getMaze();
        pacman = new Pacman(mazeArray);

        CELL_SIZE = maze.getCELL_SIZE();
        ROWS = maze.getROWS();
        COLUMNS = maze.getCOLUMNS();

        pacman.countProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 300) {
                System.out.println("Congratulations, you won!");
                primaryStage.close();
            }
        });

//
//        // Создаем JavaFX Canvas для отображения лабиринта
//        Canvas canvas = new Canvas(COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        // Отрисовываем лабиринт на Canvas
//        drawMaze(gc, maze);
//
//        StackPane root = new StackPane();
//        root.getChildren().add(canvas);
//
//        primaryStage.setScene(new Scene(root, COLUMNS * CELL_SIZE, ROWS * CELL_SIZE));
//        primaryStage.show();

        Canvas canvas = new Canvas(COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);

        Duration duration = Duration.millis(200);  // Уменьшенный интервал времени
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            movePacman();
            gc.clearRect(0, 0, COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);
            drawMaze(gc);
            pacman.drawPacman(gc);
        });

        pacmanAnimation = new Timeline(keyFrame);
        pacmanAnimation.setCycleCount(Timeline.INDEFINITE);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    pacman.setDirection(Pacman.Direction.UP);
                    break;
                case DOWN:
                    pacman.setDirection(Pacman.Direction.DOWN);
                    break;
                case LEFT:
                    pacman.setDirection(Pacman.Direction.LEFT);
                    break;
                case RIGHT:
                    pacman.setDirection(Pacman.Direction.RIGHT);
                    break;
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();


        pacmanAnimation.play();

    }

//    private void drawMaze(GraphicsContext gc, Maze maze) {
//        CellType[][] mazeArray = maze.getMaze();
//
//        // Отрисовываем каждую клетку лабиринта
//        for (int i = 0; i < mazeArray.length; i++) {
//            for (int j = 0; j < mazeArray[i].length; j++) {
//                if(mazeArray[i][j] == CellType.EMPTY){
//                    gc.setFill(Color.BLACK);
//                    gc.fillRect(j * CELL_SIZE, i * CELL_SIZE,CELL_SIZE, CELL_SIZE);
//                }else if(mazeArray[i][j] == CellType.WALL){
//
//
////                    gc.setFill(Color.BLUE);
////                    gc.fillRect(j * CELL_SIZE, i * CELL_SIZE,CELL_SIZE, CELL_SIZE);
//                    gc.setFill(Color.BLACK);
//                    gc.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
//
//                    // Рисуем синий внутренний квадрат
//                    gc.setFill(Color.DARKBLUE);
//                    double innerSquareSize = 13;
//                    double startX = j * CELL_SIZE + (CELL_SIZE - innerSquareSize) / 2;
//                    double startY = i * CELL_SIZE + (CELL_SIZE - innerSquareSize) / 2;
//                    gc.fillRect(startX, startY, innerSquareSize, innerSquareSize);
//                }
//              //  gc.fillRect(j * CELL_SIZE, i * CELL_SIZE,CELL_SIZE, CELL_SIZE);
//            }
//        }
//    }

//        PacmanPane pacmanPane = new PacmanPane();
//        primaryStage.setTitle("Pacman Animation");
//        primaryStage.setScene(pacmanPane.createScene());
//        primaryStage.show();
//
//        PacmanAnimation.animatePacman(pacmanPane.getPacman());
//    }
private void initializePacmanAnimation() {

}

    private void movePacman() {
        pacman.move();
    }



    private void drawMaze(GraphicsContext gc) {
        finalMaze = pacman.getMazeArray();

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                if (mazeArray[i][j] == CellType.EMPTY) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (mazeArray[i][j] == CellType.WALL) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                    gc.setFill(Color.DARKBLUE);
                    double innerSquareSize = 13;
                    double startX = j * CELL_SIZE + (CELL_SIZE - innerSquareSize) / 2;
                    double startY = i * CELL_SIZE + (CELL_SIZE - innerSquareSize) / 2;
                    gc.fillRect(startX, startY, innerSquareSize, innerSquareSize);
                } else if(mazeArray[i][j] == CellType.POINTS){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                    gc.setFill(Color.GREY);
                    double circleSize = 4;
                    double centerX = j * CELL_SIZE + (CELL_SIZE - circleSize) / 2;
                    double centerY = i * CELL_SIZE + (CELL_SIZE - circleSize) / 2;
                    gc.fillOval(centerX, centerY, circleSize, circleSize);

                }
            }
        }
    }



}
