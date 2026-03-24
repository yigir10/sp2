package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    int x, y;
    float vY = 0;
    float downSpeed = -0.15f;
    int jumpHeight = 5;

    int frameCounter = 0;
    Texture[] framesArray;
    int width = 250;
    int height = 200;

    Bird(int x, int y, Texture texture, int speed) {
        this.x = x;
        this.y = y;
        framesArray = new Texture[]{
                new Texture("birdTiles/bird0.png"),
                new Texture("birdTiles/bird1.png"),
                new Texture("birdTiles/bird2.png"),
                new Texture("birdTiles/bird1.png"),
        };
    }

    public void onClick() {
        if (vY < 0) {
            vY = jumpHeight;
        } else {
            vY += jumpHeight;
        }
    }
    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > MyGdxGame.SCR_HEIGHT) return false;
        return true;
    }

    public void fly() {
        vY += downSpeed;
        y += vY;
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ >= framesArray.length * frameMultiplier - 1) {
            frameCounter = 0;
        }
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }
}