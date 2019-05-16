/**
 * 
 */
package com.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**   
 * @ClassName:  Delete   
 * @Description:TODO  
 * @author: xzr 
 * @date:   2019年5月16日 
 *     
 */
public class Delete {

    private Integer dirNum = 0;
    private Integer fileNum = 0;

    public Map<String, Integer> delete(String path) {
	deleteDirAndFile(path);
	Map<String, Integer> map = new HashMap<String, Integer>();
	map.put("dirNum", dirNum);
	map.put("fileNum", fileNum);
	return map;

    }

    /** 
      * @Title: deleteDirAndFile 
      * @Description: TODO 删除文件及文件夹
      * @param path 需要删除的路径
      * @return: Map<String,Integer> 返回值类型
    */
    private void deleteDirAndFile(String path) {
	File file = new File(path);
	// 判断是不是文件夹
	if (file.isDirectory()) {
	    // 获取目录下的文件夹及文件数组
	    String[] filePath = file.list();
	    if (filePath != null) {
		if (filePath.length > 0) {
		    for (int i = 0; i < filePath.length; i++) {
			deleteDirAndFile(path + File.separator + filePath[i]);
		    }
		}
		// 删除文件夹
		file.delete();
		dirNum++;
		System.out.println("删除目录： " + path);
	    }
	}
	// 判断是否是文件
	if (file.isFile()) {
	    file.delete();
	    System.out.println("删除文件： " + file);
	    fileNum++;
	}
    }
}
