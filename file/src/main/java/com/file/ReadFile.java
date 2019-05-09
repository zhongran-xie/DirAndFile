package com.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Map;

public class ReadFile {

    /**   
     * @Title: readFile   
     * @Description: TODO 读取配置文件
     *  @param path 文件路径 
     * @return: Map<String,String>
     */
    public Map<String, String> readFile(String path) {
	FileInputStream fis = null;
	InputStreamReader isr = null;
	BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
	String str = "";

	try {
	    Map<String, String> maps = new Hashtable<String, String>();
	    fis = new FileInputStream(path);// FileInputStream

	    // 获得文件编码
	    String fileEncode = ReadFile.resolveCode(path);
	    // 从文件系统中的某个文件中获取字节
	    isr = new InputStreamReader(fis, fileEncode);// InputStreamReader 是字节流通向字符流的桥梁,
	    br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
	    while ((str = br.readLine()) != null) {
		String[] split = str.split("=");
		if (split.length > 1) {
		    maps.put(split[0].trim(), split[1].trim());
		}
	    }
	    return maps;
	} catch (FileNotFoundException e) {
	    System.out.println("找不到指定文件");
	} catch (IOException e) {
	    System.out.println("读取文件失败");
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println("读取文件编码失败");
	} finally {
	    try {
		br.close();
		isr.close();
		fis.close();
		// 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return null;

    }

    /**   
     * @Title: resolveCode   
     * @Description: TODO 获取文件编码
     * @param path 文件路径
     * @throws Exception      
     * @return: String 文件编码
     */
    public static String resolveCode(String path) throws Exception {
	InputStream inputStream = new FileInputStream(path);
	byte[] head = new byte[3];
	inputStream.read(head);
	String code = "gb2312"; // 或GBK
	if (head[0] == -1 && head[1] == -2)
	    code = "UTF-16";
	else if (head[0] == -2 && head[1] == -1)
	    code = "Unicode";
	else if (head[0] == -17 && head[1] == -69 && head[2] == -65)
	    code = "UTF-8";

	inputStream.close();
	return code;
    }
}
