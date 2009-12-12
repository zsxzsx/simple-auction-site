/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author tcook
 */
public interface SearchItemSessionLocalHome extends EJBLocalHome {
    
    project5376.SearchItemSessionLocal create()  throws CreateException;

}
