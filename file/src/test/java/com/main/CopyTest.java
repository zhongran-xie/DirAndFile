/**
 * 
 */
package com.main;

import java.io.IOException;

import org.junit.Test;

import com.file.Copy;

/**   
 * @ClassName:  CopyTest    
 * @Description:TODO  文件或文件夹复制测试类
 * @author: xzr 
 * @date:   2019年5月16日 
 *     
 */
public class CopyTest {

    @Test
    public void copy() {
	String oldPath ="C:\\Users\\Administrator\\Desktop\\文档\\test\\test";
	String newPath ="C:\\Users\\Administrator\\Desktop\\文档\\test\\new";
	try {
	    
	    new Copy().copy(oldPath, newPath, null, null);
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
