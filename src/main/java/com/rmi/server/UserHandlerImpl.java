package com.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserHandlerImpl extends UnicastRemoteObject implements UserHandler {

	/** 该构造器必须存在，因为集继承了UnicastRemoteObject类，其构造器要抛出RemoteException*/
	protected UserHandlerImpl() throws RemoteException {
	}

	public String getUserName(int id) throws RemoteException {
		return "aaa";
	}

	public int getUserCount() throws RemoteException {
		return 0;
	}

	public User getUserByName(String name) throws RemoteException {
		User user = new User();
		user.setAge(1);
		user.setId(1);
		user.setName("aaa");
		return user;
	}
}
