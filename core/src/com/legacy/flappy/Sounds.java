package com.legacy.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    public static Sound hitSound;
    public static Sound pointSound;
    public static Sound wingSound;

    public static void init() {
        hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_hit.wav"));
        pointSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_point.wav"));
        wingSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_wing.wav"));
    }

    public static void dispose() {
        hitSound.dispose();
        pointSound.dispose();
        wingSound.dispose();
    }
}
