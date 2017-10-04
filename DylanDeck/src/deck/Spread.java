package deck;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class Spread extends JPanel {

	private static final long serialVersionUID = 1L;
	protected String name;
	protected Card cards[];
	protected Point positions[];

	public Spread(int numOfCards, String name) {
		cards = new Card[numOfCards];
		positions = new Point[numOfCards];
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	@Override
	public void paintComponent(Graphics g){
		for(int i = 0; i < cards.length; i++){
			cards[i].draw(g, positions[i].x, positions[i].y, 1);
		}
	}

}
