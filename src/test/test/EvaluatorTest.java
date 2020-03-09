package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EvaluatorTest {
	StringEvaluator EvaluatorInstance;

	Evaluator EvalInstance;
	@BeforeEach
	void setUp() throws Exception {
		EvaluatorInstance = new StringEvaluator();
		
		EvalInstance = new Evaluator();
	}

	@AfterEach
	void tearDown() throws Exception {
		EvaluatorInstance=null;
		
		EvalInstance = null;
	}

	@Test
	void test() {
		assertEquals("8",EvaluatorInstance.calculate("3 + 5"));
	}
	@Test
	void test2() {
		assertEquals("16.2",EvaluatorInstance.calculate("21-12*2/5"));
	}
	@Test
	void test3() {
		assertEquals("1.0E9",EvaluatorInstance.calculate("100*10000000"));
	}
	@Test
	void test4() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4+3*E"));
	}
	@Test
	void test5() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4+3*?"));
	}
	@Test
	void test6() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4+3*2-"));
	}
	@Test
	void test7() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4+3*2+"));
	}@Test
	void test8() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4+*3*2+"));
	}@Test
	void test9() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4++3*2+"));
	}@Test
	void test10() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4+3**2+"));
	}@Test
	void test11() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4+3*/2+"));
	}
	@Test
	void test12() {
		assertEquals("10",EvalInstance.evaluate("4+3*2"));
	}
	@Test
	void test13() {
		assertEquals("11.28",EvalInstance.evaluate("2*pi+5"));
	}
	@Test
	void test14() {
		assertEquals("Unknown Constant",EvalInstance.evaluate("2*	py"));
	}
	@Test
	void test15() {
		assertEquals("6.28",EvalInstance.evaluate("2*	pi"));
	}
	@Test
	void test16() {
		assertEquals("2.01",EvalInstance.evaluate("1.005+1.005"));
	}
}
