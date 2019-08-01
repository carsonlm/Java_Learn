package com.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 实现了java.rmi.Remote接口的类或者继承了java.rmi.Remote接口的所有接口都是远程对象。
 这些继承或者实现了该接口的类或者接口中定义了客户端可以访问的方法。这个远程对象中可能有很多个方法，
 但是只有在远程接口中声明的方法才能从远程调用，其他的公共方法只能在本地虚拟机中使用。
 ---------------------
 子接口的每个方法都必须声明抛出java.rmi.RemoteException异常，该异常是使用RMI时可能抛出的大多数异常的父类。
 ---------------------
 子接口的实现类应该直接或者间接继承java.rmi.server.UnicastRemoteObject类，该类提供了很多支持RMI的方法，
 具体来说，这些方法可以通过JRMP协议导出一个远程对象的引用，并通过动态代理构建一个可以和远程对象交互的Stub对象。
 ---------------------

 */
public interface UserHandler extends Remote {

	String getUserName(int id) throws RemoteException;
	int getUserCount() throws RemoteException;
	User getUserByName(String name) throws RemoteException;

}
