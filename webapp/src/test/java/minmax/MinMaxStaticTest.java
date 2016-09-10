package minmax;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

/**
 * Comprehensive unit tests for MinMaxStatic
 */
public class MinMaxStaticTest extends Assert {

    @Test
    public void testCalculateWithIntegers() {
        Integer[] array = new Integer[]{2, 4, 7, 1, 9, 123, -5};
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(array);
        assertEquals((Integer)(-5), pair.min);
        assertEquals((Integer)123, pair.max);
    }

    @Test
    public void testCalculateWithStrings() {
        String[] array = new String[]{"apple", "zebra", "banana", "aardvark"};
        MinMaxStatic.Pair<String> pair = MinMaxStatic.calculate(array);
        assertEquals("aardvark", pair.min);
        assertEquals("zebra", pair.max);
    }

    @Test
    public void testCalculateWithEmptyArray() {
        Integer[] empty = new Integer[0];
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(empty);
        assertNull(pair.min);
        assertNull(pair.max);
    }

    @Test
    public void testCalculateWithSingleElement() {
        Integer[] single = new Integer[]{42};
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(single);
        assertEquals((Integer)42, pair.min);
        assertEquals((Integer)42, pair.max);
    }

    @Test
    public void testCalculateWithCustomComparator() {
        Integer[] array = new Integer[]{1, 5, 3, 9, 2};
        Comparator<Integer> reverseComparator = (a, b) -> b.compareTo(a);
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(reverseComparator, array);
        assertEquals((Integer)9, pair.min);
        assertEquals((Integer)1, pair.max);
    }

    @Test
    public void testCalculateWithAllSameValues() {
        Integer[] same = new Integer[]{7, 7, 7, 7};
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(same);
        assertEquals((Integer)7, pair.min);
        assertEquals((Integer)7, pair.max);
    }

    @Test
    public void testCalculateWithNegativeNumbers() {
        Integer[] negatives = new Integer[]{-1, -5, -10, -3};
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(negatives);
        assertEquals((Integer)(-10), pair.min);
        assertEquals((Integer)(-1), pair.max);
    }

    @Test
    public void testCalculateWithMixedNumbers() {
        Integer[] mixed = new Integer[]{-5, 10, -20, 30, 0};
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(mixed);
        assertEquals((Integer)(-20), pair.min);
        assertEquals((Integer)30, pair.max);
    }

    @Test
    public void testCalculateWithDoubles() {
        Double[] doubles = new Double[]{1.5, 2.7, 0.3, 9.9, -1.2};
        MinMaxStatic.Pair<Double> pair = MinMaxStatic.calculate(doubles);
        assertEquals(-1.2, pair.min, 0.0001);
        assertEquals(9.9, pair.max, 0.0001);
    }

    @Test
    public void testPairEquals() {
        MinMaxStatic.Pair<Integer> pair1 = new MinMaxStatic.Pair<>(1, 10);
        MinMaxStatic.Pair<Integer> pair2 = new MinMaxStatic.Pair<>(1, 10);
        assertEquals(pair1, pair2);
    }

    @Test
    public void testPairNotEquals() {
        MinMaxStatic.Pair<Integer> pair1 = new MinMaxStatic.Pair<>(1, 10);
        MinMaxStatic.Pair<Integer> pair2 = new MinMaxStatic.Pair<>(2, 10);
        assertNotEquals(pair1, pair2);
    }

    @Test
    public void testPairHashCode() {
        MinMaxStatic.Pair<Integer> pair1 = new MinMaxStatic.Pair<>(1, 10);
        MinMaxStatic.Pair<Integer> pair2 = new MinMaxStatic.Pair<>(1, 10);
        assertEquals(pair1.hashCode(), pair2.hashCode());
    }

    @Test
    public void testPairToString() {
        MinMaxStatic.Pair<Integer> pair = new MinMaxStatic.Pair<>(1, 10);
        String str = pair.toString();
        assertTrue(str.contains("min=1"));
        assertTrue(str.contains("max=10"));
    }

    @Test
    public void testPairEqualsNull() {
        MinMaxStatic.Pair<Integer> pair = new MinMaxStatic.Pair<>(1, 10);
        assertNotEquals(null, pair);
    }

    @Test
    public void testPairEqualsSelf() {
        MinMaxStatic.Pair<Integer> pair = new MinMaxStatic.Pair<>(1, 10);
        assertEquals(pair, pair);
    }

    @Test
    public void testPairWithNullValues() {
        MinMaxStatic.Pair<Integer> pair = new MinMaxStatic.Pair<>(null, null);
        assertNull(pair.min);
        assertNull(pair.max);
    }

    @Test
    public void testPairImmutability() {
        MinMaxStatic.Pair<Integer> pair = new MinMaxStatic.Pair<>(1, 10);
        assertEquals((Integer)1, pair.min);
        assertEquals((Integer)10, pair.max);
        // Fields are final, so they cannot be changed
    }

    @Test
    public void testCalculateWithComparatorEmptyArray() {
        Integer[] empty = new Integer[0];
        Comparator<Integer> comparator = Integer::compareTo;
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(comparator, empty);
        assertNull(pair.min);
        assertNull(pair.max);
    }

    @Test
    public void testCalculateWithCustomComparatorStrings() {
        String[] strings = new String[]{"apple", "zebra", "banana"};
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        MinMaxStatic.Pair<String> pair = MinMaxStatic.calculate(lengthComparator, strings);
        assertEquals("apple", pair.min); // or "zebra", both have length 5
        assertEquals("banana", pair.max);
    }

    @Test
    public void testPairEqualsWithDifferentMax() {
        MinMaxStatic.Pair<Integer> pair1 = new MinMaxStatic.Pair<>(1, 10);
        MinMaxStatic.Pair<Integer> pair2 = new MinMaxStatic.Pair<>(1, 20);
        assertNotEquals(pair1, pair2);
    }

    @Test
    public void testPairEqualsWithDifferentTypes() {
        MinMaxStatic.Pair<Integer> pair1 = new MinMaxStatic.Pair<>(1, 10);
        Object obj = new Object();
        assertNotEquals(pair1, obj);
    }

    @Test
    public void testPairHashCodeWithNullValues() {
        MinMaxStatic.Pair<Integer> pair = new MinMaxStatic.Pair<>(null, null);
        int hashCode = pair.hashCode();
        assertEquals(0, hashCode);
    }

    @Test
    public void testCalculateWithLargeArray() {
        Integer[] large = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            large[i] = i;
        }
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(large);
        assertEquals((Integer)0, pair.min);
        assertEquals((Integer)999, pair.max);
    }

    @Test
    public void testCalculateWithDuplicateMinMax() {
        Integer[] array = new Integer[]{5, 1, 9, 1, 9, 5};
        MinMaxStatic.Pair<Integer> pair = MinMaxStatic.calculate(array);
        assertEquals((Integer)1, pair.min);
        assertEquals((Integer)9, pair.max);
    }
}
