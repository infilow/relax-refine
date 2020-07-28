package com.infilos.refine.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author infilos on 2020-06-13.
 */

public class Refineder<T> {
    private final String name;
    private final Supplier<Boolean> condition;
    private final T value;
    private final List<Refined<T>> refineds;
    private final RefineRange logical;

    public Refineder(T value, Refined<T> refined) {
        this.name = "Value";
        this.condition = () -> true;
        this.value = value;
        this.refineds = Collections.unmodifiableList(Collections.singletonList(refined));
        this.logical = RefineRange.Head;
    }

    public Refineder(String name, T value, Refined<T> refined) {
        this.name = name;
        this.condition = () -> true;
        this.value = value;
        this.refineds = Collections.unmodifiableList(Collections.singletonList(refined));
        this.logical = RefineRange.Head;
    }

    public Refineder(String name, Supplier<Boolean> condition, T value, List<Refined<T>> refineds, RefineRange logical) {
        this.name = name;
        this.condition = condition;
        this.value = value;
        this.refineds = Collections.unmodifiableList(refineds);
        this.logical = logical;
    }

    public RefineResult check() {
        boolean shoudCheck;
        try {
            shoudCheck = condition.get();
            if (!shoudCheck) {
                return RefineResult.allSkipped(name, value, refineds);
            }
        } catch (Throwable ex) {
            return RefineResult.allThrowed(name, value, refineds, ex);
        }

        List<RefineResult> results = new ArrayList<>();

        for (int ridx = 0; ridx < refineds.size(); ridx++) {
            try {
                RefineResult result = refineds.get(ridx).check(name, value);
                results.add(ridx, result);

                if (!result.isSucced()) {
                    break;
                }
            } catch (Throwable ex) {
                RefineResult result = RefineResult.throwed(name, value, refineds.get(ridx), ex);
                results.add(ridx, result);
                break;
            }
        }

        return RefineResult.format(name, value, refineds, results, logical);
    }
}
