package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.Snake;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.laser.Laser;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import static com.codecool.snake.Globals.snakeHead;

public class SnakeHead extends GameEntity implements Animatable {

    public static long laserShootTime;
    public static long lastTimeOfShot;

    public static float getSpeed() {
        return speed;
    }

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private List<GameEntity> snakeParts = new ArrayList<>();

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(snakeHead);
        pane.getChildren().add(this);
        addPart(4);

    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }

        if (Globals.laserKeyDown ) {
            long timeFromLastShoot = System.currentTimeMillis() - lastTimeOfShot;
            System.out.println(timeFromLastShoot);
            if (timeFromLastShoot > 1000) {
                new Laser(pane, this);
                lastTimeOfShot = System.currentTimeMillis();
            }
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (!isOutOfBounds().equals("in") || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
            int score = (int) Globals.getGameObjects().stream()
                    .filter(w -> w instanceof SnakeBody).count();
            Snake.gameOver(score);
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
            snakeParts.add(tail);
        }
    }

    public List<GameEntity> getSnakeParts() {
        return snakeParts;
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public int getHealth() {return health;}
}
