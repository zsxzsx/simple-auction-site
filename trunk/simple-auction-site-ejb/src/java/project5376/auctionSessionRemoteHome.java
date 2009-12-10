package project5376;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;
public interface AuctionSessionRemoteHome extends EJBHome
{
  AuctionSessionRemote create() throws RemoteException, CreateException;
}