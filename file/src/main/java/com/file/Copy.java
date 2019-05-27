/**
 * 
 */
package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    private Integer copyDir = 0;// 复制的文件夹数
    private Integer copyFile = 0;// 复制的文件数
    private Integer skipDir = 0;// 跳过的文件夹数
    private Integer skipFile = 0;// 跳过的文件数

    /** 
      * @Title: copy 
      * @Description: TODO 复制文件及文件夹
      * @param oldPath 源路径
      * @param newPath 新路径
      * @param paths 需要跳过的文件夹
      * @param files 需要跳过的文件
      * @param type 复制类型 0：只复制文件，1：只复制文件夹，2：复制文件及文件夹
      * @throws IOException
      * @return: Map<String,Integer> 返回值：返回复制及跳过的文件及文件夹
    */
    public Map<String, Integer> copy(String oldPath, String newPath, String[] paths, String[] files, Integer type)
	    throws IOException {
	Map<String, Integer> map = new HashMap<String, Integer>();

	if (type != 0 && type != 1 && type != 2) {
	    System.out.println("类型错误");
	    return map;
	}
	File file = new File(oldPath);
	// 获取目录下的文件夹及文件数组
	String[] filePath = file.list();

	if (filePath != null && filePath.length > 0) {
	    // 获取源路径需要复制的文件夹
	    // newPath = newPath + File.separator +
	    // oldPath.substring(oldPath.lastIndexOf(File.separator) + 1);
	    newPath = newPath + File.separator + file.getName();
	    // 判断需要复制的文件夹是否存在。
	    if (new File(newPath).exists()) {
		System.out.println("路径已经存在");
		throw new RuntimeException();
	    }
	    new File(newPath).mkdirs();

	    copyDirAndFile(oldPath, newPath, paths, files, type);

	} else if (filePath.length == 0) {
	    System.out.println("原路径\t" + file.toString().replace("/", "\\") + "   为空目录");
	} else {
	    System.out.println("原路径\t" + file.toString().replace("/", "\\") + "   未找到");
	}

	if (copyDir == 0 && copyFile == 0) {
	    new File(newPath).delete();
	}

	map.put("copyDir", copyDir);
	map.put("copyFile", copyFile);
	map.put("skipDir", skipDir);
	map.put("skipFile", skipFile);
	return map;
    }

    /** 
      * @Title: copy 
      * @Description: TODO 复制文件夹及文件
      * @param oldPath 原路径
      * @param newPath 新路径
      * @param paths 需要过滤的文件夹
      * @param files 需要过滤的文件
      * @param type 复制类型
      * @throws IOException 可能抛出的异常
      * @return: void 返回值类型
    */
    private void copyDirAndFile(String oldPath, String newPath, String[] paths, String[] files, Integer type)
	    throws IOException, RuntimeException {
	File file = new File(oldPath);
	// 获取目录下的文件夹及文件数组
	String[] filePath = file.list();
	// 判断目标文件夹是否存在，不存在就创建
	if (!(new File(newPath)).exists()) {
	    new File(newPath).mkdir();
	    copyDir++;
	}

	for (int i = 0; i < filePath.length; i++) {

	    if ((new File(oldPath + File.separator + filePath[i])).isDirectory()) {
		// 过滤的文件夹
		if (Filtrate.useArraysBinarySearch(paths, filePath[i])) {
		    System.out.println("跳过文件夹：" + filePath[i]);
		    skipDir++;
		    continue;
		}

		switch (type) {
		case 0:
		    // 只复制文件
		    copyDirAndFile(oldPath + File.separator + filePath[i], newPath, paths, files, type);
		    break;
		default:
		    // 只复制文件夹//复制文件及文件夹
		    copyDirAndFile(oldPath + File.separator + filePath[i], newPath + File.separator + filePath[i],
			    paths, files, type);
		    break;
		}

	    }

	    // 判断是否是文件
	    if (new File(oldPath + File.separator + filePath[i]).isFile() && type != 1) {
		// 过滤的文件
		if (Filtrate.useArraysBinarySearch(files, filePath[i])) {
		    System.out.println("跳过文件：" + filePath[i]);
		    skipFile++;
		    continue;
		}
		copyFile(oldPath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
		copyFile++;
	    }

	}
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
	// 获取文件路径
	// String beginStr = newPath.substring(0, newPath.lastIndexOf(File.separator));
	// 获取文件名
	// String endStr = newPath.substring(newPath.lastIndexOf(File.separator) + 1);

	File f = new File(newPath);
	// 获取路径
	String parent = f.getParent();
	// 获取文件名
	String name = f.getName();

	File newFile = new File(parent);
	// 获取目标目录下的文件夹及文件数组
	String[] filePath = newFile.list();
	List<String> list = Arrays.asList(filePath);
	List<String> filePaths = new ArrayList<String>();
	filePaths.addAll(list);
	// 只保留文件集合
	for (int i = 0; i < filePaths.size(); i++) {
	    if (new File(parent + File.separator + filePaths.get(i)).isDirectory()) {
		filePaths.remove(filePaths.get(i));
	    }
	}

	// 判断复制的文件是否已经存在
	while (filePaths.contains(name)) {
	    int parseInt = 1;
	    // 截取数字
	    String substring = name.substring(name.lastIndexOf("(") + 1, name.lastIndexOf(".") - 1);
	    try {
		parseInt = Integer.parseInt(substring);
		parseInt++;
	    } catch (NumberFormatException e) {
		System.out.println("转换失败");
	    } finally {
		// 获取(在文件名中的索引
		int begIn = name.lastIndexOf("(");
		// 获取.在文件名中的索引
		int endIn = name.lastIndexOf(".");
		// 截取(前的文件名
		String front = (begIn == -1) ? name.substring(0, endIn) : name.substring(0, begIn);
		// 截取文件的后缀名（文件类型）
		String later = name.substring(name.lastIndexOf("."));
		// 拼接为最终文件名
		name = front + "(" + parseInt + ")" + later;
	    }
	}
	newPath = parent + File.separator + name;

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
