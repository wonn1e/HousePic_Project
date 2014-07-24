package com.tacademy.penthouse.entity;


import android.os.Parcel;
import android.os.Parcelable;


public class ItemData implements Parcelable {
	
	public int room_num ;
	
	public int item_code;
	public String sort_code;
	public String item_name;
	public String brand;
	public String price;
	public String material;
	public String[]theme;
	public int likeCnt;
	public String sort_category;
	public int []item_img;
	public String link;
	
	//String item_url;
	public ItemData(){}
	public ItemData(int room_num,int item_code,           
	String sort_code ,     
	String item_name ,     
	String brand ,         
	String price,          
	String material,       
	String[]theme,  
	int likeCnt ,             
	String sort_category,int []item_img ,String link ){
		this.room_num = room_num;
		this.item_code = item_code ;           
		this.sort_code = sort_code;     
		this.item_name = item_name;     
		this.brand = brand;         
		this.price = price;          
		this.material = material;       
		this.theme = theme;  
		this.likeCnt = likeCnt;             
		this.sort_category = sort_category; 
		this.item_img = item_img ; 
		this.link = link;
	}
	public ItemData(Parcel p){
		room_num = p.readInt();
		item_code = p.readInt();
		sort_code = p.readString();
		item_name = p.readString();
		brand = p.readString();
		price = p.readString();
		material = p.readString();
		int length = p.readInt();
		theme = new String[length];
		p.readStringArray(theme);
		likeCnt = p.readInt();
		sort_category = p.readString();
		int item_cnt = p.readInt();
		item_img = new int[item_cnt];
		p.readIntArray(item_img);
		link = p.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(room_num);
		dest.writeInt(item_code);
		dest.writeString(sort_code);
		dest.writeString(item_name);
		dest.writeString(brand);
		dest.writeString(price);
		dest.writeString(material);
		dest.writeInt(theme.length);
		dest.writeStringArray(theme);
		dest.writeInt(likeCnt);
		dest.writeString(sort_category);
		dest.writeInt(item_img.length);
		dest.writeIntArray(item_img);
		dest.writeString(link);
	}
	public static Parcelable.Creator<ItemData> CREATOR = new Parcelable.Creator<ItemData>() {
		@Override
		public ItemData[] newArray(int size) {
			return new ItemData[size];
		}

		@Override
		public ItemData createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new ItemData(source);
		}
	};
}
