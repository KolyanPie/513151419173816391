package ru.edu.tetromino.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ru.edu.tetromino.control.FigureMouseListener;

import static ru.edu.tetromino.core.Cell.*;

public class Figure extends Actor {
    public static final byte T = 0;
    public static final byte L = 1;
    public static final byte LM = 2;
    public static final byte Z = 3;
    public static final byte ZM = 4;
    public static final byte S = 5;
    public static final byte I = 6;

    public static final byte TOP = 0;
    public static final byte LEFT = 1;
    public static final byte BOTTOM = 2;
    public static final byte RIGHT = 3;

    private Cell[][] matrix = new Cell[4][4];
    private Sprite sprite;
    private byte offsetX;
    private byte offsetY;

    public Figure(byte type) {
        switch (type) {
            case 0:
                createMatrix(2, 0, 2, 1, 2, 2, 3, 1);
                sprite = new Sprite(new Texture(Gdx.files.internal("figures/t.png")));
                break;
            case 1:
                createMatrix(1, 0, 2, 0, 3, 0, 3, 1);
                sprite = new Sprite(new Texture(Gdx.files.internal("figures/l.png")));
                break;
            case 2:
                createMatrix(1, 1, 2, 1, 3, 1, 3, 0);
                sprite = new Sprite(new Texture(Gdx.files.internal("figures/lm.png")));
                break;
            case 3:
                createMatrix(2, 0, 2, 1, 3, 1, 3, 2);
                sprite = new Sprite(new Texture(Gdx.files.internal("figures/z.png")));
                break;
            case 4:
                createMatrix(3, 0, 2, 1, 3, 1, 2, 2);
                sprite = new Sprite(new Texture(Gdx.files.internal("figures/zm.png")));
                break;
            case 5:
                createMatrix(2, 0, 2, 1, 3, 0, 3, 1);
                sprite = new Sprite(new Texture(Gdx.files.internal("figures/s.png")));
                break;
            case 6:
                createMatrix(0, 0, 1, 0, 2, 0, 3, 0);
                sprite = new Sprite(new Texture(Gdx.files.internal("figures/i.png")));
                break;
        }
        addListener(new FigureMouseListener(this));
    }

    public Figure(byte type, byte rotation) {
        this(type);
        for (byte i = 0; i < rotation; i++) {
            rotate();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = sprite.getX();
        float y = sprite.getY();
        float size = getWidth() / 4;
        sprite.setPosition(x - offsetX * size, y - offsetY * size);
        sprite.draw(batch);
        sprite.setPosition(x, y);
    }

    @Override
    public void act(float delta) {
        sprite.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        float size = getWidth() / 4;
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j].equals(FILLED)) {
                    if (j * size < x && x < (j + 1) * size && (3 - i) * size < y && y < (4 - i) * size) {
                        return this;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        setBounds(x, y, width);
    }

    public void setBounds(float x, float y, float size) {
        setPosition(x, y);
        setSize(size);
    }

    @Override
    public void setSize(float width, float height) {
        setSize(width);
    }

    @Override
    public void setWidth(float width) {
        setSize(width);
    }

    @Override
    public void setHeight(float height) {
        setSize(height);
    }

    public void setSize(float size) {
        super.setWidth(size);
        super.setHeight(size);
    }

    public void rotate() {
        fromCorner();
        Cell[][] temp = new Cell[4][4];
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 4; j++) {
                temp[i][j] = matrix[j][3 - i];
            }
        }
        matrix = temp;
        toCorner();
        sprite.rotate90(false);
    }

    private void toCorner() {
        while (matrix[3][0].equals(EMPTY) && matrix[3][1].equals(EMPTY) && matrix[3][2].equals(EMPTY) && matrix[3][3].equals(EMPTY)) {
            Cell[] temp = matrix[3];
            matrix[3] = matrix[2];
            matrix[2] = matrix[1];
            matrix[1] = matrix[0];
            matrix[0] = temp;
            offsetY++;
        }
        while (matrix[0][0].equals(EMPTY) && matrix[1][0].equals(EMPTY) && matrix[2][0].equals(EMPTY) && matrix[3][0].equals(EMPTY)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    matrix[j][i] = matrix[j][i + 1];
                }
            }
            for (int i = 0; i < 4; i++) {
                matrix[i][3] = EMPTY;
            }
            offsetX++;
        }
    }

    private void fromCorner() {
        while (offsetX > 0) {
            offsetX--;
            for (int i = 3; i > 0; i--) {
                for (int j = 0; j < 4; j++) {
                    matrix[j][i] = matrix[j][i - 1];
                }
            }
            for (int i = 0; i < 4; i++) {
                matrix[i][0] = EMPTY;
            }
        }
        while (offsetY > 0) {
            offsetY--;
            Cell[] temp = matrix[0];
            matrix[0] = matrix[1];
            matrix[1] = matrix[2];
            matrix[2] = matrix[3];
            matrix[3] = temp;
        }
    }

    private void createMatrix(int i1, int j1, int i2, int j2, int i3, int j3, int i4, int j4) {
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 4; j++) {
                matrix[i][j] = EMPTY;
            }
        }
        matrix[i1][j1] = FILLED;
        matrix[i2][j2] = FILLED;
        matrix[i3][j3] = FILLED;
        matrix[i4][j4] = FILLED;
    }


    public Cell[][] getMatrix() {
        return matrix;
    }
}
