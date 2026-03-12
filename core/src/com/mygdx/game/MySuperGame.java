package com.mygdx.game;

public class MySuperGame {
    MyScreen screen;

    public void setScreen(MyScreen screen) {
        this.screen = screen;
    }
    public void renderScreen() {
        screen.create();
        while (true) {
            screen.render();
            Threard.sleep(20);
        }
    }
}