package com.legacy.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class FlappyGame extends ApplicationAdapter {
    public static final int WIDTH = 300, HEIGHT = 500;
    public AbstractScreen screen;
    public static TextureAtlas textureAtlas;
    public static BitmapFont font;

    @Override
    public void create() {
        textureAtlas = new TextureAtlas(Gdx.files.internal("images/atlas.txt"));
        Sounds.init();
        font = new BitmapFont();
        screen = new GameScreen();
    }

    @Override
    public void render() {
        screen.update();
        ScreenUtils.clear(0, 0, 0, 1);
        screen.draw();
    }

    @Override
    public void resize(int width, int height) {
        screen.resize(width, height);
    }

    @Override
    public void dispose() {
        screen.dispose();
        font.dispose();
        Sounds.dispose();
        textureAtlas.dispose();
    }
}
