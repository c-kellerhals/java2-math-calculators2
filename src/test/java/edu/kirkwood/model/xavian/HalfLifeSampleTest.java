package edu.kirkwood.model.xavian;

import edu.kirkwood.model.xavian.HalfLifeSample;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HalfLifeSampleTest {

    private HalfLifeSample sample1;
    private HalfLifeSample sample2;

    @BeforeEach
    void setUp() {
        sample1 = new HalfLifeSample(4,1,10,5,2,true);
        sample2 = new HalfLifeSample();
    }

    @Test
    void sampleFindInitial() {
        HalfLifeSample expected = sample1;
        HalfLifeSample output = HalfLifeSample.sampleFindInitial(1.0, 10, 5);
        assertEquals(expected, output);
    }

    @Test
    void sampleFindRemaining() {
        HalfLifeSample output = HalfLifeSample.sampleFindRemaining(4, 10, 5);
        assertEquals(sample1, output);
    }

    @Test
    void sampleFindTimeElapsed() {
        HalfLifeSample output = HalfLifeSample.sampleFindTimeElapsed(4, 1, 5);
        assertEquals(sample1, output);
    }

    @Test
    void sampleFindHalfLifeLength() {
        HalfLifeSample output = HalfLifeSample.sampleFindHalfLifeLength(4, 1, 10);
        assertEquals(sample1, output);
    }

    @Test
    void getInitialValue() {
        assertEquals(4.0, sample1.getInitialValue());
        assertEquals(2.0, sample2.getInitialValue());
    }

    @Test
    void getRemainingValue() {
        sample2 = new HalfLifeSample(8,2,10,5,2,true);
        assertEquals(1.0, sample1.getRemainingValue());
        assertEquals(2.0, sample2.getRemainingValue());
    }

    @Test
    void getTimeElapsed() {
        assertEquals(10.0, sample1.getTimeElapsed());
        assertEquals(1, sample2.getTimeElapsed());
    }

    @Test
    void getHalfLifeLength() {
        assertEquals(5.0, sample1.getHalfLifeLength());
        assertEquals(1.0, sample2.getHalfLifeLength());
    }

    @Test
    void getHalfLivesElapsed() {
        assertEquals(2.0, sample1.getHalfLivesElapsed());
        assertEquals(1.0, sample2.getHalfLivesElapsed());
    }

    @Test
    void getIsReliable() {
        assertTrue(sample1.getIsReliable());
        assertTrue(sample2.getIsReliable());
    }

    @Test
    void setInitialValueOverride() {
        sample1.setInitialValue(8, true);
        assertEquals(8.0, sample1.getInitialValue());
        assertEquals(15.0, sample1.getTimeElapsed());
        assertEquals(3.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());

        sample1.setInitialValue(2048, true);
        assertEquals(2048.0, sample1.getInitialValue());
        assertEquals(11.0, sample1.getHalfLivesElapsed());
        assertEquals(55.0, sample1.getTimeElapsed());
        assertFalse(sample1.getIsReliable());
    }

    @Test
    void setInitialValueNoOverride() {
        sample1.setInitialValue(8);
        assertEquals(8.0, sample1.getInitialValue());
        assertEquals(10.0, sample1.getTimeElapsed());
        assertEquals(2.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());

        sample1.setInitialValue(2048);
        assertEquals(2048.0, sample1.getInitialValue());
        assertEquals(10.0, sample1.getTimeElapsed());
        assertEquals(2.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setInitialValueThrow() {
        assertThrows(IllegalArgumentException.class, () -> sample1.setInitialValue(0));
        assertThrows(IllegalArgumentException.class, () -> sample1.setInitialValue(-1));
    }

    @Test
    void setRemainingValueOverride() {
        sample1.setRemainingValue(0.5, true);
        assertEquals(0.5, sample1.getRemainingValue());
        assertEquals(15.0, sample1.getTimeElapsed());
        assertEquals(3.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());

        sample1 = new HalfLifeSample(8192, 4096, 5, 5, 1, true);
        sample1.setRemainingValue(2.0, true);
        assertEquals(2.0, sample1.getRemainingValue());
        assertEquals(60.0, sample1.getTimeElapsed());
        assertEquals(12.0,sample1.getHalfLivesElapsed());
        assertFalse(sample1.getIsReliable());
    }

    @Test
    void setRemainingValueNoOverride() {
        sample1.setRemainingValue(0.5);
        assertEquals(0.5, sample1.getRemainingValue());
        assertEquals(10.0, sample1.getTimeElapsed());
        assertEquals(2.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());

        sample1 = new HalfLifeSample(8192, 4096, 5, 5, 1, true);
        sample1.setRemainingValue(2.0);
        assertEquals(2.0, sample1.getRemainingValue());
        assertEquals(5.0, sample1.getTimeElapsed());
        assertEquals(1.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setRemainingValueThrow() {
        assertThrows(IllegalArgumentException.class, () -> sample1.setRemainingValue(5));
        assertThrows(IllegalArgumentException.class, () -> sample1.setRemainingValue(0));
    }

    @Test
    void setTimeElapsedNotZeroOverride() {
        sample1.setTimeElapsed(5, true);
        assertEquals(5.0, sample1.getTimeElapsed());
        assertEquals(2.5, sample1.getHalfLifeLength());
        assertEquals(1.0, sample1.getRemainingValue());
        assertEquals(2.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());

        sample1 = new HalfLifeSample(4096, 1, 600, 5, 12, false);
        sample1.setTimeElapsed(300, true);
        assertEquals(300.0, sample1.getTimeElapsed());
        assertEquals(25.0, sample1.getHalfLifeLength());
        assertEquals(1.0, sample1.getRemainingValue());
        assertEquals(12, sample1.getHalfLivesElapsed());
        assertFalse(sample1.getIsReliable());
    }

    @Test
    void setTimeElapsedNotZeroNoOverride() {
        sample1.setTimeElapsed(5);
        assertEquals(5.0, sample1.getTimeElapsed());
        assertEquals(5.0, sample1.getHalfLifeLength());
        assertEquals(1.0, sample1.getRemainingValue());
        assertEquals(2.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());

        sample1 = new HalfLifeSample(4096, 1, 600, 5, 12, false);
        sample1.setTimeElapsed(300);
        assertEquals(300.0, sample1.getTimeElapsed());
        assertEquals(5.0, sample1.getHalfLifeLength());
        assertEquals(1.0, sample1.getRemainingValue());
        assertEquals(12.0, sample1.getHalfLivesElapsed());
        assertFalse(sample1.getIsReliable());
    }


    @Test
    void setTimeElapsedZeroOverride() {
        sample1.setTimeElapsed(0, true);
        assertEquals(0.0, sample1.getTimeElapsed());
        assertEquals(5.0, sample1.getHalfLifeLength());
        assertEquals(4.0, sample1.getRemainingValue());
        assertEquals(0.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setTimeElapsedZeroNoOverride() {
        sample1.setTimeElapsed(0);
        assertEquals(0.0, sample1.getTimeElapsed());
        assertEquals(5.0, sample1.getHalfLifeLength());
        assertEquals(1.0, sample1.getRemainingValue());
        assertEquals(2.0, sample1.getHalfLivesElapsed());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setHalfLifeLengthOverride() {
        sample1.setHalfLifeLength(10, true);
        assertEquals(10.0, sample1.getHalfLifeLength());
        assertEquals(1.0, sample1.getHalfLivesElapsed());
        assertEquals(2.0, sample1.getRemainingValue());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setHalfLifeLengthNoOverride() {
        sample1.setHalfLifeLength(10);
        assertEquals(10.0, sample1.getHalfLifeLength());
        assertEquals(2.0, sample1.getHalfLivesElapsed());
        assertEquals(1.0, sample1.getRemainingValue());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setHalfLifeLengthThrow() {
        assertThrows(IllegalArgumentException.class, () -> sample1.setHalfLifeLength(0));
        assertThrows(IllegalArgumentException.class, () -> sample1.setHalfLifeLength(-2));
    }

    @Test
    void setHalfLivesElapsedNotZeroOverride() {
        sample1.setHalfLivesElapsed(4, true);
        assertEquals(4.0, sample1.getHalfLivesElapsed());
        assertEquals(20.0, sample1.getTimeElapsed());
        assertEquals(0.25, sample1.getRemainingValue());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setHalfLivesElapsedNotZeroNoOverride() {
        sample1.setHalfLivesElapsed(4);
        assertEquals(4.0, sample1.getHalfLivesElapsed());
        assertEquals(10.0, sample1.getTimeElapsed());
        assertEquals(1.0, sample1.getRemainingValue());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setHalfLivesElapsedZeroOverride() {
        sample1.setHalfLivesElapsed(0, true);
        assertEquals(0.0, sample1.getHalfLivesElapsed());
        assertEquals(0.0, sample1.getTimeElapsed());
        assertEquals(4.0, sample1.getRemainingValue());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setHalfLivesElapsedZeroNoOverride() {
        sample1.setHalfLivesElapsed(0);
        assertEquals(0.0, sample1.getHalfLivesElapsed());
        assertEquals(10.0, sample1.getTimeElapsed());
        assertEquals(1.0, sample1.getRemainingValue());
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void setHalfLivesElapsedThrow() {
        assertThrows(IllegalArgumentException.class, () -> sample1.setHalfLivesElapsed(-1));
    }

    @Test
    void setIsReliable() {
        sample1.setIsReliable(false);
        assertFalse(sample1.getIsReliable());
        sample1.setIsReliable(true);
        assertTrue(sample1.getIsReliable());
    }

    @Test
    void halfLivesElapsed() {
        assertEquals(1.0, HalfLifeSample.halfLivesElapsed(5, 5));
        assertEquals(5.0, HalfLifeSample.halfLivesElapsed(10, 2));
    }

    @Test
    void halfLivesElapsedZero() {
        assertEquals(0.0, HalfLifeSample.halfLivesElapsed(0, 10));
    }

    @Test
    void halfLivesElapsedThrow() {
        assertThrows(ArithmeticException.class, () -> HalfLifeSample.halfLivesElapsed(10, 0));
        assertThrows(ArithmeticException.class, () -> HalfLifeSample.halfLivesElapsed(0, 0));
    }

    @Test
    void isReliableZeroAndPositive() {
        assertTrue(HalfLifeSample.isReliable(0));
        assertTrue(HalfLifeSample.isReliable(2));
        assertTrue(HalfLifeSample.isReliable(2.4));
        assertTrue(HalfLifeSample.isReliable(10));
        assertFalse(HalfLifeSample.isReliable(12));
    }

    @Test
    void isReliableNegative() {
        assertFalse(HalfLifeSample.isReliable(-2));
    }

    @Test
    void timeElapsed() {
        assertEquals(10.0, HalfLifeSample.timeElapsed(4, 1, 5));
        assertEquals(20.0, HalfLifeSample.timeElapsed(4, 2, 20));
        // will get thrown later but not in this part
        assertEquals(0.0, HalfLifeSample.timeElapsed(4, 2, 0));
        //assertEquals(-2.0, HalfLifeSample.timeElapsed(2, 8, 1)); [Should throw]
    }

    @Test
    void timeElapsedThrow() {
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.timeElapsed(0, 10, 10));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.timeElapsed(10, 0, 10));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.timeElapsed(0, 0, 10));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.timeElapsed(0, 0,0));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.timeElapsed(2, 8, 1));
    }

    @Test
    void halfLifeLength() {
        assertEquals(5.0, HalfLifeSample.halfLifeLength(4, 1, 10));
        assertEquals(1.0, HalfLifeSample.halfLifeLength(2, 1, 1));
    }

    @Test
    void halfLifeLengthThrow() {
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.halfLifeLength(0, 1, 10));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.halfLifeLength(1, 0, 10));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.halfLifeLength(2, 2, 0));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.halfLifeLength(2, 4, -1));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.halfLifeLength(4, 2, -1));
        assertThrows(ArithmeticException.class, () -> HalfLifeSample.halfLifeLength(2, 2, 10));
    }

    @Test
    void remainingValue() {
        assertEquals(1.0, HalfLifeSample.remainingValue(4.0, 10, 5));
        assertEquals(2.0, HalfLifeSample.remainingValue(4.0, 10, 10));
    }

    @Test
    void remainingValueNoTime() {
        assertEquals(4.0, HalfLifeSample.remainingValue(4.0, 0, 10));
    }

    @Test
    void compareTo() {
        HalfLifeSample sample2 = new HalfLifeSample(4, 0.25, 20, 5, 4, true);
        HalfLifeSample sample3 = new HalfLifeSample(4, 2, 10, 10, 1, true);

        assertEquals(0, sample1.compareTo(sample1));
        assertEquals(0, sample1.compareTo(sample3));
        assertEquals(0, sample3.compareTo(sample1));
        assertEquals(1, sample1.compareTo(sample2));
        assertEquals(-1, sample2.compareTo(sample1));

    }

    @Test
    void compareToThrow() {
        HalfLifeSample badSample = new HalfLifeSample(4, 1, 10, 5, 2, false);

        assertThrows(IllegalArgumentException.class, () -> sample1.compareTo(badSample));
        assertThrows(IllegalArgumentException.class, () -> badSample.compareTo(sample1));
        assertThrows(IllegalArgumentException.class, () -> badSample.compareTo(badSample));
    }

    @Test
    void testToString() {
        String expected = "Sample Details:\n" +
                "Initial Value: 4.0\n" +
                "Remaining Value: 1.0\n" +
                "Time Elapsed: 10.0\n" +
                "Half Life Length: 5.0\n" +
                "Half Lives Elapsed: 2.0\n" +
                "Sample is reliable.";
        String output = sample1.toString();
        assertEquals(expected, output);
    }

    @Test
    void testEquals() {
        sample1 = new HalfLifeSample();
        assertTrue(sample1.equals(sample2));
        sample2 = new HalfLifeSample(4, 1, 10, 5, 2, true);
        assertFalse(sample1.equals(sample2));
    }

    @Test
    void halfLivesElapsedCheck() {
        assertEquals(2.0, HalfLifeSample.halfLivesElapsedCheck(4, 1));
        assertEquals(4.0, HalfLifeSample.halfLivesElapsedCheck(16, 1));
        assertEquals(0.0, HalfLifeSample.halfLivesElapsedCheck(4, 4));
    }

    @Test
    void halfLivesElapsedCheckThrow() {
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.halfLivesElapsedCheck(0, 1));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.halfLivesElapsedCheck(1, 0));
        assertThrows(IllegalArgumentException.class, () -> HalfLifeSample.halfLivesElapsedCheck(0,0));
    }

    @Test
    void findMinTime() {
        assertEquals(5, sample1.findMinTime(1));
        assertEquals(9.313, sample1.findMinTime(10));
    }

    @Test
    void findMinTimeNoParameter() {
        assertEquals(9.993, sample1.findMinTime());
    }

    @Test
    void findMinTimeZero() {
        assertEquals(sample1.getTimeElapsed(), sample1.findMinTime(0));
        assertEquals(sample2.getTimeElapsed(), sample2.findMinTime(0));
    }

    @Test
    void findMinTimeThrow() {
        assertThrows(IllegalArgumentException.class, () -> sample1.findMinTime(-1));
    }

    @Test
    void findMaxTime() {
        assertEquals(15,sample1.findMaxTime(2));
        assertEquals(10.7600, sample1.findMaxTime(10));
    }

    @Test
    void findMaxTimeNoParameter() {
        assertEquals(10.007, sample1.findMaxTime());
    }

    @Test
    void findMaxTimeZero() {
        assertEquals(sample1.getTimeElapsed(),sample1.findMaxTime(0));
        assertEquals(sample2.getTimeElapsed(),sample2.findMaxTime(0));
    }

    @Test
    void findMaxTimeThrow() {
        assertThrows(IllegalArgumentException.class,() -> sample1.findMaxTime(1));
        assertThrows(IllegalArgumentException.class,() -> sample1.findMaxTime(-1));
    }

    // should probably make sure this works since I forgot to earlier
    @Test
    void myRoundTest() {
        assertEquals(10.0001, HalfLifeSample.myRound(10.00005));
        assertEquals(10.0, HalfLifeSample.myRound(10));
        assertEquals(10.0, HalfLifeSample.myRound(10.00001));
        assertEquals(0.0, HalfLifeSample.myRound(-0.0));
    }

//      idk if this one is necessarily testable:
//    @Test
//    void printCompareTo() {
//        fail();
//    }
}