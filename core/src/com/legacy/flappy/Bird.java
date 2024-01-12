package com.legacy.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Bird {
    public Animation<TextureRegion> birdAnimation;
    public Rectangle collider;
    private float rotationAngle;
    float stateTime = 0f;

    public Bird() {
        birdAnimation = new Animation<TextureRegion>(0.05f, FlappyGame.textureAtlas.findRegions("bird"), Animation.PlayMode.LOOP);
        collider = FlappyGame.textureAtlas.createSprite("bird", 1).getBoundingRectangle();
        collider.setPosition((float) FlappyGame.WIDTH * 1 / 3, (float) FlappyGame.HEIGHT / 2);
    }

    public void draw(SpriteBatch spriteBatch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = birdAnimation.getKeyFrame(stateTime);
        spriteBatch.draw(currentFrame, collider.getX(), collider.getY(), (float) currentFrame.getRegionWidth() / 2, (float) currentFrame.getRegionHeight() / 2, currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), 1f, 1f, rotationAngle);
    }

    public void drawCollider(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(collider.x, collider.y, collider.width, collider.height);
    }

    float vy = 0; //Velocity of y
    float vyMin = -540f;
    float vyMax = 360f;
    float gravity = -1440f;
    float rotationAngleMax = 30f;

    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched()) {
            Sounds.wingSound.play();
            if (collider.y < FlappyGame.HEIGHT) {
                vy = vyMax;
            }
            //reset rotation
            rotationAngle = rotationAngleMax;
        }
        updatePhysics(deltaTime);
    }

    public void updatePhysics(float deltaTime) {
        if (vy > vyMin) {
            vy += gravity * deltaTime;
        }

        collider.y += vy * deltaTime;

        if (rotationAngle > rotationAngleMax) {
            rotationAngle = rotationAngleMax;
        }
        if (rotationAngle <= rotationAngleMax && rotationAngle >= -90f) {
            //rotate at vy
            rotationAngle += vy / 2 * deltaTime;
        }
    }
}
