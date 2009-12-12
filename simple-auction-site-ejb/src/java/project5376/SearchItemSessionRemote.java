/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import java.util.Collection;
/**
 *
 * @author tcook
 */
public interface SearchItemSessionRemote extends EJBObject {

    public String SearchItemsByDescription(String description) throws RemoteException;
    public String SearchItemsAll() throws RemoteException;
    
}
