package com.boxel2.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Main implements ApplicationListener, InputProcessor {
    public static Audio a;
    public static Textures t;
    public static Font f;
    public int touchX;
    public int touchY;

    private Vector3 touchPos;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Game game;

    @Override
    public void create() {
        //initialize global variables
        a = new Audio();
        t = new Textures();
        f = new Font();

        // start the playback of the background music immediately
        a.rainMusic.setLooping(true);
        a.rainMusic.play();

        // create the camera and the SpriteBatch
        camera= new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        //set up input listener variables
        Gdx.input.setInputProcessor(this);
        touchPos = new Vector3();

        //create game object
        game = new Game();
    }

    @Override
    public void render() {
        update();
        draw();
    }

    public void draw(){
        //set up background color
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //tell the camera to update its matrices.
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        // draw everything!
        batch.begin();
        game.draw(batch);
        batch.end();
    }

    public void update(){
        //update the game
        game.update();
    }

    public void updateTouchInput(){ //this method sets the coordinate values starting at the bottom left of the scren
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        //set global touch variables
        touchX = (int)touchPos.x;
        touchY = (int)touchPos.y;
    }

    @Override
    public void dispose() {
        // dispose of all the native resources
        a.rainMusic.dispose();
        f.dispose();
        game.dispose();
        batch.dispose();
    }
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}

    @Override public boolean keyDown (int keycode) {
        if(keycode == Input.Keys.SPACE) game.spaceBar();
        return false;
    }
    @Override public boolean keyUp (int keycode) { return false; }
    @Override public boolean keyTyped (char character) { return false;  }
    @Override public boolean touchDown (int x, int y, int pointer, int button) {
        updateTouchInput();
        game.down(touchX, touchY);
        return false;
    }
    @Override public boolean touchDragged (int x, int y, int pointer) {
        updateTouchInput();
        game.move(touchX, touchY);
        return false;
    }
    @Override public boolean touchUp (int x, int y, int pointer, int button) {
        updateTouchInput();
        game.up(touchX, touchY);
        return false;
    }
    @Override public boolean mouseMoved(int x, int y){
        updateTouchInput();
        game.hover(touchX, touchY);
        return false;
    }
    @Override public boolean scrolled (int amount) { return false; }
}