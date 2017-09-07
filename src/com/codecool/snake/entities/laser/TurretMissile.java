package com.codecool.snake.entities.laser;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.TurretEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class TurretMissile extends Laser implements Interactable {

    private TurretEnemy turret;

    public TurretMissile(Pane pane, TurretEnemy turret){
        super(pane, turret);
        speed = 4;
        this.turret = turret;
        this.heading = setDirection();
    }

    @Override
    public void setLaserImage() {
        setImage(Globals.turretMissile);
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
            if(entityClass.equals(SnakeHead.class)){
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
