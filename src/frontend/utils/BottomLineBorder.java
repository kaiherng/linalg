package frontend.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Stroke;

import javax.swing.border.Border;

public class BottomLineBorder implements Border {
	
	private int _stroke;
	private Color _color;
	
	public BottomLineBorder(int stroke, Color color) {
		_stroke = stroke;
		_color = color;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		Graphics2D g2D = (Graphics2D)g;
		Color oldColor = g2D.getColor();
		Stroke oldStroke = g2D.getStroke();
		g2D.setColor(Color.BLACK);
		g2D.setStroke(new java.awt.BasicStroke(_stroke));
		g2D.drawLine(x, y+height,x+width,y+height);
		g2D.setColor(oldColor);
		g2D.setStroke(oldStroke);
		
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(0,0,1,0);
	}

	@Override
	public boolean isBorderOpaque() {
		// TODO Auto-generated method stub
		return false;
	}


}
