package com.infilos.refine.std;

import com.infilos.refine.api.RefineResult;
import com.infilos.refine.api.Refined;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * @author infilos on 2020-06-02.
 */

public final class Numbers {
    private Numbers() {
    }

    private static int compare(Number left, Number right) {
        if (left instanceof Byte) {
            return ((Byte) left).compareTo(right.byteValue());
        } else if (left instanceof Short) {
            return ((Short) left).compareTo(right.shortValue());
        } else if (left instanceof Integer) {
            return ((Integer) left).compareTo(right.intValue());
        } else if (left instanceof Long) {
            return ((Long) left).compareTo(right.longValue());
        } else if (left instanceof Float) {
            return ((Float) left).compareTo(right.floatValue());
        } else if (left instanceof Double) {
            return ((Double) left).compareTo(right.doubleValue());
        } else if (left instanceof BigInteger) {
            return ((BigInteger) left).compareTo((BigInteger) right);
        } else if (left instanceof BigDecimal) {
            return ((BigDecimal) left).compareTo((BigDecimal) right);
        } else {
            throw new UnsupportedOperationException("Cannot compare number of " + left.getClass().getSimpleName());
        }
    }

    public static <T extends Number> Refined<T> IsEqual(T number) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "IsEqual";
            }

            @Override
            public Object param() {
                return number;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, number)==0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> NotEqual(T number) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "NotEqual";
            }

            @Override
            public Object param() {
                return number;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, number)!=0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> GreaterEqual(T number) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "GreaterEqual";
            }

            @Override
            public Object param() {
                return number;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, number) >= 0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> GreaterThan(T number) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "GreaterThan";
            }

            @Override
            public Object param() {
                return number;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, number) > 0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> LessEqual(T number) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "LessEqual";
            }

            @Override
            public Object param() {
                return number;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, number) <= 0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> LessThan(T number) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "LessThan";
            }

            @Override
            public Object param() {
                return number;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, number) < 0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> Modulo(T divisor, T remainder) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "Modulo";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(divisor, remainder);
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                BigDecimal valueAsDecimal = new BigDecimal(value.toString());
                BigDecimal divisorAsDecimal = new BigDecimal(divisor.toString());
                BigDecimal remainderAsDecimal = new BigDecimal(remainder.toString());

                if (valueAsDecimal.remainder(divisorAsDecimal).compareTo(remainderAsDecimal)==0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> Divisible(T divisor) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "Divisible";
            }

            @Override
            public Object param() {
                return divisor;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                BigDecimal valueAsDecimal = new BigDecimal(value.toString());
                BigDecimal divisorAsDecimal = new BigDecimal(divisor.toString());

                if (valueAsDecimal.remainder(divisorAsDecimal).compareTo(BigDecimal.ZERO)==0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> ScaleAt(Integer scale) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "ScaleAt";
            }

            @Override
            public Object param() {
                return scale;
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                BigDecimal valueAsDecimal = new BigDecimal(value.toString());

                if (valueAsDecimal.scale()==scale) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> RangeClose(T min, T max) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "RangeClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, min) > 0 && compare(value, max) < 0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> RangeCloseOpen(T min, T max) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "RangeCloseOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, min) > 0 && compare(value, max) <= 0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> RangeOpen(T min, T max) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "RangeOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, min) >= 0 && compare(value, max) <= 0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T extends Number> Refined<T> RangeOpenClose(T min, T max) {
        return new Refined<T>() {
            @Override
            public String name() {
                return "RangeOpenClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, T value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (compare(value, min) >= 0 && compare(value, max) < 0) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }
}
