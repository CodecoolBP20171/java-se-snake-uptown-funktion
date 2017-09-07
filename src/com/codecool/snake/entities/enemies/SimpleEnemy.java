package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


public class SimpleEnemy extends Enemy {

    public SimpleEnemy(Pane pane, SnakeHead snake) {
        super(pane);
        this.pane = pane;
        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

        speed = 1;

        this.id = ++numberOfSimpleEnemies;

        setSpawnPosition(snake);
    }
}
