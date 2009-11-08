/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.util.Collection;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author tcook
 */
public interface ItemLocal extends EJBLocalObject {

    Integer getItemNo();

    void setItemNo(Integer itemNo);

    String getItemName();

    void setItemName(String itemName);

    String getDescription();

    void setDescription(String description);

    Integer getCondition1();

    void setCondition1(Integer condition1);

    Collection getAuctionCollection();

    void setAuctionCollection(Collection auctionCollection);


}
