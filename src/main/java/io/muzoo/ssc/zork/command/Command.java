package io.muzoo.ssc.zork.command;

import io.muzoo.ssc.zork.Game;

public interface Command {

    void execute(Game game);

}