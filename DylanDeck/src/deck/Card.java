package deck;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Card {

	private String number, title, description, name;
	private boolean turned = true;
	private int width = 256, height = 320;

	public Card(String number, String name, String title, String description) {
		this.setTitle(title);
		this.setDescription(description);
		this.setName(name);
		this.setNumber(number);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void draw(Graphics g, int x, int y) {
		if(turned){
			g.setColor(Color.BLACK);
			g.setFont(new Font("", Font.BOLD, 15));
			g.drawString(number, x + (7*width/8), y + height / 12);
			g.drawString(name, x + width / 12, y + height / 12);
			g.drawString(title, x + width / 6, y + height / 4);
			g.drawString(description, x + width / 8, y + 2 * height / 5);
		}
	}

	public boolean isTurned() {
		return turned;
	}

	public void setTurned(boolean turned) {
		this.turned = turned;
	}

}
