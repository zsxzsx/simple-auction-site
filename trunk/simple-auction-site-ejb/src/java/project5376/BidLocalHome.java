/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.lang.*;

/**
 *
 * @author tcook
 */
public interface BidLocalHome extends EJBLocalHome {

    project5376.BidLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
  //  project5376.BidLocal create(Integer key)  throws CreateException;

    project5376.BidLocal create(Integer key, Integer bidAmt, UserLocal bidderId, AuctionLocal auctionNo)  throws CreateException;

    Collection findByBidNo(Integer bidNo) throws javax.ejb.FinderException;

    Collection findByBidAmt(Integer bidAmt) throws javax.ejb.FinderException;
    
    Collection findAllBids() throws javax.ejb.FinderException;

}
