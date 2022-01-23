package eus.ehu.adsi.arkanoid.view.game;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class ScoreBoard {

	public int score = 0;
	public int lives = Config.PLAYER_LIVES;
	public boolean win = false;
	public boolean gameOver = false;
	String text = "";
	String text2 = null;

	Font font;

	public ScoreBoard() {
		font = new Font(Config.FONT, Font.PLAIN, 12);
		text = "Bienvenido a Arkanoid";
	}

	public void increaseScore(int nivel, String ventaja) {
		score++;
		if (score == (Config.getCountBlocksX(nivel) * Config.getCountBlocksY(nivel))) {
			win = true;
			text = "Has ganado! \nTus puntos fueron: " + score
					+ "\n\nPulsa enter para \nvolver a jugar";
		} else {
			updateScoreboard(ventaja);
		}
	}

	void die() {
		lives--;
		if (lives == 0) {
			gameOver = true;
			text = "Has perdido! \nTus puntos fueron: " + score
					+ "\n\nPulsa enter para \nvolver a jugar";
		} else {
			updateScoreboard(null);
		}
	}

	//Actualizar vidas
	public void updateLives(int l) {
		this.lives = lives + l;
		updateScoreboard(null);
	}

	public void updateScoreboard(String ventaja) {
		text = "Puntos: " + score + "  Vidas: " + lives;
		if (ventaja != null) {
			text2 = ventaja;
		}
	}

	public void draw(Graphics g) {
		if (win || gameOver) {
			font = font.deriveFont(50f);
			FontMetrics fontMetrics = g.getFontMetrics(font);
			g.setColor(Config.FONT_COLOR);
			g.setFont(font);
			int titleHeight = fontMetrics.getHeight();
			int lineNumber = 1;
			for (String line : text.split("\n")) {
				int titleLen = fontMetrics.stringWidth(line);
				g.drawString(line, (Config.SCREEN_WIDTH / 2) - (titleLen / 2),
						(Config.SCREEN_HEIGHT / 4) + (titleHeight * lineNumber));
				lineNumber++;

			}
		} else {
			font = font.deriveFont(34f);
			Font font2 = font.deriveFont(12f);
			FontMetrics fontMetrics = g.getFontMetrics(font);
			g.setColor(Config.FONT_COLOR);
			g.setFont(font);
			int titleLen = fontMetrics.stringWidth(text);
			int titleHeight = fontMetrics.getHeight();
			g.drawString(text, (Config.SCREEN_WIDTH / 2) - (titleLen / 2),
					titleHeight + 20);
			if (text2 != null) {
				g.setFont(font2);
				g.drawString(text2, (Config.SCREEN_WIDTH / 2) - (titleLen / 2),
<<<<<<< HEAD
					titleHeight+40);
=======
						titleHeight+40);
>>>>>>> origin/feature-premios
			}
		}
	}
}