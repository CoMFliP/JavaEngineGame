package com.comflip.engine.audio;

import java.io.*;

import javax.sound.sampled.*;

public class SoundClip {
	private Clip clip = null;
	private FloatControl gainControl;

	public SoundClip(String path) {
		try {
			AudioInputStream audioStream;
			clip = AudioSystem.getClip();
			audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
			clip.open(audioStream);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (clip == null) {
			return;
		}
		stop();
		clip.setFramePosition(0);
		while (!clip.isRunning()) {
			clip.start();
		}
	}

	private void stop() {
		if (clip.isRunning()) {
			clip.stop();
		}
	}

	public void close() {
		stop();
		clip.drain();
		clip.close();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		play();

	}

	public void setVolume(float value) {
		gainControl.setValue(value);
	}

	public boolean isRunning() {
		return clip.isRunning();
	}
}
