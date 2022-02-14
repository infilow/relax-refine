package com.infilos.refine.api;

public interface RefinedType6<T extends RefinedType<T>, P1, P2, P3, P4, P5, P6> {

    @SuppressWarnings("unchecked")
    default RefineResult refine(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5, P6 param6) {
        return refine((T) this, param1, param2, param3, param4, param5, param6);
    }

    RefineResult refine(T bean, P1 param1, P2 param2, P3 param3, P4 param4, P5 param5, P6 param6);
}