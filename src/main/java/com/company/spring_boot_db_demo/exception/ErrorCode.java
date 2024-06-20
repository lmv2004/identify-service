package com.company.spring_boot_db_demo.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized exception"),
    INVALID_KEY(999,"Not found enumKey in ErrorCode"),
    USER_EXISTED(1001,"User existed"),
    USERNAME_INVALID(1003,"username must be at least 3 characters"),
    PASSWORD_INVALID(1004,"password must be at least 8 characters"),
    USER_NONEXISTENT(1005,"User not existed"),
    UNAUTHENTICATED(1006,"Unauthenticated"),
    CREATE_TOKEN_IS_FAIL(1007,"Cannot create token")
    ;
    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
