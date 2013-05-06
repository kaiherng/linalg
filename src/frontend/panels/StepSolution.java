package frontend.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import backend.blocks.Countable;
import frontend.swing.Button;
import frontend.swing.CurrentConstants;
import frontend.swing.ScrollPane;

public class StepSolution extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1780266591415441861L;
	private Saved _savePanel;
	private Solution _display, _answer, _comp;
	private List<String> _solList, _compList;
	private int _stepNumber = 0;
	private ScrollPane _scroll;
	private JButton _forwardButton, _backButton;
	private Countable _ans;
	private JPanel _bottomBar;
	private JLabel _stepNumberLabel;
	private JTabbedPane _tabbedPane;
	
	public StepSolution(){
		super();
		this.setLayout(new BorderLayout());
		this.setBackground(CurrentConstants.STEPSOLUTION_BG);
		this.setBorder(CurrentConstants.STEPSOLUTION_BORDER);
		this.setPreferredSize(new Dimension(100,100));

		_solList = new ArrayList<>();
		
		//main display
		_display = new Solution("\\text{Solutions will be displayed here}");
//		_display.addMouseListener(new Listener(this));
		_scroll = new ScrollPane(_display);
		_scroll.getVerticalScrollBar().setUnitIncrement(20);
		_scroll.setBorder(CurrentConstants.STEPSOLUTION_SCROLL_BORDER);
		_scroll.setBackground(CurrentConstants.STEPSOLUTION_SCROLL_BG);
		
		//bottom bar
		_bottomBar = new JPanel(new BorderLayout());
		_bottomBar.setBorder(CurrentConstants.STEPSOLUTION_BOTTOMBAR_BORDER);
		_bottomBar.setBackground(CurrentConstants.STEPSOLUTION_BOTTOMBAR_BG);
		
		//bottom bar left
		JPanel bottomBarLeft = new JPanel(new BorderLayout());
		
		JPanel bottomBarLeftUp = new JPanel();
		bottomBarLeftUp.setBorder(CurrentConstants.STEPSOLUTION_BOTTOMBARLEFTUP_BORDER);
		bottomBarLeftUp.setBackground(CurrentConstants.STEPSOLUTION_BOTTOMBARLEFTUP_BG);
		_stepNumberLabel = new JLabel("");
		_stepNumberLabel.setBorder(CurrentConstants.STEPSOLUTION_STEPNUMBERLABEL_BORDER);
		_stepNumberLabel.setBackground(CurrentConstants.STEPSOLUTION_STEPNUMBERLABEL_BG);
		_stepNumberLabel.setForeground(CurrentConstants.STEPSOLUTION_STEPNUMBERLABEL_FG);
		_stepNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		_stepNumberLabel.setFont(CurrentConstants.STEPSOLUTION_STEPNUMBERLABEL_FONT);
		bottomBarLeft.setBackground(CurrentConstants.STEPSOLUTION_BOTTOMBARLEFT_BG);
		bottomBarLeft.setBorder(CurrentConstants.STEPSOLUTION_BOTTOMBARLEFT_BORDER);
		_comp = new Solution(80,80);
		_comp.setMargins(10, 10);
		_comp.setBorder(CurrentConstants.STEPSOLUTION_COMP_BORDER);
		_comp.setBackground(CurrentConstants.STEPSOLUTION_COMP_BG);
		ScrollPane compScroll = new ScrollPane(_comp);
		compScroll.setBackground(CurrentConstants.STEPSOLUTION_COMPSCROLL_BG);
		compScroll.setBorder(CurrentConstants.STEPSOLUTION_COMPSCROLL_BORDER);
		compScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		bottomBarLeft.add(compScroll, BorderLayout.CENTER);
		_forwardButton = new Button("\u25ba", CurrentConstants.BUTTON_NEXT_BG, CurrentConstants.BUTTON_NEXT_FG, CurrentConstants.BUTTON_NEXT_HOVER_BG, CurrentConstants.BUTTON_NEXT_HOVER_FG, CurrentConstants.BUTTON_NEXT_PRESSED_BG, CurrentConstants.BUTTON_NEXT_PRESSED_FG, CurrentConstants.BUTTON_NEXT_BORDER);
		_backButton = new Button("\u25c4", CurrentConstants.BUTTON_NEXT_BG, CurrentConstants.BUTTON_NEXT_FG, CurrentConstants.BUTTON_NEXT_HOVER_BG, CurrentConstants.BUTTON_NEXT_HOVER_FG, CurrentConstants.BUTTON_NEXT_PRESSED_BG, CurrentConstants.BUTTON_NEXT_PRESSED_FG, CurrentConstants.BUTTON_NEXT_BORDER);
		_forwardButton.setFont(CurrentConstants.BUTTON_NEXT_FONT);
		_backButton.setFont(CurrentConstants.BUTTON_NEXT_FONT);
		_forwardButton.addActionListener(new ForwardBack(this, true));
		_backButton.addActionListener(new ForwardBack(this, false));
		bottomBarLeftUp.add(_backButton);
		bottomBarLeftUp.add(_stepNumberLabel);
		bottomBarLeftUp.add(_forwardButton);
		bottomBarLeft.add(bottomBarLeftUp, BorderLayout.NORTH);
		
		JPanel bottomBarRight = new JPanel(new BorderLayout());
		JPanel bottomBarRightUp = new JPanel(new BorderLayout());
		bottomBarRightUp.setBackground(CurrentConstants.STEPSOLUTION_BOTTOMBARRIGHTUP_BG);
		bottomBarRightUp.setBorder(CurrentConstants.STEPSOLUTION_BOTTOMBARRIGHTUP_BORDER);
		
		JLabel answerLabel = new JLabel("Final Answer");
		answerLabel.setBorder(CurrentConstants.STEPSOLUTION_ANSWERLABEL_BORDER);
		answerLabel.setBackground(CurrentConstants.STEPSOLUTION_ANSWERLABEL_BG);
		answerLabel.setForeground(CurrentConstants.STEPSOLUTION_ANSWERLABEL_FG);
		answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		answerLabel.setFont(CurrentConstants.STEPSOLUTION_ANSWERLABEL_FONT);

		bottomBarRightUp.add(answerLabel);
		bottomBarRight.add(bottomBarRightUp, BorderLayout.NORTH);
		
		
		_answer = new Solution(90, 90);
		_answer.setMargins(5, 5);
		_answer.addMouseListener(new AnswerListener());
		_answer.setToolTipText("Double click to save answer");
		_answer.setBorder(CurrentConstants.STEPSOLUTION_ANSWER_BORDER);
		_answer.setBackground(CurrentConstants.STEPSOLUTION_ANSWER_BG);
		_answer.setPreferredSize(new Dimension(0,90));
		bottomBarRight.add(_answer, BorderLayout.CENTER);
		
		_bottomBar.add(bottomBarLeft, BorderLayout.CENTER);
		_bottomBar.add(bottomBarRight, BorderLayout.EAST);
		
		this.add(_scroll, BorderLayout.CENTER);
		this.add(_bottomBar, BorderLayout.SOUTH);
		_bottomBar.setVisible(false);
		
		checkButtons();
		
		this.revalidate();
	}
	
	public void setTabbedPane(JTabbedPane tabbedPane) {
		_tabbedPane = tabbedPane;
	}
	
	
	public void setSavedPanel(Saved savePanel){
		_savePanel = savePanel;
	}
	
	public void clear(){
		_bottomBar.setVisible(false);
		_display.setTex("\\text{Solutions will be displayed here}");
		_comp.setTex("");
		_answer.setTex("");
		_stepNumber = 0;
		_tabbedPane.setTitleAt(0, "Solution");
		_solList.clear();
		_ans = null;
		checkButtons();
	}
	
	public void setSolution(List<List<String>> steps, Countable answer){
		_bottomBar.setVisible(true);
		_solList = steps.get(1);
		_compList = steps.get(0);
		_stepNumber = 0;
		setStepNumbers();
		_display.setTex(_solList.get(0));
		_comp.setTex(_compList.get(0));
		_answer.setTex(answer.toLatex());
		_ans = answer;
		checkButtons();
		this.revalidate();
		this.repaint();
	}
	
	public void setError(String error){
		clear();
		_display.setTex(error);
	}
	
	private void setStepNumbers() {
		_stepNumberLabel.setText("<html>Step <font color=#BFD9F2>" + (_stepNumber + 1) + "</font> of " + _solList.size());
		_tabbedPane.setTitleAt(0, "<html>Step <font color=#BFD9F2>" + (_stepNumber + 1) + "</font> of " + _solList.size());
	}
	
	public void next(){
		if(_stepNumber < _solList.size() - 1){
			_stepNumber++;
			_stepNumberLabel.setText("<html>Step <font color=#BFD9F2>" + (_stepNumber + 1) + "</font> of " + _solList.size());
		}
		checkButtons();
		setStepNumbers();
		_display.setTex(_solList.get(_stepNumber));
		_comp.setTex(_compList.get(_stepNumber));
		resetScroll();
		this.repaint();
	}
	
	public void prev(){
		if(_stepNumber > 0){
			_stepNumber--;
		}
		checkButtons();
		setStepNumbers();
		_display.setTex(_solList.get(_stepNumber));
		_comp.setTex(_compList.get(_stepNumber));
		resetScroll();
		this.repaint();
	}
	
	public void checkButtons(){
		_backButton.setEnabled(true);
		_forwardButton.setEnabled(true);
		if(_stepNumber == 0){
			_backButton.setEnabled(false);
		}
		if(_stepNumber == _solList.size()-1 || _solList.size() == 0){
			_forwardButton.setEnabled(false);
		}
	}
	
	public void resetScroll(){
		_scroll.getVerticalScrollBar().setValue(0);
		_scroll.getHorizontalScrollBar().setValue(0);
	}
	
	private class AnswerListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(_ans != null){
				if(e.getClickCount() == 2){
					_savePanel.addCountable(_ans);
				}
			}
		}
	}
	
	private class ExportListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(_ans != null){
				if(e.getClickCount() == 2){
					_savePanel.addCountable(_ans);
				}
			}
		}
	}
	
	private class ForwardBack implements ActionListener{
		StepSolution _ss;
		boolean _forward;
		
		public ForwardBack(StepSolution ss, Boolean forward){
			_ss = ss;
			_forward = forward; 
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(_forward){
				_ss.next();
			} else {
				_ss.prev();
			}
		}
	}
	
}
