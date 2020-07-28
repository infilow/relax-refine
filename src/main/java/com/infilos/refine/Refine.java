package com.infilos.refine;

import com.infilos.refine.api.RefineRange;
import com.infilos.refine.api.RefineResult;
import com.infilos.refine.api.Refined;
import com.infilos.refine.api.Refineder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author infilos on 2020-06-13.
 */

public final class Refine {
    private Refine() {
    }

    public static <R, T extends R> RefineResult check(T value, Refined<R> refined) {
        requireNonNull("refined", refined);
        return new Refineder<>(value, refined).check();
    }

    public static <R, T extends R> RefineResult check(String name, T value, Refined<R> refined) {
        requireNonNull("refined", refined);
        return new Refineder<>(name, value, refined).check();
    }

    public static RefineResult check(Refineder<?>... refineders) {
        requireNonNull("refineders", refineders);
        return RefineResult.merge(
            Arrays.stream(refineders).map(Refineder::check).collect(Collectors.toList())
        );
    }

    public static ValueBuilder of(String name) {
        requireNonNull("name", name);
        return new ValueBuilder(name);
    }

    private static void requireNonNull(String name, Object arg) {
        if (Objects.isNull(arg)) {
            throw new IllegalArgumentException(String.format("Refine argument %s must not be null.", name));
        }
    }

    public static final class ValueBuilder {
        private final String name;

        ValueBuilder(String name) {
            this.name = name;
        }

        public <R, T extends R> MatchBuilder<R, T> expect(T value) {
            return new MatchBuilder<>(name, value);
        }

        public ConditionalValueBuilder when(Supplier<Boolean> condition) {
            requireNonNull("condition", condition);
            return new ConditionalValueBuilder(name, condition);
        }
    }

    public static final class ConditionalValueBuilder {
        private final String name;
        private final Supplier<Boolean> condition;

        ConditionalValueBuilder(String name, Supplier<Boolean> condition) {
            this.name = name;
            this.condition = condition;
        }

        public <R, T extends R> MatchBuilder<R, T> expect(T value) {
            return new MatchBuilder<>(name, condition, value);
        }
    }

    public static final class MatchBuilder<R, T extends R> {
        private final String name;
        private final T value;
        private Supplier<Boolean> condition = () -> true;

        MatchBuilder(String name, T value) {
            this.name = name;
            this.value = value;
        }

        MatchBuilder(String name, Supplier<Boolean> condition, T value) {
            this.name = name;
            this.condition = condition;
            this.value = value;
        }

        public Refineder<R> match(Refined<R> refined) {
            requireNonNull("refined", refined);
            return new Refineder<>(name, condition, value, Collections.singletonList(refined), RefineRange.Head);
        }

        @SafeVarargs
        public final Refineder<R> matchAll(Refined<R>... refineds) {
            requireNonNull("refineds", refineds);
            return new Refineder<>(name, condition, value, Arrays.asList(refineds), RefineRange.All);
        }

        @SafeVarargs
        public final Refineder<R> matchAny(Refined<R>... refineds) {
            requireNonNull("refineds", refineds);
            return new Refineder<>(name, condition, value, Arrays.asList(refineds), RefineRange.Any);
        }

        @SafeVarargs
        public final Refineder<R> matchOne(Refined<R>... refineds) {
            requireNonNull("refineds", refineds);
            return new Refineder<>(name, condition, value, Arrays.asList(refineds), RefineRange.One);
        }

        @SafeVarargs
        public final Refineder<R> matchNone(Refined<R>... refineds) {
            requireNonNull("refineds", refineds);
            return new Refineder<>(name, condition, value, Arrays.asList(refineds), RefineRange.None);
        }
    }
}