package PJ4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java .awt.event.*;
import java.util.List;

public class GuiGame {
    
    private JFrame f;
    private JPanel menu;
    private JPanel game;
    private JButton Start, Exit;
    private JButton b1;
    private JButton b2;
    private JLabel lab;
    
    private JButton c1;
    private JButton c2;
    private JButton c3;
    private JButton c4;
    private JButton c5;
    
    
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    
    private static final int[] multipliers={1,2,3,5,6,9,25,50,250};
    private static final String[] goodHandTypes={ 
	  "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    
    private static final Decks oneDeck = new Decks(1);

      
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;
    
    public GuiGame(){
        game();
    }
    
    public void game(){
        playerBalance = startingBalance;
        
        f = new JFrame("Poker");
        f.setVisible(true);
        f.setSize(600,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        
        menu = new JPanel(new GridBagLayout());
        menu.setBackground(Color.LIGHT_GRAY);
        
        Start = new JButton("Play");
        Exit = new JButton("Exit");
        lab = new JLabel("P  O  K  E  R");
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets (10,10,10,10);
        
        menu.add(lab);
        c.gridx=0;
        c.gridy=1;
        menu.add(Start,c);
        c.gridx=0;
        c.gridy=2;
        menu.add(Exit,c);
        
        f.getContentPane().add(menu);
        
        Start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                f.getContentPane().removeAll();
                f.getContentPane().add(game);
                f.revalidate(); 
            }
        });
        
        
        game = new JPanel(new GridBagLayout());
        game.setBackground(Color.GRAY);
        
        
        b1 = new JButton("Bet");
        b2 = new JButton("Deal");
        
        c1 = new JButton();
        c2 = new JButton();
        c3 = new JButton();
        c4 = new JButton();
        c5 = new JButton();
        
        c.gridx=0;
        game.add(c1,c);
        c.gridx=1;
        game.add(c2,c);
        c.gridx=2;
        game.add(c3,c);
        c.gridx=3;
        game.add(c4,c);
        c.gridx=4;
        game.add(c5,c);
        JButton button = new JButton();
        
        c1.setIcon(new ImageIcon(this.getClass().getResource("Pics/Clubs 2.JPG")));
        c1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                c1.setIcon(new ImageIcon(this.getClass().getResource("Pics/Back.JPG")));
            }
        });
        c1.setBorder(null);
        //c1.setMargin(new Insets(0,0,0,0));
        
        c2.setIcon(new ImageIcon(this.getClass().getResource("Pics/Clubs 10.JPG")));
        c2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                c2.setIcon(new ImageIcon(this.getClass().getResource("Pics/Back.JPG")));
            }
        });
        c2.setBorder(null);
        
        c3.setIcon(new ImageIcon(this.getClass().getResource("Pics/Clubs 10.JPG")));
        c3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                c3.setIcon(new ImageIcon(this.getClass().getResource("Pics/Back.JPG")));
            }
        });
        c3.setBorder(null);
        
        c4.setIcon(new ImageIcon(this.getClass().getResource("Pics/Clubs 10.JPG")));
        c4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                c4.setIcon(new ImageIcon(this.getClass().getResource("Pics/Back.JPG")));
            }
        });
        c4.setBorder(null);
        
        c5.setIcon(new ImageIcon(this.getClass().getResource("Pics/Clubs 10.JPG")));
        c5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                c5.setIcon(new ImageIcon(this.getClass().getResource("Pics/Back.JPG")));
            }
        });
        c5.setBorder(null);
        
    }
    
    public static void main(String[] args){
        
        new GuiGame();
        
    }
}
