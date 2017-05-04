package com.lille1.tps.car.command.impl;

import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.user.UserConnection;

public class NoopCommand extends Command {

	@Override
	public void execute(String[] params, UserConnection connection) throws IOException {
		writeReturnCode(connection, ReturnCodes.RC_220);
	}

}
