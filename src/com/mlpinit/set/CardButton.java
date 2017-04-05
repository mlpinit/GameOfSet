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
        this.setIcon(new ImageIcon(getImageURL()));
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.deck.updateSelectionStatus(card);
                setIcon(new ImageIcon(getImageURL()));
                mainFrame.nextThreeCards();
            }
        });
    }

    private URL getImageURL() {
        return getClass().getResource(
                card.getImageLocation(mainFrame.deck.isSelected(card)));
    }
}
