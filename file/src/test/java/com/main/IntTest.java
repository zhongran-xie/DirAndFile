/**
 * 
 */
package com.main;

import org.junit.Test;

/**   
 * @ClassName:  IntTest   
 * @Description:TODO  
 * @author: xzr 
 * @date:   2019年5月24日 
 *     
 */
public class IntTest {

    @Test
    public void TT() {
	String string = null;
	try {
	    int parseInt = Integer.parseInt(string);
	    System.out.println(parseInt);
	} catch (NumberFormatException e) {
	    System.out.println("转换失败");
	}

    }
}
