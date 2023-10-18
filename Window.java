import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Создание окна (Stage)
        primaryStage.setTitle("Pacman");
      //  primaryStage.setWidth(448); // Установите ширину окна в пикселях
        primaryStage.setWidth(450); // Установите ширину окна в пикселях
        primaryStage.setHeight(576); // Установите высоту окна в пикселях
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        Group root = new Group();

        int numRows = 31;
        int numCols = 28;
        int cellWidth = 16; // Ширина ячейки
        int cellHeight = 20; // Высота ячейки

        // Создаем GridPane и заполняем его данными
        GridPane gridPane = new GridPane();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Label label = new Label("0"); // Замените "Data" на ваш элемент массива
                label.setMinWidth(cellWidth);
                label.setMinHeight(cellHeight);
                gridPane.add(label, col, row);
            }
        }

        Scene mainScene = new Scene(gridPane, Color.BLACK);




        // Создание контента для окна
        //Label label = new Label("Привет, мир!");
        //Scene scene = new Scene(label);


        // Установка сцены в окно
        primaryStage.setScene(mainScene);

        // Отобразить окно
        primaryStage.show();
    }
}
