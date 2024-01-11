package com.legacy.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    float x, speed = 90f;
    Sprite sprite;

    public Background() {
        sprite = FlappyGame.textureAtlas.createSprite("bg");
    }
    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < 3; i++) {
            spriteBatch.draw(sprite, sprite.getWidth() * i - x, 0);
        }
    }

    public void update(float deltaTime) {
        x += speed * deltaTime;
        if (x >= 288f) {
            x = 0;
        }
    }
}
