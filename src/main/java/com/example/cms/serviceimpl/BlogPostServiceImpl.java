package com.example.cms.serviceimpl;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Blog;
import com.example.cms.entity.BlogPost;
import com.example.cms.enums.PostType;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.BlogPostNotFoundByIdException;
import com.example.cms.exception.BlogPostTitleAlreadyExistException;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.repository.BlogPostRepo;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepo;
import com.example.cms.repository.UserRepostitory;
import com.example.cms.requestdto.BlogPostRequest;
import com.example.cms.responsesdto.BlogPostResponse;
import com.example.cms.service.BlogPostService;
import com.example.cms.utility.ResponseStructure;
@Service
public class BlogPostServiceImpl implements BlogPostService {
	
	private BlogRepository blogRepo;
	private BlogPostRepo blogPostRepo;
	private ResponseStructure<BlogPostResponse> responseStructure;
	private ContributionPanelRepo contributionPanelRepo;
	private UserRepostitory userRepo;
	
	public BlogPostServiceImpl(UserRepostitory userRepo,BlogRepository blogRepo, BlogPostRepo blogPostRepo,
			ResponseStructure<BlogPostResponse> responseStructure,ContributionPanelRepo contributionPanelRepo) {
		this.blogRepo = blogRepo;
		this.blogPostRepo = blogPostRepo;
		this.responseStructure = responseStructure;
		this.contributionPanelRepo=contributionPanelRepo;
		this.userRepo=userRepo;
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(BlogPostRequest blogPostReq, int blogId) {
		return blogRepo.findById(blogId).map(blog->{
			if(blogPostRepo.existsByTitle(blogPostReq.getTitle()))
				throw new BlogPostTitleAlreadyExistException("Invalid Title");
			if(!checkAuthorize(blog))
				throw new IllegalAccessRequestException("UnAuthrozied User");
				
			BlogPost save = blogPostRepo.save(mapToBlogPost(new BlogPost(), blogPostReq,blog));
			blog.getList().add(save);
			blogRepo.save(blog);
			
			return ResponseEntity.ok(responseStructure
					.setMessage("BlogPost  Created Success")
					.setStutusCode(HttpStatus.OK.value()).setData(mapToBlogPostResponse(new BlogPostResponse(), save)));
				
		})
				.orElseThrow(()->new BlogNotFoundByIdException("Invalid Blog Id"));
	}
	
	private BlogPost mapToBlogPost(BlogPost blogPost, BlogPostRequest blogReq,Blog blog)
	{
		blogPost.setTitle(blogReq.getTitle());
		blogPost.setSubTitle(blogReq.getSubTitle());
		blogPost.setSummary(blogReq.getSummary());
		blogPost.setBlog(blog);
		blogPost.setPostType(PostType.DRAFT);
		
		
		return blogPost;
	}
	
	private BlogPostResponse mapToBlogPostResponse(BlogPostResponse blogRes, BlogPost blog)
	{
		blogRes.setBlogPostId(blog.getPostId());
		blogRes.setTitle(blog.getTitle());
		blogRes.setSummary(blog.getSummary());
		blogRes.setSubTitle(blog.getSubTitle());
		blogRes.setCreatedAt(blog.getCreatedAt());
		blogRes.setCreatedBy(blog.getCreatedBy());
		blogRes.setLastModifiedAt(blog.getLastModifiedAt());
		blogRes.setLastModifiedBy(blog.getLastModifiedBy());
		blogRes.setPostType(blog.getPostType());
		
		return blogRes;
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(int postId) {
		return blogPostRepo.findById(postId).map(blogPost->{
			blogPost.setPostType(PostType.PUBLISHED);
			
			return ResponseEntity.ok(responseStructure.setMessage("BlogPost Draft Updated Successfully")
					.setStutusCode(HttpStatus.OK.value())
					
					.setData(mapToBlogPostResponse(new BlogPostResponse(), blogPostRepo.save(blogPost))));
		})
				.orElseThrow(()->new BlogPostNotFoundByIdException(" Invalid  BlogPostId"));
	} 
	
	private boolean checkAuthorize(Blog blog)
	{
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		
	return	userRepo.findByEmail(email).map(user->{
			
		if(blog.getUser().getEmail().equals(email) || contributionPanelRepo.existsByPanelIdAndList(blog.getContributionPanel().getPanelId(),user));	
			return true;
		}).get();
		}
	
		
	}


