package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import handlers.InputHandler;
import handlers.ResourceHandler;

public class MainMenu {

	private Image border = ResourceHandler.getImage("twist_border");
	private String title = "The Dylan Tarot Deck", oneCard = "ONE CARD", threeCard = "THREE CARD",
			theHat = "THE DYLAN HAT",
			blurb = "The Dylan Deck illuminates the images of conventional tarot with interpretations of Bob Dylan songs by Claire Dowie. Each tarot card has its own Bob Dylan song as an aid to divination.";
	private Point[] positions = new Point[5];
	private Dimension screenDims = InputHandler.screenSize;
	private Rectangle oneCardBox, threeCardBox, theHatBox;

	public MainMenu() {
		positions[0] = new Point(screenDims.width / 6, screenDims.height / 6);
		positions[1] = new Point(screenDims.width / 5, screenDims.height / 3);
		positions[2] = new Point(screenDims.width / 4, 3 * screenDims.height / 4);
		positions[3] = new Point(2 * screenDims.width / 4, 3 * screenDims.height / 4);
		positions[4] = new Point(3 * screenDims.width / 4, 3 * screenDims.height / 4);
		oneCardBox = new Rectangle(positions[2].x - screenDims.width / 12, positions[2].y - screenDims.height / 16,
				screenDims.width / 6, screenDims.height / 8);
		threeCardBox = new Rectangle(positions[3].x - screenDims.width / 12, positions[3].y - screenDims.height / 16,
				screenDims.width / 6, screenDims.height / 8);
		theHatBox = new Rectangle(positions[4].x - screenDims.width / 12, positions[4].y - screenDims.height / 16,
				screenDims.width / 6, screenDims.height / 8);
	}

	public int click(int x, int y) {
		Point point = new Point(x, y);
		if(oneCardBox.contains(point)){
			return 1;
		}else if(threeCardBox.contains(point)){
			return 2;
		}else if(theHatBox.contains(point)){
			return 3;
		}else{
			return 0;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		Font font = new Font("", Font.BOLD, 120);
		g.setFont(font);
		g.drawString(title, positions[0].x, positions[0].y);

		font = new Font("", Font.ITALIC, 40);
		g.setFont(font);
		String[] blurbParts = blurb.split(" ");
		String temp = blurbParts[0];
		int count = 0;
		for (int i = 1; i < blurbParts.length; i++) {
			if (g.getFontMetrics().stringWidth(temp) < 3 * screenDims.width / 6) {
				temp = temp + " " + blurbParts[i];
			} else {
				temp = temp + " " + blurbParts[i];
				g.drawString(temp, positions[1].x, positions[1].y + g.getFontMetrics().getHeight() * count);
				temp = "";
				count++;
			}
		}
		g.drawString(temp, positions[1].x, positions[1].y + g.getFontMetrics().getHeight() * count);

		g.setColor(Color.BLACK);
		font = new Font("", Font.PLAIN, 30);
		g.setFont(font);
		g.drawImage(border, oneCardBox.x, oneCardBox.y, oneCardBox.width, oneCardBox.height, null);
		g.drawImage(border, threeCardBox.x, threeCardBox.y, threeCardBox.width, threeCardBox.height, null);
		g.drawImage(border, theHatBox.x, theHatBox.y, theHatBox.width, theHatBox.height, null);
		g.drawString(oneCard, positions[2].x - screenDims.width / 26, positions[2].y + screenDims.height / 68);
		g.drawString(threeCard, positions[3].x - screenDims.width / 20, positions[3].y + screenDims.height / 68);
		g.drawString(theHat, positions[4].x - screenDims.width / 18, positions[4].y + screenDims.height / 68);
	}

}
