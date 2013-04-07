package backend.blocks;


/** The primary matrix datastructure for this project
 * 
 * @author baebi
 */
class Matrix extends Countable{
	private int _numRows,_numCols;
	
	// multidimensional array containing double values of matrix (regardless of stated format)
	private Double[][] _internalValues;
	
	// multidimensional array containing representations of _internalValues contingent on stated format
	private String[][] _displayValues;
	
	
	/** Constructor 1 for Matrix (This is if you have all the values you want for the matrix)
	 * 
	 * @param isFraction boolean indicating if the values of the matrix should be displayed
	 * in fraction format
	 * @param isDouble boolean indicating if the values of the matrix should be displayed
	 * in decimal format
	 * @param values the values of this matrix. THE FIRST DIMENSION IS COLUMNS; THE
	 * SECOND DIMENSION IS ROWS
	 */
	public Matrix(boolean isFraction, boolean isDouble, Double[][] values) throws IllegalArgumentException {
		_isFraction = isFraction;
		_isDouble = isDouble;
		_numCols = values.length;
		_numRows = values[0].length;
		_internalValues = values;
	}
	
	
	/** Constructor 2 for Matrix (This is if you know the size of the matrix, but have
	 *  not determined the values to enter)
	 * 
	 * @param isFraction boolean indicating if the values of the matrix should be displayed
	 * in fraction format
	 * @param isDouble boolean indicating if the values of the matrix should be displayed
	 * in decimal format
	 * @param numRows the number of rows in this matrix
	 * @param numCols the number of columns in this matrix
	 */
	public Matrix(boolean isFraction, boolean isDouble, int numRows, int numCols){
		_isFraction = isFraction;
		_isDouble = isDouble;
		_numRows = numRows;
		_numCols = numCols;
		_internalValues = new Double[numCols][numRows];
	}
	
	
	/** Changes the dimension of the matrix. If the dimensions shrink, the values in the higher row or columns are removed.
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
	
	
	/** Sets the index of the matrix as the user should see it
	 * 
	 * @param row the row of the index to set
	 * @param col the column of the index to set
	 */
	private void setDisplayIndex(int row, int col){
		// TODO
	}
			
	
	/** Returns a copy of the values in this matrix
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
	

}