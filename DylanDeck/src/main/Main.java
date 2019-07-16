package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import deck.Spread;
import handlers.InputHandler;
import utils.AbstractMain;

public class Main extends AbstractMain {

	private static final long serialVersionUID = 1L;
	private InputHandler input;
	private Spread spread;
	private boolean spreadSelected = false;
	private Dimension screenDims = InputHandler.screenSize;
	private JButton oneCard, threeCard, theHat;

	@Override
	public void initialise() {
		running = true;
		spreadSelected = false;
		this.defaultInit("The Dylan Deck");
		input = new InputHandler(this);
		oneCard = new JButton("ONE CARD");
		oneCard.setBounds(screenDims.width / 2 - screenDims.width / 20, screenDims.height / 4 - screenDims.height / 20, screenDims.width / 10, screenDims.height / 10);
		oneCard.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				spread = new Spread(Spread.spreadType.ONE_CARD);
				spreadSelected = true;
			}
		});
		this.add(oneCard);
		threeCard = new JButton("THREE CARD");
		threeCard.setBounds(screenDims.width / 2 - screenDims.width / 20, screenDims.height / 2 - screenDims.height / 20, screenDims.width / 10, screenDims.height / 10);
		threeCard.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spread = new Spread(Spread.spreadType.THREE_CARD);
				spreadSelected = true;
			}
		});
		this.add(threeCard);
		theHat = new JButton("THE HAT");
		theHat.setBounds(screenDims.width / 2 - screenDims.width / 20, 3 * screenDims.height / 4 - screenDims.height / 20, screenDims.width / 10, screenDims.height / 10);
		theHat.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				spread = new Spread(Spread.spreadType.THE_HAT);
				spreadSelected = true;
			}
		});
		this.add(theHat);
	}

	@Override
	public void update(float time) {
		if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			close();
			this.dispose();
		}
		if(spreadSelected){
			this.remove(oneCard);
			this.remove(threeCard);
			this.remove(theHat);
		}
		if(input.isMouseDown(MouseEvent.BUTTON1) && spreadSelected) {
			Point mouse = input.getMousePositionOnScreen();
			if(spread.click(mouse.x, mouse.y)){
				draw();
			}
			input.artificialMouseReleased(MouseEvent.BUTTON1);
		}
	}

	@Override
	public void draw() {
		Graphics offGraphics = offImage.getGraphics();
		if(spreadSelected){
			spread.draw(offGraphics);
		}else{
			repaint();
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
