package org.space.helper;

import org.space.Card;
import org.space.helper.database.entity.Cards;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GameUtil {
    public static JLabel turnDirection  = new JLabel("FIGHT");
    public static Clip clip;
    public GameUtil() {

    }
    public static Cards randomize(List<Cards> cardsList){
        if (cardsList != null && !cardsList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(cardsList.size());
            Cards randomCard = cardsList.remove(randomIndex);
            return randomCard;
        } else {
            return null;
        }
    }

    public static void playMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playOnce(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void stopMusic() {
        if (clip != null && clip.isActive()) {
            clip.stop();
            clip.setFramePosition(0);
            clip.close();
        }
    }
    public static Card getRandomCard(List<Card> cards) {
        if (cards.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(cards.size());
        return cards.get(randomIndex);
    }

    public static void setTurnDirection(String x){
        turnDirection = new JLabel(x);
    }
}
