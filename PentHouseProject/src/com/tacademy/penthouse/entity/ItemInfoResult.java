package com.tacademy.penthouse.entity;

import java.util.ArrayList;

public class ItemInfoResult {
	public ItemData item = new ItemData();
	public ArrayList<ItemData> items = new ArrayList<ItemData>();

	public int success;
	public String result_msg;
}
