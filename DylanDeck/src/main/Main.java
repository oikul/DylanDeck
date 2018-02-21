package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import deck.Spread;
import handlers.InputHandler;
import utils.AbstractMain;

public class Main extends AbstractMain {

	private static final long serialVersionUID = 1L;
	private InputHandler input;
	private Spread spread;

	@Override
	public void initialise() {
		running = true;
		this.defaultInit("The Dylan Deck");
		input = new InputHandler(this);
		spread = new Spread("One Card");
	}

	@Override
	public void update(float time) {
		if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			close();
		}
		if(input.isMouseDown(MouseEvent.BUTTON1)) {
			
		}
	}

	@Override
	public void draw() {
		Graphics offGraphics = offImage.getGraphics();
		spread.draw(offGraphics);
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
