package com.boxel2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {

    public Texture background;

    public Textures(){
        // load the images
        background = new Texture(Gdx.files.internal("background.png"));
        //background.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Nearest);
    }
}
