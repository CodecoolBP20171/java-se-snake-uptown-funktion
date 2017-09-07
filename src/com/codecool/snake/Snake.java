package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Snake extends Application {

    public static Stage primaryStage;
    public static Game game;
    public static RestartButton restartButton;
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

    public static void restart() {
        game.stop();
        Globals.leftKeyDown = false;
        Globals.rightKeyDown = false;
        Globals.laserKeyDown = false;
        Globals.score = 0;
        for (GameEntity entity : Globals.getGameObjects()) entity.destroy();
        game.getChildren().clear();
        game = new Game();
        setupHealthBar();
        setupAmmoBar();
        setupScoreDisplay();
        game.getChildren().add(restartButton);
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        game.start();
    }

    public static void setupHealthBar() {
        health = new Label();
        health.setStyle("-fx-font-size: 20px;" +
                "-fx-padding: 0px 0px 0px 80px;");
        health.setTextFill(Color.web("ff0000"));
        game.getChildren().add(health);
    }

    public static void setupAmmoBar() {
        ammo = new Label();
        ammo.setStyle("-fx-font-size: 20px;" +
                "-fx-padding: 0px 0px 0px 250px;" +
                "-fx-color: blue;");
        ammo.setTextFill(Color.web("0000ff"));
        game.getChildren().add(ammo);
    }


    public static void setupScoreDisplay() {
        scoreDisplay = new Label();
        scoreDisplay.setStyle("-fx-font-size: 20px;" +
                "-fx-padding: 0px 0px 0px 360px;" +
                "-fx-color: blue;");
        game.getChildren().add(scoreDisplay);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.game = new Game();
        this.health = new Label();

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();

        this.restartButton = new RestartButton();

        setupScoreDisplay();
        setupHealthBar();
        setupAmmoBar();
        restartButton.setOnAction(e-> restart());

        game.getChildren().add(restartButton);

        game.start();


    }
}
