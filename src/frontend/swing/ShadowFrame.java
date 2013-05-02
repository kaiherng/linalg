package frontend.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ShadowFrame {

    private JFrame frame;

    public ShadowFrame() {
        initComponents();
    }

    public static void main(String[] args) {
                new ShadowFrame();
    }

    private void initComponents() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(0,0,0,0));
        frame.setTitle("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//app exited when frame closes
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(0, 0, 0, 0);
        frame.add(new RoundedPanel(), gc);

        //pack frame (size components to preferred size)
        frame.pack();
        frame.setVisible(true);//make frame visible
    }
}

class RoundedPanel extends JPanel {

    /**
     * Stroke size. it is recommended to set it to 1 for better view
     */
    protected int strokeSize = 1;
    /**
     * Color of shadow
     */
    protected Color shadowColor = Color.black;
    /**
     * Sets if it drops shadow
     */
    protected boolean shady = true;
    /**
     * Sets if it has an High Quality view
     */
    protected boolean highQuality = true;
    /**
     * Double values for Horizontal and Vertical radius of corner arcs
     */
    protected Dimension arcs = new Dimension(20, 20);
    //protected Dimension arcs = new Dimension(20, 20);//creates curved borders and panel
    /**
     * Distance between shadow border and opaque panel border
     */
    protected int shadowGap = 10;
    /**
     * The offset of shadow.
     */
    protected int shadowOffset = 4;
    /**
     * The transparency value of shadow. ( 0 - 255)
     */
    protected int shadowAlpha = 150;
    int width = 300, height = 300;

    public RoundedPanel() {
        super();
        setBorder(new EmptyBorder(0,0,0,0));
        setLayout(new FlowLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(new Dimension(200,200));
        mainPanel.setBorder(new LineBorder(Color.BLACK, 1));
        mainPanel.add(new JLabel("hey"));
        add(mainPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color shadowColorA = new Color(shadowColor.getRed(),
                shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

//        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws shadow borders if any.
        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset,// X position
                    shadowOffset,// Y position
                    width - strokeSize - shadowOffset, // width
                    height - strokeSize - shadowOffset, // height
                    arcs.width, arcs.height);// arc Dimension
        } else {
            shadowGap = 1;
        }

        //Draws the rounded opaque panel with borders.
        graphics.setColor(Color.PINK);
        graphics.fillRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(new Color(0,0,0,0));
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);

        //Sets strokes to default, is better.
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}