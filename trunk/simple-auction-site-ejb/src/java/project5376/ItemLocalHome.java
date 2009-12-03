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
public interface ItemLocalHome extends EJBLocalHome {

    project5376.ItemLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    project5376.ItemLocal create(java.lang.Integer key)  throws CreateException;

    Collection findByItemNo(Integer itemNo) throws javax.ejb.FinderException;

    Collection findByItemName(String itemName) throws javax.ejb.FinderException;

    Collection findByDescription(String description) throws javax.ejb.FinderException;

    Collection findByCondition1(Integer condition1) throws javax.ejb.FinderException;

    Collection findAllItems() throws javax.ejb.FinderException;

}
