package com.infilos.refine.std;

import com.infilos.refine.api.RefineResult;
import com.infilos.refine.api.Refined;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author infilos on 2020-06-02.
 */

public final class Objects {
    private Objects() {
    }

    public static <T> Refined<T> IsNull() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsNull";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.succed(name, null, this);
                }

                return RefineResult.failed(name, value, this);
            }
        };
    }

    public static <T> Refined<T> NotNull() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "NotNull";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.nonNull(value)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsChildOf(Class<?> clazz) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsChildOf";
            }

            @Override
            public Object param() {
                return clazz.getName();
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (clazz.isAssignableFrom(value.getClass())) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsSuperOf(Class<?> clazz) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsSuperOf";
            }

            @Override
            public Object param() {
                return clazz.getName();
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.getClass().isAssignableFrom(clazz)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsEnum() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsEnum";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Enum<?>) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsEnumOf(Class<? extends Enum<?>> clazz) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsEnumOf";
            }

            @Override
            public Object param() {
                return clazz.getName();
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.getClass()==clazz) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsString() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsString";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof String) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsChar() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsChar";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Character) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsByte() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsByte";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Byte) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsShort() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsShort";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Short) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsInteger() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsInteger";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Integer) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };

    }

    public static <T> Refined<T> IsLong() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsLong";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Long) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsFloat() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsFloat";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Float) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsDouble() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsDouble";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Double) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsDecimal() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsDecimal";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof BigDecimal) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsBoolean() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsBoolean";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Boolean) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsArray() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsArray";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value.getClass().isArray()) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsList() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsList";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof List<?>) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsSet() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsSet";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Set<?>) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsMap() {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsMap";
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, value, this);
                }
                if (value instanceof Map<?, ?>) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsIn(Object... objects) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsIn";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(objects);
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (Arrays.asList(objects).contains(value)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> NotIn(Object... objects) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "NotIn";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(objects);
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (Stream.of(objects).noneMatch(o -> o.equals(value))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> IsEqual(Object object) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsEqual";
            }

            @Override
            public Object param() {
                return object;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.equals(object)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T> Refined<T> NotEqual(Object object) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "NotEqual";
            }

            @Override
            public Object param() {
                return object;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (!value.equals(object)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }
}
