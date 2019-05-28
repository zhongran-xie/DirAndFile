/**
 * 
 */
package com.file;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.UpdateDTO;

/**   
 * @ClassName:  Update   
 * @Description:TODO  修改文件名
 * @author: xzr 
 * @date:   2019年5月28日 
 *     
 */
public class Update {

    private Integer updateDir = 0;// 修改的文件夹名数量
    private Integer updateFile = 0;// 修改的文件名数量

    /** 
      * @Title: update 
      * @Description: TODO 
      * @param list List<UpdateDTO> 集合
      * @return: Map<String,Object> 返回值类型
    */
    public Map<String, Object> update(List<UpdateDTO> list) {
	Map<String, Object> map = new HashMap<String, Object>();
	for (UpdateDTO updateDTO : list) {
	    update(updateDTO);
	}
	map.put("updateDir", updateDir);
	map.put("updateFile", updateFile);
	return map;
    }

    /** 
      * @Title: update 
      * @Description: TODO
      * @param updateDTO 文件操作类
      * @return: void 返回值类型
    */
    private void update(UpdateDTO updateDTO) {
	String path = updateDTO.getPath();
	File file = new File(path);
	String[] list = file.list();

	for (String string : list) {
	    File file1 = new File(path + File.separator + string);
	    if (file1.isDirectory()) {
		// 是修改子文件夹
		if (updateDTO.isSubFolder()) {
		    updateDTO.setPath(path + File.separator + string);
		    update(updateDTO);
		}
		// 修改文件夹名
		if (updateDTO.getDirOrFile() == 0) {

		}
	    }
	    // 修改文件名
	    if (file1.isFile() && updateDTO.getDirOrFile() == 1) {
		update(updateDTO, string, path, file1);
	    }
	}

    }

    
    
    /** 
      * @Title: update 
      * @Description: TODO
      * @param updateDTO 文件操作
      * @param string 文件全名
      * @param path 路径
      * @param file1 
      * @return: void 返回值类型
    */
    private void update(UpdateDTO updateDTO, String string, String path, File file1) {
	// 获取文件的后缀名（文件类型）
	String fileSuffix = string.substring(string.lastIndexOf("."));
	// 获取文件名（无后缀）
	String fileName = string.substring(0, string.lastIndexOf("."));

	
	if (updateDTO.getFileType() != null) {// 指定文件类型
	    if (updateDTO.getFileType().equalsIgnoreCase(fileSuffix)) {// 是否匹配（匹配继续操作，不匹配不操作）
		update(path, fileName, fileSuffix, updateDTO, file1);
	    }
	} else {// 不指定文件类型（所有文件类型）
	    update(path, fileName, fileSuffix, updateDTO, file1);
	}

    }

    /** 
      * @Title: update 
      * @Description: TODO 
      * @param path 路径
      * @param fileName 文件名
      * @param fileSuffix 文件后缀（文件类型）
      * @param updateDTO 文件操作
      * @param file1 
      * @return: void 返回值类型
    */
    private void update(String path, String fileName, String fileSuffix, UpdateDTO updateDTO, File file1) {
	if (fileName.indexOf(updateDTO.getOldName()) != -1) {
	    if (updateDTO.getType() == 0) {
		String newrString = fileName.replace(updateDTO.getOldName(), updateDTO.getNewName()) + fileSuffix;
		File file2 = new File(path + File.separator + newrString);
		file1.renameTo(file2);
		updateFile++;
	    }
	    if (updateDTO.getType() == 1 && fileName.equalsIgnoreCase(updateDTO.getOldName())) {
		File file2 = new File(path + File.separator + updateDTO.getNewName() + fileSuffix);
		file1.renameTo(file2);
		updateFile++;
	    }
	}
    }
}
