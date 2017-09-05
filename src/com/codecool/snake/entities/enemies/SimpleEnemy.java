package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.Random;


// a simple enemy TODO make better ones.
public class SimpleEnemy extends Enemy {

    public SimpleEnemy(Pane pane, SnakeHead snake) {
        super(pane);
        this.pane = pane;
        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

        speed = 1;
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

        this.id = ++numberOfSimpleEnemies;

        setSpawnPosition(snake);
    }
}
