package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.scene.image.Image;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("nyan_cat_icon.png");
    public static Image snakeBody = new Image("Nyan-Cat-body_icon.png");
    public static Image simpleEnemy = new Image("nyan_dog_enemy1.png");
    public static Image GhostEnemy = new Image("nyanDog.png");
    public static Image turretEnemy = new Image("darknyancat.gif");
    public static Image powerupBerry = new Image("cloud.png");
    public static Image powerupHealth = new Image("powerup_health.png");
    public static Image powerupAmmo = new Image("cupcake.png");
    public static Image snakeMissile = new Image("Rainbow_Paintball.png");
    public static Image turretMissile = new Image("skull.gif");
    public static Music music = new Music();


    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean laserKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static int score;


    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
