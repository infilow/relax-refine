package com.infilos.refine.api;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author infilos on 2020-06-13.
 */

public class RefineResult {
    private final RefineMark mark;
    private final List<String> details;
    private Throwable throwable;

    private RefineResult(RefineMark mark, String detail, Throwable cause) {
        this.mark = mark;
        this.details = Collections.unmodifiableList(Collections.singletonList(detail));
        this.throwable = cause;
    }

    private RefineResult(RefineMark mark, String detail) {
        this.mark = mark;
        this.details = Collections.unmodifiableList(Collections.singletonList(detail));
    }

    private RefineResult(RefineMark mark, List<String> detail) {
        this.mark = mark;
        this.details = Collections.unmodifiableList(detail);
    }

    public static <T> RefineResult succed(String name, T value, Refined<T> refined) {
        return format(name, value, refined, RefineMark.SUCCED, null);
    }

    public static <T> RefineResult failed(String name, T value, Refined<T> refined) {
        return format(name, value, refined, RefineMark.FAILED, null);
    }

    public static <T> RefineResult skipped(String name, T value, Refined<T> refined) {
        return format(name, value, refined, RefineMark.SKIPPED, null);
    }

    public static <T> RefineResult throwed(String name, T value, Refined<T> refined, Throwable cause) {
        return format(name, value, refined, RefineMark.THROWED, cause);
    }

    public static <T> RefineResult allSkipped(String name, T value, List<Refined<T>> refineds) {
        return format(name, value, refineds, RefineMark.SKIPPED, null);
    }

    public static <T> RefineResult allThrowed(String name, T value, List<Refined<T>> refineds, Throwable cause) {
        return format(name, value, refineds, RefineMark.THROWED, cause);
    }

    public static RefineResult merge(List<RefineResult> results) {
        RefineMark mark = markOf(results, RefineRange.All);
        return new RefineResult(mark, results.stream().flatMap(r -> r.details().stream()).collect(Collectors.toList()));
    }

    static <T> RefineResult format(String name, T value, Refined<T> refined, RefineMark mark, Throwable cause) {
        return format(name, value, Collections.singletonList(refined), Collections.singletonList(new MarkResult(mark, cause)), RefineRange.All);
    }

    static <T> RefineResult format(String name, T value, List<Refined<T>> refineds, RefineMark mark, Throwable cause) {
        List<RefineResult> marks = new ArrayList<>();
        for (int idx = 0; idx < refineds.size(); idx++) {
            marks.add(new MarkResult(mark, cause));
        }
        return format(name, value, refineds, marks, RefineRange.All);
    }

    /**
     * [MARK:NAME]Expect `VALUE` match RANGE REFINEDS ACTUAL
     * [MARK:NAME]Expect $NULL match RANGE REFINEDS ACTUAL;
     * [MARK:NAME]Expect $BLANK match RANGE REFINEDS ACTUAL;
     */
    static <T> RefineResult format(String name, T value, List<Refined<T>> refineds, List<RefineResult> results, RefineRange logical) {
        RefineMark mark = markOf(results, logical);
        String markSeg = mark.name();
        String valueSeg = formatValue(value);
        String rangeSeg = logical.name();
        String refinesSeg = formatRefineds(refineds);
        String actualSeg = actualOf(refineds, results, mark, logical!=RefineRange.None);

        StringBuilder builder = new StringBuilder();
        builder.append("[").append(markSeg).append(":").append(name).append("]");
        builder.append("Expect").append(" ").append(valueSeg).append(" ");
        builder.append("match").append(" ");
        if (logical!=RefineRange.Head) {
            builder.append(rangeSeg).append(" ");
        }
        builder.append(refinesSeg);
        if (actualSeg.length()!=0) {
            builder.append(", ").append(actualSeg);
        }

        return new RefineResult(mark, builder.toString(), null);
    }

