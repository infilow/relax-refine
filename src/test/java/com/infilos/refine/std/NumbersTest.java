package com.infilos.refine.std;

import com.infilos.refine.Refine;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author zhiguang.zhang on 2020-07-25.
 */

public class NumbersTest {

    @Test
    public void test() {
        assertTrue(Refine.of("check").expect(1).match(Numbers.IsEqual(1)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.IsEqual(2)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Numbers.NotEqual(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.NotEqual(1)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Numbers.GreaterEqual(0)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.GreaterEqual(2)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Numbers.GreaterThan(0)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.GreaterThan(1)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Numbers.LessEqual(1)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.LessEqual(0)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Numbers.LessThan(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.LessThan(0)).check().isFailed());

        assertTrue(Refine.of("check").expect(5).match(Numbers.Modulo(2, 1)).check().isSucced());
        assertTrue(Refine.of("check").expect(5).match(Numbers.Modulo(2, 0)).check().isFailed());

        assertTrue(Refine.of("check").expect(4).match(Numbers.Divisible(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(4).match(Numbers.Divisible(3)).check().isFailed());

        assertTrue(Refine.of("check").expect(12.34F).match(Numbers.ScaleAt(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(12.3455F).match(Numbers.ScaleAt(2)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Numbers.RangeClose(0, 2)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.RangeClose(1, 2)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Numbers.RangeCloseOpen(0, 1)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.RangeCloseOpen(1, 2)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Numbers.RangeOpen(1, 1)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.RangeOpen(2, 3)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Numbers.RangeOpenClose(1, 2)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Numbers.RangeOpenClose(2, 3)).check().isFailed());
    }
}