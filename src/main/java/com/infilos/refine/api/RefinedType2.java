package com.infilos.refine.api;

public interface RefinedType2<T extends RefinedType<T>, P1,P2> {

    @SuppressWarnings("unchecked")
    default RefineResult refine(P1 param1, P2 param2) {
        return refine((T) this, param1, param2);
    }

    RefineResult refine(T bean, P1 param1, P2 param2);
}
