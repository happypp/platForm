package com.platform.util;


import com.platform.constant.Constant;

import java.util.ArrayList;
import java.util.List;


public class Page {
	private int pageCount;  //页数
	private int pageItemCount; //每页的条目数
	private int currentIndex;  //当前索引
	private List<Integer> navItems; //页码
	private Object object;
	
	public Page(int pageIndex, int count) {  //当前页、数据总条数
		this.currentIndex = pageIndex;
		this.pageCount = (int) Math.ceil((double)count / Constant.LIST_ITEM_NUM);  //页数
		this.pageItemCount = Constant.LIST_ITEM_NUM; //每页的条目数
		this.navItems = new ArrayList<Integer>();
		initNavItems();
	}
	
	public void initNavItems() {  //算出页面显示的页面 最多10条
		int length = Constant.LIST_PAGES;  //以10页为基准
		if(pageCount <= length) {
			for (int i = 1; i <= pageCount; i++) {  // 1.2.3.4.5.6.7.8.9.10
				navItems.add(i);
			}
			return;
		}
		if(currentIndex - length/2 <= 0) {   //前5页
			for (int i = 1; i <= length; i++) {
				navItems.add(i);
			}
			return;
		}
		if(currentIndex + length/2 > pageCount) {  //若当前页为7，页码2.3.4.5.6.7.8.9.10.11
			for (int i = pageCount-length+1; i <= pageCount; i++) {
				navItems.add(i);
			}
			return;
		}
		for (int i = currentIndex-length/2; i <= currentIndex+length/2; i++) {
			navItems.add(i);
		}
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getPageItemCount() {
		return pageItemCount;
	}
	
	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	public List<Integer> getNavItems() {
		return navItems;
	}
	
	public void setNavItems(List<Integer> navItems) {
		this.navItems = navItems;
	}
	
	public Object getObject() {
		return object;
	}
	
	public void setObj(Object obj) {
		this.object = obj;
	}
	
}
