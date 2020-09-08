
public class Apple {

	private int x;
	private int y;

	public Apple() {
		spawn();
	}

	public void spawn() {
		x = (int) Math.floor(Math.random() * (Game.width / Game.size)) * Game.size;
		y = (int) Math.floor(Math.random() * (Game.height / Game.size)) * Game.size + 30;
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

}
