package deck;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import handlers.ResourceHandler;

public class Card {
	
	protected String title, description, name;
	protected int number, x, y, width, height;
	protected BufferedImage image;

	public Card(String title, String description, String name, int number, String imagePath, int x, int y, int width, int height){
		this.setTitle(title);
		this.setDescription(description);
		this.setName(name);
		this.setNumber(number);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setImage(ResourceHandler.getBufferedImage(imagePath).getSubimage(x, y, width, height));
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public void draw(Graphics g, int x, int y, int scale){
		g.drawImage(image, x, y, width * scale, height * scale, null);
	}

}
