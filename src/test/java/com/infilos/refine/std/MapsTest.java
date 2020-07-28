package com.infilos.refine.std;

import com.infilos.refine.Refine;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @author infilos on 2020-07-25.
 */

public class MapsTest {

    private static final Map<String, Integer> MAP = new HashMap<String, Integer>() {{
        put("a", 1);
        put("b", 2);
        put("c", 3);
    }};

    @Test
    public void test() {
        assertTrue(Refine.of("check").expect(MAP).match(Maps.IsKeyContains("a")).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.IsKeyContains("d")).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.NotKeyContains("d")).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.NotKeyContains("a")).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.AllKeyMatches(k -> !k.isEmpty())).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.AllKeyMatches(k -> k.isEmpty())).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.AnyKeyMatches(k -> k.equals("a"))).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.AnyKeyMatches(k -> k.equals("d"))).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.NonKeyMatches(k -> k.equals("d"))).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.NonKeyMatches(k -> k.equals("a"))).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.OneKeyMatches(k -> k.equals("a"))).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.OneKeyMatches(k -> k.equals("d"))).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.IsValueContains(1)).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.IsValueContains(4)).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.NotValueContains(4)).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.NotValueContains(1)).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.AllValueMatches(v -> v > 0)).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.AllValueMatches(v -> v < 0)).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.AnyValueMatches(v -> v==1)).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.AnyValueMatches(v -> v==4)).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.NonValueMatches(v -> v==4)).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.NonValueMatches(v -> v==1)).check().isFailed());

        assertTrue(Refine.of("check").expect(MAP).match(Maps.OneValueMatches(v -> v==1)).check().isSucced());
        assertTrue(Refine.of("check").expect(MAP).match(Maps.OneValueMatches(v -> v==4)).check().isFailed());
    }
}