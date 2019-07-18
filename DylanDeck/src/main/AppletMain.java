package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;

import deck.Spread;
import deck.Spread.spreadType;
import menu.MainMenu;

public class AppletMain extends JApplet implements Runnable{

	private static final long serialVersionUID = 1L;
	private boolean spreadSelected, running = false;
	private BufferedImage offImage;
	private InputHandler input;
	private MainMenu menu;
	private Spread spread;
	private Dimension screenDims;
	private Image background = ResourceHandler.getImage("background");

	public void init() {
		running = true;
//		Dimension dim = InputHandler.screenSize;
//		this.setSize(dim.width, dim.height);
		screenDims = this.getSize();
		offImage = new BufferedImage(screenDims.width, screenDims.height, BufferedImage.TYPE_INT_ARGB);
		spreadSelected = false;
		input = new InputHandler(this);
		menu = new MainMenu(screenDims);
		Thread thread = new Thread(this);
		thread.start();
	}

	public void update() {
		if (input.isMouseDown(MouseEvent.BUTTON1)) {
			Point mouse = input.getMousePositionRelativeToComponent();
			if (spreadSelected) {
				if (spread.click(mouse.x, mouse.y) == 1) {
					spreadSelected = false;
				}
			} else {
				int selection = menu.click(mouse.x, mouse.y);
				if (selection == 1) {
					spread = new Spread(spreadType.ONE_CARD, screenDims);
					spreadSelected = true;
				} else if (selection == 2) {
					spread = new Spread(spreadType.THREE_CARD, screenDims);
					spreadSelected = true;
				} else if (selection == 3) {
					spread = new Spread(spreadType.THE_HAT, screenDims);
					spreadSelected = true;
				} else if (selection == 4) {
					spread = new Spread(spreadType.CELTIC_CROSS, screenDims);
					spreadSelected = true;
				} else {
					// do nothing
				}
			}
			input.artificialMouseReleased(MouseEvent.BUTTON1);
		}
	}

	public void paint(Graphics g) {
		Graphics offGraphics = offImage.getGraphics();
		offGraphics.drawImage(background, 0, 0, screenDims.width, screenDims.height, null);
		if (spreadSelected) {
			spread.draw(offGraphics);
		} else {
			menu.draw(offGraphics);
		}
		g.drawImage(offImage, 0, 0, null);
	}

	@Override
	public void run() {
		while(running){
			update();
			repaint();
		}
	}

}
