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
public interface RatingLocal extends EJBLocalObject {

    Integer getRatingNo();

    void setRatingNo(Integer ratingNo);

    int getRating();

    void setRating(int rating);

    String getComment();

    void setComment(String comment);

    UserLocal getRaterId();

    void setRaterId(UserLocal raterId);

    UserLocal getRatedUserId();

    void setRatedUserId(UserLocal ratedUserId);


}
