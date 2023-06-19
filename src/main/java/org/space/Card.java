package org.space;

import org.space.helper.CustomAlert;
import org.space.helper.GameUtil;
import org.space.helper.database.entity.Cards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Card extends JButton implements ActionListener {
    private static final int SHAKE_MAGNITUDE = 10; // The amount of shaking (in pixels)
    private static final int SHAKE_DURATION = 500; // The duration of the animation (in milliseconds)
    private static final int SHAKE_DELAY = 20; // The delay between each shake update (in milliseconds)
    private int health,defense,attack;
    private List<Cards> deck;

    public Card(Cards icon, int x, int y) {
        setLayout(null);
        this.deck = deck;
        this.health = icon.getHealth();
        this.defense = icon.getDefense();
        this.attack = icon.getAttack();
        int width = 180;
        int height = 250;

        ImageIcon c = new ImageIcon("src/main/java/org/space/drawable/cards/"+icon.getName()+".png");
        Image image = c.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));

        setBounds(x, y, width, height);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        addActionListener(this);
        String labelText = "<html>" +
                "<h3 style='color:green'>Health:<br><b>" + icon.getHealth() + "</b></h3>" +
                "<h3 style='color:blue'>Defense:<br><b>" + icon.getDefense() + "</b></h3>"+
                "<h3 style='color:red'>Attack:<br><b>" + icon.getAttack() + "</b></h3>"+
                "</html>";
        setText(labelText);
        int fontSize = 20; // Desired font size
        Font font = getFont();
        Font newFont = font.deriveFont(Font.PLAIN, fontSize);
       setFont(newFont);

    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    private void startShakeAnimation() {
        final int shakeX = getLocation().x;
        final int shakeY = getLocation().y;

        Timer shakeTimer = new Timer(SHAKE_DELAY, new ActionListener() {
            private int shakeCount = 0;
            private boolean shakeDirection = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (shakeCount >= SHAKE_DURATION / SHAKE_DELAY) {
                    stopShakeAnimation();
                } else {
                    int offsetX = shakeDirection ? SHAKE_MAGNITUDE : -SHAKE_MAGNITUDE;
                    int offsetY = shakeDirection ? SHAKE_MAGNITUDE : -SHAKE_MAGNITUDE;

                    setLocation(shakeX + offsetX, shakeY + offsetY);
                    shakeDirection = !shakeDirection;
                    shakeCount++;
                }
            }
        });

        shakeTimer.start();
    }

    private void stopShakeAnimation() {
        setLocation(this.getX(), this.getY()); // Reset the location of the component
    }
    @Override
    public void actionPerformed(ActionEvent e) {
      if (Battle.turn==0){
          GameUtil.playOnce("src/main/java/org/space/drawable/equip.wav");
          Battle.turn =1;
          Battle.attack = attack;
      }else{
          GameUtil.playOnce("src/main/java/org/space/drawable/shoot.wav");
          if (Battle.player ==1){
              Battle.player = 2;
              GameUtil.turnDirection.setText("PLAYER 2 TURN");

          }else{
              Battle.player = 1;

              GameUtil.turnDirection.setText("PLAYER 1 TURN");

          }
          Battle.turn = 0;
          startShakeAnimation();
          int newHealth = (int) Math.round(health - Battle.attack + (this.defense));
          this.defense = defense-Battle.attack<=0?0:defense-Battle.attack;
          health= newHealth <= 0?0:newHealth;
          if (health ==0){
              Container parent = this.getParent();
              parent.remove(this);
              parent.revalidate();
              parent.repaint();
              if (parent instanceof Deck) {
                  Deck parentContainer = (Deck) parent;
                  List<Card>  cardleft = parentContainer.getCards();
                  cardleft.remove(this);
                  if (cardleft.size()==0){
                      new CustomAlert("VICTORY");
                  }

              }
          }

          String labelText = "<html>" +
                  "<h3 style='color:green'>Health:<br><b>" + this.getHealth() + "</b></h3>" +
                  "<h3 style='color:blue'>Defense:<br><b>" + this.getDefense() + "</b></h3>"+
                  "<h3 style='color:red'>Attack:<br><b>" + this.getAttack() + "</b></h3>"+
                  "</html>";
          setText(labelText);
      }
    }
}
