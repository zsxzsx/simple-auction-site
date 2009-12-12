package project5376;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;
public interface auctionSessionRemoteHome extends EJBHome
{
  auctionSessionRemote create() throws RemoteException, CreateException;
}