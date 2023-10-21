import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Window extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Pacman");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        Label label = new Label("Score:     Lives:");
        label.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        // Создаем HBox для размещения лейбла по центру
        HBox topScene = new HBox(label);
        topScene.setStyle("-fx-background-color: black;");
        topScene.setAlignment(Pos.CENTER); // Выравнивание по центру по горизонтали и вертикали
        Scene topSceneObject = new Scene(topScene, 448, 48, Color.BLACK);

        VBox bottomScene = new VBox();
        bottomScene.setSpacing(0);

//        Maze maze = new Maze();
//
      //    int[][] mazeData = maze.getMaze();

//        int mazeWidth = mazeData[0].length;
//        int mazeHeight = mazeData.length;
//
//        double cellWidth = 448.0 / mazeWidth;
//        double cellHeight = 496.0 / mazeHeight;


//        for (int row = 0; row < mazeHeight; row++) {
//            HBox rowBox = new HBox();
//            for (int col = 0; col < mazeWidth; col++) {
//                int cellValue = mazeData[row][col];
//                bottomScene.getChildren().add(maze.createTile(cellValue, cellWidth, cellHeight));
//            }
//        }
        Maze maze = new Maze();
        Group mazeGroup = maze.createMaze(448.0 / 28, 496.0 / 31, maze.getMaze());
        bottomScene.getChildren().add(mazeGroup);

        VBox root = new VBox();
        root.getChildren().addAll(topSceneObject.getRoot(), bottomScene);

        Scene scene = new Scene(root, 448, 544, Color.BLACK);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
