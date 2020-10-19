package com.bjb.project.util;

import com.github.pagehelper.Page;

import java.util.HashMap;

/**
 * 分页共通
 * @author shikai
 *
 */
public class PageUtils {
	
	/**
	 * 类型转换：将Page转换成Map
	 * @param obj
	 * @return HashMap<String, Object> 或 NULL
	 */
	public static HashMap<String, Object> pageToMap(Object obj) {
		return pageToMap((Page<?>) obj);
	}
	
	/**
	 * 类型转换：将Page转换成Map
	 * @param page
	 * @return HashMap<String, Object> 或 NULL
	 */
	public static HashMap<String, Object> pageToMap(Page<?> page) {
		HashMap<String, Object> map = null;
		if (page != null) {
			map = new HashMap<String, Object>();
			HashMap<String,Object> pageMap = new HashMap<String,Object>();
			pageMap.put("totalPage", page.getPages()==0?1:page.getPages());
			pageMap.put("curPage", page.getPageNum()==0?1:page.getPageNum());
			pageMap.put("pageSize", page.getPageSize());
			pageMap.put("totalRow", page.getTotal());
			map.put("page", pageMap);
			map.put("list", page);
		}
		return map;
	}
	
}
