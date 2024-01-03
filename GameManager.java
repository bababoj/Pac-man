import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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

    private int loseAttempts = 0;

    @Getter@Setter
    private  CellType[][] mazeArray;

    private Ghost ghost1;
    private Ghost ghost2;
    private Ghost ghost3;
    private Ghost ghost4;

    private Label livesLabel;
    private Label scoreLabel;

    private Booster booster1;
    private Booster booster2;
    private Booster booster3;
    private Booster booster4;
    private boolean boosterActive = false;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Maze maze = new Maze();
        mazeArray = maze.getMaze();
        pacman = new Pacman(mazeArray);
        ghost1 = new Ghost(mazeArray);
        ghost2 = new Ghost(mazeArray);
        ghost3 = new Ghost(mazeArray);
        ghost4 = new Ghost(mazeArray);
        booster1 = new Booster(1, 3);
        booster2 = new Booster(26, 3);
        booster3 = new Booster(1, 23);
        booster4 = new Booster(26, 23);

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

        BorderPane root = new BorderPane();

        HBox infoPanel = new HBox();
        infoPanel.setSpacing(20);
        infoPanel.setStyle("-fx-background-color: black;");

        livesLabel = new Label("Lives: " + (3 - loseAttempts));
        livesLabel.setTextFill(Color.WHITE);
        infoPanel.getChildren().add(livesLabel);

        scoreLabel = new Label("Score: " + pacman.getCount());
        scoreLabel.setTextFill(Color.WHITE);
        infoPanel.getChildren().add(scoreLabel);

        root.setTop(infoPanel);
        root.setCenter(canvas);

        Scene scene = new Scene(root, COLUMNS * CELL_SIZE, ROWS * CELL_SIZE + CELL_SIZE);

        Duration duration = Duration.millis(200);
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

            scoreLabel.setText("Score: " + pacman.getCount() + "/ 300");

        });

        Timeline pacmanAnimation = new Timeline(keyFrame);
        pacmanAnimation.setCycleCount(Timeline.INDEFINITE);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> pacman.setDirection(Direction.UP);
                case DOWN -> pacman.setDirection(Direction.DOWN);
                case LEFT -> pacman.setDirection(Direction.LEFT);
                case RIGHT -> pacman.setDirection(Direction.RIGHT);
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();


        pacmanAnimation.play();

    }
    private void moveGhostsAndCheckCollision(Stage primaryStage) {
        moveGhosts();
        if (!boosterActive) {
            checkGhostCollision(primaryStage);
        }else {
            checkGhostEating();
        }
    }
    private void checkGhostCollision(Stage primaryStage) {
        if (pacman.getX() == ghost1.getX() && pacman.getY() == ghost1.getY() ||
                pacman.getX() == ghost2.getX() && pacman.getY() == ghost2.getY() ||
                pacman.getX() == ghost3.getX() && pacman.getY() == ghost3.getY() ||
                pacman.getX() == ghost4.getX() && pacman.getY() == ghost4.getY()) {
            handleGhostCollision(primaryStage);
        }
    }
    private void checkGhostEating() {
        if (pacman.getX() == ghost1.getX() && pacman.getY() == ghost1.getY()){
            eatGhost(ghost1);
        }
        else if(pacman.getX() == ghost2.getX() && pacman.getY() == ghost2.getY()){
            eatGhost(ghost2);

        }else if(pacman.getX() == ghost3.getX() && pacman.getY() == ghost3.getY()){
            eatGhost(ghost3);

        }else if(pacman.getX() == ghost4.getX() && pacman.getY() == ghost4.getY()){
            eatGhost(ghost4);
        }
    }
    private void eatGhost(Ghost ghost){
        ghost.resetPosition();
    }

    private void moveGhosts() {
        ghost1.move();
        ghost2.move();
        ghost3.move();
        ghost4.move();
    }

    private void movePacman(Stage primaryStage) {
        pacman.move();
        checkBoosterCollision();
        if (!boosterActive) {
            checkGhostCollision(primaryStage);
        }else {
            checkGhostEating();
        }
    }

    private void checkBoosterCollision() {
        if (booster1.isActive() && pacman.getX() == booster1.getX() && pacman.getY() == booster1.getY()) {
            booster1.deactivate();
            activateGhostVulnerability();
        }
        if (booster2.isActive() && pacman.getX() == booster2.getX() && pacman.getY() == booster2.getY()) {
            booster2.deactivate();
            activateGhostVulnerability();
        }
        if (booster3.isActive() && pacman.getX() == booster3.getX() && pacman.getY() == booster3.getY()) {
            booster3.deactivate();
            activateGhostVulnerability();
        }
        if (booster4.isActive() && pacman.getX() == booster4.getX() && pacman.getY() == booster4.getY()) {
            booster4.deactivate();
            activateGhostVulnerability();
        }
    }

    private void activateGhostVulnerability() {
        boosterActive = true;

        ghost1.setVulnerable(true);
        ghost2.setVulnerable(true);
        ghost3.setVulnerable(true);
        ghost4.setVulnerable(true);

        int boosterDuration = 15;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(boosterDuration), event -> {
            boosterActive = false;
            ghost1.setVulnerable(false);
            ghost2.setVulnerable(false);
            ghost3.setVulnerable(false);
            ghost4.setVulnerable(false);
        }));
        timeline.play();
    }

    private void handleGhostCollision(Stage primaryStage) {
        System.out.println("You lost!");

        loseAttempts++;

        if (loseAttempts == 3) {
            System.out.println("Game over!");
            primaryStage.close();
        }

        pacman.setX(pacman.getInitialX());
        pacman.setY(pacman.getInitialY());

        livesLabel.setText("Lives: " + (3 - loseAttempts));
    }

    private void drawMaze(GraphicsContext gc) {
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
        booster1.drawBooster(gc);
        booster2.drawBooster(gc);
        booster3.drawBooster(gc);
        booster4.drawBooster(gc);
    }

}
