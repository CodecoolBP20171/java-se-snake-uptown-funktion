package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Snake extends Application {

    private static Stage primaryStage;
    private static Game game;
    private static Button restartButton;
    public static Label health;
    public static Label ammo;
    public static Label scoreDisplay;

    public static void gameOver() {
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
        alert.setContentText("Score: " + Globals.score);
        alert.getButtonTypes().setAll(buttonList);
        alert.setOnHidden(evt -> {
            if(alert.getResult() == restartButton){
                restart();
            } else {
                Platform.exit();
            }
        });
        alert.show();
    }

    public static void setupHealthBar() {
        health = new Label();
        health.setStyle("-fx-font-size: 20px;" +
                "-fx-padding: 0px 0px 0px 80px;" +
                "-fx-background-color: antiquewhite");
        health.setTextFill(Color.web("ff0000"));
        game.getChildren().add(health);
    }

    public static void setupAmmoBar() {
        ammo = new Label();
        ammo.setStyle("-fx-font-size: 20px;" +
                "-fx-padding: 0px 0px 0px 250px;" +
                "-fx-color: blue;" +
                "-fx-background-color: antiquewhite");
        ammo.setTextFill(Color.web("0000ff"));
        game.getChildren().add(ammo);
    }

    public static void setupScoreDisplay() {
        scoreDisplay = new Label();
        scoreDisplay.setStyle("-fx-font-size: 20px;" +
                "-fx-padding: 0px 0px 0px 360px;" +
                "-fx-color: black;" +
                "-fx-background-color: antiquewhite");
        game.getChildren().add(scoreDisplay);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.game = new Game();
        this.health = new Label();
        this.restartButton = new Button("Restart");
        restartButton.setOnAction(e-> restart());
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
        startGame();
    }

    public static void restart() {
        game.stop();
        Globals.leftKeyDown = false;
        Globals.rightKeyDown = false;
        Globals.laserKeyDown = false;
        Globals.score = 0;
        for (GameEntity entity : Globals.getGameObjects()) entity.destroy();
        game.getChildren().clear();
        game = new Game();
        startGame();
    }

    public static void startGame() {
        game.setId("game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.getScene().getStylesheets().add("style.css");
        setupScoreDisplay();
        setupAmmoBar();
        setupHealthBar();
        game.getChildren().add(restartButton);
        game.start();
    }
}
