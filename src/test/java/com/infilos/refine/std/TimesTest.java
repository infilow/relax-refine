package com.infilos.refine.std;

import com.infilos.refine.Refine;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

/**
 * @author infilos on 2020-07-25.
 */

public class TimesTest {

    private static LocalDate date(String date) {
        return LocalDate.parse(date, Times.DateFormatter);
    }

    private static LocalDateTime time(String time) {
        return LocalDateTime.parse(time, Times.TimeFormatter);
    }

    @Test
    public void test() {
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.Equal(date("2020-12-12"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.Equal(date("2020-12-13"))).check().isFailed());

        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.After(date("2020-12-11"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.After(date("2020-12-12"))).check().isFailed());

        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.AfterEqual(date("2020-12-11"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.AfterEqual(date("2020-12-12"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.AfterEqual(date("2020-12-13"))).check().isFailed());

        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.Before(date("2020-12-13"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.Before(date("2020-12-12"))).check().isFailed());

        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.BeforeEqual(date("2020-12-12"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.BeforeEqual(date("2020-12-13"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.BeforeEqual(date("2020-12-11"))).check().isFailed());

        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.RangeClose(date("2020-12-11"), date("2020-12-13"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.RangeClose(date("2020-12-11"), date("2020-12-11"))).check().isFailed());

        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.RangeCloseOpen(date("2020-12-11"), date("2020-12-12"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.RangeCloseOpen(date("2020-12-11"), date("2020-12-11"))).check().isFailed());

        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.RangeOpen(date("2020-12-12"), date("2020-12-12"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.RangeOpen(date("2020-12-11"), date("2020-12-11"))).check().isFailed());

        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.RangeOpenClose(date("2020-12-12"), date("2020-12-13"))).check().isSucced());
        assertTrue(Refine.of("check").expect(date("2020-12-12")).match(Times.RangeOpenClose(date("2020-12-11"), date("2020-12-11"))).check().isFailed());


        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.Equal(time("2020-12-12 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.Equal(time("2020-12-13 12:23:34"))).check().isFailed());

        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.After(time("2020-12-11 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.After(time("2020-12-12 12:23:34"))).check().isFailed());

        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.AfterEqual(time("2020-12-11 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.AfterEqual(time("2020-12-12 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.AfterEqual(time("2020-12-13 12:23:34"))).check().isFailed());

        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.Before(time("2020-12-13 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.Before(time("2020-12-12 12:23:34"))).check().isFailed());

        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.BeforeEqual(time("2020-12-12 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.BeforeEqual(time("2020-12-13 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.BeforeEqual(time("2020-12-11 12:23:34"))).check().isFailed());

        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.RangeClose(time("2020-12-11 12:23:34"), time("2020-12-13 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.RangeClose(time("2020-12-11 12:23:34"), time("2020-12-11 12:23:34"))).check().isFailed());

        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.RangeCloseOpen(time("2020-12-11 12:23:34"), time("2020-12-12 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.RangeCloseOpen(time("2020-12-11 12:23:34"), time("2020-12-11 12:23:34"))).check().isFailed());

        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.RangeOpen(time("2020-12-12 12:23:34"), time("2020-12-12 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.RangeOpen(time("2020-12-11 12:23:34"), time("2020-12-11 12:23:34"))).check().isFailed());

        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.RangeOpenClose(time("2020-12-12 12:23:34"), time("2020-12-13 12:23:34"))).check().isSucced());
        assertTrue(Refine.of("check").expect(time("2020-12-12 12:23:34")).match(Times.RangeOpenClose(time("2020-12-11 12:23:34"), time("2020-12-11 12:23:34"))).check().isFailed());
    }
}