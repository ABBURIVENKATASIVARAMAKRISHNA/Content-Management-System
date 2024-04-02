package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestdto.BlogPostRequest;
import com.example.cms.responsesdto.BlogPostResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogPostService {

	ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(BlogPostRequest blogPostReq, int blogId);

	ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(int postId, BlogPostRequest blogReq);

	
}
