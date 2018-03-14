import java.util.LinkedList;
import java.util.List;

public class Hand{
		  private LinkedList<Card> cardList;

		  public Hand(){
					 this.cardList = new LinkedList<Card>();
		  }
		  public void add(Card card){
					 this.cardList.add(card);
		  }
		  public void clear(){
					 this.cardList.clear();
		  }
		  public List<Integer> count(){
					 LinkedList<Integer> listRes = new LinkedList<Integer>();
					 int valCard, numRes, sizeList, valRes;
					 listRes.add(0, 0);
					 for(Card cardTmp : this.cardList){
								valCard = cardTmp.getPoints();
								sizeList = listRes.size();
								//								System.out.println("vc="+valCard + "s="+sizeList);
								for(numRes=0; numRes<sizeList; numRes++){
										  valRes =  listRes.get(numRes);
										  listRes.set(numRes,valRes+valCard);
										  if(valCard==1){
													 listRes.add(valRes+11);
										  }
								}
					 }
					 return listRes;
		  }
		  public int best(){
					 List<Integer> listRes = this.count();
					 int best = listRes.get(0);
					 int numRes;
					 for(numRes=1; numRes<listRes.size(); numRes++)
								if(listRes.get(numRes)>best && listRes.get(numRes)<=21)
										  best = listRes.get(numRes);
					 return best;

		  }
		  public List<Card> getCardList(){
					 return this.cardList;
		  }
		  @Override
					 public String toString(){
								String message;
								int numCard, numRes;
								message = "[";
								for(numCard=0; numCard<this.cardList.size()-1; numCard++)
										  message += this.cardList.get(numCard).getValueSymbole()+ this.cardList.get(numCard).getColorSymbole()+", ";
								if(numCard>0)
										  message +=  this.cardList.get(numCard).getValueSymbole()+ this.cardList.get(numCard).getColorSymbole();
								message +="] : [";
								List<Integer> listRes = this.count();
								for(numRes=0; numRes<listRes.size()-1; numRes++)
										  message += listRes.get(numRes) + ", ";
								message += listRes.get(numRes)+"]";
								return message;
					 }
}
