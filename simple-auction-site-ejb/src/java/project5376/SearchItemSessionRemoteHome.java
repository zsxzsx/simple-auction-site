/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 *
 * @author tcook
 */
public interface SearchItemSessionRemoteHome extends EJBHome {

    project5376.SearchItemSessionRemote create()  throws CreateException, RemoteException;
    
}
