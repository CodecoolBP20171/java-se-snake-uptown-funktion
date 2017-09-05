package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public double direction;
    public int speed;

    public static int numberOfSimpleEnemies=0;

    public int id;

    public SimpleEnemy(Pane pane) {
        super(pane);

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
    }

    @Override
    public void step() {
        String isBounce = isOutOfBounds();
        if (!isBounce.equals("in")) {
            //destroy();
            System.out.println("x" + getX() + " y " + getY());
            System.out.println("Bouncing " + this.id + " from " + isBounce);
            System.out.println("pre " + direction);

            /*
                    if (getX() > Globals.WINDOW_WIDTH || getX() < 0 ||
            getY() > Globals.WINDOW_HEIGHT || getY() < 0) {
             */

            /*
            if (getY() < 0) direction = 90+direction;
            System.out.println("post " + direction);
            */

            if (getX() > Globals.WINDOW_WIDTH-35) {
                System.out.println("Bouncing from right");
                direction = - direction;
            }
            else if (getX() < 0+5) {
                System.out.println("Bouncing from left");
                direction = - direction;
            }
            else if (getY() > Globals.WINDOW_HEIGHT-35) {
                System.out.println("Bouncing from bottom");
                direction = 180 - direction;
            }
            else {
                System.out.println("Bouncing from top");
                direction = 180 - direction;
            }

            //direction = direction < 90 ? direction+90 : direction-90;
            //System.out.println("pre: " + direction);
            //direction += 90;

            if (direction > 360) direction -= 360;
            else if (direction < 0) direction += 360;
            System.out.println("post " + direction);

            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);

            double nextX = (getX() + heading.getX());
            if ( nextX > Globals.WINDOW_WIDTH-35 ) setX(heading.getX() + Globals.WINDOW_WIDTH-35-5);
            else if (nextX < 0+5) setX(heading.getX() + 0+5+5);
            double nextY = getY() + heading.getY();
            if (nextY > Globals.WINDOW_HEIGHT-35) setY(heading.getY() + Globals.WINDOW_HEIGHT-35-5);
            else if (nextY < +5) setY(heading.getY() + 0+5+5);

            System.out.println("x " + getX() + " y " + getY());
            System.out.println();

            /*
            if (getX() > Globals.WINDOW_WIDTH-35 || getX() < 0+5 ||
            getY() > Globals.WINDOW_HEIGHT-35 || getY() < 0+5)
             */
        }
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
