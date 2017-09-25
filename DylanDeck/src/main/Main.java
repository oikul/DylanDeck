package main;

import utils.AbstractMain;

public class Main extends AbstractMain {

	private static final long serialVersionUID = 1L;

	@Override
	public void initialise() {
		running = true;
		this.defaultInit("The Dylan Deck");
	}

	@Override
	public void update(float time) {
		
	}

	@Override
	public void draw() {
		
	}

	@Override
	public void close() {
		
	}
	
	public static void main(String args[]){
		Main main = new Main();
		main.run();
	}

}
