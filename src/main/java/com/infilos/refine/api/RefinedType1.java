package com.infilos.refine.api;

public interface RefinedType1<T extends RefinedType<T>, P> {

    @SuppressWarnings("unchecked")
    default RefineResult refine(P param) {
        return refine((T) this, param);
    }

    RefineResult refine(T bean, P param);
}
