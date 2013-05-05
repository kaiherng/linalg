package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
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
public class CustomDialog extends JDialog {
	
    private JTextField _textField = new JTextField(5);
    private JLabel _warning = new JLabel(" ");

    public CustomDialog(JFrame frame, String instruction, final DialogListener listener, Component enclosingComponent, final CustomLayerUI customLayerUI) {
        super(frame, true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        customLayerUI.setActive(true);
        _textField.setBorder(CurrentConstants.CUSTOM_DIALOG_TEXTFIELD_BORDER);
        _textField.setBackground(CurrentConstants.CUSTOM_DIALOG_TEXTFIELD_BG);
        _textField.setFont(CurrentConstants.CUSTOM_DIALOG_TEXTFIELD_FONT);
        
        _textField.addKeyListener(new KeyAdapter() {
        	
        	@Override
        	public void keyPressed(KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                	Double value;
    				String s = _textField.getText();
            		if(s == null){
    					_warning.setText("Must enter a number");
    				}
    				try{
    					value = Double.parseDouble(s);
    					listener.doDialogReturn(value);
    					customLayerUI.setActive(false);
    					dispose();
    				} catch (NumberFormatException exception){
    					_warning.setText("Must enter a number");
    					return;
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
        northPanel.add(new JLabel("Enter value to " + instruction + ":"), BorderLayout.WEST);
        
        contentPanel.add(northPanel);
        
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(CurrentConstants.CUSTOM_DIALOG_MIDDLEPANEL_BORDER);
        middlePanel.setBackground(CurrentConstants.CUSTOM_DIALOG_MIDDLEPANEL_BG);
        middlePanel.add(_textField, BorderLayout.WEST);
        Button fillButton = new Button("OK", CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_BG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_FG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_HOVER_BG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_HOVER_FG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_PRESSED_BG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_PRESSED_FG, CurrentConstants.CUSTOM_DIALOG_FILL_BUTTON_BORDER);
        fillButton.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Double value;
				String s = _textField.getText();
        		if(s == null){
					_warning.setText("Must enter a number");
				}
				try{
					value = Double.parseDouble(s);
					listener.doDialogReturn(value);
					customLayerUI.setActive(false);
					dispose();
				} catch (NumberFormatException exception){
					_warning.setText("Must enter a number");
					return;
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