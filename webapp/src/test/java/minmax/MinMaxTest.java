package minmax;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Тест минимума и максимума
 */
public class MinMaxTest extends Assert {
    private static final Integer[] ARRAY_INT;
    private static final String[] ARRAY_STRING;

    static {
        ARRAY_INT = new Integer[]{2, 4, 7, 1, 4, 9, 123, -5};
        ARRAY_STRING = new String[]{"2", "4", "7", "1", "4", "9", "123", "-5"};
    }

    @Test
    public void testToString() throws Exception {
        System.out.println(Arrays.toString(ARRAY_INT));
    }

    @Test
    public void testIntegerCalculate() throws Exception {
        MinMax.Pair p1 = new MinMax<>(ARRAY_INT).calculate();
        assertEquals(new MinMax<>(null).new Pair(-5, 123), p1);

        MinMaxStatic.Pair p2 = MinMaxStatic.calculate(ARRAY_INT);
        assertEquals(new MinMaxStatic.Pair<>(-5, 123), p2);
    }

    @Test
    public void testStringCalculate() throws Exception {
        MinMax.Pair p1 = new MinMax<>(ARRAY_STRING).calculate();
        assertEquals(new MinMax<>(null).new Pair("-5", "9"), p1);

        MinMaxStatic.Pair p2 = MinMaxStatic.calculate(ARRAY_STRING);
        assertEquals(new MinMaxStatic.Pair<>("-5", "9"), p2);
    }

    @Test
    public void testEmptyArray() {
        Integer[] empty = new Integer[0];
        MinMax<Integer> minMax = new MinMax<>(empty);
        MinMax.Pair pair = minMax.calculate();
        assertNull(pair.min);
        assertNull(pair.max);
    }

    @Test
    public void testSingleElement() {
        Integer[] single = new Integer[]{42};
        MinMax<Integer> minMax = new MinMax<>(single);
        MinMax.Pair pair = minMax.calculate();
        assertEquals(42, pair.min);
        assertEquals(42, pair.max);
    }

    @Test
    public void testAllSameValues() {
        Integer[] same = new Integer[]{5, 5, 5, 5};
        MinMax<Integer> minMax = new MinMax<>(same);
        MinMax.Pair pair = minMax.calculate();
        assertEquals(5, pair.min);
        assertEquals(5, pair.max);
    }

    @Test
    public void testCustomComparator() {
        MinMax<Integer> minMax = new MinMax<>(ARRAY_INT);
        Comparator<Integer> reverseComparator = (a, b) -> b.compareTo(a);
        MinMax.Pair pair = minMax.calculate(reverseComparator);
        assertEquals(123, pair.min);
        assertEquals(-5, pair.max);
    }

    @Test
    public void testPairEquals() {
        MinMax<Integer> minMax1 = new MinMax<>(null);
        MinMax<Integer> minMax2 = new MinMax<>(null);
        MinMax.Pair pair1 = minMax1.new Pair(1, 10);
        MinMax.Pair pair2 = minMax2.new Pair(1, 10);
        assertEquals(pair1, pair2);
    }

    @Test
    public void testPairNotEquals() {
        MinMax<Integer> minMax1 = new MinMax<>(null);
        MinMax<Integer> minMax2 = new MinMax<>(null);
        MinMax.Pair pair1 = minMax1.new Pair(1, 10);
        MinMax.Pair pair2 = minMax2.new Pair(2, 10);
        assertNotEquals(pair1, pair2);
    }

    @Test
    public void testPairHashCode() {
        MinMax<Integer> minMax1 = new MinMax<>(null);
        MinMax<Integer> minMax2 = new MinMax<>(null);
        MinMax.Pair pair1 = minMax1.new Pair(1, 10);
        MinMax.Pair pair2 = minMax2.new Pair(1, 10);
        assertEquals(pair1.hashCode(), pair2.hashCode());
    }

    @Test
    public void testPairToString() {
        MinMax<Integer> minMax = new MinMax<>(null);
        MinMax.Pair pair = minMax.new Pair(1, 10);
        String str = pair.toString();
        assertTrue(str.contains("min=1"));
        assertTrue(str.contains("max=10"));
    }

    @Test
    public void testPairEqualsNull() {
        MinMax<Integer> minMax = new MinMax<>(null);
        MinMax.Pair pair = minMax.new Pair(1, 10);
        assertNotEquals(null, pair);
    }

    @Test
    public void testPairEqualsSelf() {
        MinMax<Integer> minMax = new MinMax<>(null);
        MinMax.Pair pair = minMax.new Pair(1, 10);
        assertEquals(pair, pair);
    }

    @Test
    public void testPairWithNullValues() {
        MinMax<Integer> minMax = new MinMax<>(null);
        MinMax.Pair pair = minMax.new Pair(null, null);
        assertNull(pair.min);
        assertNull(pair.max);
    }

    @Test
    public void testNegativeNumbers() {
        Integer[] negatives = new Integer[]{-1, -5, -10, -3};
        MinMax<Integer> minMax = new MinMax<>(negatives);
        MinMax.Pair pair = minMax.calculate();
        assertEquals(-10, pair.min);
        assertEquals(-1, pair.max);
    }

    @Test
    public void testMixedPositiveNegative() {
        Integer[] mixed = new Integer[]{-5, 10, -20, 30, 0};
        MinMax<Integer> minMax = new MinMax<>(mixed);
        MinMax.Pair pair = minMax.calculate();
        assertEquals(-20, pair.min);
        assertEquals(30, pair.max);
    }

    @Test
    public void testDoubleValues() {
        Double[] doubles = new Double[]{1.5, 2.7, 0.3, 9.9, -1.2};
        MinMax<Double> minMax = new MinMax<>(doubles);
        MinMax.Pair pair = minMax.calculate();
        assertEquals(-1.2, pair.min);
        assertEquals(9.9, pair.max);
    }

    @Test
    public void testLongStrings() {
        String[] strings = new String[]{"aaa", "zzz", "bbb", "yyy"};
        MinMax<String> minMax = new MinMax<>(strings);
        MinMax.Pair pair = minMax.calculate();
        assertEquals("aaa", pair.min);
        assertEquals("zzz", pair.max);
    }

    @Test
    public void testMinMaxComparable() {
        MinMaxComparable<Integer> minMax = new MinMaxComparable<>(ARRAY_INT);
        MinMaxComparable<Integer>.Pair pair = minMax.calculate();
        assertEquals((Integer)(-5), pair.min);
        assertEquals((Integer)123, pair.max);
    }

    @Test
    public void testMinMaxComparableEmptyArray() {
        Integer[] empty = new Integer[0];
        MinMaxComparable<Integer> minMax = new MinMaxComparable<>(empty);
        MinMaxComparable<Integer>.Pair pair = minMax.calculate();
        assertNull(pair.min);
        assertNull(pair.max);
    }

    @Test
    public void testMinMaxComparableSingleElement() {
        Integer[] single = new Integer[]{99};
        MinMaxComparable<Integer> minMax = new MinMaxComparable<>(single);
        MinMaxComparable<Integer>.Pair pair = minMax.calculate();
        assertEquals((Integer)99, pair.min);
        assertEquals((Integer)99, pair.max);
    }
}
