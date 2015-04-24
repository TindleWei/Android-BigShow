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
	
	@Column(name = "pageOrder")
	public int pageOrder; //从0开始，可以用来当List下标

	@Column(name = "slotContent")
	public String slotContent;

	@Column(name = "slotPhoto")
	public String slotPhoto;

	@Column(name = "slotQuestion")
	public String question;
	
	@Column(name = "isCompleted")
	public String isCompleted;

	@Column(name = "fromStory")
	public Story fromStory;
	
	public List<End> ends(){
		return getMany(End.class, "fromSlot");
	}

}
