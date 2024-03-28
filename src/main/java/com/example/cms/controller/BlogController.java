package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestdto.BlogReq;
import com.example.cms.responsesdto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class BlogController {

	private BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@RequestBody BlogReq blogReq, @PathVariable int userId)
	{
		return blogService.createBlog(blogReq, userId);
	}

	@GetMapping("/titles/{title}/blogs")
	public boolean checkBlogTitleAvailability(@PathVariable String blogTitle)
	{
		return blogService.checkBlogTitleAvailability(blogTitle);
	}

	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(@PathVariable int blogId)
	{
		return	blogService.findBlogById(blogId);
	}

	

}
