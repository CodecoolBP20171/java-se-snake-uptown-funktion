package com.codecool.snake.entities.laser;

import com.codecool.snake.Globals;
import com.codecool.snake.Snake;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class Laser extends GameEntity implements Animatable, Interactable {

    protected Point2D heading;
    protected Pane pane;

    protected double direction;
    protected float speed;


    public Laser(Pane pane, SnakeHead snake) {
        super(pane);
        setX(snake.getX());
        setY(snake.getY());
        direction = snake.getRotate();
        setRotate(direction);
        speed = snake.getSpeed() * 2;
        heading = Utils.directionToVector(direction, speed);
        setImage(Globals.Laser);
        pane.getChildren().add(this);
    }


    @Override
    public void step() {
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        //check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable && !entity.getClass().equals(Laser.class)) {
                    //Interactable interactable = (Interactable) entity;
                    shot(entity);
                    //System.out.println(interactable.getMessage());
                }
            }
        }
    }

    public void shot(GameEntity entity) {
        System.out.println("I shot " + entity);
        entity.destroy();
    }

    @Override
    public void apply(SnakeHead snake) {

    }

    @Override
    public String getMessage() {
        return null;
    }
}
