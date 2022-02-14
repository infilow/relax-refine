package com.infilos.refine.api;

public interface RefinedType<T extends RefinedType<T>> {

    @SuppressWarnings("unchecked")
    default RefineResult refine() {
        return refine((T) this);
    }

    RefineResult refine(T bean);
}
