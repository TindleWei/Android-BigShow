package com.ml.bigshow.entity;

import java.io.Serializable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Ends")
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
	
	@Column(name = "endContent")
	public String endContent;
	
	@Column(name = "endPhoto")
	public String endPhoto;
	
	@Column(name = "choosenContent")
	public String choosenContent;
	
	@Column(name = "formSlot")
	public Story fromSlot;
	
	@Column(name = "isCompleted")
	public String isCompleted;
	
	//-1表示Story结束， 0表示Slot结束， 其他的值就是下一个Slot的objectId
	@Column(name = "endNext")
	public String endNext; 
	
	

}
