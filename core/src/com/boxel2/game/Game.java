package com.boxel2.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game {

    private MovingBackground background;

    public Game(){
        background = new MovingBackground();
    }
    public void draw(SpriteBatch batch){
        background.draw(batch);
    }
    public void update(){
        background.update();
    }
    public void down(int x, int y){
        background.down(x,y);
    }
    public void move(int x, int y){
        background.move(x,y);
    }
    public void up(int x, int y){ background.up(x,y);}
    public void hover(int x, int y){ }
    public void spaceBar(){
        //background.rotate(0);
    }
    public void dispose(){
        background.dispose();
    }
}
