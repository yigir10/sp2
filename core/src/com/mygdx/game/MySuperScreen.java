package com.mygdx.game;

public class MySuperScreen extends MyScreen {
    @Override
    public void create() {
        System.out.println("Load sprites");
    }

    @Override
    public void render() {
        System.out.println("Draw bird");
        System.out.println("Move bird");
    }
}
