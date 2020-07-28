package com.infilos.refine.api;

import java.util.Collections;
import java.util.List;

/**
 * @author infilos on 2020-06-13.
 *
 * Implement this interface to provide check logic for the value of type T.
 */

public interface Refined<T> {

    /**
     * Anonymous implemention must override this method to provide a meaningful name.
     */
    default String name() {
        return this.getClass().getName();
    }

    default Object param() {
        return null;
    }

    default List<Object> params() {
        return Collections.emptyList();
    }

    RefineResult check(String name, T value);
}