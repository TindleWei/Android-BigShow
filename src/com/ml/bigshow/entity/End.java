package com.ml.bigshow.entity;

import java.io.Serializable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "End")
public class End extends Model implements Serializable{
	
	private static final long serialVersionUID = -7148244611760293445L;

	public End(){
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
	
	@Column(name = "photo")
	public String photo;
	
	@Column(name = "content")
	public String content;
	
	@Column(name = "nextStory")
	public Story nextStory;
	
	

}
