package com.infilos.refine.api;

/**
 * @author infilos on 2020-06-13.
 */

public final class RefineFailure extends RuntimeException {

    public RefineFailure(String message, Throwable cause) {
        super(message, cause);
    }
}
