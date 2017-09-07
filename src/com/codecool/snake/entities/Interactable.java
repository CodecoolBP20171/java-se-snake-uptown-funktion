package com.codecool.snake.entities;

import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;

// interface that all game objects that can be interacted with must implement.
public interface Interactable {

    void apply(SnakeHead snakeHead);

    String getMessage();

    // Creates a new instance of the destroyed object with same type
    static void randomSpawn(Pane pane, SnakeHead player, Object entity) {
        try {
            entity.getClass().getConstructor(Pane.class, SnakeHead.class).newInstance(pane, player);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
