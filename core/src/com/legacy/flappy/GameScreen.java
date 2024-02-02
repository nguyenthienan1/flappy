package com.legacy.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;

public class GameScreen extends AbstractScreen {
    public OrthographicCamera camera;
    public ExtendViewport viewport;
    public SpriteBatch spriteBatch = new SpriteBatch();
    public ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final Background bg = new Background();
    ArrayList<Pipe> pipes;
    private Bird bird = new Bird();
    public static int score = 0;

    public GameScreen() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(FlappyGame.WIDTH, FlappyGame.HEIGHT, FlappyGame.WIDTH, FlappyGame.HEIGHT, camera);
        pipes = new ArrayList<>();
        pipes.add(new Pipe());
    }

    @Override
    public void draw() {
        spriteBatch.begin();

        bg.draw(spriteBatch);
        bird.draw(spriteBatch);
        for (Pipe pipe : pipes) {
            pipe.draw(spriteBatch);
        }
        drawScore();
        FlappyGame.font.setColor(Color.BLACK);
        FlappyGame.font.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, FlappyGame.HEIGHT - 25);

        spriteBatch.end();

//        drawCollider();
    }

    private void drawCollider() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        bird.drawCollider(shapeRenderer);
        for (Pipe pipe : pipes) {
            pipe.drawCollider(shapeRenderer);
        }
        shapeRenderer.end();
    }


    public void drawScore() {
        FlappyGame.font.setColor(0, 0, 0, 1);
        FlappyGame.font.draw(spriteBatch, "SCORE: " + score, 10, FlappyGame.HEIGHT - 50);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    boolean gameOver;

    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        bg.update(deltaTime);
        bird.update(deltaTime);
        for (Pipe pipe : pipes) {
            pipe.update(deltaTime);
            if (bird.collider.overlaps(pipe.spriteUp.getBoundingRectangle()) || bird.collider.overlaps(pipe.spriteDown.getBoundingRectangle())) {
                gameOver();
                return;
            }
            if (!pipe.isScored && bird.collider.getX() > pipe.xPipe) {
                pipe.isScored = true;
                score++;
                Sounds.pointSound.play(0.5f);
            }
        }
        if (bird.collider.getY() <= 0) {
            gameOver();
            return;
        }
        Pipe firstPipe = pipes.get(0);
        if (firstPipe.xPipe <= -100) {
            pipes.remove(firstPipe);
        }
        Pipe lastPipe = pipes.get(pipes.size() - 1);
        if (lastPipe.xPipe <= 620) {
            Pipe pipe = new Pipe();
            pipes.add(pipe);
        }
    }

    public void gameOver() {
        score = 0;
        gameOver = true;
        pipes.clear();
        pipes.add(new Pipe());
        bird = new Bird();
        Sounds.hitSound.play();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
}
