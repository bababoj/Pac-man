
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

    private int loseAttempts = 0;

    @Getter@Setter
    private  CellType[][] mazeArray;
    private CellType[][] finalMaze;

    private Ghost ghost1;
    private Ghost ghost2;
    private Ghost ghost3;
    private Ghost ghost4;

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {
        // Создаем лабиринт
        maze = new Maze();
        mazeArray = maze.getMaze();
        pacman = new Pacman(mazeArray);
        ghost1 = new Ghost(mazeArray);
        ghost2 = new Ghost(mazeArray);
        ghost3 = new Ghost(mazeArray);
        ghost4 = new Ghost(mazeArray);

        CELL_SIZE = maze.getCELL_SIZE();
        ROWS = maze.getROWS();
        COLUMNS = maze.getCOLUMNS();

        pacman.countProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 300) {
                System.out.println("Congratulations, you won!");
                primaryStage.close();
            }
        });

        Canvas canvas = new Canvas(COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);

        Duration duration = Duration.millis(200);  // Уменьшенный интервал времени
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            movePacman(primaryStage);
            moveGhostsAndCheckCollision(primaryStage);
            gc.clearRect(0, 0, COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);
            drawMaze(gc);
            pacman.drawPacman(gc);
            ghost1.drawGhost(gc, Color.RED);
            ghost2.drawGhost(gc, Color.YELLOW);
            ghost3.drawGhost(gc, Color.PINK);
            ghost4.drawGhost(gc, Color.CYAN);
        });

        pacmanAnimation = new Timeline(keyFrame);
        pacmanAnimation.setCycleCount(Timeline.INDEFINITE);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    pacman.setDirection(Direction.UP);
                    break;
                case DOWN:
                    pacman.setDirection(Direction.DOWN);
                    break;
                case LEFT:
                    pacman.setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    pacman.setDirection(Direction.RIGHT);
                    break;
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();


        pacmanAnimation.play();

    }
    private void moveGhostsAndCheckCollision(Stage primaryStage) {
        moveGhosts();
        loseCheck(primaryStage);
    }
    private void loseCheck(Stage primaryStage) {
        // Проверяем, есть ли совпадение координат между пакманом и призраками
        if (pacman.getX() == ghost1.getX() && pacman.getY() == ghost1.getY() ||
                pacman.getX() == ghost2.getX() && pacman.getY() == ghost2.getY() ||
                pacman.getX() == ghost3.getX() && pacman.getY() == ghost3.getY() ||
                pacman.getX() == ghost4.getX() && pacman.getY() == ghost4.getY()) {
            // Совпадение координат, пакман проиграл, перемещаем его в начальную точку
            System.out.println("You lost!");

            // Увеличиваем счетчик попыток
            loseAttempts++;

            if (loseAttempts == 3) {
                System.out.println("Game over!");
                primaryStage.close();
            }

            // Перемещаем пакман в начальную точку
            pacman.setX(pacman.getInitialX());
            pacman.setY(pacman.getInitialY());
        }
    }


    private void movePacman(Stage primaryStage) {
        pacman.move();
        loseCheck(primaryStage);
    }
    private void moveGhosts(){
        ghost1.move();
        ghost2.move();
        ghost3.move();
        ghost4.move();
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
