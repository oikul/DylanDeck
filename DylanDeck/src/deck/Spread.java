package deck;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import handlers.SoundHandler;

public class Spread {

	public static enum spreadType {
		ONE_CARD, THREE_CARD, THE_HAT
	}

	private Card deck[], selected[];
	private Point positions[];
	private int width = 256, height = 320;
	private Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
	private Random random = new Random();
	private float shuffleTime = 0, maxTime = 50;
	private int shuffleCount = 5, deckClicked = 0;
	private Image back = ResourceHandler.getImage("back_twist"), background = ResourceHandler.getImage("background");
	private SoundHandler shuffle =  new SoundHandler("shuffle.wav");
	private spreadType type;

	public Spread(spreadType type) {
		shuffle.play();
		this.type = type;
		loadCards();
		switch (type) {
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
			while (selected[1].isSelected()) {
				selected[1] = deck[random.nextInt(deck.length)];
			}
			selected[1].setSelected(true);
			selected[2] = deck[random.nextInt(deck.length)];
			while (selected[2].isSelected()) {
				selected[2] = deck[random.nextInt(deck.length)];
			}
			selected[2].setSelected(true);
			positions = new Point[3];
			positions[0] = new Point(1 * screenDims.width / 4 - width / 2, screenDims.height / 2);
			positions[1] = new Point(2 * screenDims.width / 4 - width / 2, screenDims.height / 2);
			positions[2] = new Point(3 * screenDims.width / 4 - width / 2, screenDims.height / 2);
			break;
		case THE_HAT:
			selected = new Card[6];
			selected[0] = deck[random.nextInt(deck.length)];
			selected[0].setSelected(true);
			selected[1] = deck[random.nextInt(deck.length)];
			while (selected[1].isSelected()) {
				selected[1] = deck[random.nextInt(deck.length)];
			}
			selected[1].setSelected(true);
			selected[2] = deck[random.nextInt(deck.length)];
			while (selected[2].isSelected()) {
				selected[2] = deck[random.nextInt(deck.length)];
			}
			selected[2].setSelected(true);
			selected[3] = deck[random.nextInt(deck.length)];
			while (selected[3].isSelected()) {
				selected[3] = deck[random.nextInt(deck.length)];
			}
			selected[3].setSelected(true);
			selected[4] = deck[random.nextInt(deck.length)];
			while (selected[4].isSelected()) {
				selected[4] = deck[random.nextInt(deck.length)];
			}
			selected[4].setSelected(true);
			selected[5] = deck[random.nextInt(deck.length)];
			while (selected[5].isSelected()) {
				selected[5] = deck[random.nextInt(deck.length)];
			}
			selected[5].setSelected(true);
			positions = new Point[6];
			positions[0] = new Point(2 * screenDims.width / 5 - width / 2, screenDims.height / 6 - height / 4);
			positions[1] = new Point(3 * screenDims.width / 5 - width / 2, screenDims.height / 6 - height / 4);
			positions[2] = new Point(1 * screenDims.width / 5 - width / 2, 4 * screenDims.height / 6 - height / 4);
			positions[3] = new Point(2 * screenDims.width / 5 - width / 2, 4 * screenDims.height / 6 - height / 4);
			positions[4] = new Point(3 * screenDims.width / 5 - width / 2, 4 * screenDims.height / 6 - height / 4);
			positions[5] = new Point(4 * screenDims.width / 5 - width / 2, 4 * screenDims.height / 6 - height / 4);
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

	public boolean click(int x, int y) {
		if(new Rectangle(screenDims.width / 12, screenDims.height / 12, width, height).contains(x, y)){
			deckClicked++;
		}
		for (int i = 0; i < positions.length; i++) {
			Rectangle bounds = new Rectangle(positions[i].x, positions[i].y, width, height);
			if (bounds.contains(x, y)) {
				selected[i].setTurned(true);
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, screenDims.width, screenDims.height, null);
		if (shuffleCount > 0) {
			if (shuffleTime < maxTime) {
				if (shuffleTime > (maxTime/2)) {
					g.drawImage(back, screenDims.width / 2 - width / 2, screenDims.height / 2 - height / 2, width,
							height, null);
					g.drawImage(back,
							screenDims.width / 2 - width / 2 + width / 20 + (int) ((maxTime/2) / 100.0 * (width / 3))
									- (int) ((shuffleTime - (maxTime/2)) / 100.0 * (width / 3)),
							screenDims.height / 2 - height / 2 + height / 20, width, height, null);
					g.drawImage(back,
							screenDims.width / 2 - width / 2 + 2 * width / 20 + (int) ((maxTime/2) / 100.0 * (2 * width / 3))
									- (int) ((shuffleTime - (maxTime/2)) / 100.0 * (2 * width / 3)),
							screenDims.height / 2 - height / 2 + 2 * height / 20, width, height, null);
					g.drawImage(back,
							screenDims.width / 2 - width / 2 + 3 * width / 20 + (int) ((maxTime/2) / 100.0 * width)
									- (int) ((shuffleTime - (maxTime/2)) / 100.0 * width),
							screenDims.height / 2 - height / 2 + 3 * height / 20, width, height, null);
				} else {
					g.drawImage(back,
							screenDims.width / 2 - width / 2 + 3 * width / 20 + (int) (shuffleTime / 100.0 * width),
							screenDims.height / 2 - height / 2 + 3 * height / 20, width, height, null);
					g.drawImage(back,
							screenDims.width / 2 - width / 2 + 2 * width / 20
									+ (int) (shuffleTime / 100.0 * (2 * width / 3)),
							screenDims.height / 2 - height / 2 + 2 * height / 20, width, height, null);
					g.drawImage(back,
							screenDims.width / 2 - width / 2 + width / 20 + (int) (shuffleTime / 100.0 * (width / 3)),
							screenDims.height / 2 - height / 2 + height / 20, width, height, null);
					g.drawImage(back, screenDims.width / 2 - width / 2, screenDims.height / 2 - height / 2, width,
							height, null);
				}
				shuffleTime += 5;
			} else {
				shuffleCount--;
				shuffleTime = 0;
			}
		} else {
			g.drawImage(back, screenDims.width / 12 + 3*width/30, screenDims.height / 12 + 3* height/30, width, height, null);
			g.drawImage(back, screenDims.width / 12 + 2*width/30, screenDims.height / 12 + 2*height/30, width, height, null);
			g.drawImage(back, screenDims.width / 12 + width/30, screenDims.height / 12 + height/30, width, height, null);
			g.drawImage(back, screenDims.width / 12, screenDims.height / 12, width, height, null);
			g.setColor(Color.WHITE);
			Font font = new Font("", Font.BOLD, width / 16);
			g.setFont(font);
			switch(type){
			case ONE_CARD:
				g.drawString("SONG OF THE DAY", positions[0].x + width / 20, positions[0].y - height / 20);
				break;
			case THREE_CARD:
				g.drawString("FOUNDATION", positions[0].x + width / 20, positions[0].y - height / 20);
				g.drawString("CURRENT POSITION", positions[1].x + width / 20, positions[1].y - height / 20);
				g.drawString("OUTCOME", positions[2].x + width / 20, positions[2].y - height / 20);
				break;
			case THE_HAT:
				g.drawString("FEELINGS OF SELF", positions[0].x + width / 20, positions[0].y - height / 20);
				g.drawString("DESIRES", positions[1].x + width / 20, positions[1].y - height / 20);
				g.drawString("FEARS", positions[2].x + width / 20, positions[2].y - height / 20);
				g.drawString("FOR YOU", positions[3].x + width / 20, positions[3].y - height / 20);
				g.drawString("AGAINST YOU", positions[4].x + width / 20, positions[4].y - height / 20);
				g.drawString("FUTURE", positions[5].x + width / 20, positions[5].y - height / 20);
				break;
			default:
				break;
			}
			for (int i = 0; i < deckClicked; i++) {
				if(i < selected.length){
					selected[i].draw(g, positions[i].x, positions[i].y);
				}
			}
		}
	}

}
