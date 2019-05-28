/**
 * 
 */
package com.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.common.UpdateDTO;
import com.file.Update;

/**   
 * @ClassName:  UpdateTest   
 * @Description:TODO  
 * @author: xzr 
 * @date:   2019年5月27日 
 *     
 */
public class UpdateTest {

    @Test
    public void update() {
	File f = new File("C:\\Users\\Administrator\\Desktop\\文档\\test\\old\\新建文件.txt");
	String c = f.getParent();// 获取前缀
	String name = f.getName();// 获取后缀名
	File mm = new File(c + File.separator + "修改.txt");
	if (f.renameTo(mm)) {
	    System.out.println("修改成功!");
	} else {
	    System.out.println("修改失败");
	}
    }

    @Test
    public void updateTest() {
	List<UpdateDTO> list = new ArrayList<UpdateDTO>();

	list.add(new UpdateDTO("C:\\Users\\Administrator\\Desktop\\文档\\test\\old", "修改", "压缩", 0, false, ".zip", 0));
	Map<String, Object> update = new Update().update(list);
	System.out.println("修改了文件夹名：" + update.get("updateDir") + " 个");
	System.out.println("修改了文件名：" + update.get("updateFile") + " 个");
    }
}
