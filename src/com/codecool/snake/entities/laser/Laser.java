package com.codecool.snake.entities.laser;


import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Enemy;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

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
        System.out.println(heading.getX());
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

    // getBoundsInParent().intersects(entity.getBoundsInParent())
    public void shot(GameEntity entity) {
        System.out.println("I shot " + entity);
        entity.destroy();
        this.destroy();
    }
}
