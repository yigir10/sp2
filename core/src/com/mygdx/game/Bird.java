package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    int x, y;
    int speed;
    Texture texture;
    int jumpHeight;
    final int MAX_HEIGHT_OF_JUMP = 200;
    boolean jump;
    int frameCounter = 0;
    Texture[] framesArray;

    Bird(int x, int y, Texture texture, int speed) {
        frameCounter = 0;
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.speed = speed;
        framesArray = new Texture[]{
                new Texture("birdTiles/bird0.png"),
                new Texture("birdTiles/bird1.png"),
                new Texture("birdTiles/bird2.png"),
                new Texture("birdTiles/bird1.png"),
        };
    }

    public void setTexture(Texture texture){
        this.texture = texture;
    }
    public Texture getTexture() {
        return texture;
    }

    public void onClick() {
        jump = true;
        jumpHeight = MAX_HEIGHT_OF_JUMP + y;
    }

    public void fly() {
        if (y >= jumpHeight) {
            jump = false;
        }
        if (jump) {
            y += speed;
        } else {
            y -= speed;
        }
        x += speed;
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) {
            frameCounter = 0;
        }
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }
}
