package com.zsh.labouCapital.controller.request;

public class SourceRequest extends BaseForm{
	private String itemName;
	private String itemUrl;
	private int type;
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemUrl() {
        return itemUrl;
    }
    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}

