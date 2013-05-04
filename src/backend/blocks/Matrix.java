package backend.blocks;

import matrixDraw.MatrixDraw;


/** 
 * The primary matrix datastructure for this project
 * 
 * @author baebi
 */
public class Matrix extends Countable{
	private int _numRows,_numCols;
	private static String _name = "MATRIX";
	private String _id;
	
	// multidimensional array containing double values of matrix (regardless of stated format)
	private Double[][] _internalValues;
	
	// multidimensional array containing representations of _internalValues contingent on stated format
	private String[][] _displayValues;
	
	// multidimensional array containing representations of _internalValues. This is manually set
	private String[][] _customDisplayValues;
	
	
	
	//===================================
	// Matrix Constructors
	//===================================
	
	/** 
	 * Constructor 1 for Matrix (This is if you have all the values you want for the matrix)
	 * 
	 * @param isFraction boolean indicating if the values of the matrix should be displayed
	 * in fraction format
	 * @param isDouble boolean indicating if the values of the matrix should be displayed
	 * in decimal format
	 * @param values the values of this matrix. THE FIRST DIMENSION IS COLUMNS; THE
	 * SECOND DIMENSION IS ROWS
	 */
	public Matrix(DisplayType displayType, Double[][] values) throws IllegalArgumentException {
		super(displayType);
		_numCols = values.length;
		_numRows = values[0].length;
		_internalValues = values;
		_displayValues = new String[_numCols][_numRows];
		for (int i = 0; i < _numCols; i++){
			for (int j = 0; j < _numRows; j++){
				setDisplayIndex(j,i);
			}
		}

		_customDisplayValues = new String[_numCols][_numRows];
	}
	
	
	/** 
	 *  Constructor 2 for Matrix (This is if you know the size of the matrix, but have
	 *  not determined the values to enter)
	 * 
	 * @param isFraction boolean indicating if the values of the matrix should be displayed
	 * in fraction format
	 * @param isDouble boolean indicating if the values of the matrix should be displayed
	 * in decimal format
	 * @param numRows the number of rows in this matrix
	 * @param numCols the number of columns in this matrix
	 */
	public Matrix(DisplayType displayType, int numRows, int numCols){
		super(displayType);
		_numRows = numRows;
		_numCols = numCols;
		_internalValues = new Double[numCols][numRows];
		_customDisplayValues = new String[_numCols][_numRows];
		_displayValues = new String[_numCols][_numRows];
	}
	
	
	
	//===================================================
	// Setters for matrix indices, size, and displayType
	//===================================================
	
	/** Sets the value at a specified index in this matrix
	 * 
	 * @param value the value to set the index to
	 * @param row the row of the index to set
	 * @param col the column of the index to set
	 */
	public void setValueIndex(double value, int row, int col){
		if (row > _numRows - 1 || col > _numCols - 1){
			throw new IllegalArgumentException("ERROR: Index exceeds matrix dimension");
		}
		
		if (row < 0 || col < 0){
			throw new IllegalArgumentException("ERROR: Index must be nonnegative");
		}
		
		_internalValues[col][row] = value;
		setDisplayIndex(row,col);
	}
	

