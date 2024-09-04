package com.federica.space.response;

public enum CommandResponseStatus { 
    DONE, PARTIAL, ABORT;

    public String getLabel() {
        return switch (this) {
            case DONE -> "Done";
            case PARTIAL -> "Partial done";
            case ABORT -> "Abort";
        };
    }
}