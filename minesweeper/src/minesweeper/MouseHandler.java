package minesweeper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			Enviroment.clicked(e.getX(), e.getY());
		else if(e.getButton() == MouseEvent.BUTTON3)
			Enviroment.setFlag(e.getX(), e.getY());
	}
}
