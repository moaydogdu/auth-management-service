package com.oguz.authmanagementservice.auth.exception;

import java.io.Serial;
import java.util.UUID;

public class TokenAlreadyInvalidatedException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 4052601517031140536L;

    private static final String DEFAULT_MESSAGE = """
            Token is already invalidated!
            """;

    public TokenAlreadyInvalidatedException()
    {
        super(DEFAULT_MESSAGE);
    }

    public TokenAlreadyInvalidatedException(
            final UUID tokenId
    ) {
        super(DEFAULT_MESSAGE + " TokenID = " + tokenId);
    }
}
