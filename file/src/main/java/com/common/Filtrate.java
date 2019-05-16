package com.common;

/**   
 * @ClassName:  Filtrate   
 * @Description:TODO  文件夹（文件）筛选
 * @author: xzr 
 * @date:   2019年5月16日 
 *     
 */
public class Filtrate {
    /** 
      * @Title: useArraysBinarySearch 
      * @Description: TODO 筛选文件夹/文件
      * @param arr 需要过滤的文件夹/文件
      * @param targetValue 文件夹/文件
      * @return: boolean (是否过滤) 返回值类型   
    */
    public static boolean useArraysBinarySearch(String[] arr, String targetValue) {
	if (arr == null || arr.length < 1)
	    return false;
	for (String string : arr) {
	    if (string.equalsIgnoreCase(targetValue))
		return true;
	}
	return false;
    }
}
