package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.PrimitiveIterator;

public class Snake extends Application {

    public static Stage primaryStage;
    public static Game game;

    public static void gameOver(int score) {
        game.stop();
        for (GameEntity entity : Globals.getGameObjects()) entity.destroy();
        game.getChildren().clear();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Game Over");
        alert.setHeaderText("You're dead: Game Over");
        alert.setContentText("Score: " + score);
        alert.setOnHidden(evt -> Platform.exit());
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
