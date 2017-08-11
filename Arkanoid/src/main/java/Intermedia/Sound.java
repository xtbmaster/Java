package Intermedia;

import java.io.*;

import javax.sound.sampled.*;


public class Sound {

    private static AudioInputStream inputStream = null;
    private Clip clip;


    public Sound(SoundState ss, boolean loop){
        playSound(ss, loop);
    }

    public void playSound(SoundState ss, boolean loop) {

        try {
            InputStream f = Sound.class.getClassLoader().getResourceAsStream("sound/" + ss.toString() + ".wav");
            inputStream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(inputStream);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else
                clip.start();

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {

            e.printStackTrace();
        }
    }

    public void stopSound(){
        clip.stop();
    }


    public enum SoundState {
        MENU ("menu"), GAME ("game"), SHOT ("shot"), HIT ("hit"), CRASH ("crash"), NEWWAVE ("newWave"),
        GAMEOVER ("gameOver"), EXPLOSION ("explosion"), AID ("aid");

        private final String music;

        SoundState(String music){
            this.music = music;
        }

        @Override
        public String toString(){
            return music;
        }
    }
}
