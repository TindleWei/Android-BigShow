package com.ml.bigshow.entity;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Slots")
public class Slot extends Model implements Serializable {

	private static final long serialVersionUID = 8486707988115480549L;

	public Slot() {
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
	
	@Column(name = "sid")
	public String sid; // story id

	@Column(name = "content")
	public String content;

	@Column(name = "photo")
	public String photo;

	@Column(name = "question")
	public String question;

	@Column(name = "fromStory")
	public Story fromStory;
	
	public List<End> ends(){
		return getMany(End.class, "fromSlot");
	}

}
