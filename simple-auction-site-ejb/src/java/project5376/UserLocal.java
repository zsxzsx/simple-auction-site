/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.util.Collection;
import java.util.ArrayList;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author tcook
 */
public interface UserLocal extends EJBLocalObject {

    Integer getUserNo();

    void setUserNo(Integer userNo);

    String getUserId();

    void setUserId(String userId);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getAddress1();

    void setAddress1(String address1);

    String getAddress2();

    void setAddress2(String address2);

    String getState();

    void setState(String state);

    Integer getZipCode();

    void setZipCode(Integer zipCode);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Collection getAuctionCollection();

    void setAuctionCollection(Collection auctionCollection);

    Collection getPaymentCollection();

    void setPaymentCollection(Collection paymentCollection);

    Collection getRatingCollection();

    void setRatingCollection(Collection ratingCollection);

    Collection getRatingCollection1();

    void setRatingCollection1(Collection ratingCollection1);

    Collection getBidCollection();

    void setBidCollection(Collection bidCollection);
      ArrayList getUserAuctions();
      ArrayList getUserBids();
      ArrayList getSellerRatings();

}
