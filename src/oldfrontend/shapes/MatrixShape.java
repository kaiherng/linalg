package oldfrontend.shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import javax.swing.JLabel;

import oldfrontend.graphicsengine.Container;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;


import backend.blocks.Matrix;

public class MatrixShape {
	
	private Double[][] _values;
	private Coord _location;
	private Coord _boundingBoxSize;
	private Coord _offSet;
	private int _size;
	private String _latex;
	private TeXIcon _ti;
	private Container _c;

	public MatrixShape(Matrix m, int size, Coord location, Container c) {
		_values = m.getValues();
		_latex = getLatex();
		TeXFormula f = new TeXFormula(_latex);
		_ti = f.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
		_size = size;
		_location = c.getLocation().plus(location);
		_c = c;
	}
	
	public void setLocation(Coord location){
		_location = location;
	}
	
	public Coord getLocation() {
		return _location;
	}
	
	public Coord getBoundingBoxSize() {
		return _boundingBoxSize;
	}
	
	public void onDraw(java.awt.Graphics2D g) {
		_offSet = _c.getLocation();
		_ti.paintIcon(new JLabel(), g, _location.x + _offSet.x, _location.y + _offSet.y);
		_boundingBoxSize = new Coord(_ti.getIconWidth(), _ti.getIconHeight());
	}
	
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
		return b.toString();
	}
	
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
		return b.toString();
	}

}
