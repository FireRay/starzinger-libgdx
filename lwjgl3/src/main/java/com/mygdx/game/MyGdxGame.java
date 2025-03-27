package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture starzingerImage;

    @Override
    public void create () {
        batch = new SpriteBatch();
        starzingerImage = new Texture("starzinger-ii-850-1.jpg"); // use the exact file name
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(starzingerImage, 50, 50); // (x, y) position on screen
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        starzingerImage.dispose();
    }
}
