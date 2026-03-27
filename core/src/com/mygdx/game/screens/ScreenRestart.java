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

    public ScreenRestart(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonRestart = new TextButton(100, 400, "Restart");
        background = new MovingBackground("backgrounds/restart_bg.png");
        System.out.println(buttonRestart.buttonWidth + "\n" + buttonRestart.buttonHeight);
    }
    public boolean isClickedRestart() {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);
            return buttonRestart.isHit((int) touch.x, (int) touch.y);
        }
        return false;
    }

    @Override
    public void show() {
        ScreenGame.isGameOver = false;
        pointCounter = new PointCounter(750, 530);
    }

    @Override
    public void render(float delta) {
        if (isClickedRestart()) {
            myGdxGame.setScreen(new ScreenGame(myGdxGame));
            ScreenGame.gamePoints = 0;
        }
        ScreenUtils.clear(0.1f, 0.1f, 0.5f, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        buttonRestart.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch,ScreenGame.gamePoints);
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
        buttonRestart.dispose();
        background.dispose();
        buttonRestart.dispose();
    }
}
