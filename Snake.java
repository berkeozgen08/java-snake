import java.util.LinkedList;

public class Snake {

	private int x;
	private int y;
	private int xv;
	private int yv;

	public Snake() {
		x = (Game.width / (2 * Game.size)) * Game.size;
		y = (Game.height / (2 * Game.size)) * Game.size;
		xv = 0;
		yv = 0;
	}

	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
		xv = 0;
		yv = 0;
	}
	
	public LinkedList<Snake> updateTail(LinkedList<Snake> tail) {
		tail.add(new Snake(x, y));
		while (tail.size() > Game.length) 
			tail.pop();
		return tail;
	}

	public void eat(Apple apple, Game game) {
		if (x + xv * Game.size == apple.getX() && y + yv * Game.size == apple.getY()) {
			Game.length++;
			game.setTitle("Score: " + ++Game.score);
			apple.spawn();
		}
	}

	public void move() {
		x += xv * Game.size;
		y += yv * Game.size;
	}
	
	public void teleport() {
		if (x < 0)
			x = Game.width;
		if (x > Game.width)
			x = 0;
		if (y <= 0)
			y = Game.height;
		if (y > Game.height)
			y = Game.size;
	}

	public void death(LinkedList<Snake> tail) {
		for (int i = 0; i < tail.size(); i++) {
			Snake s = tail.get(i);
			if (Game.score != 0 && x == s.getX() && y == s.getY()) {
				System.exit(1);
			}
		}
	}
		
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXv() {
		return xv;
	}

	public void setXv(int xv) {
		this.xv = xv;
	}

	public int getYv() {
		return yv;
	}

	public void setYv(int yv) {
		this.yv = yv;
	}

}
