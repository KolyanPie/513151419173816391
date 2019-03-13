package ru.edu.tetromino.control;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import ru.edu.tetromino.core.Figure;

public class FigureMouseListener extends InputListener {
    private Figure figure;
    private boolean dragged;
    private float x;
    private float y;
    private long timeDown;

    public FigureMouseListener(Figure figure) {
        this.figure = figure;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.x = x;
        this.y = y;
        dragged = false;
        timeDown = System.currentTimeMillis();
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (!dragged) {
            figure.rotate();
        }
        super.touchUp(event, x, y, pointer, button);
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if (System.currentTimeMillis() - timeDown > 100) {
            figure.moveBy(x - this.x, y - this.y);
            dragged = true;
        }
        super.touchDragged(event, x, y, pointer);
    }
}
