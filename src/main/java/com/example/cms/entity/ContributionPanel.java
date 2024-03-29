package com.example.cms.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class ContributionPanel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int panelId;
	
	
	@ManyToMany
	private List<User>list=new ArrayList<User>();


	public int getPanelId() {
		return panelId;
	}


	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}


	public List<User> getList() {
		return list;
	}


	public void setList(List<User> list) {
		this.list = list;
	}
	
	
}
