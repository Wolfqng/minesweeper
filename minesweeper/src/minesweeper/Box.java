package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Box {
	private int x, y;
	private boolean flagged;
	private boolean isBomb;
	private int bombCount;
	private boolean covered;
	
	public Box(int x, int y) {
		this.x = x;
		this.y = y;
		this.isBomb = false;
		this.flagged = false;
		this.covered = true;
	}
	
	public void draw(Graphics g, int WIDTH, int countX) {
		int boxSize = WIDTH / countX;
		
		g.setColor(Color.BLACK);
		g.drawRect(x * boxSize, y * boxSize, boxSize, boxSize);
		
		g.setFont(new Font("TimesRoman", Font.BOLD, boxSize));
		
		if(bombCount != 0) {
			
			switch(bombCount) {
				case 1:
					g.setColor(Color.BLUE);
					break;
				case 2:
					g.setColor(Color.GREEN);
					break;
				case 3:
					g.setColor(Color.RED);
					break;
				case 4:
					g.setColor(Color.MAGENTA);
					break;
				case 5:
					g.setColor(Color.ORANGE);
					break;
				case 6:
					g.setColor(Color.CYAN);
					break;
				case 7:
					g.setColor(Color.YELLOW);
					break;
			}
			
			g.drawString("" + bombCount, x * boxSize + 10, y * boxSize + boxSize - 6);
		}	
		
		if(covered) {
			g.setColor(Color.WHITE);
			g.fillRect(x * boxSize, y * boxSize, boxSize, boxSize);
			g.setColor(new Color(240, 240, 240));
			g.fillRect(x * boxSize + 1, y * boxSize + 1, boxSize - 1, boxSize - 1);
			
			g.setColor(new Color(40, 40, 40));
			g.fillRect(x * boxSize + 2, y * boxSize + 2, boxSize - 2, boxSize - 2);
			g.setColor(new Color(60, 60, 60));
			g.fillRect(x * boxSize + 2, y * boxSize + 2, boxSize - 1, boxSize - 1);
		
			g.setColor(new Color(150, 150, 150));
			g.fillRect(x * boxSize + 2, y * boxSize + 2, boxSize - 4, boxSize - 4);
			
		}
		
		if(!covered && isBomb) {
			g.setColor(Color.RED);
			g.fillRect(x * boxSize, y * boxSize, boxSize, boxSize);
		}
		
		if(flagged) {
			g.setColor(Color.RED);
			g.drawString("X", x * boxSize + 6, y * boxSize + boxSize - 6);
		}
		
		
	}
	
	public void getNeighborCount(Box[][] map) {
		int count = 0;
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if((x + i < 0 || x + i >= map.length || y + j < 0 || y + j >= map[0].length) || (x + i == x && y + j == y)) continue;
				if(map[x + i][y + j].isBomb()) count++;
			}
		}
		
		this.bombCount = count;
	}

	
	/*
	 * GETTERS AND SETTERS
	 */
	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public boolean isCovered() {
		return covered;
	}

	public void setCovered(boolean covered) {
		this.covered = covered;
	}
	
	public boolean isBomb() {
		return isBomb;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	public int getBombCount() {
		return bombCount;
	}

	public void setBombCount(int bombCount) {
		this.bombCount = bombCount;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Box [x=" + x + ", y=" + y + ", flagged=" + flagged + ", isBomb=" + isBomb + ", bombCount=" + bombCount
				+ ", covered=" + covered + "]";
	}
}
