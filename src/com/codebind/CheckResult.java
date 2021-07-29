package com.codebind;

public class CheckResult {
    boolean isSuccess;
    String message;

    /**
     * Simple class that defines a tuple
     */
    public CheckResult(boolean newIsSuccess, String newMessage) {
        message = newMessage;
        isSuccess = newIsSuccess;
    }
}
