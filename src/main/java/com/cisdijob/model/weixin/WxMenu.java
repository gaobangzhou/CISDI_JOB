package com.cisdijob.model.weixin;


import java.util.List;


public class WxMenu {
	private List<WxButton> ButtonList;
	private String menuStr;

	
	public List<WxButton> getButtonList() {
		return ButtonList;
	}

	public void setButtonList(List<WxButton> buttonList) {
		ButtonList = buttonList;
	}
	


	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}
	
	public String toString(){
		String menuStr ="";
		if(ButtonList!=null&ButtonList.size()>0){
			for(WxButton wbt : ButtonList){
				menuStr+=","+wbt.toString();
			}		
		}
		if(menuStr!=""){
			menuStr="{ \"button\":["+menuStr.substring(1)+"]}";
		}
		return menuStr;
	}
}
