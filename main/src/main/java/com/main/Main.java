package com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.file.Copy;
import com.gui.Gui;

/**   
 * @ClassName:  Main   
 * @Description:TODO  
 * @author: xzr 
 * @date:   2019年5月6日 
 *     
 */

public class Main {

    public static void main(String[] args) {
	Gui gui = new Gui();

	gui.btn.addActionListener(new ActionListener() {// 按钮点击事件
	    @Override
	    public void actionPerformed(ActionEvent e) {
		Map<String, Object> data = getData(gui);
		Integer state = transactionProcessing(data);

		switch (state) {
		case 0:
		    System.out.println("复制完成");
		    break;
		case 1:
		    System.err.println("复制出错了");
		    break;
		case 2:
		    System.out.println("原路径不能为空！");
		    break;
		case 3:
		    System.out.println("新路径不能为空！");
		    break;
		case 4:
		    System.out.println("原路径错误(不存在此盘)！");
		    break;
		case 5:
		    System.out.println("新路径错误(不存在此盘)！");
		    break;
		default:
		    System.out.println("新路径不能在旧路径下！");
		    break;
		}
	    }
	});
    }

    public static Map<String, Object> getData(Gui gui) {
	Map<String, Object> map = new HashMap<String, Object>();
	// 获取电脑盘符
	File[] dish = File.listRoots();
	String[] dishs = new String[dish.length];
	for (int i = 0; i < dish.length; i++) {
	    String end = dish[i].toString();
	    dishs[i] = end.replace("\\", "/");
	}

	// 获取输入的内容
	// 输入的路径
	String oldPath = gui.textField1.getText();// 原路径
	oldPath = oldPath.replace("\\", "/");
	String newPath = gui.textField2.getText();// 新路径
	newPath = newPath.replace("\\", "/");
	String path = gui.textField3.getText(); // 过滤文件夹
	String[] paths = null;
	if (!path.isEmpty()) {
	    paths = path.split(",");
	}
	String file = gui.textField4.getText(); // 过滤文件
	String[] files = null;
	if (!file.isEmpty()) {
	    files = file.split(",");
	}

	// "".equals(path)

	map.put("oldPath", oldPath);
	map.put("newPath", newPath);
	map.put("paths", paths);
	map.put("files", files);
	map.put("dishs", dishs);
	System.out.println("原目录: " + oldPath);
	System.out.println("新目录: " + newPath);
	System.out.println("过滤目录: " + path);
	System.out.println("过滤文件: " + file);
	String s = "";
	for (String string : dishs) {
	    s = s + " " + string;
	}
	System.out.println("系统盘: " + s);

	return map;
    }

    public static Integer transactionProcessing(Map<String, Object> map) {
	// 判断字符串开头(正则表达)
	// String pattern1 = "[[a-z][A-Z]]:/.*";
	// && Pattern.matches(pattern1, oldPath)
	// && Pattern.matches(pattern1, newPath)
	String oldPath = (String) map.get("oldPath");// 原路径
	String newPath = (String) map.get("newPath");// 新路径
	String[] paths = (String[]) map.get("paths");// 过滤文件夹
	String[] files = (String[]) map.get("files");// 过滤文件
	String[] dishs = (String[]) map.get("dishs");// 系统所有的盘
	
	if (newPath.indexOf(oldPath) != -1)
	    return -1;

	int temp = 0;

	// 判断原路径是否输入，及是否错误
	if (oldPath.isEmpty())
	    return 2;
	for (int i = 0; i < dishs.length; i++) {
	    String start = "";
	    if (oldPath.length() >= 3) {
		start = oldPath.substring(0, 3);
	    }
	    if (dishs[i].equalsIgnoreCase(start)) {
		break;
	    }
	    temp++;
	}
	if (temp == dishs.length) {
	    return 4;
	}
	// 判断新路径是否输入，及是否错误
	if (newPath.isEmpty())
	    return 3;
	temp = 0;
	for (int i = 0; i < dishs.length; i++) {
	    String start = "";
	    if (newPath.length() >= 3) {
		start = newPath.substring(0, 3);
	    }
	    if (dishs[i].equalsIgnoreCase(start)) {
		break;
	    }
	    temp++;
	}
	if (temp == dishs.length) {
	    return 5;
	}
	// 复制文件
	try {
	    new Copy().copy(oldPath, newPath, paths, files,0);
	} catch (Exception ex) {
	    return 1;
	}
	return 0;
    }
}
