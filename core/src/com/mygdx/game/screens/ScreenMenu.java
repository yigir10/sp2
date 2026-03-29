package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.characters.TextButton;
import com.mygdx.game.components.MovingBackground;

public class ScreenMenu implements Screen {
    MyGdxGame myGdxGame;
    MovingBackground background;
    TextButton buttonStart;
    TextButton buttonExit;
    Sound soundButton;
    public ScreenMenu(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        soundButton = Gdx.audio.newSound(Gdx.files.internal("Sounds/Button.mp3"));
        buttonStart = new TextButton(100, 400, "Start");
        buttonExit = new TextButton(100,200, "Exit");
        background = new MovingBackground("backgrounds/restart_bg.png");
    }
    public boolean isClickedStart() {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);
            return buttonStart.isHit((int) touch.x, (int) touch.y);
        }
        return false;
    }
    public boolean isClickedExit() {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);
            return buttonExit.isHit((int) touch.x, (int) touch.y);
        }
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (isClickedStart()) {
            soundButton.play(0.1f);
            myGdxGame.setScreen(new ScreenGame(myGdxGame));
            ScreenGame.gamePoints = 0;
        } else if (isClickedExit()) {
            Gdx.app.exit();
        }
        ScreenUtils.clear(0.1f, 0.1f, 0.5f, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        buttonStart.draw(myGdxGame.batch);
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
        background.dispose();
        buttonStart.dispose();
        soundButton.dispose();
        buttonExit.dispose();
    }
}
