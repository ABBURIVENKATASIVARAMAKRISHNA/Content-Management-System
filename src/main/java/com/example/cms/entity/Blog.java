package com.example.cms.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Blog {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogId;
	
	private String title;
	
	private String[] topics;
	
	private String about;
	
	
	@OneToOne
	private ContributionPanel contributionPanel;

	@ManyToOne
	private User user;
	
	
	

	public ContributionPanel getContributionPanel() {
		return contributionPanel;
	}

	public void setContributionPanel(ContributionPanel contributionPanel) {
		this.contributionPanel = contributionPanel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public int getBlogId() {
		return blogId; 
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getTopics() {
		return topics;
	}

	public void setTopics(String[] topics) {
		this.topics = topics;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	
}
