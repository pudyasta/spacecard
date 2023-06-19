package org.space;

import org.space.helper.GameUtil;
import org.space.helper.database.entity.Cards;
import org.space.helper.database.repository.CardsRepository;
import org.space.helper.database.repository.CardsRepositoryImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battle extends JPanel {
    public static int turn = 0;
    public  static int player = 1;
    public static  int attack = 0;
    private List<Cards> allCards;
    private List<Cards> earthCards;
    private  List<Cards> deckP1 = new ArrayList<>();
    private  List<Cards> deckP2 = new ArrayList<>();
    private  List<Cards> stockP1 = new ArrayList<>();
    private  List<Cards> stockP2 = new ArrayList<>();
    private Image backgroundImage;
    private JPanel field;
private   Deck deckPEnemy;
    private   Deck deckPlayer;

    public Battle()   {

        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon("src/main/java/org/space/drawable/solar.png").getImage();
        CardsRepository cardsRepository = new CardsRepositoryImpl();
        earthCards = cardsRepository.findByPlanet("earth");
        allCards = cardsRepository.findAll();
        for (int i = 0; i <5; i++) {
            deckP1.add(GameUtil.randomize(earthCards));
            deckP2.add(GameUtil.randomize(allCards));
        }
        for (int i = 0; i <5; i++) {
            stockP1.add(GameUtil.randomize(earthCards));
            stockP2.add(GameUtil.randomize(allCards));
        }

        deckPEnemy = new Deck(deckP2);
        deckPlayer = new Deck(deckP1);

        GameUtil.turnDirection.setForeground(Color.WHITE);
        int fontSize = 32;
        Font font = getFont();
        Font newFont = font.deriveFont(Font.PLAIN, fontSize);
        GameUtil.turnDirection.setFont(newFont);
        GameUtil.turnDirection.setHorizontalAlignment(JLabel.CENTER);
        add(GameUtil.turnDirection,BorderLayout.CENTER);

        add(deckPEnemy, BorderLayout.NORTH);
        add(deckPlayer, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(2000, 1055));


    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public List<Cards> getPDeck1(){
        return deckP1;
    }

    public List<Cards> getPDeck2(){
        return deckP2;
    }
}