    private static RefineMark markOf(List<RefineResult> results, RefineRange range) {
        RefineMark mark;
        switch (range) {
            case Head:
                mark = results.get(0).mark();
                break;
            case All:
                if (results.stream().allMatch(RefineResult::isSucced)) {
                    mark = RefineMark.SUCCED;
                } else {
                    mark = RefineMark.FAILED;
                }
                break;
            case Any:
                if (results.stream().anyMatch(RefineResult::isSucced)) {
                    mark = RefineMark.SUCCED;
                } else {
                    mark = RefineMark.FAILED;
                }
                break;
            case One:
                if (results.stream().filter(RefineResult::isSucced).count()==1) {
                    mark = RefineMark.SUCCED;
                } else {
                    mark = RefineMark.FAILED;
                }
                break;
            case None:
                if (results.stream().noneMatch(RefineResult::isSucced)) {
                    mark = RefineMark.SUCCED;
                } else {
                    mark = RefineMark.FAILED;
                }
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return mark;
    }

    private static <T> String actualOf(List<Refined<T>> refineds, List<RefineResult> results, RefineMark mark, boolean expectMatches) {
        switch (mark) {
            case SUCCED:
            case SKIPPED:
                return "";
            case THROWED:
                return results.stream()
                    .filter(r -> r.isThrowed() && Objects.nonNull(r.cause()))
                    .map(r -> formatFailure(r.cause()))
                    .collect(Collectors.joining(","));
            case FAILED:
                List<String> segs = new ArrayList<>();
                if (expectMatches) {
                    List<Refined<T>> notMatches = filterRefineds(refineds, results, RefineMark.FAILED);
                    if (!notMatches.isEmpty()) {
                        segs.add("not matches " + formatRefineds(notMatches));
                    }
                } else {
                    List<Refined<T>> matches = filterRefineds(refineds, results, RefineMark.SUCCED);
                    if (!matches.isEmpty()) {
                        segs.add("but matches " + formatRefineds(matches));
                    }
                }
                List<Refined<T>> skippeds = filterRefineds(refineds, results, RefineMark.SKIPPED);
                if (!skippeds.isEmpty()) {
                    segs.add("skippeds " + formatRefineds(skippeds));
                }
                List<Refined<T>> throweds = filterRefineds(refineds, results, RefineMark.THROWED);
                if (!throweds.isEmpty()) {
                    segs.add("throweds " + formatRefineds(throweds));
                }

                return String.join(", ", segs);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private static <T> List<Refined<T>> filterRefineds(List<Refined<T>> refineds, List<RefineResult> results, RefineMark mark) {
        List<Refined<T>> specifics = new ArrayList<>();
        for (int idx = 0; idx < results.size(); idx++) {
            if (mark==results.get(idx).mark) {
                specifics.add(refineds.get(idx));
            }
        }

        return specifics;
    }

    private static String formatFailure(Throwable cause) {
        if (Objects.isNull(cause)) {
            return "Unknown";
        }

        return String.format("%s(%s)", cause.getClass().getName(), Optional.ofNullable(cause.getMessage()).orElse(""));
    }

    private static String formatValue(Object value) {
        if (Objects.isNull(value)) {
            return "$Null";
        }
        if (value instanceof String && ((String) value).trim().length()==0) {
            return "$Blank";
        }

        String string;
        try {
            if (value.getClass().isArray()) {
                if (value.getClass().getComponentType().isPrimitive()) {
                    if (value instanceof boolean[]) {
                        return Arrays.toString((boolean[]) value);
                    }
                    if (value instanceof char[]) {
                        return Arrays.toString((char[]) value);
                    }
                    if (value instanceof short[]) {
                        return Arrays.toString((short[]) value);
                    }
                    if (value instanceof byte[]) {
                        return Arrays.toString((byte[]) value);
                    }
                    if (value instanceof int[]) {
                        return Arrays.toString((int[]) value);
                    }
                    if (value instanceof long[]) {
                        return Arrays.toString((long[]) value);
                    }
                    if (value instanceof float[]) {
                        return Arrays.toString((float[]) value);
                    }
                    if (value instanceof double[]) {
                        return Arrays.toString((double[]) value);
                    }
                }
                string = Arrays.deepToString((Object[]) value);
            } else if (value instanceof Collection<?>) {
                string = formatValue(((Collection<?>) value).toArray());
            } else {
                string = value.toString();
            }
        } catch (Throwable ex) {
            string = value.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(value));
        }

        if (string.length() > 55) {
            return "`" + string.substring(0, 55) + "...`";
        }

        return string;
    }

    private static <T> String formatRefineds(List<Refined<T>> refineds) {
        return refineds.stream().map(refined -> {
            List<String> params = Optional.ofNullable(refined.param())
                .map(p -> Collections.singletonList(refined.param()))
                .orElse(refined.params())
                .stream()
                .map(RefineResult::formatValue)
                .collect(Collectors.toList());
            return String.format("%s(%s)", refined.name(), String.join(",", params));
        }).collect(Collectors.joining(","));
    }

    public RefineMark mark() {
        return mark;
    }

    @Nullable
    public Throwable cause() {
        return throwable;
    }

    public boolean isSucced() {
        return mark()==RefineMark.SUCCED;
    }

    public boolean isFailed() {
        return mark()==RefineMark.FAILED;
    }

    public boolean isSkipped() {
        return mark()==RefineMark.SKIPPED;
    }

    public boolean isThrowed() {
        return mark()==RefineMark.THROWED;
    }

    public boolean isSuccedOrSkipped() {
        return isSucced() || isSkipped();
    }

    public boolean isFailedOrThrowed() {
        return isFailed() || isThrowed();
    }

    public void onFailedOrThrowed(Consumer<RefineFailure> consumer) {
        if (isThrowed()) {
            consumer.accept(new RefineFailure(throwable.getMessage(), throwable));
        } else if (isFailed()) {
            consumer.accept(new RefineFailure(String.join("", details), null));
        }
    }

    public <T> Optional<T> mapFailedOrThrowed(Function<RefineFailure, T> mapper) {
        if (isThrowed()) {
            return Optional.ofNullable(mapper.apply(new RefineFailure(throwable.getMessage(), throwable)));
        } else if (isFailed()) {
            return Optional.ofNullable(mapper.apply(new RefineFailure(String.join("", details), null)));
        }

        return Optional.empty();
    }

    public List<String> details() {
        return details;
    }

    public String joinDetails() {
        return String.join(";", details) + ".";
    }

    public String joinDetails(String jointer) {
        return String.join(jointer, details) + ".";
    }

    public void showDetails() {
        System.out.println(joinDetails("\n"));
    }

    @Override
    public String toString() {
        return joinDetails();
    }

    static final class MarkResult extends RefineResult {

        public MarkResult(RefineMark mark, Throwable throwable) {
            super(mark, "not-formatted", throwable);
        }
    }
}
