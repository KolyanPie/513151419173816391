package ru.edu.tetromino;

import ru.edu.tetromino.visual.MainScreen;

public class Tetromino extends com.badlogic.gdx.Game {
    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }
}
