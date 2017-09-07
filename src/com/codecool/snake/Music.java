package com.codecool.snake;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {

    public Music(){}

    public static void playSound(String fileName){
        Media music = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/resources/" + fileName);
        MediaPlayer player = new MediaPlayer(music);
        player.play();
    }

}
