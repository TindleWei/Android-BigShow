package com.ml.bigshow.entity;

import com.avos.avoscloud.AVAnonymousUtils;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

//@AVClassName("_User")
public class User extends AVUser {

	public static User curUser = null;

	public User() {
	}

	public static User getUser() {

		if (curUser == null) {
			AVUser avUser = getCurrentUser(User.class);
			curUser = User.cast(avUser, User.class);
		}

		if (curUser != null && curUser.isAnonymous()) {
			//是匿名的
			return curUser;
		}

		if (curUser == null) {
			AVAnonymousUtils.logIn(new LogInCallback<AVUser>() {
				@Override
				public void done(AVUser user, AVException e) {
				}
			});
		}

		return curUser;
	}

}
