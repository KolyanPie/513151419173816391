package ru.edu.tetromino;

import ru.edu.tetromino.visual.MainScreen;

public class Game extends com.badlogic.gdx.Game {
    @Override
    public void create() {
        setScreen(new MainScreen());
    }
}
