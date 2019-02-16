package ru.edu.tetromino.core;

import static ru.edu.tetromino.core.Cell.*;

public class Figure {
    public static final byte T = 0;
    public static final byte L = 1;
    public static final byte Z = 2;
    public static final byte S = 3;
    public static final byte I = 4;

    public static final byte TOP = 0;
    public static final byte RIGHT = 1;
    public static final byte BOTTOM = 2;
    public static final byte LEFT = 3;

    private Cell[][] matrix = new Cell[4][4];

    public Figure(byte type) {
        switch (type) {
            case 0:
                createMatrix(0, 0, 1, 0, 2, 0, 1, 1);
                break;
            case 1:
                createMatrix(0, 0, 0, 1, 0, 2, 1, 2);
                break;
            case 2:
                createMatrix(0, 0, 1, 0, 1, 1, 2, 1);
                break;
            case 3:
                createMatrix(0, 0, 1, 0, 0, 1, 1, 1);
                break;
            case 4:
                createMatrix(0, 0, 0, 1, 0, 2, 0, 3);
                break;
        }
    }

    public Figure(byte type, byte rotation) {
        this(type);
        for (byte i = 0; i < rotation; i++) {
            rotate();
        }
    }

    public Figure(byte type, byte rotation, boolean isMirrored) {
        this(type, rotation);
        if (isMirrored) {
            mirror();
        }
    }

    private void mirror() {
        Cell[][] temp = new Cell[4][4];
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 4; j++) {
                temp[i][j] = matrix[j][i];
            }
        }
        matrix = temp;
    }

    private void rotate() {
        Cell[][] temp = new Cell[4][4];
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 4; j++) {
                temp[i][j] = matrix[3 - j][i];
            }
        }
        matrix = temp;
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

}
