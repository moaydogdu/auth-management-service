package com.oguz.authmanagementservice.auth.exception;

import java.io.Serial;

public class PasswordNotValidException extends RuntimeException{


    @Serial
    private static final long serialVersionUID = -4872866182620958256L;

    private static final String DEFAULT_MESSAGE = """
            Password is not valid!
            """;

    public PasswordNotValidException()
    {
        super(DEFAULT_MESSAGE);
    }

    public PasswordNotValidException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
