package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


public class GhostEnemy extends Enemy {

    public GhostEnemy(Pane pane, SnakeHead snake) {
        super(pane);
        this.pane = pane;
        setImage(Globals.GhostEnemy);
        pane.getChildren().add(this);

        speed = 1;

        this.id = ++numberOfSimpleEnemies;

        setSpawnPosition(snake);
    }

    @Override
    public void step() {
        String isBounce = isOutOfBounds();
        if (!isBounce.equals("in")) {
            if (isBounce.equals("right")) setX(0 + 5);
            else if (isBounce.equals("left")) setX(Globals.WINDOW_WIDTH - 35);
            else if (isBounce.equals("top")) setY(Globals.WINDOW_HEIGHT - 35);
            else setY(0 + 5);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }
}
