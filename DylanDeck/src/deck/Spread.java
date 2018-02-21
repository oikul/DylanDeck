package deck;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import handlers.ResourceHandler;

public class Spread extends JPanel {

	private static final long serialVersionUID = 1L;
	protected String name;
	protected Card deck[];
	protected Point positions[];
	private Image border = ResourceHandler.getImage("twist_border"), back = ResourceHandler.getImage("back_twist");
	private int width = 256, height = 320;

	public Spread(String name) {
		this.name = name;
		loadCards();
	}

	private void loadCards() {
		List<String> cards = new ArrayList<String>();
		try {
			cards = ResourceHandler.getAllTextFromFile("cards");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		deck = new Card[cards.size()];
		positions = new Point[cards.size()];
		for (int i = 0; i < cards.size(); i++) {
			int endex = cards.get(i).indexOf(',');
			String number = cards.get(i).substring(0, endex);
			cards.set(i, cards.get(i).substring(endex + 2, cards.get(i).length()));
			endex = cards.get(i).indexOf(',');
			String name = cards.get(i).substring(0, endex);
			cards.set(i, cards.get(i).substring(endex + 3, cards.get(i).length()));
			endex = cards.get(i).indexOf('"');
			String title = cards.get(i).substring(0, endex);
			cards.set(i, cards.get(i).substring(endex + 4, cards.get(i).length()));
			endex = cards.get(i).indexOf('"');
			String description = cards.get(i).substring(0, endex);
			deck[i] = new Card(number, name, title, description);
			positions[i] = new Point(i * 50, 50);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Card[] getCards() {
		return deck;
	}

	public void setCards(Card[] cards) {
		this.deck = cards;
	}

	public void draw(Graphics g) {
		/*for (int i = 0; i < deck.length; i++) {
			if (deck[i].isTurned()) {
				g.drawImage(border, positions[i].x, positions[i].y, width, height, null);
				deck[i].draw(g, positions[i].x, positions[i].y);
			} else {
				g.drawImage(back, positions[i].x, positions[i].y, width, height, null);
			}
		}*/
		g.drawImage(border, positions[1].x, positions[1].y, width, height, null);
		deck[1].draw(g, positions[1].x, positions[1].y);
	}

}
