package com.boxel2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {

    public Texture bg1;
    public Texture bg2;
    public Texture bg3;

    public Textures(){
        // load the images for the droplet and the bucket, 64x64 pixels each
        bg1 = new Texture(Gdx.files.internal("bg1.png"));
        bg2 = new Texture(Gdx.files.internal("bg2.png"));
        bg3 = new Texture(Gdx.files.internal("bg3.png"));
    }
}
