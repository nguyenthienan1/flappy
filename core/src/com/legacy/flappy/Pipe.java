package com.legacy.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Pipe {
    Sprite spriteUp;
    Sprite spriteDown;
    float xPipe = 800;
    boolean isScored;

    public Pipe() {
        spriteUp = FlappyGame.textureAtlas.createSprite("pipe-up");
        spriteDown = FlappyGame.textureAtlas.createSprite("pipe-down");
        float yUpPipe = (float) (230 + (400 - 230) * Math.random());
        spriteUp.setPosition(xPipe, yUpPipe);
        float yDownPipe = (yUpPipe - 120) - spriteDown.getHeight();
        spriteDown.setPosition(xPipe, yDownPipe);
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteUp.draw(spriteBatch);
        spriteDown.draw(spriteBatch);
    }

    public void drawCollider(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(spriteUp.getBoundingRectangle().x, spriteUp.getBoundingRectangle().y, spriteUp.getBoundingRectangle().width, spriteUp.getBoundingRectangle().height);
        shapeRenderer.rect(spriteDown.getBoundingRectangle().x, spriteDown.getBoundingRectangle().y, spriteDown.getBoundingRectangle().width, spriteDown.getBoundingRectangle().height);
    }

    public void update(float deltaTime) {
        xPipe -= 120f * deltaTime;
        spriteUp.setX(xPipe);
        spriteDown.setX(xPipe);
    }
}
