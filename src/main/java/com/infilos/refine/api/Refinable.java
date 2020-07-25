package com.infilos.refine.api;

import javax.annotation.Nullable;

/**
 * @author zhiguang.zhang on 2020-06-13.
 *
 * Implement this interface to make a bean become refinable.
 */

public interface Refinable<P, T extends Refinable<P, T>> {

    @SuppressWarnings("unchecked")
    default RefineResult refine() {
        return refine((T) this, null);
    }

    @SuppressWarnings("unchecked")
    default RefineResult refine(P params) {
        return refine((T) this, params);
    }

    /**
     * Implement this method to check the bean.
     */
    RefineResult refine(T bean, @Nullable P params);
}