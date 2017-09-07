package com.codecool.snake;

import com.codecool.snake.entities.enemies.GhostEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.AmmoUp;
import com.codecool.snake.entities.enemies.TurretEnemy;
import com.codecool.snake.entities.laser.TurretMissile;
import com.codecool.snake.entities.powerups.HealthUp;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;



public class Game extends Pane {


    public Game() {
        SnakeHead snakeHead= new SnakeHead(this, 500, 500);

        for (int i = 0; i < 1; i++) {
            new SimpleEnemy(this, snakeHead);
            new SimpleEnemy(this, snakeHead);
            new SimpleEnemy(this, snakeHead);
            new SimpleEnemy(this, snakeHead);
        }

        for (int i = 0; i < 5; i++) {
            new GhostEnemy(this, snakeHead);
            new TurretEnemy(this, snakeHead);
        }
        new HealthUp(this);
        new HealthUp(this);
        new HealthUp(this);

        new AmmoUp(this);
        new AmmoUp(this);
        new AmmoUp(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
    }



    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case X: {
                    Globals.laserKeyDown = true;
                    break;
                }
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case X: {
                    Globals.laserKeyDown = false;
                    break;
                }
            }
        });

        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();

    }

    public void stop() {
        Globals.gameLoop.stop();
    }
}
