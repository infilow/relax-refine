package com.infilos.refine.std;

import com.infilos.refine.Refine;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * @author infilos on 2020-07-25.
 */

public class ListsTest {

    @Test
    public void test() {
        Set<Integer> set = new HashSet<Integer>() {{
            add(1);
            add(2);
        }};

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsContains(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(set).match(Lists.IsContains(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsContains(3)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.NotContains(3)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.NotContains(2)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.AllMatches(v -> v > 0)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.AllMatches(v -> v < 0)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.AnyMatches(v -> v==1)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.AnyMatches(v -> v==3)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.NonMatches(v -> v==3)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.NonMatches(v -> v==1)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.OneMatches(v -> v==1)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.OneMatches(v -> v==3)).check().isFailed());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2, 2)).match(Lists.OneMatches(v -> v==2)).check().isFailed());

        assertTrue(Refine.of("check").expect(Collections.emptyList()).match(Lists.IsEmpty()).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsEmpty()).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.NotEmpty()).check().isSucced());
        assertTrue(Refine.of("check").expect(Collections.emptyList()).match(Lists.NotEmpty()).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeEqual(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeEqual(3)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.NotSizeEqual(3)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.NotSizeEqual(2)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeGreaterEqual(1)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeGreaterEqual(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeGreaterEqual(3)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeGreaterThan(1)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeGreaterThan(2)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeLessEqual(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeLessEqual(3)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeLessEqual(1)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeLessThan(3)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeLessThan(2)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeRangeClose(1, 3)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeRangeClose(2, 2)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeRangeCloseOpen(1, 2)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeRangeCloseOpen(3, 4)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeRangeOpen(1, 2)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeRangeOpen(3, 4)).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeRangeOpenClose(2, 3)).check().isSucced());
        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Lists.IsSizeRangeOpenClose(3, 4)).check().isFailed());
    }
}