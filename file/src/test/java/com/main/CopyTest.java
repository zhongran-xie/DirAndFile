/**
 * 
 */
package com.main;

import java.io.IOException;
import java.util.Map;

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
//	String oldPath = "C:\\Users\\Administrator\\Desktop\\文档\\test\\old";
	String oldPath = "C:\\Users\\Administrator\\Desktop\\文档\\test\\1";
	String newPath = "C:\\Users\\Administrator\\Desktop\\文档\\test\\new";
	String[] paths = { "pay" };
	String[] files = { "新建 WinRAR ZIP 压缩文件.zip" };
	try {
	    Map<String, Integer> map = new Copy().copy(oldPath, newPath, paths, files, 1);
	    System.out.println("复制的文件夹：" + map.get("copyDir") + " 个");
	    System.out.println("复制的文件：" + map.get("copyFile") + " 个");
	    System.out.println("跳过的文件夹：" + map.get("skipDir") + " 个");
	    System.out.println("跳过的文件：" + map.get("skipFile") + " 个");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
