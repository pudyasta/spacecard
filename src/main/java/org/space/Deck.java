package org.space;

import org.space.helper.database.entity.Cards;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Deck extends JPanel {
    private List<Cards> kartu;
    private JButton button;
    private List<Card> cards = new ArrayList<>();
    protected JPanel deckPanel = new JPanel();
    protected JButton selectedCardButton;

    private int totalHealth=0;
    public Deck(List<Cards> kartu) {

        this.kartu =kartu;
        setOpaque(false);

        for (Cards card : kartu) {
            JButton button = new JButton(card.getName());
            deckPanel.add(button);
        }

        for (int i = 0; i < kartu.size(); i++) {
            int y = i%2 != 0 ?600:700;
            int x = 900 + 200*i;
            Card pp = new Card(kartu.get(i),x,y);
            cards.add(pp);
            add(pp);
        }


    }
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public Card getRandomCard() {
        int index = (int) (Math.random() * cards.size());
        return cards.get(index);
    }
    public void disableCardButtons(Deck a) {
        Component[] components = a.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setEnabled(false);
            }
        }
    }
    public void enableCardButtons(Deck b) {
        Component[] components = b.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setEnabled(true);
            }
        }
    }

    public List<Card> getCards(){
        return cards;
    }
}

