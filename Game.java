import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener {

	public static int width;
	public static int height;
	public static int size;
	public static int length;
	public static int score;
	private LinkedList<Snake> tail;
	private Snake snake;
	private Apple apple;
	private boolean ready_to_turn;
	private int speed;

	public Game(int tile, Speed speed) {
		size = 30;
		width = size * tile; // 1500
		height = tile > 25 ? size * 25 : size * tile; // 720
		score = 0;
		length = 5;
		
		tail = new LinkedList<Snake>();
		for (int i = 0; i < length; i++)
			tail.add(new Snake());
		
		snake = new Snake();
		apple = new Apple();
		
		setSpeed(speed);
		
		setSize(width + 44, height + 37);
		getContentPane().setBackground(Color.BLACK);
		setTitle("Score: " + score);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
//		addComponentListener(new ComponentAdapter() {
//		    public void componentResized(ComponentEvent componentEvent) {
//		        // do stuff
//		    }
//		});
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						snake.eat(apple, getThis());
						tail = snake.updateTail(tail);
						snake.move();
						snake.teleport();
						snake.death(tail);
						ready_to_turn = true;
						repaint();
						Thread.sleep(getSpeed());
					}
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}			
		});
		t.setPriority(10);
		t.start();
	}

	@Override
	public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.RED);
        g.fillRect(apple.getX() + 7, apple.getY(), size, size);
        
        for (int i = 0; i < length; i++) {
        	Snake s = tail.get(i);
	        g.setColor(Color.GREEN);
	        g.fillRect(s.getX() + 7, s.getY(), size, size);
        }
        
        g.setColor(Color.GREEN);
        g.fillRect(snake.getX() + 7, snake.getY(), size, size);
    }
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == 37 && snake.getXv() != 1 && ready_to_turn) {
			snake.setXv(-1);
			snake.setYv(0);
			ready_to_turn = false;
		}
		else if (c == 38 && snake.getYv() != 1 && ready_to_turn) {
			snake.setXv(0);
			snake.setYv(-1);
			ready_to_turn = false;
		}
		else if (c == 39 && snake.getXv() != -1 && ready_to_turn) {
			snake.setXv(1);
			snake.setYv(0);
			ready_to_turn = false;
		}
		else if (c == 40 && snake.getYv() != -1 && ready_to_turn) {
			snake.setXv(0);
			snake.setYv(1);
			ready_to_turn = false;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	public Game getThis() {
		return this;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(Speed speed) {
		switch (speed) {
		case SLOW:
			this.speed = 60 / 2 * 3;
			break;
		case NORMAL:
			this.speed = 60;
			break;
		case FAST:
			this.speed = 60 / 2;
		}
	}
		
	public static void main(String[] args) {	
		new Game(20, Speed.NORMAL);
	}
	
}

enum Speed {
	SLOW, NORMAL, FAST
}
