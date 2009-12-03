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
public interface UserLocalHome extends EJBLocalHome {

    project5376.UserLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
//    project5376.UserLocal create(java.lang.Integer key)  throws CreateException;
    project5376.UserLocal create(java.lang.Integer id, String username, String password,
            String firstname, String lastname, String address1, String address2, String city,
            String state, String zip, String email)  throws CreateException;

    UserLocal findByUserNo(Integer userNo) throws javax.ejb.FinderException;

    Collection findByUserId(String userId) throws javax.ejb.FinderException;

    Collection findByFirstName(String firstName) throws javax.ejb.FinderException;

    Collection findByLastName(String lastName) throws javax.ejb.FinderException;

    Collection findByAddress1(String address1) throws javax.ejb.FinderException;

    Collection findByAddress2(String address2) throws javax.ejb.FinderException;

    Collection findByState(String state) throws javax.ejb.FinderException;

    Collection findByZipCode(Integer zipCode) throws javax.ejb.FinderException;

    Collection findByEmail(String email) throws javax.ejb.FinderException;

    Collection findByPassword(String password) throws javax.ejb.FinderException;

    Collection findAllUsers() throws javax.ejb.FinderException;
}
