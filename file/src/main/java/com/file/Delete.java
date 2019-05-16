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

    private Integer DirNum = 0;
    private Integer FileNum = 0;
    private Map<String, Integer> map = new HashMap<String, Integer>();

    public Map<String, Integer> delete(String path) {
	File file = new File(path);
	// 判断是不是文件夹
	if (file.isDirectory()) {
	    // 获取目录下的文件夹及文件数组
	    String[] filePath = file.list();
	    if (filePath != null) {
		if (filePath.length > 0) {
		    for (int i = 0; i < filePath.length; i++) {
			map = delete(path + File.separator + filePath[i]);
		    }
		}
		// 删除文件夹
		file.delete();
		map.put("dirNum", DirNum++);
		System.out.println("删除目录： " + path);
	    }
	}
	// 判断是否是文件
	if (file.isFile()) {
	    file.delete();
	    System.out.println("删除文件： " + file);
	    map.put("fileNum", FileNum++);
	}
	return map;
    }
}
