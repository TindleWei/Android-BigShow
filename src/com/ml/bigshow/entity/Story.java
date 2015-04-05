package com.ml.bigshow.entity;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Storys")
public class Story extends Model implements Serializable{

	private static final long serialVersionUID = 211861241939739402L;

	public Story(){
		super();
	}
	
	// id是默认的, 不用设

	// { AVOS特有的

	@Column(name = "objectId")
	public String objectId;

	@Column(name = "createdAt")
	private long createdAt;

	@Column(name = "updatedAt")
	private long updatedAt;

	// } AVOS特有的
	
	@Column(name = "title")
	public String title;
	
	@Column(name = "cName")
	public String cName; //character name
	
	@Column(name = "cAvatar")
	public String cAvatar; //character avatar
	
	@Column(name = "uid")
	public String uid; // user id
	
	@Column(name = "uName")
	public String uName; // user name
	
	@Column(name = "uAvatar")
	public String uAvatar; // user avatar;
	
	@Column(name = "status") 
	public String status; //-1未完成 0已经完成，未上传  1已上传
	
	@Column(name = "hotScore")
	public String hotScore;
	
	public List<Slot> slots(){
		return getMany(Slot.class, "fromStory");
	}

	
}
