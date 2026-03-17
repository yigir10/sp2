package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    int x, y;
    float vY = 0;      // Текущая вертикальная скорость
    float gravity = -0.5f;    // Сила гравитации (тянет вниз)
    int jumpHeight = 10;    // Сила прыжка (импульс вверх)

    int frameCounter = 0;
    Texture[] framesArray;
    int width = 100;
    int height = 100;

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

    public void fly() {
        vY += gravity;  // Гравитация постоянно уменьшает скорость
        y += vY;  // Изменяем координату Y на значение скорости

        if (y < 0) {
           y = 0;
        }
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