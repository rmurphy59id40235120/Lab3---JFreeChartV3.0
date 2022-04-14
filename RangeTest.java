package org.jfree.data;

import org.junit.Test;

import static org.junit.Assert.fail;

import org.jfree.data.Range;
import junit.framework.TestCase;

public class RangeTest extends TestCase {
	
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////// Central Value Method Test ///////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////

	// TC 1
	@Test
	public void testCentralValueReturnsCorrectCentralValue() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("The central value of 1.0 and 10.0 is 5.5, "
				+ "expected 5.5", 5.5, rangeTest.getCentralValue());
	}
	
	// TC 2
	@Test
	public void testCentralValueNegativeRangeReturnsCorrectCentralValue() {
		Range rangeTest = new Range(-10.0, -1.0);
		assertEquals("The central value of -10.0 and -1.0 is -5.5, "
				+ "expected -5.5", -5.5, rangeTest.getCentralValue());
	}
	
	// TC 3
	@Test
	public void testCentralValueRangeValuesEqualReturnsCorrectCentralValue() {
		Range rangeTest = new Range(10.0, 10.0);
		assertEquals("The central value of 10.0 and 10.0 is 10.0, "
				+ "expected 10.0", 10.0, rangeTest.getCentralValue());
	}
	
	// TC 4
	@Test
	public void testCentralValueNullRangeObjectThrowsNullPointerException() {
		Range rangeTest = null;
		try {
			rangeTest.getCentralValue();
			fail("No exception thrown, expected NullPointerException to be thrown");
		} catch (NullPointerException e) {
			return;
		}
	}
		
	// TC 5
	@Test
	public void testCentralValueNegativeAndPositiveValuesReturnsCorrectCentralValue() {
		Range rangeTest = new Range(-10.0, 5.0);
		assertEquals("The central value of -10.0 and 5.0 is -2.5, "
				+ "expected -2.5", -2.5, rangeTest.getCentralValue());
	}
	
	// TC 6
	@Test
	public void testCentralValueRangeIntersectsAtZeroReturnsZero() {
		Range rangeTest = new Range(-10.0, 10.0);
		assertEquals("The central value of -10.0 and -10.0 is 0, "
				+ "expected 0", 0.00, rangeTest.getCentralValue());
	}
	
	// TC 7
	@Test
	public void testCentralValueInvalidRangeThrowsIllegalArgumentException() {
		try {
			Range rangeTest = new Range(10.0, 1.0);
			rangeTest.getCentralValue();
			fail("No exception thrown, expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////// Combine Method Tests ///////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	
	// TC 1
	@Test
	public void testCombineValidFirstInputValidSecondInput() {
		Range firstRange = new Range(2.0, 5.0);
		Range secondRange = new Range(1.0, 4.0);
		Range newRange = Range.combine(firstRange, secondRange);
		assertEquals("The combined range is expected to contain 1.0 from secondRange"
				+ " and 5.0 from firstRange", new Range(1.0, 5.0), newRange);
	}
	
	// TC 2
	@Test
	public void testCombineValidFirstInputNullSecondInput() {
		Range firstRange = new Range(1.0, 5.0);
		Range secondRange = null;
		Range newRange = Range.combine(firstRange, secondRange);
		assertEquals("firstRange expected to be returned", new Range(1.0, 5.0), newRange);
	}
	
	// TC 3
	@Test
	public void testCombineNullFirstInputValidSecondInput() {
		Range firstRange = null;
		Range secondRange = new Range(1.0, 5.0);
		Range newRange = Range.combine(firstRange, secondRange);
		assertEquals("secondRange expected to be returned", new Range(1.0, 5.0), newRange);
	}
	
	// TC 4
	@Test
	public void testCombineNullFirstInputNullSecondInput() {
		Range firstRange = null;
		Range secondRange = null;
		Range newRange = Range.combine(firstRange, secondRange);
		assertEquals("null expected to be returned", null, newRange);
	}
	
	// TC 5
	@Test
	public void testCombineNegativeRangeValueInputReturnsNegativeCombinedRange() {
		Range firstRange = new Range(2.0, 5.0);
		Range secondRange = new Range(-1.0, 4.0);
		Range newRange = Range.combine(firstRange, secondRange);
		assertEquals("The combined range is expected to contain a negative 1.0 from secondRange"
				+ " and 5.0 from firstRange", new Range(-1.0, 5.0), newRange);
	}
	
	// TC 6
	@Test
	public void testCombineInvalidRangeThrowsIllegalArgumentException() {
		try {
			Range firstRange = new Range(10.0, 1.0);
			Range secondRange = new Range(2.0, 5.0);
			Range newRange = Range.combine(firstRange, secondRange);
			fail("No exception thrown, expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////// Constrain Method Tests /////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	
	// TC 1
	@Test
	public void testConstrainValueLowerReturnsLowerBound() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Constrain value is lower than 1.0, therefore is not in the range, "
				+ "expected return lower bound of 1.0", 1.0, rangeTest.constrain(0.5));
	} //returned 5.5, which is the central value of the range
	
	// TC 2
	@Test
	public void testConstrainValueIsLowerBoundReturnsLowerBound() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Constrain value is 1.0, therefore is the lower bound, "
				+ "expected return lower bound of 1.0", 1.0, rangeTest.constrain(1.0));
	}
	
	// TC 3
	@Test
	public void testConstrainValueHigherReturnsUpperBound() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Constrain value is higher than 10.0, therefore is not in the range, "
				+ "expected return upper bound of 10.0", 10.0, rangeTest.constrain(10.5));
	}
	
	// TC 4
	@Test
	public void testConstrainValueIsUpperBoundReturnsUpperBound() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Constrain value is 10.0, therefore is the upper bound, "
				+ "expected return upper bound of 10.0", 10.0, rangeTest.constrain(10.0));
	}
	
	// TC 5
	@Test
	public void testConstrainValueIsWithinRangeReturnsValidAnswer() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Constrain value is 5.0, therefore is in range, "
				+ "expected return contrain specified value", 5.0, rangeTest.constrain(5.0));
	}
	
	//TC 6
	@Test
	public void testConstrainValueNegativeRangeReturnsValidAnswer() {
		Range rangeTest = new Range(-10.0, -1.0);
		assertEquals("Constrain value is -5.0, therefore is in range, "
				+ "expected return contrain specified value", -5.0, rangeTest.constrain(-5.0));
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////// Contains Method Tests ////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	
	// TC 1
	@Test
	public void testContainsValueBelowRangeReturnsFalse() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Contain value is -1, therefore is below range "
				+ "expected return false", false ,rangeTest.contains(-1));
	}
	
	// TC 2
	@Test 
	public void testContainsValueIsLowerBoundRangeReturnsTrue() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Contain value is 1, therefore is lower bound "
				+ "expected return true", true ,rangeTest.contains(1));
	}
	
	// TC 3
	@Test 
	public void testContainsValueIsWithinRangeReturnsTrue() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Contain value is 5, therefore is in range "
				+ "expected return true", true ,rangeTest.contains(5));
	}
	
	// TC 4
	@Test 
	public void testContainsValueIsUpperBoundRangeReturnsTrue() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Contain value is 10, therefore is upper bound "
				+ "expected return true", true ,rangeTest.contains(10));
	}
	
	// TC 5
	@Test 
	public void testContainsValueAboveRangeReturnsFalse() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Contain value is 10.5, therefore is above range "
				+ "expected return false", false ,rangeTest.contains(10.5));
	}
	
	// TC 6
	@Test 
	public void testContainsNegativeRangeReturnsTrue() {
		Range rangeTest = new Range(-10.0, -1);
		assertEquals("Contain value is -5.0, therefore is in range "
				+ "expected return true", true ,rangeTest.contains(-5.0));
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////// Get Length Method Tests ///////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	
	// TC 1
	@Test 
	public void testGetLengthValidRangeReturnsCorrectLength() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("10.0 - 1.0 = 9.0, therefore the length is 9.0 "
				+ "expected return 9.0", 9.0 ,rangeTest.getLength());
	}
	
	// TC 2
	@Test 
	public void testGetLengthNegativeRangeReturnsCorrectLength() {
		Range rangeTest = new Range(-10.0, -1.0);
		assertEquals("-10.0 - -1.0 = -9.0, the absolute value of -9.0 is 9.0, "
				+ "therefore the length is 9.0, expected return 9.0", 9.0 ,rangeTest.getLength());
	}
	
	// TC 3
	@Test 
	public void testGetLengthNegativeAndPositiveRangeReturnsCorrectLength() {
		Range rangeTest = new Range(-10.0, 10.0);
		assertEquals("-10.0 - 10.0 = -20.0, the absolute value of -20.0 is 20.0, "
				+ "therefore the length is 20.0, expected return 20.0", 20.0 ,rangeTest.getLength());
	}
	
	// TC 4
	@Test 
	public void testGetLengthRangeValuesEqualReturnsZero() {
		Range rangeTest = new Range(10.0, 10.0);
		assertEquals("Range values are equal, expected return 0", 0.0 ,rangeTest.getLength());
	}
	
	// TC 5
	@Test
	public void testGetLengthNullRangeThrowsNullPointerException() {
		Range rangeTest = null;
		try {
			rangeTest.getLength();
			fail("No exception thrown, expected NullPointerException to be thrown");
		} catch (NullPointerException e) {
			return;
		}
	}
	
	// TC 6
	@Test
	public void testGetLengthInvalidRangeThrowsIllegalArgumentException() {
		try {
			Range rangeTest = new Range(10.0, 1.0);
			rangeTest.getLength();
			fail("No exception thrown, expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////// Lab 3 Method Tests ////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	
	// TC 1
	@Test
	public void testIntersectsUpperValueLessThanLowerBoundReturnsFalse() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Upper value is less than lower bound, expects false", false ,rangeTest.intersects(-5.0, 0.0));
	}
	
	// TC 2
	@Test
	public void testIntersectsIfLowerValueMoreThanUpperBoundReturnsFalse() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Lower value is more than upper bound, expects false", false ,rangeTest.intersects(11.0, 15.0));
	}
	
	// TC 3
	@Test
	public void testIntersectsLowerBoundIntersectsReturnsTrue() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Lower bound intersects, expected true", true ,rangeTest.intersects(8.0, 15.0));
	} // defect, should return true
	
	// TC 4
	@Test
	public void testIntersectsUpperBoundIntersectsReturnsTrue() {
		Range rangeTest = new Range(1.0, 10.0);
		assertEquals("Upper bound intersects, expected true", true ,rangeTest.intersects(0.0, 5.0));
	}
	
	// TC 5
	@Test
	public void testExpandToIncludeNullRangeReturnsValidRange() {
		Range rangeTest = null;
		Range newRange = Range.expandToInclude(rangeTest, 5.0);
		Range expected = new Range(5.0, 5.0);
		assertEquals("Null range, expected (5.0, 5.0)", expected, newRange);
	}
	
	
	// TC 6
	@Test
	public void testExpandToIncludeValueAboveUpperBoundReturnsValidRange() {
		Range rangeTest = new Range(10.0, 20.0);
		Range newRange = Range.expandToInclude(rangeTest, 25.0);
		Range expected = new Range(10.0, 25.0);
		assertEquals("25.0 above upper bound, expect new upper bound to be 25.0"
				+ ", new range (10.0, 25.0)", expected, newRange);
	}
	
	// TC 7
	@Test
	public void testExpandToIncludeValueInRangeReturnsValidRange() {
		Range rangeTest = new Range(10.0, 20.0);
		Range newRange = Range.expandToInclude(rangeTest, 15.0);
		assertEquals("Value in range, expect no change", rangeTest, newRange);
	}
	
	// TC 8
	@Test
	public void testExpandToIncludeValueBelowLowerBoundReturnsValidRange() {
		Range rangeTest = new Range(10.0, 20.0);
		Range newRange = Range.expandToInclude(rangeTest, 5.0);
		Range expected = new Range(5.0, 20.0);
		assertEquals("5.0 below lower bound, expect new lower bound to be 5.0"
				+ ", new range (5.0, 20.0)", expected, newRange);
	}
	
	// TC 9
	@Test
	public void testHashCodeValidRangeReturnsHashCode() {
		Range rangeTest = new Range(1.0, 10.0);
		Range rangeTest2 = new Range(1.0, 10.0);
		assertEquals("Same upper and lower bounds, therefore expected to have the same"
				+ " hash code", rangeTest2.hashCode(), rangeTest.hashCode());
	}
	
	// TC 10
	@Test
	public void testEqualsRangeIsEqualReturnsTrue() {
		Range rangeTest = new Range(1.0, 10.0);
		Range equalRange = new Range(1.0, 10.0);
		assertEquals("Two objects are equal, expected true", true, rangeTest.equals(equalRange));
	}
	
	// TC 11
	@Test
	public void testEqualsBothInputsNotEqualReturnsFalse() {
		Range rangeTest = new Range(1.0, 10.0);
		Range notEqualRange = new Range(5.0, 15.0);
		assertEquals("Two objects are not equal, expected false", false, rangeTest.equals(notEqualRange));
	}
	
	// TC 12
	@Test
	public void testEqualsLowerBoundInputNotEqualUpperBoundEqualReturnsFalse() {
		Range rangeTest = new Range(1.0, 10.0);
		Range notEqualRange = new Range(5.0, 10.0);
		assertEquals("Two objects are not equal, expected false", false, rangeTest.equals(notEqualRange));
	}
	
	// TC 13
	@Test
	public void testEqualsLowerBoundInputEqualUpperBoundNotEqualReturnsFalse() {
		Range rangeTest = new Range(1.0, 10.0);
		Range notEqualRange = new Range(1.0, 15.0);
		assertEquals("Two objects are not equal, expected false", false, rangeTest.equals(notEqualRange));
		} // defect, two ranges are not equal
	
	// TC 14
	@Test 
	public void testExpandNullRangeThrowsInvalidParameterException() {
		Range rangeTest = null;
		try {
			Range.expand(rangeTest, 0.5, 0.5);
			fail("No exception thrown, expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	// TC 15
	@Test
	public void testExpandValidRangeReturnsCorrectNewRange() {
		Range rangeTest = new Range(4.0, 10.0);
		Range expected = new Range(1.0, 13.0);
		assertEquals("Valid range, expected", expected, Range.expand(rangeTest, 0.5, 0.5));
	}
	
	// TC 16
	@Test
	public void testShiftNullRangeThrowsParam() {
		Range rangeTest = null;
		try {
			Range.shift(rangeTest, 0);
			fail("No exception thrown, expected IllegalArgumentException to be thrown");
		} catch (NullPointerException e) {
			return;
		} // catches the wrong exception, expected IllegalArgumentException
	}
	
	// TC 17
	@Test
	public void testShiftValidRangeReturnsCorrectNewRange() {
		Range rangeTest = new Range(1.0, 10.0);
		Range expected = new Range(5.0, 15.0);
		assertEquals("Valid range, shift value is 4 so upper and lower bound should"
				+ " be added 4 to, expects (5.0, 15.0)", expected, Range.shift(rangeTest, 4));
	}
}


////TC 1
//	@Test
//	public void testGetLowerBoundValilRangeReturnsLowerBound() {
//		Range rangeTest = new Range(1.0, 10.0);
//		assertEquals("Lower bound is 1.0, expected 1.0", 1.0 ,rangeTest.getLowerBound());
//	}
//	
//	// TC 2
//	@Test
//	public void testGetLowerBoundNegativeRangeReturnsLowerBound() {
//		Range rangeTest = new Range(-10.0, -1.0);
//		assertEquals("Lower bound is -10.0, expected -10.0", -10.0 ,rangeTest.getLowerBound());
//	}
//	
//	// TC 3
//	@Test
//	public void testGetLowerBoundPositiveAndNegativeRangeReturnsLowerBound() {
//		Range rangeTest = new Range(-10.0, 10.0);
//		assertEquals("Lower bound is -10.0, expected -10.0", -10.0 ,rangeTest.getLowerBound());
//	}
//	
//	// TC 4
//	@Test
//	public void testGetLowerBoundIsZeroReturnsLowerBound() {
//		Range rangeTest = new Range(0, 10.0);
//		assertEquals("Lower bound is 0.0, expected 0.0", 0.0 ,rangeTest.getLowerBound());
//	}

