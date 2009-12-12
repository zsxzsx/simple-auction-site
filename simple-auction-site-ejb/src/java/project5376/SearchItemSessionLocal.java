/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import javax.ejb.EJBLocalObject;
import java.util.Collection;
/**
 *
 * @author tcook
 */
public interface SearchItemSessionLocal extends EJBLocalObject {

    public String SearchItemsByDescription(String description);
    public String SearchItemsAll();
    
}
