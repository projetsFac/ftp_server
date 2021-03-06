package com.lille1.tps.car.command.impl;

import java.io.IOException;

import com.lille1.tps.car.command.Command;
import com.lille1.tps.car.config.ConfigurationService;
import com.lille1.tps.car.config.Mode;
import com.lille1.tps.car.user.UserConnection;

public class EpsvCommand extends Command {

	@Override
	public void execute(final String[] params, final UserConnection connection) throws IOException {
		ConfigurationService.getInstance().setMode(Mode.EXTENDED_PASSIVE, connection);
		int port = ConfigurationService.getInstance().getPort(connection);
		try {
			writeReturnCode(connection, ReturnCodes.compile(ReturnCodes.RC_229, String.valueOf(port)));
			connection.updateMode();
		} catch (Exception e) {
			writeReturnCode(connection, ReturnCodes.RC_425);
		}
	}

}
