package backend.main;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import backend.blocks.Bracket;
import backend.blocks.Countable.DisplayType;
import backend.blocks.Matrix;
import backend.blocks.Numerical;
import backend.blocks.Op;
import backend.blocks.Operation;

public class ParserTest {
	private Matrix matrixA = new Matrix(DisplayType.DECIMAL, new Double[][]{{1.0,1.0},{1.0,1.0}});
	private Bracket openBracket = new Bracket(true);
	private Bracket closeBracket = new Bracket(false);
	private Operation plus = new Operation(Op.PLUS);
	private Operation times = new Operation(Op.TIMES);
	
	
	//====================================
	// Test removeOuterBrackets
	//====================================
	
	@Test
	// Tests what happens with no brackets
	// A+A
	public void noBracketTest() {
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		Operation o = new Operation(Op.PLUS);
		l.add(o);
		l.add(matrixA);
		Parser.removeOuterBrackets(l);
		assertTrue(l.size() == 3);
	}
	
	@Test
	// Tests when there is only a beginning and end bracket around computation
	// (A+A)
	public void simpleBracketTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(new Bracket(true));
		l.add(matrixA);
		Operation o = new Operation(Op.PLUS);
		l.add(o);
		l.add(matrixA);
		l.add(new Bracket(false));
		l = Parser.removeOuterBrackets(l);
		assertTrue(l.size() == 3);
	}
	
	@Test
	// Tests when there are two beginning brackets and two end bracket around computation
	// ((A+A))
	public void doubleStartEndBracketTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(openBracket);
		l.add(openBracket);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		l.add(closeBracket);
		l = Parser.removeOuterBrackets(l);
		assertTrue(l.size() == 5);
		
		List<Numerical> expect = new ArrayList<>();
		expect.add(openBracket);
		expect.add(matrixA);
		expect.add(plus);
		expect.add(matrixA);
		expect.add(closeBracket);
		assertTrue(l.equals(expect));
	}
	
	@Test
	// Tests when beginning bracket doesn't surround whole equation
	// (A+B)+A
	public void ignoreStartBracketTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(openBracket);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		l.add(plus);
		l.add(matrixA);
		l = Parser.removeOuterBrackets(l);
		assertTrue(l.size() == 7);
		
		List<Numerical> expect = new ArrayList<>();
		expect.add(openBracket);
		expect.add(matrixA);
		expect.add(plus);
		expect.add(matrixA);
		expect.add(closeBracket);
		expect.add(plus);
		expect.add(matrixA);
		assertTrue(l.equals(expect));
	}
	
	@Test
	// Tests when end bracket doesn't surround whole equation
	// A+(A+A)
	public void ignoreEndBracketTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		l.add(plus);
		l.add(openBracket);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		l = Parser.removeOuterBrackets(l);
		assertTrue(l.size() == 7);
		
		List<Numerical> expect = new ArrayList<>();
		expect.add(matrixA);
		expect.add(plus);
		expect.add(openBracket);
		expect.add(matrixA);
		expect.add(plus);
		expect.add(matrixA);
		expect.add(closeBracket);
		assertTrue(l.equals(expect));
	}
	
	@Test
	// Multibracket test
	// ((A+(A+B)) + A)
	public void multiBracketTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(openBracket);
		l.add(openBracket);
		l.add(matrixA);
		l.add(plus);
		l.add(openBracket);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		l.add(closeBracket);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		
		l = Parser.removeOuterBrackets(l);
		List<Numerical> expect = new ArrayList<>();
		expect.add(openBracket);
		expect.add(matrixA);
		expect.add(plus);
		expect.add(openBracket);
		expect.add(matrixA);
		expect.add(plus);
		expect.add(matrixA);
		expect.add(closeBracket);
		expect.add(closeBracket);
		expect.add(plus);
		expect.add(matrixA);
		assertTrue(expect.equals(l));
	}
	
	@Test
	// Only two brackets
	// ()
	public void twoBracketsTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(openBracket);
		l.add(closeBracket);
		l = Parser.removeOuterBrackets(l);
		assertTrue(l.size() == 0);
	}
	
	
	
	//==================================
	// Test findLeastPreferentialOp
	//==================================
	
	@Test
	// finds the only operation 
	// A + A
	public void singleOpTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		assertTrue(Parser.findLeastPreferentialOp(l)==1);
	}
	
	
	@Test
	// chooses + over *
	// A * A + A
	public void twoOpTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		l.add(times);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		assertTrue(Parser.findLeastPreferentialOp(l)==3);
	}
	
	
	@Test
	// chooses * over + when + surrounded by brackets
	// A * (A + A)
	public void bracketTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		l.add(times);
		l.add(openBracket);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		assertTrue(Parser.findLeastPreferentialOp(l)==1);
	}
	
	
	@Test
	// chooses first + over second +
	// A * A + A
	public void leftToRightTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		assertTrue(Parser.findLeastPreferentialOp(l)==1);
	}
	
	
	
	//================================
	// Test createSortedTree // TODO
	//================================
	
	
	//================================
	// Test checkBrackets
	//================================
	
	@Test
	// no brackets
	public void noBracketsTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		Parser.checkBrackets(l);
	}
	
	@Test
	// legal brackets
	public void legalBracketsTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(openBracket);
		l.add(openBracket);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		Parser.checkBrackets(l);
	}
	
	@Test
	// equal number of open and close brackets, but the first bracket
	// is closed
	public void firstBracketClosedTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(closeBracket);
		l.add(openBracket);
		l.add(openBracket);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		l.add(openBracket);
		try{
			Parser.checkBrackets(l);
		}catch(IllegalArgumentException e){
			e.getMessage().equals("ERROR: Close bracket without open bracket");
		}
	}
	
	@Test
	// unequal number of open and close brackets
	public void unequalBracketsTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(openBracket);
		l.add(openBracket);
		l.add(matrixA);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		l.add(plus);
		l.add(matrixA);
		l.add(closeBracket);
		l.add(openBracket);
		try{
			Parser.checkBrackets(l);
		}catch(IllegalArgumentException e){
			e.getMessage().equals("ERROR: Every open bracket must have a close bracket and vice versa");
		}
	}
	
	
	//================================
	// Test validInput // TODO
	//================================
	
	@Test
	// error on computation size of 0
	public void sizeZeroComputationTest(){
		List<Numerical> l = new ArrayList<>();
		try{
			Parser.checkValidInput(l);
			fail();
		}catch (IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Require expression to compute"));
		}
	}
	
	@Test
	// error one Numerical size computation containing unary operator without operand
	public void unaryOpWithoutOperandTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(new Operation(Op.DETERMINANT));
		try{
			Parser.checkValidInput(l);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Unary operator requires operand"));
		}
	}
	
	@Test
	// error  unary operator without operand
	public void unaryOpWithoutOperandTest2(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		l.add(plus);
		l.add(new Operation(Op.DETERMINANT));
		try{
			Parser.checkValidInput(l);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Unary operator requires operand"));
		}
	}
	
	@Test
	// error one Numerical size computation containing unary operator without operand
	public void binaryOpWithoutOperandTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(new Operation(Op.PLUS));
		try{
			Parser.checkValidInput(l);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Binary operator requires two operands"));
		}
	}
	
	@Test
	// error one Numerical size computation containing unary operator without operand
	public void noExpressionTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		try{
			Parser.checkValidInput(l);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Require expression to compute"));
		}
	}
	
	@Test
	// error one Numerical size computation containing unary operator without operand
	public void oneBracketTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(new Bracket(true));
		try{
			Parser.checkValidInput(l);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Every open bracket must have a close bracket and vice versa"));
		}
	}
	
	@Test
	// error on binary op without second arg
	public void binaryOpWithoutSecondArgTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		l.add(plus);
		try{
			Parser.checkValidInput(l);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Binary operator requires two operands"));
		}
	}
	
	@Test
	// error on two binary op's in row
	public void twoConsecutiveBinaryOpsTest(){
		List<Numerical> l = new ArrayList<>();
		l.add(matrixA);
		l.add(plus);
		l.add(plus);
		l.add(matrixA);
		try{
			Parser.checkValidInput(l);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Binary operation requires two operands"));
		}
	}
	
	@Test
	// Error on binary op without first arg
	public void binaryOpWithoutFirstArg(){
		List<Numerical> l = new ArrayList<>();
		l.add(plus);
		l.add(matrixA);
		try{
			Parser.checkValidInput(l);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("ERROR: Binary operation requires two operands"));
		}
	}
	
	// Test that brackets don't screw stuff up
	
	//@Test 
	// No error on two unary op's in a row
	
	
}
