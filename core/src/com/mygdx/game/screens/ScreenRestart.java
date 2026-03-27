package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.characters.TextButton;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.PointCounter;


public class ScreenRestart implements Screen {
    MyGdxGame myGdxGame;
    MovingBackground background;
    TextButton buttonRestart;
    PointCounter pointCounter;
    int gamePoints;

    Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

    public ScreenRestart(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonRestart = new TextButton(100, 400, "Restart");
        background = new MovingBackground("backgrounds/restart_bg.png");
    }
    public boolean isClickRestart() {
        if (((int) touch.x <= 750 && (int) touch.x >= 100) && ((int) touch.y <= 530 && (int) touch.y >= 400)) {
            return true;
        }
        return false;
    }

    @Override
    public void show() {
        ScreenGame.isGameOver = false;
        pointCounter = new PointCounter(750, 530); //(MyGdxGame.SCR_WIDTH - ScreenGame.pointCounterMarginRight, MyGdxGame.SCR_HEIGHT - ScreenGame.pointCounterMarginTop);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.5f, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        buttonRestart.draw(myGdxGame.batch);
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
    }
}