	/** 
	 * Sets the index of the matrix as the user should see it (bases setting on DisplayType)
	 * 
	 * @param row the row of the index to set
	 * @param col the column of the index to set
	 */
	private void setDisplayIndex(int row, int col){
		if (_internalValues[col][row] != null){
			switch (_displayType){
				case DECIMAL:{
					_displayValues[col][row] = Double.toString(_internalValues[col][row]);
					break;
				}
				case WHOLENUMBERFRACTION:{
					// TODO
					break;
				}
				case WHOLENUMBER:{
					_displayValues[col][row] = Integer.toString((int) Math.floor(_internalValues[col][row]));
					break;
				}
				case CUSTOM:{
					// do nothing
					break;
				}
				default:{
					System.out.println(_displayType);
					System.err.println("ERROR: Unrecognized display type");
				}
			}
		}
	}
	
	
	/** 
	 * Sets the index of an entry in _customDisplayValues
	 * 
	 * @param row the row to set
	 * @param col the column to set
	 */
	public void setCustomDisplayIndex(int row, int col, String setting){
		_customDisplayValues[col][row] = setting;
	}
	
	
	/** 
	 * Sets all of the _customDisplayVals at once
	 * 
	 * @param displayVals the matrix of custom strings
	 */
	public void setCustomDisplay(String[][] displayVals){
		if (displayVals.length != _numCols || displayVals[0].length != _numRows){
			throw new IllegalArgumentException("ERROR (Matrix): setCustomDisplay only allows a " +
					"multidimensional array of the same dimenions as this Matrix");
		}
		_customDisplayValues = displayVals;
	}
	
	
	/** 
	 *  Changes the dimension of the matrix. If the dimensions shrink, the values in the higher row or columns are removed.
	 *  If the dimensions increase, additional rows and columns will be initialized to null. Columns will be appended to the
	 *  right of the matrix and rows will be appended to the bottom of the matrix.
	 * 
	 * @param row the new row length
	 * @param col the new column length
	 */
	public void changeDimensions(int rows, int cols){
		Double[][] newMatrix = new Double[cols][rows];
		int leastColumns = (cols > _numCols) ? _numCols : cols;
		int leastRows = (rows > _numRows) ? _numRows : rows;
		
		for(int i = 0; i < leastColumns; i++){
			for(int j=0; j< leastRows; j++){
				newMatrix[i][j] = _internalValues[i][j];
			}
		}
		
		_internalValues = newMatrix;
		_numCols = cols;
		_numRows = rows;
	}
	
	
	/** 
	 * Resets the DisplayType of this matrix and resets all of the _displayValues entries
	 * 
	 * @param displayType the DisplayType to set this matrix to
	 */
	public void setDisplayType(DisplayType displayType){
		_displayType = displayType;
		for (int i = 0; i < _numCols; i++){
			for (int j = 0; j < _numRows; j++){
				setDisplayIndex(j,i);
			}
		}
	}
	
	
			
	//==========================================
	// Getters for matrix values
	//==========================================
	
	/** 
	 * Returns a copy of the values in this matrix
	 * 
	 * @return a multidimensional array containing the value of this matrix
	 */
	public Double[][] getValues(){
		Double[][] toReturn = new Double[_numCols][_numRows]; // we want to return a copy
		for (int i = 0; i < _numCols; i++){
			for (int j =0; j<_numRows; j++){
				if (_internalValues[i][j] == null){
					toReturn[i][j] = null;
				}else{
					toReturn[i][j] = new Double(_internalValues[i][j]);
				}
			}
		}
		return toReturn;
	}
	
	
	/** 
	 * Returns a copy of the display strings of this matrix
	 * 
	 * @return a multidimensional array containing the display values of this matrix
	 */
	public String[][] getDisplayValues(){
		String[][] toReturn = new String[_numCols][_numRows]; // we want to return a copy
		for (int i = 0; i < _numCols; i++){
			for (int j =0; j<_numRows; j++){
				if (_displayValues[i][j] == null){
					toReturn[i][j] = null;
				}else{
					toReturn[i][j] = _displayValues[i][j];
				}
			}
		}
		return toReturn;
	}
	
	
	/** 
	 * Returns a copy of the custom display strings of this matrix
	 * 
	 * @return a multidimensional array containing the custon display values of this matrix
	 */
	public String[][] getCustomDisplayValues(){
		String[][] toReturn = new String[_numCols][_numRows]; // we want to return a copy
		for (int i = 0; i < _numCols; i++){
			for (int j =0; j<_numRows; j++){
				if (_customDisplayValues[i][j] == null){
					toReturn[i][j] = null;
				}else{
					toReturn[i][j] = _customDisplayValues[i][j];
				}
			}
		}
		return toReturn;
	}
	
	/**
	 * @return the name of this class
	 */
	public String getName(){
		return _name;
	}
	
	/**
	 * @param id sets the id of this matrix. Used for testing
	 */
	public void setID(String id){
		_id = id;
	}

	/**
	 * @return returns the id of this matrix. Used for testing
	 */
	public String getID(){
		return _id;
	}
	
	/**
	 * @return the number of rows in this matrix
	 */
	public int getNumRows(){
		return _numRows;
	}
	
	/**
	 * @return the number of columns in this matrix
	 */
	public int getNumCols(){
		return _numCols;
	}
	
	//TODO: SHOULD MOVE ACTUAL MATRIX TO LATEX METHOD HERE!!
	public String toLatex(){
		return MatrixDraw.getCorrectLatex(_displayType,this);
	}

}
