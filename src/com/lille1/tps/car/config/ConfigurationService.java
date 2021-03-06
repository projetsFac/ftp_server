package com.lille1.tps.car.config;

import com.lille1.tps.car.user.UserConnection;
import com.lille1.tps.car.utils.MyLogger;

public class ConfigurationService {
	private static int port = 10000;
	
	private static ConfigurationService instance;
	
	private ConfigurationService() {}
	
	public static ConfigurationService getInstance() {
		if(instance == null) {
			instance = new ConfigurationService();
		}
		
		return instance;
	}
	
	/*
	 * TYPE
	 */
	// TODO : Add printing mode
	public void setType(Type type, UserConnection connection) { 
		connection.getConfig().setTypeFile(type);
		MyLogger.i("TYPE de fichier a tranférer : " + type);
	}
	
	/*
	 * MODE
	 */
	
	public void setMode(Mode mode, UserConnection connection) {
		connection.getConfig().setMode(mode);
		if (mode == Mode.PASSIVE || mode == Mode.EXTENDED_PASSIVE) {
			connection.getConfig().setPort(port++);
		}
		MyLogger.i("Passage en mode " + mode);
	}
	
	public Mode getMode(UserConnection connection) {
		return connection.getConfig().getMode();
	}

	/*
	 * PORT
	 */
	public void setPort(String[] params, UserConnection connection) {
		MyLogger.i("Configuration du port pour le prochain envoi");
		final String[] tokens = params[1].split(",");
		String ip = "";
		for(int i = 0; i < 4; ++i) {
			ip += tokens[i];
		}
		final Integer port = Integer.valueOf(tokens[4]) * 256 + Integer.valueOf(tokens[5]);
		connection.getConfig().setIp(ip);
		connection.getConfig().setPort(port);
		MyLogger.i("IP : " + ip);
		MyLogger.i("PORT : " + port);
	}

	public void setExtendedPort(String[] params, UserConnection connection) {
		final String[] tokens = params[1].split("\\|");
		NetworkProtocol protocol = NetworkProtocol.values()[Integer.valueOf(tokens[1]) - 1];
		String ip = tokens[2];
		int port = Integer.valueOf(tokens[3]);
		connection.getConfig().setNetworkProtocol(protocol);
		connection.getConfig().setIp(ip);
		connection.getConfig().setPort(port);
		MyLogger.i("Protocol : " + protocol);
		MyLogger.i("IP : " + ip);
		MyLogger.i("PORT : " + port);
	}

	public int getPort(UserConnection connection) {
		return connection.getConfig().getPort();
	}

	public Type getType(UserConnection connection) {
		return connection.getConfig().getTypeFile();
	}
	
}
