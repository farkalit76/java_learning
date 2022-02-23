/**
 * 
 */
package com.farkalit.test.models;

/**
 * @author farkalitusman
 *
 */
public class UserInfo {

	protected Long userId;

	protected String channel;

	protected String username;

	protected String password;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", channel=" + channel + ", username=" + username + ", password="
				+ password + "]";
	}

}
