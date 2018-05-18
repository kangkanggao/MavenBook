package com.highlion.domain;

import java.io.Serializable;

public class TypeVO implements Serializable{
private int id;
private String name;
public TypeVO() {

}
public int getid() {
	return id;
}
public void setTid(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
