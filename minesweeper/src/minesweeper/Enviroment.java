package minesweeper;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Enviroment extends JPanel {
	private static final long serialVersionUID = 1L;
	private static MouseHandler mouseHandler = new MouseHandler();
	public static JFrame f = new JFrame("Minesweeper");
	public static final int WIDTH = 1200 + 16;
	public static final int HEIGHT = 800 + 39;
	public static Box[][] map;
	
	
	public void paintComponent(Graphics g) {
		for(int i = 0; i < map.length; i++) {
        	for(int j = 0; j < map[0].length; j++) {
        		map[i][j].draw(g, WIDTH, map.length);
        	}
		}
	}
	
	public static void main(String args[]) {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setSize(WIDTH, HEIGHT);
        f.add(new Enviroment());
        f.setBackground(new Color(200, 200, 200));
        f.setVisible(true);
        f.addMouseListener(mouseHandler);
        
        setUpBoxes();
        
        f.repaint();
	}
	
	public static void setUpBoxes() {
		//Create all the mines
		map = new Box[30][16];
        for(int i = 0; i < map.length; i++) 
        	for(int j = 0; j < map[0].length; j++) 
        		map[i][j] = new Box(i, j);
 
        //Setup isBomb
        int count = 0;
        while(count < 100) {
        	int x = (int)(Math.random() * map.length);
        	int y = (int)(Math.random() * map[0].length);
        	
        	if(!map[x][y].isBomb()) 
        		map[x][y].setBomb(true);
        	else
        		continue;
        	
        	count++;
        }
        
        //Set all the boxes neighbor count
        for(int i = 0; i < map.length; i++) 
        	for(int j = 0; j < map[0].length; j++) 
        		map[i][j].getNeighborCount(map);  
	}
	
	public static void clicked(int x, int y) {
		Box b = getBoxFromClick(x, y);
		
		if(b != null && b.isCovered() && !b.isFlagged()) {
			b.setCovered(false);
			
			if(b.getBombCount() == 0) 
				uncoverMultiple(b);
			
		}
		
		f.repaint();
	}
	
	public static void uncoverMultiple(Box b) {
		int x = b.getX();
		int y = b.getY();
		
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if((x + i < 0 || x + i >= map.length || y + j < 0 || y + j >= map[0].length) || (x + i == x && y + j == y)) continue;
				if(map[x + i][y + j].isCovered() && !map[x + i][y + j].isFlagged()) {
					map[x + i][y + j].setCovered(false);
					
					if(map[x + i][y + j].getBombCount() == 0)
						uncoverMultiple(map[x + i][y + j]);
				}
			}
		}
	}
	
	public static void setFlag(int x, int y) {
		Box b = getBoxFromClick(x, y);
		
		if(b.isCovered() && !b.isFlagged())
			b.setFlagged(true);
		else if(b.isFlagged())
			b.setFlagged(false);
		
		f.repaint();
	}
	
	public static Box getBoxFromClick(int x, int y) {
		int boxSize = WIDTH / map.length;
		int newX = (x - 6) / boxSize; //You'll need to add the offsets later
		int newY = (y + 16) / boxSize - 1;
		
		System.out.println(x + " " + y + " : " + newX + " " + newY);
		
		return map[newX][newY];
	}

}
