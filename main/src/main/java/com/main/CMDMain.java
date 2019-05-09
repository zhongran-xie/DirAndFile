/**
 * 
 */
package com.main;

import java.util.Map;
import java.util.Scanner;

import com.file.ReadFile;

/**   
 * @ClassName:  CopyDirAndFileTest   
 * @Description:TODO  
 * @author: xzr 
 * @date:   2019年4月30日 
 *    
 */

public class CMDMain {

    public static void main(String[] args) {
	@SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
	String path = "";
	// 配置文件路径
	while (path == null || path == "") {
	    System.out.println("请输入配置文件路径：");
	    path = sc.next();
	}
	ReadFile readFile = new ReadFile();
	Map<String, String> map = readFile.readFile(path);
	try {
	    CopyDirAndFile.copyDir(map.get("oldPath"), map.get("newPath"),
		    (map.get("paths") == null) ? null : map.get("paths").split(","),
		    (map.get("files") == null) ? null : map.get("files").split(","));
	    System.out.println("复制完成");
	} catch (Exception e) {
	    System.err.println("复制出错了");
	}

    }

}
