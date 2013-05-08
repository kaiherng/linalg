package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frontend.utils.CustomLayerUI;

@SuppressWarnings("serial")
public class StringDialog extends JDialog {
	
    private JTextField _textField = new JTextField(5);
    private JLabel _warning = new JLabel(" ");

    public StringDialog(JFrame frame, final DialogStringListener listener, Component enclosingComponent, final CustomLayerUI customLayerUI) {
        super(frame, true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        customLayerUI.setActive(true);
        frame.repaint();
        _textField.setBorder(CurrentConstants.CUSTOM_DIALOG_TEXTFIELD_BORDER);
        _textField.setBackground(CurrentConstants.CUSTOM_DIALOG_TEXTFIELD_BG);
        _textField.setFont(CurrentConstants.CUSTOM_DIALOG_TEXTFIELD_FONT);
        
        _textField.addKeyListener(new KeyAdapter() {
        	
        	@Override
        	public void keyPressed(KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
    				String s = _textField.getText();
            		if(s.length() == 0){
    					_warning.setText("Must enter something");
    				} //Note: we are not checking for the types of characters entered here! No sanitization at all!!!
            		else {
            			listener.doDialogReturn(s);
            			customLayerUI.setActive(false);
                    	dispose();
            		}
            		
                }
                else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                	customLayerUI.setActive(false);
                	dispose();
                }
                
            }
        });
        
        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(CurrentConstants.CUSTOM_DIALOG_CONTENTPANEL_BORDER);
        contentPanel.setBackground(CurrentConstants.CUSTOM_DIALOG_CONTENTPANEL_BG);
        
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBorder(CurrentConstants.CUSTOM_DIALOG_NORTHPANEL_BORDER);
        northPanel.setBackground(CurrentConstants.CUSTOM_DIALOG_NORTHPANEL_BG);
        northPanel.add(new JLabel("Matrix Name:"), BorderLayout.WEST);
        
        contentPanel.add(northPanel);
        
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(CurrentConstants.CUSTOM_DIALOG_MIDDLEPANEL_BORDER);
        middlePanel.setBackground(CurrentConstants.CUSTOM_DIALOG_MIDDLEPANEL_BG);
        middlePanel.add(_textField, BorderLayout.WEST);
        Button fillButton = new Button("OK", CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_BG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_FG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_HOVER_BG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_HOVER_FG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_PRESSED_BG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_PRESSED_FG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_BORDER);
        fillButton.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
				String s = _textField.getText();
        		if(s.length() == 0){
					_warning.setText("Must enter something");
				} //Note: we are not checking for the types of characters entered here! No sanitization at all!!!
        		else {
        			listener.doDialogReturn(s);
        			customLayerUI.setActive(false);
                	dispose();
        		}
        	}
        	
        });
                
        Button cancelButton = new Button("CANCEL", CurrentConstants.CUSTOM_DIALOG_CANCEL_BUTTON_BG, CurrentConstants.CUSTOM_DIALOG_CANCEL_BUTTON_FG, CurrentConstants.CUSTOM_DIALOG_CANCEL_BUTTON_HOVER_BG, CurrentConstants.CUSTOM_DIALOG_CANCEL_BUTTON_HOVER_FG, CurrentConstants.CUSTOM_DIALOG_CANCEL_BUTTON_PRESSED_BG, CurrentConstants.CUSTOM_DIALOG_CANCEL_BUTTON_PRESSED_FG, CurrentConstants.CUSTOM_DIALOG_CANCEL_BUTTON_BORDER);
        cancelButton.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		customLayerUI.setActive(false);
        		dispose();
        	}
        	
        });
        
        
        middlePanel.add(fillButton, BorderLayout.CENTER);
        middlePanel.add(cancelButton, BorderLayout.EAST);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(CurrentConstants.CUSTOM_DIALOG_SOUTHPANEL_BORDER);
        southPanel.setBackground(CurrentConstants.CUSTOM_DIALOG_SOUTHPANEL_BG);
        
        _warning.setForeground(CurrentConstants.CUSTOM_DIALOG_WARNING_FG);
        southPanel.add(_warning, BorderLayout.WEST);
        
        contentPanel.add(northPanel, BorderLayout.NORTH);
        contentPanel.add(middlePanel, BorderLayout.CENTER);
        contentPanel.add(southPanel, BorderLayout.SOUTH);
        
        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                _textField.requestFocusInWindow();
            }
        });

        pack();
		setLocationRelativeTo(enclosingComponent);
		setVisible(true);
        

    }

    
}