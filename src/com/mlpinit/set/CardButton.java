package com.mlpinit.set;

import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

import java.net.URL;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardButton extends JButton {
    public Card card;
    private MainFrame mainFrame;
    private boolean selected;

    public CardButton(Card card, MainFrame frame) {
        this.mainFrame = frame;
        this.card = card;
        this.selected = false;
        this.setIcon(new ImageIcon(getLightImageURL()));
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selected = !selected;
                if (selected) {
                    addCard();
                } else {
                    removeCard();
                }
                updateIcon();
                if (mainFrame.selectedButtons.size() == 3) mainFrame.nextThreeCards();
            }
        });

    }

    public void updateIcon() {
        if (selected) {
            setIcon(new ImageIcon(getDarkImageURL()));
        } else {
            setIcon(new ImageIcon(getLightImageURL()));
        }
    }
    public void unselect() {
        this.selected = false;
    }

    public Card getCard() {
        return card;
    }

    private void addCard() {
        mainFrame.selectedButtons.add(this);
    }

    private void removeCard() {
        mainFrame.selectedButtons.remove(this);
    }

    private URL getLightImageURL() {
        return getClass().getResource(card.getLightImageLocation());
    }

    private URL getDarkImageURL() {
        return getClass().getResource(card.getDarkImageLocation());
    }

}
