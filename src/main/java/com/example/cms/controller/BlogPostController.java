package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestdto.BlogPostRequest;
import com.example.cms.responsesdto.BlogPostResponse;
import com.example.cms.service.BlogPostService;
import com.example.cms.utility.ResponseStructure;


@RestController
public class BlogPostController {
	
	private BlogPostService bs;
	
	

	public BlogPostController(BlogPostService bs) {
		super();
		this.bs = bs;
	}

	@PostMapping("/blogs/{blogId}/blog-posts")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(@RequestBody BlogPostRequest blogPostReq,@PathVariable int blogId){
		return bs.createDraft(blogPostReq,blogId); 
	} 
	
	@PutMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(@PathVariable int postId)
	{
		return bs.updateDraft(postId);
	}
	
}
