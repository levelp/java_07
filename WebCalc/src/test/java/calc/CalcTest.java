package calc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Comprehensive unit tests for Calc class
 */
public class CalcTest extends Assert {

    private Calc calc;

    @Before
    public void setUp() {
        calc = new Calc();
    }

    // Tests for sum method

    @Test
    public void testSumPositiveNumbers() {
        double result = calc.sum("5.5", "3.3");
        assertEquals(8.8, result, 0.0001);
    }

    @Test
    public void testSumNegativeNumbers() {
        double result = calc.sum("-5.5", "-3.3");
        assertEquals(-8.8, result, 0.0001);
    }

    @Test
    public void testSumMixedNumbers() {
        double result = calc.sum("10.5", "-3.5");
        assertEquals(7.0, result, 0.0001);
    }

    @Test
    public void testSumZeros() {
        double result = calc.sum("0", "0");
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testSumWithZero() {
        double result = calc.sum("5.5", "0");
        assertEquals(5.5, result, 0.0001);
    }

    @Test
    public void testSumIntegers() {
        double result = calc.sum("5", "3");
        assertEquals(8.0, result, 0.0001);
    }

    @Test
    public void testSumLargeNumbers() {
        double result = calc.sum("999999.999", "1000000.001");
        assertEquals(2000000.0, result, 0.0001);
    }

    @Test
    public void testSumVerySmallNumbers() {
        double result = calc.sum("0.0001", "0.0002");
        assertEquals(0.0003, result, 0.000001);
    }

    @Test(expected = NumberFormatException.class)
    public void testSumInvalidFirstParameter() {
        calc.sum("abc", "5");
    }

    @Test(expected = NumberFormatException.class)
    public void testSumInvalidSecondParameter() {
        calc.sum("5", "xyz");
    }

    @Test(expected = NumberFormatException.class)
    public void testSumBothInvalidParameters() {
        calc.sum("abc", "xyz");
    }

    @Test(expected = NumberFormatException.class)
    public void testSumEmptyFirstParameter() {
        calc.sum("", "5");
    }

    @Test(expected = NullPointerException.class)
    public void testSumNullFirstParameter() {
        calc.sum(null, "5");
    }

    @Test(expected = NullPointerException.class)
    public void testSumNullSecondParameter() {
        calc.sum("5", null);
    }

    // Tests for mul method

    @Test
    public void testMulPositiveNumbers() {
        double result = calc.mul("5.5", "2.0");
        assertEquals(11.0, result, 0.0001);
    }

    @Test
    public void testMulNegativeNumbers() {
        double result = calc.mul("-5.0", "-2.0");
        assertEquals(10.0, result, 0.0001);
    }

    @Test
    public void testMulMixedNumbers() {
        double result = calc.mul("5.0", "-2.0");
        assertEquals(-10.0, result, 0.0001);
    }

    @Test
    public void testMulWithZero() {
        double result = calc.mul("5.5", "0");
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testMulZeros() {
        double result = calc.mul("0", "0");
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testMulWithOne() {
        double result = calc.mul("5.5", "1");
        assertEquals(5.5, result, 0.0001);
    }

    @Test
    public void testMulIntegers() {
        double result = calc.mul("5", "3");
        assertEquals(15.0, result, 0.0001);
    }

    @Test
    public void testMulLargeNumbers() {
        double result = calc.mul("1000.0", "2000.0");
        assertEquals(2000000.0, result, 0.0001);
    }

    @Test
    public void testMulVerySmallNumbers() {
        double result = calc.mul("0.1", "0.2");
        assertEquals(0.02, result, 0.0001);
    }

    @Test
    public void testMulDecimalNumbers() {
        double result = calc.mul("2.5", "4.5");
        assertEquals(11.25, result, 0.0001);
    }

    @Test(expected = NumberFormatException.class)
    public void testMulInvalidFirstParameter() {
        calc.mul("abc", "5");
    }

    @Test(expected = NumberFormatException.class)
    public void testMulInvalidSecondParameter() {
        calc.mul("5", "xyz");
    }

    @Test(expected = NumberFormatException.class)
    public void testMulBothInvalidParameters() {
        calc.mul("abc", "xyz");
    }

    @Test(expected = NumberFormatException.class)
    public void testMulEmptyFirstParameter() {
        calc.mul("", "5");
    }

    @Test(expected = NullPointerException.class)
    public void testMulNullFirstParameter() {
        calc.mul(null, "5");
    }

    @Test(expected = NullPointerException.class)
    public void testMulNullSecondParameter() {
        calc.mul("5", null);
    }

    // Edge cases

    @Test
    public void testSumInfinity() {
        double result = calc.sum(String.valueOf(Double.MAX_VALUE), String.valueOf(Double.MAX_VALUE));
        assertEquals(Double.POSITIVE_INFINITY, result, 0.0001);
    }

    @Test
    public void testMulInfinity() {
        double result = calc.mul(String.valueOf(Double.MAX_VALUE), "2");
        assertEquals(Double.POSITIVE_INFINITY, result, 0.0001);
    }

    @Test
    public void testSumScientificNotation() {
        double result = calc.sum("1e10", "2e10");
        assertEquals(3e10, result, 1);
    }

    @Test
    public void testMulScientificNotation() {
        double result = calc.mul("1e5", "2e5");
        assertEquals(2e10, result, 1);
    }
}
