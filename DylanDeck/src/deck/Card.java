package deck;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import main.ResourceHandler;

public class Card {

	private String number, title, description, name;
	private boolean turned = false, selected = false, clicked = false;
	private int width, height;
	private Image border = ResourceHandler.getImage("twist_border"), back = ResourceHandler.getImage("back_twist"), popup = ResourceHandler.getImage("popup_border");

	public Card(String number, String name, String title, String description, int width, int height) {
		this.setTitle(title);
		this.setDescription(description);
		this.setName(name);
		this.setNumber(number);
		this.width = width;
		this.height = height;
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
		if (turned) {
			g.setColor(Color.BLACK);
			Font font = new Font("", Font.BOLD, width / 14);
			g.setFont(font);
			g.drawImage(border, x, y, width, height, null);
			g.drawString(number, x + (7 * width / 8) - g.getFontMetrics().stringWidth(number), y + height / 12);
			g.drawString(number, x + width / 12, y + height - height / 15);
			g.drawString(name, x + width / 12, y + height / 12);
			g.drawString(name, x + (7 * width / 8) - g.getFontMetrics().stringWidth(name), y + height - height / 15);

			String[] titleParts = title.split(" ");
			String temp = titleParts[0];
			int count = 1;
			for (int i = 1; i < titleParts.length; i++) {
				if (g.getFontMetrics().stringWidth(temp) < 3 * width / 8) {
					temp = temp + " " + titleParts[i];
				} else {
					temp = temp + " " + titleParts[i];
					g.drawString(temp, x + width / 7, y + height / 3 + g.getFontMetrics().getHeight() * count);
					temp = "";
					count++;
				}
			}
			g.drawString(temp, x + width / 7, y + height / 3 + g.getFontMetrics().getHeight() * count);
			if (clicked) {
				Rectangle popup = new Rectangle(x, y, width, height);
				g.setColor(Color.white);
				g.drawImage(this.popup, popup.x, popup.y, popup.width, popup.height, null);
				String[] descParts = description.split(" ");
				temp = descParts[0];
				count = 1;
				g.setColor(Color.black);
				for (int i = 1; i < descParts.length; i++) {
					if (g.getFontMetrics().stringWidth(temp) + g.getFontMetrics().stringWidth(" " + descParts[i]) < popup.width) {
						temp = temp + " " + descParts[i];
					} else {
						g.drawString(" " + temp, x, y + popup.height / 3 + g.getFontMetrics().getHeight() * count);
						temp = descParts[i];
						count++;
					}
				}
				g.drawString(" " + temp, x, y + popup.height / 3 + g.getFontMetrics().getHeight() * count);
			}
		} else {
			g.drawImage(back, x, y, width, height, null);
		}
	}

	public boolean isTurned() {
		return turned;
	}

	public void setTurned(boolean turned) {
		this.turned = turned;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

}
