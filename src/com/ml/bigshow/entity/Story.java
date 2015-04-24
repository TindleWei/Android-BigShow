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
	
	@Column(name = "storyTitle")
	public String storyTitle;
	
	@Column(name = "storyName")
	public String storyName; //character name
	
	@Column(name = "storyAvatar")
	public String storyAvatar; //character avatar
	
	@Column(name = "userId")
	public String userId; // user id
	
	@Column(name = "userName")
	public String userName; // user name
	
	@Column(name = "userAvatar")
	public String userAvatar; // user avatar;
	
	@Column(name = "isCompleted") 
	public String isCompleted; //-1未完成 0已经完成，未上传  1已上传
	
	@Column(name = "hotScore")
	public int hotScore;
	
	public List<Slot> slots(){
		return getMany(Slot.class, "fromStory");
	}

	
}
