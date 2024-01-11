package com.legacy.flappy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen {
    public abstract void draw();

    public abstract void update();

    public abstract void resize(int width, int height);

    public abstract void dispose();
}
