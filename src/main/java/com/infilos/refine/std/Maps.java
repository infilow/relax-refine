package com.infilos.refine.std;

import com.infilos.refine.api.RefineResult;
import com.infilos.refine.api.Refined;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author zhiguang.zhang on 2020-07-24.
 */

public final class Maps {
    private Maps() {
    }

    public static <K, V> Refined<Map<K, V>> IsKeyContains(K key) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "IsKeyContains";
            }

            @Override
            public Object param() {
                return key;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.keySet().stream().anyMatch(e -> e.equals(key))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> NotKeyContains(K key) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "NotKeyContains";
            }

            @Override
            public Object param() {
                return key;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.keySet().stream().noneMatch(e -> e.equals(key))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> AllKeyMatches(Predicate<K> predicate) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "AllKeyMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.keySet().stream().allMatch(predicate)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> AnyKeyMatches(Predicate<K> predicate) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "AnyKeyMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.keySet().stream().anyMatch(predicate)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> NonKeyMatches(Predicate<K> predicate) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "NonKeyMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.keySet().stream().noneMatch(predicate)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> OneKeyMatches(Predicate<K> predicate) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "OneKeyMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.keySet().stream().filter(predicate).count()==1) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> IsValueContains(V val) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "IsValueContains";
            }

            @Override
            public Object param() {
                return val;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.values().stream().anyMatch(e -> e.equals(val))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> NotValueContains(V val) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "NotValueContains";
            }

            @Override
            public Object param() {
                return val;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.values().stream().noneMatch(e -> e.equals(val))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> AllValueMatches(Predicate<V> predicate) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "AllValueMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.values().stream().allMatch(predicate)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> AnyValueMatches(Predicate<V> predicate) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "AnyValueMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.values().stream().anyMatch(predicate)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> NonValueMatches(Predicate<V> predicate) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "NonValueMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.values().stream().noneMatch(predicate)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <K, V> Refined<Map<K, V>> OneValueMatches(Predicate<V> predicate) {
        return new Refined<Map<K, V>>() {
            @Override
            public String name() {
                return "OneValueMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, Map<K, V> value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.values().stream().filter(predicate).count()==1) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }
}
