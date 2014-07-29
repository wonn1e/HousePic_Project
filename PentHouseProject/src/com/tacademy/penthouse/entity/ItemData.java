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
	public int sort_category;
	//public int []item_img;
	public String link;
	public boolean item_like;
	public String[] item_img_url;
	
	public String result;
	public String result_msg;
	
	public ItemData(){}
	public ItemData(int room_num,int item_code,           
	String sort_code ,     
	String item_name ,     
	String brand ,         
	String price,          
	String material,       
	String[]theme,  
	int likeCnt ,             
	int sort_category,String []item_img_url ,String link , boolean item_like){
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
		this.item_img_url = item_img_url ; 
		this.link = link;
		this.item_like = item_like;
	
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
		sort_category = p.readInt();
		int item_cnt = p.readInt();
		item_img_url = new String[item_cnt];
		p.readStringArray(item_img_url);
		link = p.readString();
		item_like = p.readByte() != 0;
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
		dest.writeInt(sort_category);
		dest.writeInt(item_img_url.length);
		dest.writeStringArray(item_img_url);
		dest.writeString(link);
		dest.writeByte((byte)(item_like ? 1:0));
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
