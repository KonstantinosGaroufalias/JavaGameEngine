package org.ict.GameBoard;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager implements GameSoundManager {
    private Clip backgroundAudio;
    private Clip backgroundAudio2;
    private Clip diceRollSound;
    private Clip winSound;

    public SoundManager() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File diceRollSoundFile = new File("DiceRoll.wav");
        File winSoundFile = new File("WinSound.wav");
        File backgroundAudioFile = new File("BackroundAudio.wav");
        File backgroundAudioFile2 = new File("BackroundAudio2.wav");

        diceRollSound = AudioSystem.getClip();
        winSound = AudioSystem.getClip();
        backgroundAudio = AudioSystem.getClip();
        backgroundAudio2 = AudioSystem.getClip();

        diceRollSound.open(AudioSystem.getAudioInputStream(diceRollSoundFile));
        winSound.open(AudioSystem.getAudioInputStream(winSoundFile));
        backgroundAudio.open(AudioSystem.getAudioInputStream(backgroundAudioFile));
        backgroundAudio2.open(AudioSystem.getAudioInputStream(backgroundAudioFile2));
    }
    @Override
    public void playDiceRollSound() {
        diceRollSound.setFramePosition(0);
        diceRollSound.start();
    }
    @Override
    public void playWinSound() {
        winSound.setFramePosition(0);
        winSound.start();
    }


    public void playBackgroundAudio() {
        backgroundAudio.loop(Clip.LOOP_CONTINUOUSLY);
        backgroundAudio.start();
    }
    public void playBackgroundAudio2() {
        backgroundAudio2.loop(Clip.LOOP_CONTINUOUSLY);
        backgroundAudio2.start();
    }
}