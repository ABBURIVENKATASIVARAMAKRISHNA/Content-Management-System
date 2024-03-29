package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestdto.BlogReq;
import com.example.cms.responsesdto.BlogResponse;
import com.example.cms.responsesdto.ContributionPanelResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogService {

	ResponseEntity<ResponseStructure<BlogResponse>> createBlog(BlogReq blog,int userId );
	
	ResponseEntity<Boolean> checkBlogTitleAvailability(String blogTitle);
	
	ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId);
	
	ResponseEntity<ResponseStructure<BlogResponse>> updateBlogData(BlogReq blogReq, int blogId);

	ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributors(int userId, int panelId);

	ResponseEntity<ResponseStructure<ContributionPanelResponse>> removeUserFromContributorPanel(int userId, int panelId);
	
}
