package com.ml.bigshow.entity;

import java.io.Serializable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Slot")
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

	@Column(name = "endIdA")
	public String endIdA;

	@Column(name = "endIdB")
	public String endIdB;

	@Column(name = "endIdC")
	public String endIdC;

	@Column(name = "endIdD")
	public String endIdD;

	@Column(name = "fromStory")
	public Story fromStory;

}
