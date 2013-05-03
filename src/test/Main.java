package test;

import java.util.ArrayList;
import java.util.List;

import backend.blocks.Countable;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Numerical;
import backend.blocks.Op;
import backend.blocks.Operation;
import backend.blocks.Scalar;
import backend.computations.infrastructure.Solution;
import backend.computations.infrastructure.Step;
import backend.computations.operations.Determinant;
import backend.computations.operations.MM_Multiply;
import backend.computations.operations.M_Inverse;
import backend.main.ParseNode;
import backend.main.Parser;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Matrix m1 = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
		Matrix m2 = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,2.0},{3.0,4.0}});
		Operation multiply = new Operation(Op.PLUS);
		
		List<Numerical> list = new ArrayList<>();
		list.add(m1);
		list.add(multiply);
		list.add(m2);
		ParseNode result = Parser.parse(list);
		Solution sol = result.getSolution();
		Matrix m = (Matrix) sol.getAnswer();
		printMatrix(m);
	}
	
	public static void printMatrix(Matrix m){
		Double[][] values = m.getValues();
		for(Double[] da : values){
			for(Double d : da){
				System.out.print(d + ",");
			}
			System.out.println();
		}
	}

}
