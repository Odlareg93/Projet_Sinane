import java.lang.System;
import java.util.List;
import java.util.LinkedList;

public class BlackJack{
		  private Deck deck;
		  private Hand playerHand;
		  private Hand bankHand;
		  public boolean gameFinished;

		  public void reset() throws EmptyDeckException{
					 this.playerHand.clear();
					 this.bankHand.clear();
					 this.bankHand.add(this.deck.draw());
					 this.playerHand.add(this.deck.draw());
					 this.playerHand.add(this.deck.draw());
					 this.gameFinished = false;
		  }

		  public BlackJack(){
					 this.deck = new Deck(4);
					 this.playerHand = new Hand();
					 this.bankHand = new Hand();
					 this.gameFinished = false;
					 try{
								this.reset();
					 }catch(EmptyDeckException ex){
								System.err.println(ex.getMessage());
								System.exit(0);
					 }
		  }
		  public String getPlayerHandString(){
					 return this.playerHand.toString();
		  }
		  public String getBankHandString(){
					 return this.bankHand.toString();
		  }
		  public int getPlayerBest(){
					 return this.playerHand.best();
		  }
		  public int getBankBest(){
					 return this.bankHand.best();
		  }
		  public boolean isPlayerWinner(){
					 if(this.gameFinished == true && this.playerHand.best()<=21 && (this.playerHand.best()>this.bankHand.best() || this.bankHand.best()>21))
								return true;
					 return false;
		  }
		  public boolean isBankWinner(){
					 if(this.gameFinished == true && this.bankHand.best()<=21 && (this.bankHand.best()>this.playerHand.best() || this.playerHand.best()>21))
								return true;
					 return false;
		  }
		  public boolean isGameFinished(){
					 return this.gameFinished;
		  }
		  public void playerDrawAnotherCard() throws EmptyDeckException{
					 if(this.gameFinished == false){
								this.playerHand.add(this.deck.draw());
								if(this.playerHand.best()>21)
										  this.gameFinished = true;
					 }
		  }
		  public void bankLastTurn() throws EmptyDeckException{
					 if(this.gameFinished == false && this.playerHand.best()<=21 && this.bankHand.best()<=21){
								while(this.playerHand.best()>this.bankHand.best()){
										  this.bankHand.add(this.deck.draw());
								}
					 }
					 this.gameFinished = true;
		  }
		  public List<Card> getPlayerCardList(){
					 List<Card> originalList = playerHand.getCardList();
					 LinkedList<Card> copyList = new LinkedList<Card>(originalList);
					 return copyList;
		  }
		  public List<Card> getBankCardList(){
					 List<Card> originalList = bankHand.getCardList();
					 return new LinkedList<Card>(originalList);
		  }
}
