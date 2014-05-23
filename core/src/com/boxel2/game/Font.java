package com.boxel2.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Font {

    //private String message;
    private BitmapFont font;

    public Font(){
        font = new BitmapFont();
    }
    public void draw(String message, int x, int y, SpriteBatch batch){
        font.draw(batch, message, x, y);
    }
    public void dispose(){
        font.dispose();
    }
}
