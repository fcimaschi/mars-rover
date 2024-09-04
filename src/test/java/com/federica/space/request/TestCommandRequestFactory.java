package com.federica.space.request;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.federica.space.command.Movement;
import com.federica.space.command.Turn;


public class TestCommandRequestFactory {
    
    @Test
    public void testCreateBye() throws Exception {
        final CommandRequest commandRequest = CommandRequestFactory.createCommandRequest("bye");
        Assertions.assertTrue(commandRequest instanceof CommandRequestBye);
    }

    @Test
    public void testCreateState() throws Exception {
        final CommandRequest commandRequest = CommandRequestFactory.createCommandRequest("state");
        Assertions.assertTrue(commandRequest instanceof CommandRequestState);
    }

    @Test
    public void testCreateSequence() throws Exception {
        final CommandRequestSequence expected = new CommandRequestSequence(List.of(Turn.LEFT, Turn.LEFT, Turn.RIGHT, Movement.BACKWARD, Movement.FORWARD));
        final CommandRequest found = CommandRequestFactory.createCommandRequest("l,l,r,b,f");
        Assertions.assertTrue(found instanceof CommandRequestSequence);
        Assertions.assertEquals(expected.getSequence(), ((CommandRequestSequence)found).getSequence());
    }

    @Test
    public void testCreateSequenceFormat() throws Exception {
        final CommandRequestSequence expected = new CommandRequestSequence(List.of(Turn.LEFT, Turn.LEFT, Turn.RIGHT, Movement.BACKWARD, Movement.FORWARD));
        final CommandRequest found = CommandRequestFactory.createCommandRequest("l,L,r,B ,f,,");
        Assertions.assertTrue(found instanceof CommandRequestSequence);
        Assertions.assertEquals(expected.getSequence(), ((CommandRequestSequence)found).getSequence());
    }

    @Test
    public void testCreateSequenceWrongCommands() throws Exception {
        try {
            CommandRequestFactory.createCommandRequest("l,l,r,b,n");
        } catch (Exception e) {
            Assertions.assertEquals("Bad command in sequence 'n'. Abort.", e.getMessage());
        }
    }
}
