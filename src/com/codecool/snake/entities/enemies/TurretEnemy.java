package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.laser.TurretMissile;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class TurretEnemy extends Enemy{

    public long lastTimeOfShot;
    private Pane pane;

    public TurretEnemy(Pane pane, SnakeHead snake) {
        super(pane);
        this.pane = pane;
        setImage(Globals.turretEnemy);
        pane.getChildren().add(this);
        this.id = ++numberOfSimpleEnemies;
        setSpawnPosition(snake);
    }

    @Override
    public void step() {
        long timeFromLastShoot = System.currentTimeMillis() - lastTimeOfShot;
        if (timeFromLastShoot > 1000) {
            new TurretMissile(pane, this);
            lastTimeOfShot = System.currentTimeMillis();
        }
    }
}
