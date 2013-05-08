package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import backend.blocks.Countable;
import backend.blocks.Numerical;
import backend.blocks.Op;
import backend.main.ParseNode;
import backend.main.Parser;
import frontend.blocks.BracketBlock;
import frontend.blocks.OpBlock;
import frontend.swing.Button;
import frontend.swing.CurrentConstants;
import frontend.swing.ScrollPane;
import frontend.utils.WrapLayout;

public class Compute extends JPanel {

	Map<Integer, Numerical> _numericals;
	JPanel _computeBar, _ops, _bar;
	Integer _id = 0;
	StepSolution _solPanel;
	Solution _stepPanel;
	private JLabel _instructionsLabel;
	
	public Compute(StepSolution sol, Solution step) {
		super();
		_numericals = new LinkedHashMap<>();
		_solPanel = sol;
		_stepPanel = step;
		
		this.setLayout(new BorderLayout());
		this.setBorder(CurrentConstants.COMPUTE_BORDER);
		this.setBackground(CurrentConstants.COMPUTE_BG);
		
		_computeBar = new JPanel(new BorderLayout());
		_computeBar.setBorder(CurrentConstants.COMPUTE_COMPUTEBAR_BORDER);
		_computeBar.setBackground(CurrentConstants.COMPUTE_COMPUTEBAR_BG);
				
		WrapLayout wrapLayout = new WrapLayout(FlowLayout.LEFT);
		wrapLayout.setHgap(CurrentConstants.COMPUTE_WRAPLAYOUT_HGAP);
		wrapLayout.setVgap(CurrentConstants.COMPUTE_WRAPLAYOUT_VGAP);
		_ops = new JPanel(new GridBagLayout());
		_ops.setBorder(CurrentConstants.COMPUTE_OPS_BORDER);
		_ops.setBackground(CurrentConstants.COMPUTE_OPS_BG);
		
		//compute bar
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(CurrentConstants.COMPUTE_BUTTONPANEL_BORDER);
		buttonPanel.setBackground(CurrentConstants.COMPUTE_BUTTONPANEL_BG);
		buttonPanel.setLayout(new BorderLayout());
		
		JButton computeButton = new Button("Compute", CurrentConstants.BUTTON_COMPUTE_BG, CurrentConstants.BUTTON_COMPUTE_FG, CurrentConstants.BUTTON_COMPUTE_HOVER_BG, CurrentConstants.BUTTON_COMPUTE_HOVER_FG, CurrentConstants.BUTTON_COMPUTE_PRESSED_BG, CurrentConstants.BUTTON_COMPUTE_PRESSED_FG, CurrentConstants.BUTTON_COMPUTE_BORDER);
		JButton clearButton = new Button("Clear", CurrentConstants.BUTTON_CLEAR_BG, CurrentConstants.BUTTON_CLEAR_FG, CurrentConstants.BUTTON_CLEAR_HOVER_BG, CurrentConstants.BUTTON_CLEAR_HOVER_FG, CurrentConstants.BUTTON_CLEAR_PRESSED_BG, CurrentConstants.BUTTON_CLEAR_PRESSED_FG, CurrentConstants.BUTTON_CLEAR_BORDER);
		
		clearButton.addActionListener(new ClearButtonListener(this));
		computeButton.addActionListener(new SolButtonListener(this));
		_computeBar.add(buttonPanel, BorderLayout.EAST);
		buttonPanel.add(clearButton, BorderLayout.EAST);
		buttonPanel.add(computeButton, BorderLayout.WEST);
		
		JPanel scrollPanel = new JPanel(new BorderLayout());
		scrollPanel.setBorder(CurrentConstants.COMPUTE_SCROLLPANEL_BORDER);
		scrollPanel.setBackground(CurrentConstants.COMPUTE_SCROLLPANEL_BG);
		scrollPanel.setPreferredSize(CurrentConstants.COMPUTE_SCROLLPANEL_SIZE);
		
		_bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ScrollPane scrollBar = new ScrollPane(_bar);
		scrollBar.setBorder(CurrentConstants.COMPUTE_SCROLLBAR_BORDER);
		scrollBar.setBackground(CurrentConstants.COMPUTE_SCROLLBAR_BG);
		scrollBar.getHorizontalScrollBar().setUnitIncrement(20);

		scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//		scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPanel.add(scrollBar, BorderLayout.CENTER);
		_bar.setBorder(CurrentConstants.COMPUTE_BAR_BORDER);
		_bar.setBackground(CurrentConstants.COMPUTE_BAR_BG);
		
		_instructionsLabel = new JLabel(CurrentConstants.COMPUTE_INSTRUCTIONSLABEL_TEXT);
		_instructionsLabel.setBorder(CurrentConstants.COMPUTE_INSTRUCTIONSLABEL_BORDER);
		_bar.add(_instructionsLabel,BorderLayout.CENTER);
		_computeBar.add(scrollPanel, BorderLayout.CENTER);
		
		
		//operations
		JPanel unary = new JPanel(new WrapLayout(FlowLayout.LEFT));
		JPanel binary = new JPanel(new WrapLayout(FlowLayout.LEFT));
		JPanel brackets = new JPanel(new WrapLayout(FlowLayout.LEFT));
		
		JLabel unaryLabel = new JLabel("Unary Operations (Place before matrix)");
		JLabel binaryLabel = new JLabel("Binary Operations (Place between/after matrices)");
		JLabel miscLabel = new JLabel("Misc.");
		unaryLabel.setFont(CurrentConstants.COMPUTE_LABEL_FONT);
		binaryLabel.setFont(CurrentConstants.COMPUTE_LABEL_FONT);
		miscLabel.setFont(CurrentConstants.COMPUTE_LABEL_FONT);
		unary.setOpaque(false);
		binary.setOpaque(false);
		brackets.setOpaque(false);
//		unary.setBorder(BorderFactory.createLineBorder(Color.black));
//		binary.setBorder(BorderFactory.createLineBorder(Color.black));
		for(Op o : Op.values()){
			if(o.isUnary()){
				unary.add(new OpBlock(o, o.getIconPath(), this));
			} else {
				binary.add(new OpBlock(o, o.getIconPath(), this));
			}
		}
		brackets.add(new BracketBlock(true, this));
		brackets.add(new BracketBlock(false, this));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 0.0;
		c.weightx = 0.1;
		c.insets = new Insets(10, 10, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		_ops.add(unaryLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		_ops.add(binaryLabel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		_ops.add(miscLabel, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.weighty = .1;
		c.gridx = 0;
		c.gridy = 1;
		_ops.add(unary, c);
		
		c.gridx = 0;
		c.gridy = 3;
		_ops.add(binary, c);
		
		c.gridx = 0;
		c.gridy = 5;
		_ops.add(brackets, c);
		
		JPanel opsWrapper = new JPanel(new BorderLayout());
		opsWrapper.setBorder(CurrentConstants.COMPUTE_OPS_BORDER);
		opsWrapper.setBackground(CurrentConstants.COMPUTE_OPS_BG);
		opsWrapper.add(_ops, BorderLayout.NORTH);
		//Bracket
//		_ops.add(new BracketBlock(true, this));
//		_ops.add(new BracketBlock(false, this));
				
		this.add(_computeBar, BorderLayout.SOUTH);
		this.add(opsWrapper, BorderLayout.CENTER);
	}
	
	public void addToBar(Numerical n, String s){
		if (_instructionsLabel.isVisible()) {
			_instructionsLabel.setVisible(false);
		}
		_numericals.put(_id, n);
		_bar.add(new BarObject(n, s, _id, this));
		_id++;
		this.revalidate();
		this.repaint();
	}
	
	public void addToBar(Numerical n, String s, Image bi){
		if (_instructionsLabel.isVisible()) {
			_instructionsLabel.setVisible(false);
		}
		_numericals.put(_id, n);
		_bar.add(new BarObject(n, s, _id, this, bi));
		_id++;
		this.revalidate();
		this.repaint();
	}
	
	public void removeFromBar(int id, Component c){
		_numericals.remove(id);
		_bar.remove(c);
		this.revalidate();
		this.repaint();
		if (_numericals.size() == 0) {
			_instructionsLabel.setVisible(true);
		}
	}
	
	public void clearBar(){
		_numericals.clear();
		_bar.removeAll();
		this.repaint();
		_bar.add(_instructionsLabel);
		_instructionsLabel.setVisible(true);
	}
	
	public void compute(){
		List<Numerical> l = new ArrayList<>();
		for(Numerical n : _numericals.values()){
			l.add(n);
		}
		try{
			ParseNode result = Parser.parse(l);
			if(result == null){
				String s = "\\text{No solution found}";
				_stepPanel.setTex(s);
				//_solPanel.setTex(s);
				return;
			}
			Countable answer = result.getSolution().getAnswer();
			
			//DFS for each child .getsolution().getsteps();
			
			List<List<String>> list = new ArrayList<>();
			list.add(new ArrayList<String>());
			list.add(new ArrayList<String>());
			list.add(new ArrayList<String>());
			_solPanel.setSolution(traverseTree(result, list), result.getSolution().getAnswer());
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
			_solPanel.setError("\\text{" + e.getMessage() + "}");
		}
	}
	
	/**
	 * Solutions are stored in a list of list of strings
	 * list 0 is for the computeString
	 * list 1 is for the concatenated steps strings
	 * @param n
	 * @param list
	 * @return
	 */
	public List<List<String>> traverseTree(ParseNode n, List<List<String>> list){
		if(n.getLeft() != null){
			traverseTree(n.getLeft(), list);
		}
		if(n.getRight() != null){
			traverseTree(n.getRight(), list);
		}
		//put all strings in the step list into one long string
		List<String> nList = n.getSolution().getLatex();
		StringBuilder sb = new StringBuilder();
		//used for pdf output;
		StringBuilder output = new StringBuilder();
		for(String s : nList){
			sb.append(s);
			sb.append("\\\\");
			s = s.replaceAll("\\\\vspace\\{\\d\\dmm\\}", "");
			s = s.replaceAll("\\\\hspace\\{\\d\\dmm\\}", "");
			s = s.replaceAll("\\$", "");
			output.append("$" + s + "$\\\\\\\\\n");
		}
		list.get(1).add(sb.toString());
		list.get(2).add(output.toString());
		
		//add the computeString to list 0
		try {
			list.get(0).add(n.getComputeString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private class BarObject extends JPanel implements MouseListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 3647938517413519896L;
		private Compute _c;
		private Integer _id;
		public BarObject(Numerical n, String s, int id, Compute c){
			this.setLayout(new BorderLayout());
			JLabel label = new JLabel(s);
			label.setForeground(CurrentConstants.COMPUTE_BAR_OBJECT_FG);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setPreferredSize(CurrentConstants.COMPUTE_BAR_OBJECT_SIZE);
			this.setPreferredSize(CurrentConstants.COMPUTE_BAR_OBJECT_SIZE);
			this.setBackground(CurrentConstants.COMPUTE_BAR_OBJECT_BG);
			this.add(label, BorderLayout.CENTER);
			this.setToolTipText(s);
			_c = c;
			_id = id;
			this.addMouseListener(this);
		}
		
		public BarObject(Numerical n, String s, int id, Compute c, Image bi){
			this.setLayout(new BorderLayout());
			JLabel label = new JLabel(new ImageIcon(bi.getScaledInstance(40, 40, BufferedImage.SCALE_DEFAULT)));
			label.setForeground(CurrentConstants.COMPUTE_BAR_OBJECT_FG);
			label.setHorizontalAlignment(JLabel.CENTER);
			this.setPreferredSize(CurrentConstants.COMPUTE_BAR_OBJECT_SIZE);
			this.setBackground(CurrentConstants.COMPUTE_BAR_OBJECT_BG);
			this.add(label, BorderLayout.CENTER);
			this.setToolTipText(s);
			_c = c;
			_id = id;
			this.addMouseListener(this);
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			_c.removeFromBar(_id, this);
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			setBackground(CurrentConstants.COMPUTE_BAR_OBJECT_HOVER_BG);
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			setBackground(CurrentConstants.COMPUTE_BAR_OBJECT_BG);
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	private class SolButtonListener implements ActionListener{
		
		Compute _c;
		
		public SolButtonListener(Compute c){
			_c = c;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_c.compute();
		}
		
	}
	
	private class ClearButtonListener implements ActionListener{
		
		Compute _c;
		
		public ClearButtonListener(Compute c){
			_c = c;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_c.clearBar();
//			_stepPanel.clear();
			_solPanel.clear();
		}
		
	}
	
}
