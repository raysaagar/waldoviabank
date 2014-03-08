package edu.harvard.cs262.ComputeServer.SimpleServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import edu.harvard.cs262.ComputeServer.ComputeServer;
import edu.harvard.cs262.ComputeServer.WorkTask;

public class Server implements ComputeServer {
	private static final long serialVersionUID = 1L;
	public Server(){
		super();
	}
	
	@Override
	public Object sendWork(WorkTask work) throws RemoteException {
		return work.doWork();
	}
	
	public static void main(String args[]){
		try{
			if (System.getSecurityManager()==null){
				System.setSecurityManager(new SecurityManager());
			}
			Server mySrv = new Server();
			// note - the tutorial is a bit dated, this doesn't require a port?
			ComputeServer stub = (ComputeServer)UnicastRemoteObject.exportObject(mySrv, 0);
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("SimpleServer", stub);
			
			System.out.println("Server ready");
		} catch (Exception e) {
			System.err.println("[Server exception]: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public boolean PingServer() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}