package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import deck.Spread;
import deck.Spread.spreadType;
import handlers.InputHandler;
import handlers.ResourceHandler;
import menu.MainMenu;
import utils.AbstractMain;

public class Main extends AbstractMain {

	private static final long serialVersionUID = 1L;
	private InputHandler input;
	private Spread spread;
	private MainMenu menu;
	private boolean spreadSelected = false;
	private Dimension screenDims = InputHandler.screenSize;
	private Image background = ResourceHandler.getImage("background");

	@Override
	public void initialise() {
		running = true;
		spreadSelected = false;
		this.defaultInit("The Dylan Deck");
		input = new InputHandler(this);
		menu = new MainMenu();
	}

	@Override
	public void update(float time) {
		if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.out.println("escape pressed");
			close();
		}
		if (input.isMouseDown(MouseEvent.BUTTON1)) {
			Point mouse = input.getMousePositionOnScreen();
			if (spreadSelected) {
				if(spread.click(mouse.x, mouse.y) == 1){
					spreadSelected = false;
				}
			}else{
				int selection = menu.click(mouse.x, mouse.y);
				if(selection == 1){
					spread = new Spread(spreadType.ONE_CARD);
					spreadSelected = true;
				}else if(selection == 2){
					spread = new Spread(spreadType.THREE_CARD);
					spreadSelected = true;
				}else if(selection == 3){
					spread = new Spread(spreadType.THE_HAT);
					spreadSelected = true;
				}else if(selection == 4){
					spread = new Spread(spreadType.CELTIC_CROSS);
					spreadSelected = true;
				}else{
					//do nothing
				}
			}
			input.artificialMouseReleased(MouseEvent.BUTTON1);
		}
	}

	@Override
	public void draw() {
		Graphics offGraphics = offImage.getGraphics();
		offGraphics.drawImage(background, 0, 0, screenDims.width, screenDims.height, null);
		if (spreadSelected) {
			spread.draw(offGraphics);
		} else {
			menu.draw(offGraphics);
		}
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
