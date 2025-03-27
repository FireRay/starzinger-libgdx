package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Sound;


public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture image;
	private float x, y;
	private Texture enemyImage;
	private float enemyX, enemyY;
	private Rectangle playerRect;
	private Rectangle enemyRect;
	private Sound collisionSound;


	@Override
	public void create() {
		batch = new SpriteBatch();
	    image = new Texture("starzinger-ii-850-1.jpg");
	    enemyImage = new Texture("starzinger_enemy.jpg");

	    // Start hero near bottom-left
	    x = 50;
	    y = 50;

	    // Center the enemy
	    enemyX = (Gdx.graphics.getWidth() - enemyImage.getWidth()) / 2f;
	    enemyY = (Gdx.graphics.getHeight() - enemyImage.getHeight()) / 2f;

	    playerRect = new Rectangle(x, y, image.getWidth(), image.getHeight());
	    enemyRect = new Rectangle(enemyX, enemyY, enemyImage.getWidth(), enemyImage.getHeight());
		
		collisionSound = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));

	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();

		// Movement
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= 600 * delta;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += 600 * delta;
		if (Gdx.input.isKeyPressed(Input.Keys.UP))    y += 600 * delta;
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))  y -= 600 * delta;

		// Boundaries
		if (x < 0) x = 0;
		if (x > screenWidth - image.getWidth()) x = screenWidth - image.getWidth();
		if (y < 0) y = 0;
		if (y > screenHeight - image.getHeight()) y = screenHeight - image.getHeight();

		// Always center enemy
		enemyX = (screenWidth - image.getWidth()) / 2f;
		enemyY = (screenHeight - image.getHeight()) / 2f;

		// Update rectangles for collision
		playerRect.set(x, y, image.getWidth(), image.getHeight());
		enemyRect.set(enemyX, enemyY, image.getWidth(), image.getHeight());

		// Collision check
		if (playerRect.overlaps(enemyRect)) {
			System.out.println("💥 Collision Detected!");
			collisionSound.play();

		}

		// ESC key: reset to windowed mode
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.graphics.setWindowedMode(800, 600);
		}

		// Drawing
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(enemyImage, enemyX, enemyY, image.getWidth(), image.getHeight());
		batch.draw(image, x, y);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		image.dispose();
		enemyImage.dispose();
		collisionSound.dispose();

	}
}
