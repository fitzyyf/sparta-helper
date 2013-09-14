/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.servlets.assets;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7084957514695533766L;

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
