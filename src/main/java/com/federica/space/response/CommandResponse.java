package com.federica.space.response;

public final class CommandResponse {

    private CommandResponseStatus executed;
    private boolean shouldAbort;
    private String message;
    
    public CommandResponse(final CommandResponseStatus  executed, final boolean shouldAbort, final String message) {
        this.executed = executed;
        this.shouldAbort = shouldAbort;
        this.message = message;
    }

    public CommandResponseStatus getExecuted() {
        return executed;
    }

    public void setExecuted(final CommandResponseStatus executed) {
        this.executed = executed;
    }
    
    public boolean getShouldAbort() {
        return shouldAbort;
    }

    public void setShouldAbort(final boolean shouldAbort) {
        this.shouldAbort = shouldAbort;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final String toString = "Command result: " + executed.getLabel() + ".";
        if (message != null && !message.isEmpty()) {
            return toString + " Found a message: " + message;
        } else {
            return toString; 
        }
    }

}