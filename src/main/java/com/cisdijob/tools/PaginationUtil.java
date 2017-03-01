package com.cisdijob.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationUtil {
	
	/**
	 * 
	 * @param pageNum
	 *            当前页面号
	 * @param number
	 *            每页显示的表格行数
	 * @param total
	 *            总数
	 * @return
	 */
	public static Map<String, Object> getPaginationMap(int pageNum, int number,
			int total) {
		Map<String, Object> pagination = new HashMap<String, Object>();

		int pagesNum = total / number;
		int numberRest = total % number;
		if (numberRest > 0) {
			pagesNum++;
		}
		pagination.put("total", total);
		pagination.put("pages", pagesNum);
		pagination.put("pageNum", pageNum);
		pagination.put("number", number);
		if (pageNum > 1) {
			int prePage = pageNum - 1;
			pagination.put("preNum", prePage);
		}
		if (pageNum < pagesNum) {
			int nextPage = pageNum + 1;
			pagination.put("nextNum", nextPage);
		}

		List<Integer> list = new ArrayList<Integer>();
		int indexNum = pageNum / 4;
		int indexRest = pageNum % 4;
		if (indexRest > 0) {
			indexNum++;
		}
		int startIndex = 0;
		if (indexNum == 1) {
			startIndex = 1;
		} else {
			startIndex = (indexNum - 1) * 4 + 1;
		}
		int endIndex = indexNum * 4;
		for (int j = 1; j <= pagesNum; j++) {
			if (j <= endIndex && j >= startIndex) {
				list.add(j);
			}
		}
		pagination.put("indexList", list);
		return pagination;
	}
}
