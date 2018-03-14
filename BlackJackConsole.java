import java.util.Scanner;
import java.lang.String;
import java.lang.System;

public class BlackJackConsole {

		  public BlackJackConsole(){
					 System.out.println("Welcome to BlackJack table.");
					 /*					 Card[] tab = {new Card(Value.TWO, Color.HEART), new Card(Value.JACK, Color.SPADE)};
											 for(Card c : tab){
											 System.out.println("Card " + c + " has a value of "+c.getPoints()+" points.");
											 System.out.print("It's a ");
											 switch(c.getColorSymbole()){
											 case "❤" : System.out.print("heart");break;
											 case "\u2660" : System.out.print("spade");break;
											 case "\u2663" : System.out.print("club");break;
											 case "\u2666" : System.out.print("diamond");break;
											 }
											 if(c.getValueSymbole().matches("[JGK]"))
											 System.out.println(" and a face !");
											 else
											 System.out.println(" and not a face.");
											 }*/
					 /*					 Deck deck = new Deck(4);
											 System.out.println(deck);
											 Hand hand = new Hand();
											 for(int i=0; i<3; i++)
											 try{
											 hand.add(deck.draw());
											 }catch(EmptyDeckException ex){
											 System.err.println(ex.getMessage());
											 System.exit(-1);
											 }					 
											 System.out.println(hand);
											 System.out.println("Best score = " +hand.best());
											 hand.clear();
											 System.out.println(hand);
											 System.out.println("Best score = " +hand.best());*/
					 try{
								BlackJack bj = new BlackJack();
								bj.reset();
								System.out.println("Bank draw :" + bj.getBankHandString());
								System.out.println("Player draw :" + bj.getPlayerHandString());
								boolean anotherCard = true;
								Scanner scan = new Scanner(System.in);
								String choice;
								while (anotherCard){
										  System.out.println("Another card? [y/n]");
										  choice = scan.next();
										  if(choice.compareToIgnoreCase("y")==0){
													 bj.playerDrawAnotherCard();
													 System.out.println("Player draw :" + bj.getPlayerHandString());
										  }
										  if(choice.compareToIgnoreCase("n")==0)
													 anotherCard = false;
										  if(bj.gameFinished==true)
													 anotherCard = false;
								}
								System.out.println("Bank turn.");
								bj.bankLastTurn();
								System.out.println("Bank final draw :" + bj.getBankHandString());
								System.out.println("Player best score: "+bj.getPlayerBest()); 
								System.out.println("Bank best score: "+bj.getBankBest());
								if(bj.isBankWinner())
										  System.out.println("Loose!");
								if(bj.isPlayerWinner())
										  System.out.println("Win!");
								if(!bj.isBankWinner() && !bj.isPlayerWinner())
										  System.out.println("Egality!");

					 }catch(EmptyDeckException ex){
								System.err.println(ex.getMessage());
								System.exit(-1);
					 }
		  }
		  public static void main(String[] args){
					 BlackJackConsole bj=new BlackJackConsole();
		  }

}
