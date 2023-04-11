package model;
/*
 * Bean 클래스 (java 에서 bean은 객체라고 생각하면 됨.)
 * property
 *     get property : getId() -> id = get property
 *                    getXxx() => xxx (변수명이 아니라 메서드에서 get 뒤에 있는 거+소문자)
 *     set property : setId(Object) -> id = set property 
 *                    setAbc() => abc
 * */
public class Member {
     private String id; //private => getter,setter를 이용해서 값을 넣어야함
     private String pass;
     private String name;
     private int gender;
     private String tel;
     private String email;
     private String picture;
     //getter, setter, toString
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", pass=" + pass + ", name=" + name + ", gender=" + gender + ", tel=" + tel
				+ ", email=" + email + ", picture=" + picture + "]";
	}
	
}
