/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author tcook
 */
public interface AuctionLocalHome extends EJBLocalHome {

    project5376.AuctionLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    project5376.AuctionLocal create(java.lang.Integer key)  throws CreateException;

    Collection findByAuctionNo(Integer auctionNo) throws javax.ejb.FinderException;

}
