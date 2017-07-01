package com.mlpinit.set;

import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Dimension;

public class CardButton extends JButton {
    public Card card;
    private MainFrame mainFrame;

    public CardButton(Card card, MainFrame frame) {
        this.mainFrame = frame;
        this.card = card;
        this.setIcon(new ImageIcon(card.getImageURL()));
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.toggleSelection();
                setIcon(new ImageIcon(card.getImageURL()));
                if (mainFrame.setBoard.isSetComplete()) {
                    mainFrame.nextThreeCards();
                }
            }
        });
    }
}
