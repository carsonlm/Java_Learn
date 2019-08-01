package com.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class TEST {

	public static void main(String[] args) throws MalformedURLException {
		UserHandler userHandler = null;
		try {
			userHandler = new UserHandlerImpl();
			/* 通过一个名称映射到该远程对象的引用，客户端通过该名称获取该远程对象的引用。*/
			Naming.rebind("user", userHandler);
			System.out.println(" rmi server is ready ...");
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}



}
