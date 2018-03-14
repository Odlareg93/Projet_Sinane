import java.util.LinkedList;
import java.util.Collections;

public class Deck{

		  private LinkedList<Card> cardList;

		  public Deck(int nbBox){
					 this.cardList = new LinkedList<Card>();
					 int numBox;
					 Color[] tabColors = Color.values();
					 Value[] tabValues = Value.values();
					 for(numBox=0; numBox<nbBox; numBox++)
								for(Color colorTmp : tabColors)
										  for(Value valueTmp : tabValues)
													 this.cardList.add(new Card(valueTmp, colorTmp));
					 //shuffle
					 Collections.shuffle(this.cardList);
		  }
		  public Card draw() throws EmptyDeckException{
					 Card card = this.cardList.pollFirst();
					 if(card == null)
								throw new EmptyDeckException("Empty deck!!");
					 return card;
		  }
		  @Override
					 public String toString(){
								String message = "[";
								int numCard;
								for(numCard=0; numCard<this.cardList.size()-1; numCard++)
										  message += cardList.get(numCard)+", ";
								message+= cardList.get(numCard)+"]";
								return message;
					 }

}
