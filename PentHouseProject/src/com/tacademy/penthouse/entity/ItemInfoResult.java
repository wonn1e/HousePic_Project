package com.tacademy.penthouse.entity;

import java.util.ArrayList;

public class ItemInfoResult {
	public ItemData item = new ItemData();
	public ArrayList<ItemData> similarItems = new ArrayList<ItemData>();

	public String result;
	public String result_msg;
}
