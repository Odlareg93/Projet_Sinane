import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Button;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.io.File;
import java.awt.Label;
import javax.swing.ImageIcon;
import java.io.FileNotFoundException;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.lang.System;

public class BlackJackGUI implements ActionListener{
		  private BlackJack bj;
		  private JPanel playerPanel;
		  private JPanel bankPanel;
		  private JButton anotherButton;
		  private JButton noMoreButton;
		  private JButton resetButton;

		  private void addToPanel(JPanel p, String token) throws FileNotFoundException{
					 File file = new File("./img/card_"+token+".png");
					 if(!file.exists()){
								throw new FileNotFoundException("Can't find "+file.getPath());
					 }
					 ImageIcon icon = new ImageIcon(file.getPath());
					 JLabel label = new JLabel(icon);
					 p.add(label);
		  }
		  public void updatePlayerPanel() throws FileNotFoundException{
					 this.playerPanel.removeAll();
					 String token;
					 for(Card cardTmp : this.bj.getPlayerCardList()){
								token = cardTmp.getColorName() + "_"+cardTmp.getValueSymbole();
								this.addToPanel(this.playerPanel, token);
					 }
					 this.playerPanel.add(new JLabel("Player Best "+this.bj.getPlayerBest()));
					 if(this.bj.getPlayerBest()==21)
								this.addToPanel(this.playerPanel, "blackjack");
					 if(this.bj.isGameFinished() == true){
								if(this.bj.isPlayerWinner() == true)
										  this.addToPanel(this.playerPanel, "winner");
								if(this.bj.isPlayerWinner() == false && this.bj.isBankWinner() == false  )
										  this.addToPanel(this.playerPanel, "draw");
								if(this.bj.isPlayerWinner() == false && this.bj.isBankWinner() == true  )
										  this.addToPanel(this.playerPanel, "looser");

					 }
					 this.playerPanel.updateUI();
		  }
		  public void updateBankPanel() throws FileNotFoundException{
					 this.bankPanel.removeAll();
					 String token;
					 for(Card cardTmp : this.bj.getBankCardList()){
								token = cardTmp.getColorName() + "_"+cardTmp.getValueSymbole();
								this.addToPanel(this.bankPanel, token);
					 }
					 this.bankPanel.add(new JLabel("Bank Best "+this.bj.getBankBest()));
					 if(this.bj.getBankBest()==21)
								this.addToPanel(this.bankPanel, "blackjack");
					 if(this.bj.isGameFinished() == true){
								if(this.bj.isBankWinner() == true)
										  this.addToPanel(this.bankPanel, "winner");
								if(this.bj.isBankWinner() == false && this.bj.isPlayerWinner() == false  )
										  this.addToPanel(this.bankPanel, "draw");
								if(this.bj.isBankWinner() == false && this.bj.isPlayerWinner() == true  )
										  this.addToPanel(this.bankPanel, "looser");
					 }
					 this.bankPanel.updateUI();

		  }
		  public BlackJackGUI(){
					 this.bj = new BlackJack();
					 JFrame frame = new JFrame();
					 frame.setMinimumSize(new Dimension(600, 480));
					 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					 frame.setLayout(new BorderLayout());
					 JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					 this.anotherButton = new JButton("Another Card");
					 this.anotherButton.setActionCommand("Another");
					 this.anotherButton.addActionListener(this);
					 topPanel.add(this.anotherButton);
					 this.noMoreButton = new JButton("No More Card");
					 this.noMoreButton.setActionCommand("NoMore");
					 this.noMoreButton.addActionListener(this);
					 topPanel.add(this.noMoreButton);
					 this.resetButton = new JButton("Reset");
					 this.resetButton.setActionCommand("Reset");
					 this.resetButton.addActionListener(this);
					 topPanel.add(this.resetButton);
					 frame.add(topPanel, BorderLayout.NORTH);
					 JPanel centerPanel = new JPanel(new GridLayout(2,1));
					 this.bankPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					 this.bankPanel.setBorder(BorderFactory.createTitledBorder("Bank"));
					 centerPanel.add(this.bankPanel);
					 this.playerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					 this.playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
					 centerPanel.add(this.playerPanel);
					 frame.add(centerPanel, BorderLayout.CENTER);
					 frame.setVisible(true);
					 try{
								this.updatePlayerPanel();
								this.updateBankPanel();
					 }catch(FileNotFoundException ex){
								JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
								System.exit(-1);
					 }
//					 this.frame.pack();
//					 this.frame.visible(true);
		  }
		  public void actionPerformed(ActionEvent e){
					 try{
								switch(e.getActionCommand()){
										  case "Another": bj.playerDrawAnotherCard();break;
										  case "NoMore": bj.bankLastTurn();break;
										  case "Reset": bj.reset();break;
								}
					 }catch(EmptyDeckException ex){
								JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
								System.exit(-1);
					 }
					 if(bj.isGameFinished()==true){
								this.anotherButton.setEnabled(false);
								this.noMoreButton.setEnabled(false);
					 }else{
								this.anotherButton.setEnabled(true);
								this.noMoreButton.setEnabled(true);
					 }
					 try{
								this.updatePlayerPanel();
								this.updateBankPanel();
					 } catch(FileNotFoundException ex){
								JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
								System.exit(-1);
					 }
		  }

		  public static void main(String[] args){
					 new BlackJackGUI();
		  }

}
