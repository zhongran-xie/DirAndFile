/**
 * 
 */
package com.main;

import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import com.file.Delete;

/**   
 * @ClassName:  DeleteTeest   
 * @Description:TODO  
 * @author: xzr 
 * @date:   2019年5月10日 
 *     
 */
public class DeleteTest {

    @Test
    public void deleteTest() {
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);
	System.out.println("请输入文件夹路径");
	String path = scanner.next();
	Map<String, Integer> delete = new Delete().delete(path);
	System.out.println("删除文件夹" + delete.get("dirNum"));
	System.out.println("删除文件" + delete.get("fileNum"));
	System.out.println("完成");
    }

}
