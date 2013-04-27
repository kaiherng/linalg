package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import frontend.utils.WrapLayout;

import backend.blocks.Numerical;
import backend.blocks.Op;

public class Compute extends JPanel {

	List<Numerical> operations;
	
	public Compute() {
		this.setLayout(new BorderLayout());
		JPanel ComputeBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel Pages = new JPanel(new GridLayout(3, 1));
		JPanel Operations = new JPanel(new WrapLayout(FlowLayout.LEFT));
		this.setMaximumSize(new Dimension(100,100));
		
		JButton computeButton = new JButton("Compute");
		ComputeBar.add(computeButton);
		
		JButton rank0Button = new JButton("Rank 0");
		JButton rank1Button = new JButton("Rank 1");
		JButton rank2Button = new JButton("Rank 2");
		Pages.add(rank0Button);
		Pages.add(rank1Button);
		Pages.add(rank2Button);
		
		Operations.add(new DetBlock(Op.DETERMINANT, "DET"));

		
		ComputeBar.setBorder(BorderFactory.createLineBorder(Color.black));
		Pages.setBorder(BorderFactory.createLineBorder(Color.black));
		Operations.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.add(ComputeBar, BorderLayout.SOUTH);
		this.add(Pages, BorderLayout.WEST);
		this.add(Operations, BorderLayout.CENTER);
	}

}
