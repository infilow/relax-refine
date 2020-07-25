package com.infilos.refine.std;

import com.infilos.refine.api.RefineResult;
import com.infilos.refine.api.Refined;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author infilos on 2020-06-02.
 */

public final class Times {
    static final DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final DateTimeFormatter TimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Times() {
    }

    // date
    public static Refined<LocalDate> Equal(LocalDate date) {
        return new Refined<LocalDate>() {
            @Override
            public String name() {
                return "Equal";
            }

            @Override
            public Object param() {
                return date.format(DateFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDate value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isEqual(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDate> After(LocalDate date) {
        return new Refined<LocalDate>() {
            @Override
            public String name() {
                return "After";
            }

            @Override
            public Object param() {
                return date.format(DateFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDate value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isAfter(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDate> AfterEqual(LocalDate date) {
        return new Refined<LocalDate>() {
            @Override
            public String name() {
                return "AfterEqual";
            }

            @Override
            public Object param() {
                return date.format(DateFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDate value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isEqual(date) || value.isAfter(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDate> Before(LocalDate date) {
        return new Refined<LocalDate>() {
            @Override
            public String name() {
                return "Before";
            }

            @Override
            public Object param() {
                return date.format(DateFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDate value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isBefore(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDate> BeforeEqual(LocalDate date) {
        return new Refined<LocalDate>() {
            @Override
            public String name() {
                return "BeforeEqual";
            }

            @Override
            public Object param() {
                return date.format(DateFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDate value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isBefore(date) || value.isEqual(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDate> RangeClose(LocalDate min, LocalDate max) {
        return new Refined<LocalDate>() {
            @Override
            public String name() {
                return "RangeClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min.format(DateFormatter), max.format(DateFormatter));
            }

            @Override
            public RefineResult check(String name, LocalDate value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isAfter(min) && value.isBefore(max)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDate> RangeCloseOpen(LocalDate min, LocalDate max) {
        return new Refined<LocalDate>() {
            @Override
            public String name() {
                return "RangeCloseOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min.format(DateFormatter), max.format(DateFormatter));
            }

            @Override
            public RefineResult check(String name, LocalDate value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isAfter(min) && (value.isBefore(max) || value.isEqual(max))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDate> RangeOpen(LocalDate min, LocalDate max) {
        return new Refined<LocalDate>() {
            @Override
            public String name() {
                return "RangeCloseOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min.format(DateFormatter), max.format(DateFormatter));
            }

            @Override
            public RefineResult check(String name, LocalDate value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if ((value.isAfter(min) || value.isEqual(min)) && (value.isBefore(max) || value.isEqual(max))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDate> RangeOpenClose(LocalDate min, LocalDate max) {
        return new Refined<LocalDate>() {
            @Override
            public String name() {
                return "RangeOpenClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min.format(DateFormatter), max.format(DateFormatter));
            }

            @Override
            public RefineResult check(String name, LocalDate value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if ((value.isAfter(min) || value.isEqual(min)) && value.isBefore(max)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    // datetime
    public static Refined<LocalDateTime> Equal(LocalDateTime date) {
        return new Refined<LocalDateTime>() {
            @Override
            public String name() {
                return "Equal";
            }

            @Override
            public Object param() {
                return date.format(TimeFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDateTime value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isEqual(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDateTime> After(LocalDateTime date) {
        return new Refined<LocalDateTime>() {
            @Override
            public String name() {
                return "After";
            }

            @Override
            public Object param() {
                return date.format(TimeFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDateTime value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isAfter(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDateTime> AfterEqual(LocalDateTime date) {
        return new Refined<LocalDateTime>() {
            @Override
            public String name() {
                return "AfterEqual";
            }

            @Override
            public Object param() {
                return date.format(TimeFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDateTime value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isEqual(date) || value.isAfter(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDateTime> Before(LocalDateTime date) {
        return new Refined<LocalDateTime>() {
            @Override
            public String name() {
                return "Before";
            }

            @Override
            public Object param() {
                return date.format(TimeFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDateTime value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isBefore(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDateTime> BeforeEqual(LocalDateTime date) {
        return new Refined<LocalDateTime>() {
            @Override
            public String name() {
                return "BeforeEqual";
            }

            @Override
            public Object param() {
                return date.format(TimeFormatter);
            }

            @Override
            public RefineResult check(String name, LocalDateTime value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isBefore(date) || value.isEqual(date)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDateTime> RangeClose(LocalDateTime min, LocalDateTime max) {
        return new Refined<LocalDateTime>() {
            @Override
            public String name() {
                return "RangeClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min.format(TimeFormatter), max.format(TimeFormatter));
            }

            @Override
            public RefineResult check(String name, LocalDateTime value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isAfter(min) && value.isBefore(max)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDateTime> RangeCloseOpen(LocalDateTime min, LocalDateTime max) {
        return new Refined<LocalDateTime>() {
            @Override
            public String name() {
                return "RangeCloseOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min.format(TimeFormatter), max.format(TimeFormatter));
            }

            @Override
            public RefineResult check(String name, LocalDateTime value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.isAfter(min) && (value.isBefore(max) || value.isEqual(max))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDateTime> RangeOpen(LocalDateTime min, LocalDateTime max) {
        return new Refined<LocalDateTime>() {
            @Override
            public String name() {
                return "RangeOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min.format(TimeFormatter), max.format(TimeFormatter));
            }

            @Override
            public RefineResult check(String name, LocalDateTime value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if ((value.isAfter(min) || value.isEqual(min)) && (value.isBefore(max) || value.isEqual(max))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<LocalDateTime> RangeOpenClose(LocalDateTime min, LocalDateTime max) {
        return new Refined<LocalDateTime>() {
            @Override
            public String name() {
                return "RangeOpenClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min.format(TimeFormatter), max.format(TimeFormatter));
            }

            @Override
            public RefineResult check(String name, LocalDateTime value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if ((value.isAfter(min) || value.isEqual(min)) && value.isBefore(max)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }
}
