package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PrimitiveIterator;

public class Snake extends Application {

    public static Stage primaryStage;
    public static Game game;

    public static void gameOver(int score) {
        game.stop();
        for (GameEntity entity : Globals.getGameObjects()) entity.destroy();
        game.getChildren().clear();

        ButtonType restartButton = new ButtonType("Restart", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        List<ButtonType> buttonList = new ArrayList();
        buttonList.add(restartButton);
        buttonList.add(cancelButton);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("You're dead: Game Over");
        alert.setContentText("Score: " + score);
        alert.getButtonTypes().setAll(buttonList);
        alert.setOnHidden(evt -> {
            if(alert.getResult() == restartButton){

            } else {
                Platform.exit();
            }
        });
        alert.show();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.game = new Game();
        
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();

        RestartButton restartButton = new RestartButton();

        restartButton.setOnAction(e-> {
            game.stop();

            for (GameEntity entity : Globals.getGameObjects()) entity.destroy();

            game.getChildren().clear();


            Game newGame = new Game();
            newGame.getChildren().add(restartButton);

            primaryStage.setScene(new Scene(newGame, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
            newGame.start();
        });

        game.getChildren().add(restartButton);

        game.start();
    }
}
