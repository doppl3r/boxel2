package com.boxel2.game;

import java.util.LinkedList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MovingBackground {
	
	private LinkedList<Layer> layers;
	
    public MovingBackground(){
    	layers = new LinkedList<Layer>();
    	layers.add(new Layer(0, 2.0, new SpriteSheet(Main.t.background,4,4,0))); //front
    	layers.add(new Layer(1, 1.5, new SpriteSheet(Main.t.background,4,4,0))); //simi-middle
    	layers.add(new Layer(2, 1.0, new SpriteSheet(Main.t.background,4,4,0))); //simi-back
    	layers.add(new Layer(3, 0, new SpriteSheet(Main.t.background,4,4,0))); //back
    	layers.get(3).sprite.resize(Main.width, Main.height); //resize background color
    	setBackgroundTheme(2);
    }
    public void draw(SpriteBatch batch) {
    	//set up background color
        //Gdx.gl.glClearColor(((float)bgR/255), ((float)bgG/255), ((float)bgB/255), 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
    	for (int i = layers.size()-1; i >= 0; i--){
    		layers.get(i).draw(batch,3); //draw three times
    	}
    }
    public void update(){
    	for (int i = 0; i < layers.size(); i++){
    		layers.get(i).update();
    	}
    }
    public void dispose(){
    	for (int i = 0; i < layers.size(); i++){
    		layers.get(i).dispose();
    	}
    }
    public void setBackgroundTheme(int theme){
    	int spriteColumns = layers.get(0).sprite.getHFrames();
    	for (int i = 0; i < layers.size(); i++){
    		layers.get(i).setAnimation((theme*spriteColumns)+i);
    	}
    }
    
    //background layer properties
    public class Layer {
    	double layerX;
    	double layerY;
    	double xRate;
    	SpriteSheet sprite;
    	LinkedList<Atmosphere> atmosphere;
    	
    	public Layer(int animation, double xRate, SpriteSheet sprite){
    		this.xRate = xRate;
    		this.sprite = sprite;

    		sprite.resize(640,320);
    		sprite.animate(animation);
    	}
    	public void draw(SpriteBatch batch, int amountX) {
    		for (int i = 0; i < amountX; i++){ //draw again to the right 'x' times
    			sprite.update(layerX+(i*sprite.getImageWidth()), layerY);
    			sprite.draw(batch);
    		}
        }
    	public void update(){
    		layerX -= xRate;
    		if (layerX < -(sprite.getImageWidth())) layerX %= -(sprite.getImageWidth());
    		sprite.update(layerX, layerY);
    	}
    	public void dispose(){ sprite.dispose(); }
    	public void setAnimation(int i){ sprite.animate(i); }
    	
    	//yay another subclass!
    	public class Atmosphere{
    		double x;
    		double y;
    		SpriteSheet sprite;
    		
    		public Atmosphere(){
    			//sprite = new SpriteSheet()
    		}
    	}
    }
}
