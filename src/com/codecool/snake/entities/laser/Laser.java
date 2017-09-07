package com.codecool.snake.entities.laser;


import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.sun.jmx.snmp.SnmpEngine;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class Laser extends GameEntity implements Animatable{

    protected Point2D heading;
    protected double direction;

    public Laser(Pane pane, GameEntity gameEntity) {
        super(pane);
        setX(gameEntity.getX());
        setY(gameEntity.getY());
        direction = gameEntity.getRotate();

        setRotate(direction);
        heading = setDirection();
        setLaserImage();
        pane.getChildren().add(this);
    }

    public abstract void setLaserImage();

    public abstract Point2D setDirection();

    @Override
    public void step() {
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        //check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (isInteract(entity)) {
                if (entity instanceof Enemy) {
                    Globals.score += 1;
                }
                shot(entity);
            }
        }
    }

    public abstract boolean isInteract(GameEntity entity);

    public void shot(GameEntity entity) {
        if (entity.getClass().equals(SnakeHead.class)){
            SnakeHead sh = (SnakeHead)entity;
            sh.changeHealth(-10);
        } else {
            entity.destroy();
        }
        this.destroy();

        if (entity instanceof Enemy && this instanceof SnakeMissile && (!(entity instanceof SnakeHead))) {
            for (GameEntity ent : Globals.getGameObjects()) {
                if (ent instanceof SnakeHead) Interactable.randomSpawn(pane, (SnakeHead) ent, entity);
            }
        }



    }
}
