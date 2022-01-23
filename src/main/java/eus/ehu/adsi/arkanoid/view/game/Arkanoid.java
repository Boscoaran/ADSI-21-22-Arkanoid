package eus.ehu.adsi.arkanoid.view.game;

// Adapted from https://gist.github.com/Miretz/f10b18df01f9f9ebfad5

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager; //TEMAS DEL LOGGER POR LA VULNERABILIDAD
import org.apache.logging.log4j.Logger;

import eus.ehu.adsi.arkanoid.controlador.GestorPartidas;
import eus.ehu.adsi.arkanoid.controlador.GestorUsuarios;
import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import eus.ehu.adsi.arkanoid.view.game.core.Game;

public class Arkanoid extends JFrame implements KeyListener { //No se si se podrá hacer MAE esta clase

	// Housekeeping
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Arkanoid.class);

	// Game variables
	private Game game;
	private Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
	private List<Brick> bricks = new ArrayList<Brick>();
	Ball ball = new Ball(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2, 1);
	Ball ball2;
	private ScoreBoard scoreboard = new ScoreBoard();

	private double lastFt;
	private double currentSlice;

	private int ladrillo1 = -1;
	private int ladrillo2 = -1;


	private int nivel;
	
	public Arkanoid(int lvl) {

		
		
		game = new Game ();
		nivel = lvl;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(false);
		this.setResizable(false);
		this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		this.setTitle("Arkanoid");
		this.setVisible(true);
		this.addKeyListener(this);
		this.setLocationRelativeTo(null);
		this.createBufferStrategy(2);
		this.setFocusable(true);

		bricks = Game.initializeBricks(bricks, nivel);

	}
		

	public void run() {
		
		this.prepararPartida();

		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = bf.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		game.setRunning(true);

		while (game.isRunning()) {

			long time1 = System.currentTimeMillis();
			if (!scoreboard.gameOver && !scoreboard.win) {
				logger.info("Playing");
				game.setTryAgain(false);
				update();
				drawScene(ball, bricks, scoreboard, ball2);

				// to simulate low FPS
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
				}

			} else {
				if (game.isTryAgain()) {
					logger.info("Trying again");
					game.setTryAgain(false);
					bricks = Game.initializeBricks(bricks, nivel);
					scoreboard.lives = Config.PLAYER_LIVES;
					scoreboard.score = 0;
					scoreboard.win = false;
					scoreboard.gameOver = false;
					scoreboard.updateScoreboard(null);
					ball.x = Config.SCREEN_WIDTH / 2;
					ball.y = Config.SCREEN_HEIGHT / 2;
					paddle.x = Config.SCREEN_WIDTH / 2;
				}
			}

			long time2 = System.currentTimeMillis();
			double elapsedTime = time2 - time1;

			lastFt = elapsedTime;

			double seconds = elapsedTime / 1000.0;
			if (seconds > 0.0) {
				double fps = 1.0 / seconds;
				logger.info("FPS: " + fps);
			}

		}

		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

	}

	private void update() {

		currentSlice += lastFt;

		for (; currentSlice >= Config.FT_SLICE; currentSlice -= Config.FT_SLICE) {

				if (ball != null) ball.update(scoreboard, paddle, nivel, this);
				if (ball2 != null) ball2.update(scoreboard, paddle, nivel, this);
				paddle.update(0);
				if (ball != null) Game.testCollision(paddle, ball, nivel);
				if (ball2 != null) Game.testCollision(paddle, ball2, nivel);

			Iterator<Brick> it = bricks.iterator();
			while (it.hasNext()) {
				Brick brick = it.next();
				if (ball != null) Game.testCollision(brick, ball, scoreboard, nivel, this);
				if (ball2 != null) Game.testCollision(brick, ball2, scoreboard, nivel, this);

				if (brick.destroyed) {
					it.remove();
				}

				if (brick.getId() == ladrillo1) {
					it.remove();
					ladrillo1 = -1;
				}

				if (brick.getId() == ladrillo2) {
					it.remove();
					ladrillo1 = -1;
				}
			}
		}
	}

	private void drawScene(Ball b, List<Brick> bricks, ScoreBoard scoreboard, Ball b2) {

		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;

		try {

			g = bf.getDrawGraphics();

			g.setColor(Config.getBackGroundColor());
			g.fillRect(0, 0, getWidth(), getHeight());

			if (b != null) b.draw(g);
			if (b2 != null) b2.draw(g);
			paddle.draw(g);
			for (Brick brick : bricks) {
				brick.draw(g);
			}
			scoreboard.draw(g);

		} finally {
			g.dispose();
		}

		bf.show();

		Toolkit.getDefaultToolkit().sync();

	}

	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.setRunning(false);
		}
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			game.setTryAgain(true);
		}
		switch (event.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			paddle.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			paddle.moveRight();
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			paddle.stopMove();
			break;
		default:
			break;
		}
	}

	public void keyTyped(KeyEvent arg0) {}

	//PRUEBAS

	private void prepararPartida() {
		Usuario u = new Usuario("null", "null", "null");
		GestorUsuarios.getGestorUsuarios().anadir(u);
		Partida p = new Partida(u);
		GestorPartidas.getGestorPartidas().anadir(p);
	}

	//////

	public void duplicarBola() {
		if (ball2 == null)
		ball2 = new Ball(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2, 2);
	}

	public void eliminarLadrillos(int ladrillos, Brick mBrick) {
		int i = 0;
		boolean enc = false;
		while (i < bricks.size() && !enc) {
			if (mBrick.equals(bricks.get(i))) {
				if (bricks.size() != 1) {
					if (i == bricks.size()-1) {
						ladrillo1 = bricks.get(i-1).getId();
					} else if (i == 0) {
						ladrillo2 = bricks.get(i+1).getId();
					} else{
						ladrillo1 = bricks.get(i-1).getId();
						ladrillo2 = bricks.get(i+1).getId();
					}
				}
				enc = true;
			}
			i++;
		}
	}

	public void aumentarPaddle(double d) {
		paddle.update(d);
	}

	public Object getBola(int b) {
		if (b == 1) return ball;
		else return ball2;
	}

	public void setBolaNull(int b) {
		if (b == 1) ball = null;
		else ball2 = null;
	}
}

