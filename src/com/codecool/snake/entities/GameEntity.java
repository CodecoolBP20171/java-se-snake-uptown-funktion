package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected String isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH-35) return "right";
        else if (getX() < 0+5) return "left";
        else if (getY() > Globals.WINDOW_HEIGHT-35) return "bottom";
        else if (getY() < 0+5) return"top";
        return "in";
    }
}
