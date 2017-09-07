package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class AmmoUp  extends GameEntity implements Interactable {

    public AmmoUp(Pane pane, SnakeHead snake) {
        super(pane);
        setImage(Globals.powerupAmmo);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeAmmo(1);
        destroy();
        Globals.music.playSound("purr.mp3");
        Interactable.randomSpawn(pane, snakeHead, this);
    }

    @Override
    public String getMessage() {
        return "Ammo picked up";
    }
}
