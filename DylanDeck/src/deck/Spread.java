package deck;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import handlers.ResourceHandler;

public class Spread {
	
	public static enum spreadType{
		ONE_CARD, THREE_CARD, THE_HAT
	}

	private Card deck[], selected[];
	private Point positions[];
	private Image border = ResourceHandler.getImage("twist_border"), back = ResourceHandler.getImage("back_twist");
	private int width = 256, height = 320;
	private Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
	private Random random = new Random();

	public Spread(spreadType type) {
		loadCards();
		switch(type){
		case ONE_CARD:
			selected = new Card[1];
			selected[0] = deck[random.nextInt(deck.length)];
			selected[0].setSelected(true);
			positions = new Point[1];
			positions[0] = new Point(screenDims.width / 2 - width / 2, screenDims.height / 2 - height / 2);
			break;
		case THREE_CARD:
			selected = new Card[3];
			selected[0] = deck[random.nextInt(deck.length)];
			selected[0].setSelected(true);
			selected[1] = deck[random.nextInt(deck.length)];
			while(selected[1].isSelected()){
				selected[1] = deck[random.nextInt(deck.length)];
			}
			selected[1].setSelected(true);
			selected[2] = deck[random.nextInt(deck.length)];
			while(selected[2].isSelected()){
				selected[2] = deck[random.nextInt(deck.length)];
			}
			selected[2].setSelected(true);
			positions = new Point[3];
			positions[0] = new Point(1 * screenDims.width / 4 - width / 2, screenDims.height / 2 - height / 2);
			positions[1] = new Point(2 * screenDims.width / 4 - width / 2, screenDims.height / 2 - height / 2);
			positions[2] = new Point(3 * screenDims.width / 4 - width / 2, screenDims.height / 2 - height / 2);
			break;
		case THE_HAT:
			selected = new Card[6];
			selected[0] = deck[random.nextInt(deck.length)];
			selected[0].setSelected(true);
			selected[1] = deck[random.nextInt(deck.length)];
			while(selected[1].isSelected()){
				selected[1] = deck[random.nextInt(deck.length)];
			}
			selected[1].setSelected(true);
			selected[2] = deck[random.nextInt(deck.length)];
			while(selected[2].isSelected()){
				selected[2] = deck[random.nextInt(deck.length)];
			}
			selected[2].setSelected(true);
			selected[3] = deck[random.nextInt(deck.length)];
			while(selected[3].isSelected()){
				selected[3] = deck[random.nextInt(deck.length)];
			}
			selected[3].setSelected(true);
			selected[4] = deck[random.nextInt(deck.length)];
			while(selected[4].isSelected()){
				selected[4] = deck[random.nextInt(deck.length)];
			}
			selected[4].setSelected(true);
			selected[5] = deck[random.nextInt(deck.length)];
			while(selected[5].isSelected()){
				selected[5] = deck[random.nextInt(deck.length)];
			}
			selected[5].setSelected(true);
			positions = new Point[6];
			positions[0] = new Point(2 * screenDims.width / 5 - width / 2, screenDims.height / 6 - height / 2);
			positions[1] = new Point(3 * screenDims.width / 5 - width / 2, screenDims.height / 6 - height / 2);
			positions[2] = new Point(1 * screenDims.width / 5 - width / 2, 4 * screenDims.height / 6 - height / 2);
			positions[3] = new Point(2 * screenDims.width / 5 - width / 2, 4 * screenDims.height / 6 - height / 2);
			positions[4] = new Point(3 * screenDims.width / 5 - width / 2, 4 * screenDims.height / 6 - height / 2);
			positions[5] = new Point(4 * screenDims.width / 5 - width / 2, 4 * screenDims.height / 6 - height / 2);
			break;
		default:
			break;
		}
	}

	private void loadCards() {
		List<String> cards = new ArrayList<String>();
		try {
			cards = ResourceHandler.getAllTextFromFile("cards");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		deck = new Card[cards.size()];
		for (int i = 0; i < cards.size(); i++) {
			String info[] = cards.get(i).split("=");
			String number = info[0];
			String name = info[1];
			String title = info[2];
			String description = info[3];
			deck[i] = new Card(number, name, title, description, width, height);
		}
	}

	public Card[] getCards() {
		return deck;
	}

	public void setCards(Card[] cards) {
		this.deck = cards;
	}
	
	public boolean click(int x, int y){
		for(int i = 0; i < positions.length; i++){
			Rectangle bounds = new Rectangle(positions[i].x, positions[i].y, width, height);
			if(bounds.contains(x, y)){
				selected[i].setTurned(true);
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		for(int i = 0; i < positions.length; i++){
			if (selected[i].isTurned()) {
				g.drawImage(border, positions[i].x, positions[i].y, width, height, null);
				selected[i].draw(g, positions[i].x, positions[i].y);
			} else {
				g.drawImage(back, positions[i].x, positions[i].y, width, height, null);
			}
		}
	}

}
