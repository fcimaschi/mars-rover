package com.federica.space.request;

public final class CommandRequestFactory {

    private CommandRequestFactory() {
        // no-op
    }

    public static CommandRequest createCommandRequest(final String commands) throws Exception {
        return switch (commands.toLowerCase()) {
            case "bye" -> CommandRequestBye.getInstance();
            case "state" -> CommandRequestState.getInstance();
            default -> CommandRequestSequence.createCommandRequest(commands);
        };
    }
}
