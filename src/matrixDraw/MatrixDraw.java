package matrixDraw;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;

public class MatrixDraw extends JPanel{

	private static final long serialVersionUID = 1L;
	private Double[][] _values;
	private TeXIcon ti_;
	private String[][] _customValues;

	/**
	 * Constructs a MatrixDraw object which can return a string of latex describing a matrix
	 * 
	 * @param m the matrix to LaTeXify
	 */
	public MatrixDraw(Matrix m) {
		_values = m.getValues();
		_customValues = m.getCustomDisplayValues();
		TeXFormula f = new TeXFormula(getLatexWhole());
		ti_ = f.createTeXIcon(TeXConstants.STYLE_DISPLAY, 30);
		this.setPreferredSize(new Dimension(ti_.getIconWidth(), ti_.getIconHeight()));
	}
	
	public void paint(Graphics g){
		ti_.paintIcon(new JLabel(), g, 0, 0);
	}
	
	
	/**
	 * Given the displayType you want, returns the properly formatted LaTeX string
	 *  
	 * @param displayType the display type you want for the latex matrix
	 */
	public String getCorrectLatex(DisplayType displayType){
		switch(displayType){
		case DECIMAL:{
			return getLatex();
		}
		case WHOLENUMBER:{
			return getLatexWhole();
		}
		case WHOLENUMBERFRACTION:{
			// TODO
			return null;
		}
		case CUSTOM:{
			return getLatexCustom();
		}
		default:
			System.err.println("ERROR (MatrixDraw.java) : Unrecognized display type");
			return null;
		}
	}
	
	
	/**
	 * @return the LaTeX string for the matrix where the indices are in custom format
	 */
	private String getLatexCustom() {
		StringBuilder b = new StringBuilder();
		b.append("\\begin{pmatrix} ");
		for(String[] i : _customValues){
			for(String j : i){
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
//		System.out.println(b.toString());
		return b.toString();
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
//		System.out.println(b.toString());
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
//		System.out.println(b.toString());
		return b.toString();
	}

}
