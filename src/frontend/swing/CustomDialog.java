package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
//property change stuff

/* 1.4 example used by DialogDemo.java. */
public class CustomDialog extends JDialog {
	
    private String _typedText = null;
    private JTextField _textField = new JTextField(5);
    private JLabel _warning = new JLabel(" ");


    public CustomDialog(Frame frame, String instruction, final DialogListener listener, Component enclosingComponent) {
        super(frame, true);
        setUndecorated(true);
        setBackground(Color.blue);

        _textField.setBorder(new EmptyBorder(0,10,0,0));
        _textField.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Dialog", Font.BOLD, 16);
        _textField.setFont(font);
        
        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10,10,10,10));
        
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBorder(new EmptyBorder(0,0,10,0));
        northPanel.add(new JLabel("Enter value to " + instruction + ":"), BorderLayout.WEST);
        
        contentPanel.add(northPanel);
        
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.add(_textField, BorderLayout.WEST);
        Button fillButton = new Button("OK", Color.BLACK, Color.white, Color.BLACK, Color.white, Color.black, Color.white, new EmptyBorder(10,10,10,10));
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
					dispose();
				} catch (NumberFormatException exception){
					_warning.setText("Must enter a number");
					return;
				}
				
        	}
        	
        });
        
        Button cancelButton = new Button("CANCEL", Color.DARK_GRAY, Color.white, Color.BLACK, Color.white, Color.black, Color.white, new EmptyBorder(10,10,10,10));
        cancelButton.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dispose();
        	}
        	
        });
        
        
        middlePanel.add(fillButton, BorderLayout.CENTER);
        middlePanel.add(cancelButton, BorderLayout.EAST);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(new EmptyBorder(10,0,0,0));
        _warning.setBackground(new Color(0,0,0,0));
        _warning.setForeground(Color.RED);
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