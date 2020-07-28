package com.infilos.refine.std;

import com.infilos.refine.Refine;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

/**
 * @author infilos on 2020-07-25.
 */

public class ObjectsTest {

    @Test
    public void test() {
        assertTrue(Refine.of("check").expect(null).match(Objects.IsNull()).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Objects.IsNull()).check().isFailed());

        assertTrue(Refine.of("check").expect(new Object()).match(Objects.NotNull()).check().isSucced());
        assertTrue(Refine.of("check").expect(null).match(Objects.NotNull()).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Objects.IsChildOf(String.class)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Objects.IsChildOf(String.class)).check().isFailed());

        assertTrue(Refine.of("check").expect(new Object()).match(Objects.IsSuperOf(String.class)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Objects.IsSuperOf(String.class)).check().isFailed());

        assertTrue(Refine.of("check").expect(Option.A).match(Objects.IsEnum()).check().isSucced());
        assertTrue(Refine.of("check").expect(new Object()).match(Objects.IsEnum()).check().isFailed());

        assertTrue(Refine.of("check").expect(Option.A).match(Objects.IsEnumOf(Option.class)).check().isSucced());
        assertTrue(Refine.of("check").expect(Option.A).match(Objects.IsEnumOf(Switch.class)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Objects.IsString()).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Objects.IsString()).check().isFailed());

        assertTrue(Refine.of("check").expect('a').match(Objects.IsChar()).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Objects.IsChar()).check().isFailed());

        assertTrue(Refine.of("check").expect("a".getBytes()[0]).match(Objects.IsByte()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsByte()).check().isFailed());

        assertTrue(Refine.of("check").expect(new Short("2")).match(Objects.IsShort()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsShort()).check().isFailed());

        assertTrue(Refine.of("check").expect(2).match(Objects.IsInteger()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsInteger()).check().isFailed());

        assertTrue(Refine.of("check").expect(2L).match(Objects.IsLong()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsLong()).check().isFailed());

        assertTrue(Refine.of("check").expect(2F).match(Objects.IsFloat()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsFloat()).check().isFailed());

        assertTrue(Refine.of("check").expect(2D).match(Objects.IsDouble()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsDouble()).check().isFailed());

        assertTrue(Refine.of("check").expect(new BigDecimal("1.2")).match(Objects.IsDecimal()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsDecimal()).check().isFailed());

        assertTrue(Refine.of("check").expect(true).match(Objects.IsBoolean()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsBoolean()).check().isFailed());

        assertTrue(Refine.of("check").expect("a".getBytes()).match(Objects.IsArray()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsArray()).check().isFailed());

        assertTrue(Refine.of("check").expect(Arrays.asList(1, 2)).match(Objects.IsList()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsList()).check().isFailed());

        assertTrue(Refine.of("check").expect(new HashSet<Integer>() {{
            add(1);
        }}).match(Objects.IsSet()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsSet()).check().isFailed());

        assertTrue(Refine.of("check").expect(new HashMap<Integer, Integer>() {{
            put(1, 2);
        }}).match(Objects.IsMap()).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsMap()).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Objects.IsIn(1, 2, 3)).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Objects.IsIn(1, 2, 3)).check().isFailed());
        assertTrue(Refine.of("check").expect(4).match(Objects.IsIn(1, 2, 3)).check().isFailed());

        assertTrue(Refine.of("check").expect(4).match(Objects.NotIn(1, 2, 3)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Objects.NotIn(1, 2, 3)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Objects.IsEqual(1)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Objects.IsEqual(2)).check().isFailed());

        assertTrue(Refine.of("check").expect(1).match(Objects.NotEqual(2)).check().isSucced());
        assertTrue(Refine.of("check").expect(1).match(Objects.NotEqual(1)).check().isFailed());
    }

    private enum Option {
        A, B
    }

    private enum Switch {
        On, Off
    }
}