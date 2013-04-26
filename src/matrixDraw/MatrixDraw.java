package matrixDraw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import backend.blocks.Matrix;

public class MatrixDraw extends JPanel{
	
	Double[][] _values;
	TeXIcon ti_;

	public MatrixDraw(Matrix m) {
		_values = m.getValues();
		TeXFormula f = new TeXFormula(getLatexWhole());
		ti_ = f.createTeXIcon(TeXConstants.STYLE_DISPLAY, 30);
		this.setPreferredSize(new Dimension(ti_.getIconWidth(), ti_.getIconHeight()));
	}
	
	public void paint(Graphics g){
		ti_.paintIcon(new JLabel(), g, 0, 0);
	}
	
	/**
	 * @return the LaTeX string for the matrix where the indices are in decimal format
	 */
	public String getLatex(){
		StringBuilder b = new StringBuilder();
		b.append("\\begin{pmatrix} ");
		for(Double[] i : _values){
			for(Double j : i){
				b.append(j.toString());
				if(!j.equals(i[i.length-1])){
					b.append(" & ");
				}
			}
			if(!i.equals(_values[_values.length - 1])){
				b.append("\\\\");
			}
		}
		b.append("\\end{pmatrix}");
		System.out.println(b.toString());
		return b.toString();
	}
	
	/**
	 * @return the LaTeX string for the matrix where the indices are in wholenumber format
	 */
	public String getLatexWhole(){
		StringBuilder b = new StringBuilder();
		b.append("\\begin{pmatrix} ");
		for(Double[] i : _values){
			for(Double j : i){
				b.append(j.intValue());
				if(!j.equals(i[i.length-1])){
					b.append(" & ");
				}
			}
			if(!i.equals(_values[_values.length - 1])){
				b.append("\\\\");
			}
		}
		b.append("\\end{pmatrix}");
		System.out.println(b.toString());
		return b.toString();
	}

}
