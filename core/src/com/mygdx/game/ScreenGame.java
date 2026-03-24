package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {
    MyGdxGame myGdxGame;
    Texture birdTexture;
    Bird bird;
    int tubeCount = 3;
    Tube[] tubes;
    boolean isGameOver;
    int gamePoints = 0;
    PointCounter pointCounter;
    final int pointCounterMarginTop = 34;
    final int pointCounterMarginRight = 200;


    ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        birdTexture = new Texture("birdTiles/bird0.png");
        bird = new Bird(100, 300, birdTexture, 5);
        initTubes();
    }

    public void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }

    @Override
    public void show() {
        isGameOver = false;
        pointCounter = new PointCounter(MyGdxGame.SCR_WIDTH - pointCounterMarginRight, MyGdxGame.SCR_HEIGHT - pointCounterMarginTop);
    }


    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }

        bird.fly();
        bird.fly();
        if (!bird.isInField()) {
            System.out.println("not in field");
            isGameOver = true;
        }
        for (Tube next: tubes){
            next.move();
            if (next.isHit(bird)) {
                System.out.println("hit");
                isGameOver = true;
            } else if (next.needAddPoint(bird)) {
                Tube.isPointReceived = true;
                gamePoints += 1;
                System.out.println(gamePoints);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.move();
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch,gamePoints);
        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bird.dispose();
        birdTexture.dispose();
    }
}
