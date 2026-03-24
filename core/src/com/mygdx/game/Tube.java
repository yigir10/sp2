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
    int gapHeight = 350;
    Random random = new Random();
    int padding = 50;
    int x;
    int distanceBetweenTubes;
    int speed = 4;
    static boolean isPointReceived;

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
            isPointReceived = false;
            x = MyGdxGame.SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(MyGdxGame.SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }
    public boolean isHit(Bird bird) {
        if (bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width)
            return true;
        if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width)
            return true;
        return false;
    }
    public boolean needAddPoint(Bird bird) {
        return bird.x > x + width && !isPointReceived;
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
