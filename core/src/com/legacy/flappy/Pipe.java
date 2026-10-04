package com.legacy.flappy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Pipe {
    public Sprite spriteUp;
    public Sprite spriteDown;
    public Rectangle colliderUp;
    public Rectangle colliderDown;

    float xPipe = 800;
    boolean isScored;

    public Pipe() {
        spriteUp = FlappyGame.textureAtlas.createSprite("pipe-up");
        spriteDown = FlappyGame.textureAtlas.createSprite("pipe-down");

        float yUpPipe = (float) (230 + (400 - 230) * Math.random());
        float yDownPipe = (yUpPipe - 120) - spriteDown.getHeight();

        spriteUp.setPosition(xPipe, yUpPipe);
        spriteDown.setPosition(xPipe, yDownPipe);

        colliderUp = new Rectangle(xPipe, yUpPipe, spriteUp.getWidth(), spriteUp.getHeight());
        colliderDown = new Rectangle(xPipe, yDownPipe, spriteDown.getWidth(), spriteDown.getHeight());
    }

    public void update(float deltaTime) {
        xPipe -= 120f * deltaTime;

        spriteUp.setX(xPipe);
        spriteDown.setX(xPipe);

        colliderUp.setX(xPipe);
        colliderDown.setX(xPipe);
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteUp.draw(spriteBatch);
        spriteDown.draw(spriteBatch);
    }

    public void drawCollider(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(colliderUp.x, colliderUp.y, colliderUp.width, colliderUp.height);
        shapeRenderer.rect(colliderDown.x, colliderDown.y, colliderDown.width, colliderDown.height);
    }
}