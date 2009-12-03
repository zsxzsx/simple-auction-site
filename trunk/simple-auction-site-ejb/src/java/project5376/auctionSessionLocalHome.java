/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author Rory
 */
public interface auctionSessionLocalHome extends EJBLocalHome {
    
    project5376.auctionSessionLocal create()  throws CreateException;

}
