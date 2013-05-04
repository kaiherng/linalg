package matrixDraw;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXIcon;

import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;

public class MatrixDraw extends JPanel{

	private static final long serialVersionUID = 1L;
	private TeXIcon ti_;
	
	private MatrixDraw(){}
	
	public void paint(Graphics g){
		ti_.paintIcon(new JLabel(), g, 0, 0);
	}
	
	
	/**
	 * Given the displayType you want, returns the properly formatted LaTeX string
	 *  
	 * @param displayType the display type you want for the latex matrix
	 */
	public static String getCorrectLatex(DisplayType displayType,Matrix m){
		switch(displayType){
		case DECIMAL:{
			return getLatex(m);
		}
		case WHOLENUMBER:{
			return getLatexWhole(m);
		}
		case WHOLENUMBERFRACTION:{
			// TODO
			return null;
		}
		case CUSTOM:{
			return getLatexCustom(m);
		}
		default:
			System.err.println("ERROR (MatrixDraw.java) : Unrecognized display type");
			return null;
		}
	}
	
	/**
	 * @return the LaTeX string for the matrix where the indices are in custom format
	 */
	private static String getLatexCustom(Matrix m) {
		Double[][] values = m.getValues();
		String[][] customValues = m.getCustomDisplayValues();
		StringBuilder b = new StringBuilder();
		b.append("\\begin{bmatrix} ");
		int count = 0;
		for(String[] i : customValues){
			for(String j : i){
				b.append(j.toString());
				if(count != i.length - 1){
					b.append(" & ");
					count++;
				} else {
					count = 0;
				}
			}
			if(!i.equals(values[values.length - 1])){
				b.append("\\\\");
			}
		}
		b.append("\\end{bmatrix}");
		return b.toString();
	}

	
	/**
	 * @return the LaTeX string for the matrix where the indices are in decimal format
	 */
	public static String getLatex(Matrix m){
		Double[][] values = m.getValues();
		StringBuilder b = new StringBuilder();
		b.append("\\begin{bmatrix} ");
		for(int i = 0; i < values[0].length; i++){
			for(int j = 0; j < values.length; j++){
				String num = values[j][i].toString();
				boolean foundDecimal = false;
				int numAfterDecimal = 0;
				for (int k = 0; k < num.length(); k++){
					if (num.charAt(k) == '.'){
						foundDecimal = true;
					}
					if (foundDecimal){
						numAfterDecimal++;
					}
					if (numAfterDecimal > 5){
						num = num.substring(0, k) + "...";
						break;
					}
				}

				b.append(num + " ");
				if(j != values.length -1){
					b.append(" & ");
				}
			}
			if(i != values[0].length - 1){
				b.append("\\\\");
			}
		}
		
		b.append("\\end{bmatrix}");
		return b.toString();
	}
	
	
	/**
	 * @return the LaTeX string for the matrix where the indices are in wholenumber format
	 */
	public static String getLatexWhole(Matrix m){
		Double[][] values = m.getValues();
		StringBuilder b = new StringBuilder();
		b.append("\\begin{bmatrix} ");
		int count = 0;
		for(Double[] i : values){
			for(Double j : i){
				b.append(j.intValue());
				if(count != i.length - 1){
					b.append(" & ");
					count++;
				} else {
					count = 0;
				}
			}
			if(!i.equals(values[values.length - 1])){
				b.append("\\\\");
			}
		}
		b.append("\\end{bmatrix}");
		return b.toString();
	}

}
