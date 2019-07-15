package deck;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Card {

	private String number, title, description, name;
	private boolean turned = false, selected = false;
	private int width, height;

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
		if(turned){
			g.setColor(Color.BLACK);
			Font font = new Font("", Font.BOLD, width / 18);
			g.setFont(font);
			g.drawString(number, x + (7*width/8), y + height / 12);
			g.drawString(number, x + width / 12, y + height - height / 15);
			g.drawString(name, x + width / 12, y + height / 12);
			g.drawString(name, x + (7*width/13), y + height - height / 15);
			g.drawString(title, x + width / 6, y + height / 3);
			String[] descParts = description.split(" ");
			String temp = " " + descParts[0];
			for(int i = 1; i < descParts.length; i++){
				if(g.getFontMetrics().stringWidth(temp) < 4 * width / 5){
					temp = temp + " " + descParts[i];
				}else{
					temp = temp + " " + descParts[i];
					g.drawString(temp, x, y + height + (i * 3));
					temp = "";
				}
			}
			g.drawString(temp, x, y + height + ((descParts.length + 2) * 3));
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

}
