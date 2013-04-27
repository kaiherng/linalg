package frontend.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.blocks.Op;

public class DetBlock extends JPanel {

	public DetBlock(Op op, String text) {
		JLabel label = new JLabel(text);
		this.setPreferredSize(new Dimension(60,60));
		this.setBackground(Color.blue);
		this.add(label);
	}
}
