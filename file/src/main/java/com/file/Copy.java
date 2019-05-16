/**
 * 
 */
package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.common.Filtrate;

/**   
 * @ClassName:  CopyDirAndFile   
 * @Description:TODO  复制文件夹或者文件
 * @author: xzr 
 * @date:   2019年4月30日 
 *     
 */
public class Copy {
    private Map<String, Integer> map = new HashMap<String, Integer>();

    /** 
      * @Title: copy 
      * @Description: TODO 复制文件夹及文件
      * @param oldPath 原路径
      * @param newPath 新路径
      * @param paths 需要过滤的文件夹
      * @param files 需要过滤的文件
      * @throws IOException 可能抛出的异常
      * @return: void 返回值类型
    */
    public Map<String, Integer> copy(String oldPath, String newPath, String[] paths, String[] files)
	    throws IOException {
	File file = new File(oldPath);
	// 获取目录下的文件夹及文件数组
	String[] filePath = file.list();
	// 判断是否存在文件夹或文件
	if (filePath != null) {
	    System.out.println(filePath.length);
	    // 判断目标文件夹是否存在，不存在就创建
	    if (!(new File(newPath)).exists()) {
		(new File(newPath)).mkdir();
	    }

	    for (int i = 0; i < filePath.length; i++) {

		if ((new File(oldPath + File.separator + filePath[i])).isDirectory()) {
		    // 过滤的文件夹
		    if (Filtrate.useArraysBinarySearch(paths, filePath[i])) {
			System.out.println("跳过文件夹：" + filePath[i]);
			continue;
		    }
		    map = copy(oldPath + File.separator + filePath[i], newPath + File.separator + filePath[i], paths,
			    files);
		}
		// 判断是否是文件
		if (new File(oldPath + File.separator + filePath[i]).isFile()) {
		    // 过滤的文件
		    if (Filtrate.useArraysBinarySearch(files, filePath[i])) {
			System.out.println("跳过文件：" + filePath[i]);
			continue;
		    }
		    copyFile(oldPath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
		}

	    }
	} else {
	    System.out.println("原路径\t" + file.toString().replace("/", "\\") + "   未找到");
	}

	return map;

    }

    /** 
      * @Title: copyDir 
      * @Description: TODO  只复制文件夹
      * @param oldpath 原路径
      * @param newPath 新路径
      * @param paths 需要过滤的文件夹（不复制的文件夹）
      * @return: void 返回值类型
    */
    public void copyDir(String oldpath, String newPath, String[] paths) {

    }

    /** 
      * @Title: copyFile 
      * @Description: TODO 只复制文件
      * @param oldPath 老路径
      * @param newPath 新路径
      * @param paths 需要过滤的文件夹
      * @param files 需要过滤的文件
      * @return: void 返回值类型
    */
    public void copyFile(String oldPath, String newPath, String[] paths, String[] files) {

    }

    /** 
      * @Title: copyFile 
      * @Description: TODO 复制文件
      * @param oldPath 原路径
      * @param newPath 新路径
      * @throws IOException 可能抛出异常
      * @return: void 返回值类型
    */
    private void copyFile(String oldPath, String newPath) throws IOException {
	File oldFile = new File(oldPath);
	File file = new File(newPath);
	FileInputStream in = new FileInputStream(oldFile);
	FileOutputStream out = new FileOutputStream(file);

	byte[] buffer = new byte[2097152];
	int readByte = 0;
	while ((readByte = in.read(buffer)) != -1) {
	    out.write(buffer, 0, readByte);
	}
	in.close();
	out.close();
    }

}
