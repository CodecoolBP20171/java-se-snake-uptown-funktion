package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import jdk.nashorn.internal.objects.Global;
import sun.java2d.pipe.SpanShapeRenderer;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private Pane pane;

    public SimpleEnemy(Pane pane, SnakeHead snake) {
        super(pane);
        this.pane = pane;
        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        int speed = 1;

        boolean looper = true;

        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);


        while (looper) {
            Random rnd = new Random();
            if (!checkCoordinate(rnd, snake)) {
                if((snake.getY() + (double) 10 >
                        enemyCoordinateY(rnd) && snake.getY() - (double) 10 <
                        enemyCoordinateY(rnd)) ||
                        (snake.getX() + (double) 10 >
                                enemyCoordinateX(rnd) && snake.getX() - (double) 10 <
                                enemyCoordinateX(rnd))) {

                    setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
                    setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
                    double direction = rnd.nextDouble() * 360;
                    setRotate(direction);
                    heading = Utils.directionToVector(direction, speed);
                    looper = false;
                }
            }
        }
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
        new SimpleEnemy(pane, player);
    }

    @Override
    public String getMessage() {
        return "10 damage";
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
