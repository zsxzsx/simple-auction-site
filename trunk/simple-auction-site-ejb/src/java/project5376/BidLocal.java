/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import javax.ejb.EJBLocalObject;

/**
 *
 * @author tcook
 */
public interface BidLocal extends EJBLocalObject {

    Integer getBidNo();

    void setBidNo(Integer bidNo);

    Integer getBidAmt();

    void setBidAmt(Integer bidAmt);

    AuctionLocal getAuctionNo();

    void setAuctionNo(AuctionLocal auctionNo);

    UserLocal getBidderId();

    void setBidderId(UserLocal bidderId);

}
