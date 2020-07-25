package com.infilos.refine.std;

import com.infilos.refine.api.RefineResult;
import com.infilos.refine.api.Refined;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author infilos on 2020-06-02.
 */

public final class Lists {
    private Lists() {
    }

    public static <T, C extends Collection<T>> Refined<C> IsContains(T element) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsContains";
            }

            @Override
            public Object param() {
                return element;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.stream().anyMatch(e -> e.equals(element))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> NotContains(T element) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "NotContains";
            }

            @Override
            public Object param() {
                return element;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.stream().noneMatch(e -> e.equals(element))) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> AllMatches(Predicate<T> predicate) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "AllMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.stream().allMatch(predicate)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> AnyMatches(Predicate<T> predicate) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "AnyMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.stream().anyMatch(predicate)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> NonMatches(Predicate<T> predicate) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "NonMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.stream().noneMatch(predicate)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> OneMatches(Predicate<T> predicate) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "OneMatches";
            }

            @Override
            public Object param() {
                return predicate;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (java.util.Objects.isNull(value)) {
                    return RefineResult.failed(name, null, this);
                }
                if (value.stream().filter(predicate).count()==1) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsEmpty() {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsEmpty";
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.isNull(value) || value.size()==0) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> NotEmpty() {
        return new Refined<C>() {
            @Override
            public String name() {
                return "NotEmpty";
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size()!=0) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsSizeEqual(int size) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeEqual";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size()==size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> NotSizeEqual(int size) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeEqual";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size()!=size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsSizeGreaterEqual(int size) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeGreaterEqual";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size() >= size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsSizeGreaterThan(int size) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeGreaterThan";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size() > size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsSizeLessEqual(int size) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeLessEqual";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size() <= size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsSizeLessThan(int size) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeLessThan";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size() < size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsSizeRangeClose(int min, int max) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeRangeClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size() > min && value.size() < max) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsSizeRangeCloseOpen(int min, int max) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeRangeCloseOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size() > min && value.size() <= max) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsSizeRangeOpen(int min, int max) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeRangeOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size() >= min && value.size() <= max) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static <T, C extends Collection<T>> Refined<C> IsSizeRangeOpenClose(int min, int max) {
        return new Refined<C>() {
            @Override
            public String name() {
                return "IsSizeRangeOpenClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, C value) {
                if (Objects.nonNull(value) && value.size() >= min && value.size() < max) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }
}
