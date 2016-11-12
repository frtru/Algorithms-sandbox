package core;

import java.rmi.server.UID;

public abstract class Algo{
	
	public abstract void init();
	public abstract boolean next();
	public abstract void end();
	
	public boolean compareTo(Algo a_algo){
		return (m_ID == a_algo.m_ID);
	}
	
	private final UID m_ID = new UID();
	
}
