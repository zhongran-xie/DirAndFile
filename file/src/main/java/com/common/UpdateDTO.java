/**
 * 
 */
package com.common;

import lombok.Getter;
import lombok.Setter;

/**   
 * @ClassName:  UpdateDTO   
 * @Description:TODO  修改名称参数
 * @author: xzr 
 * @date:   2019年5月27日 
 *     
 */
@Getter
@Setter
public class UpdateDTO {
    /**   
     * @Fields path : 路径
     */
    private String path;
    /**   
     * @Fields oldName : 匹配值
     */
    private String oldName;
    // 新名
    /**   
     * @Fields newName : 修改值
     */
    private String newName;
    /**   
     * @Fields type : 匹配类型 0：包含（默认值）；1：完全匹配
     */
    private Integer type=0;
    /**   
     * @Fields subFolder : 是否包括子文件夹： true：包括（默认值）； false ：不包括
     */
    private boolean subFolder = true;
    /**   
     * @Fields fileType : 匹配文件类型（文件后缀名）   
     */
    private String fileType;

    /**   
     * @Fields dirOrFile : 文件还是文件夹 0:文件夹；1:文件
     */
    private Integer dirOrFile;

    /**   
     * @Title:  UpdateDTO   
     * @Description:    TODO   
     * @param path 路径
     * @param oldName 匹配值
     * @param newName 修改值
     * @param type 匹配类型 0：包含（默认值）；1：完全匹配
     * @param subFolder 是否包括子文件夹： true：包括（默认值）； false ：不包括
     * @param fileType 匹配文件类型（文件后缀名）   
     * @param dirOrFile 文件还是文件夹 0:文件夹；1:文件
     */
    public UpdateDTO(String path, String oldName, String newName, Integer type, boolean subFolder, String fileType,
	    Integer dirOrFile) {
	super();
	this.path = path;
	this.oldName = oldName;
	this.newName = newName;
	this.type = type;
	this.subFolder = subFolder;
	this.fileType = fileType;
	this.dirOrFile = dirOrFile;
    }

    public UpdateDTO() {

    }
}
