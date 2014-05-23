package com.boxel2.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MovingBackground {

    private double bgX;
    private double bgY;
    private SpriteSheet bg1;

    public MovingBackground(){
        bgX = 200;
        bgY = 200;
        bg1 = new SpriteSheet(Main.t.bg1, 1,4, 0);
        //bg1.center();
        //bg1.setPivot(0.5,0.5);
        bg1.resize(640,320);
        bg1.animate(2);
    }
    public void draw(SpriteBatch batch) {
        bg1.draw(batch);
    }
    public void update(){
        bg1.update(bgX,bgY);
    }
    public void dispose(){
        bg1.dispose();
    }
    public void down(int x, int y){
        bgX = x;
        bgY = y;
    }
    public void move(int x, int y){ down(x,y); bg1.rotate(x%360); }
    public void up(int x, int y){ }
}
