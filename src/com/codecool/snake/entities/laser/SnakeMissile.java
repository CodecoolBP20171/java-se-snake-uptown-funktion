package com.codecool.snake.entities.laser;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.powerups.AmmoUp;
import com.codecool.snake.entities.powerups.HealthUp;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public

class SnakeMissile extends Laser implements Interactable{

    private SnakeHead snakeHead;

    public SnakeMissile(Pane pane, SnakeHead snake){
        super(pane, snake);
        speed = snake.getSpeed() * 2;
        this.snakeHead = snake;
        this.heading = setDirection();
        Globals.music.playSound("spit.mp3");
    }

    @Override
    public void setLaserImage() {
        setImage(Globals.snakeMissile);
    }

    @Override
    public Point2D setDirection() {
        return Utils.directionToVector(direction, speed);
    }

    @Override
    public void apply(SnakeHead snakeHead) {}

    @Override
    public boolean isInteract(GameEntity entity) {
        if(getBoundsInParent().intersects(entity.getBoundsInParent())){
            Object entityClass = entity.getClass();
            if(entity instanceof Interactable && !entityClass.equals(SnakeMissile.class)
                    && !entityClass.equals(SimplePowerup.class)
                    && !entityClass.equals(AmmoUp.class)
                    && !entityClass.equals(HealthUp.class)){
                return true;
            }

        }
        return false;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
