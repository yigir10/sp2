package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {
    Texture textureUpperTube;
    Texture textureDownTube;
    int width = 200;
    int height = 500;
    int gapY;
    int gapHeight = 300;
    Random random = new Random();
    int padding = 100;
    int x;
    int distanceBetweenTubes;
    int speed = 5;

    public Tube(int tubeCount, int tubeIdx) {

        gapY = gapHeight / 2 + padding + random.nextInt(MyGdxGame.SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distanceBetweenTubes = (MyGdxGame.SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIdx + MyGdxGame.SCR_WIDTH;

        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");
    }
    void move() {
        x -= speed;
        if (x < -width) {
            x = MyGdxGame.SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(MyGdxGame.SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }

    void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }
    void dispose() {
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }

}
