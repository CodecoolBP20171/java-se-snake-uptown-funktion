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
public class GhostEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private Pane pane;

    public double direction;
    public int speed;

    public static int numberOfSimpleEnemies=0;

    public int id;

    public GhostEnemy(Pane pane, SnakeHead snake) {
        super(pane);
        this.pane = pane;
        setImage(Globals.GhostEnemy);
        pane.getChildren().add(this);

        speed = 1;
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

        this.id = ++numberOfSimpleEnemies;

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
            if (isBounce.equals("right")) setX(0 + 5);
            else if (isBounce.equals("left")) setX(Globals.WINDOW_WIDTH - 35);
            else if (isBounce.equals("top")) setY(Globals.WINDOW_HEIGHT - 35);
            else setY(0 + 5);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
        new GhostEnemy(pane, player);
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
