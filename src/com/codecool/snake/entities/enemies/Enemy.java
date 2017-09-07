package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Enemy extends GameEntity implements Animatable, Interactable {

    protected Point2D heading;
    protected static final int damage = 10;

    protected double direction;

    protected static int numberOfSimpleEnemies=0;

    protected int id;

    protected Enemy(Pane pane) {
        super(pane);
    }

    public void setSpawnPosition(SnakeHead snake){
        boolean looper = true;
        while (looper) {
            Random rndSpawn = new Random();
            if (!checkCoordinate(rndSpawn, snake)) {
                if((snake.getY() + (double) 10 >
                        enemyCoordinateY(rndSpawn) && snake.getY() - (double) 10 <
                        enemyCoordinateY(rndSpawn)) ||
                        (snake.getX() + (double) 10 >
                                enemyCoordinateX(rndSpawn) && snake.getX() - (double) 10 <
                                enemyCoordinateX(rndSpawn))) {

                    setX(rndSpawn.nextDouble() * Globals.WINDOW_WIDTH);
                    setY(rndSpawn.nextDouble() * Globals.WINDOW_HEIGHT);
                    double direction = rndSpawn.nextDouble() * 360;
                    setRotate(direction);
                    heading = Utils.directionToVector(direction, speed);
                    looper = false;
                }
            }
        }
    }

    @Override
    public void step() {
        String isBounce = isOutOfBounds();
        if (!isBounce.equals("in")) {
            //destroy();
            /*
            System.out.println("x" + getX() + " y " + getY());
            System.out.println("Bouncing " + this.id + " from " + isBounce);
            System.out.println("pre " + direction);
            */
            if (isBounce.equals("right") || isBounce.equals("left")) direction = - direction;
            else direction = 180 - direction;

            if (direction > 360) direction -= 360;
            else if (direction < 0) direction += 360;

            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);

            double nextX = (getX() + heading.getX());
            if ( nextX > Globals.WINDOW_WIDTH-35 ) setX(heading.getX() + Globals.WINDOW_WIDTH-35-5);
            else if (nextX < 0+5) setX(heading.getX() + 0+5+5);
            double nextY = getY() + heading.getY();
            if (nextY > Globals.WINDOW_HEIGHT-35) setY(heading.getY() + Globals.WINDOW_HEIGHT-35-5);
            else if (nextY < +5) setY(heading.getY() + 0+5+5);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
        Interactable.randomSpawn(pane, player, this);

    }

    @Override
    public String getMessage() {
        return "" + damage + " damage";
    }

    public boolean checkCoordinate(Random random, SnakeHead snake) {

        for (GameEntity snakePart : snake.getSnakeParts()) {
            if((snakePart.getY() + (double) 10 >
                    enemyCoordinateY(random) && snakePart.getY() - (double) 10 <
                    enemyCoordinateY(random)) ||
                    (snakePart.getX() + (double) 10 >
                            enemyCoordinateX(random) && snakePart.getX() - (double) 10 <
                            enemyCoordinateX(random))) return true;
        }
        return false;
    }

    public double enemyCoordinateX(Random random) {
        return random.nextDouble() * Globals.WINDOW_WIDTH;
    }

    public double enemyCoordinateY(Random random) {
        return random.nextDouble() * Globals.WINDOW_HEIGHT;
    }
}

