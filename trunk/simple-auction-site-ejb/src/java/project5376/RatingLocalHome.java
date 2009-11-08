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
public interface RatingLocalHome extends EJBLocalHome {

    project5376.RatingLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    project5376.RatingLocal create(java.lang.Integer key)  throws CreateException;

    Collection findByRatingNo(Integer ratingNo) throws javax.ejb.FinderException;

    Collection findByRating(int rating) throws javax.ejb.FinderException;

    Collection findByComment(String comment) throws javax.ejb.FinderException;

}
