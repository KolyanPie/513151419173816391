package ru.edu.tetromino.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.edu.tetromino.Starter;

public class DesktopLauncher {
    public static void main(String[] arg) {
        System.setProperty("user.name", "seconduser");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new Starter(), config);
    }
}
