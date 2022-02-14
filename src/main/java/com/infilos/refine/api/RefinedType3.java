package com.infilos.refine.api;

public interface RefinedType3<T extends RefinedType<T>, P1, P2, P3> {

    @SuppressWarnings("unchecked")
    default RefineResult refine(P1 param1, P2 param2, P3 param3) {
        return refine((T) this, param1, param2, param3);
    }

    RefineResult refine(T bean, P1 param1, P2 param2, P3 param3);
}
