package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import handlers.InputHandler;
import handlers.ResourceHandler;
import utils.AbstractMain;

public class Main extends AbstractMain {

	private static final long serialVersionUID = 1L;
	Image image = ResourceHandler.getImage("Ballad of a Thin Man");
	InputHandler input;

	@Override
	public void initialise() {
		running = true;
		this.defaultInit("The Dylan Deck");
		input = new InputHandler(this);
	}

	@Override
	public void update(float time) {
		if(input.isKeyDown(KeyEvent.VK_ESCAPE)){
			close();
		}
	}

	@Override
	public void draw() {
		Graphics offGraphics = offImage.getGraphics();
		offGraphics.setColor(Color.BLACK);
		offGraphics.fillRect(0, 0, width, height);
		offGraphics.setColor(Color.RED);
		offGraphics.drawImage(image, 100, 100, 256, 320, null);
		g.drawImage(offImage, 0, 0, null);
	}

	public void close() {
		running = false;
		this.dispose();
	}

	public static void main(String args[]) {
		Main main = new Main();
		main.run();
	}

}
