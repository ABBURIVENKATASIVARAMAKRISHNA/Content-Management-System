package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Blog;
import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;
import com.example.cms.exception.BlogAlreadyExitByTitleException;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.InvalidPanelIdException;
import com.example.cms.exception.TopicsNotSpecifiedException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepo;
import com.example.cms.repository.UserRepostitory;
import com.example.cms.requestdto.BlogReq;
import com.example.cms.responsesdto.BlogResponse;
import com.example.cms.responsesdto.ContributionPanelResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

@Service
public class BlogServiceImpl implements BlogService{

	private ResponseStructure<BlogResponse> responseStructure;

	private BlogRepository blogRepo;

	private UserRepostitory userRepo;

	private ContributionPanelRepo contributionPanelRepo;

	private ResponseStructure<ContributionPanelResponse> responseStructures;


	public BlogServiceImpl(ResponseStructure<ContributionPanelResponse> responseStructures,ResponseStructure<BlogResponse> responseStructure,ContributionPanelRepo contributionPanelRepo,BlogRepository blogRepo,UserRepostitory userRepo) {
		this.responseStructure = responseStructure;
		this.blogRepo=blogRepo;
		this.userRepo =userRepo;
		this.contributionPanelRepo=contributionPanelRepo;
		this.responseStructures=responseStructures;
	} 


	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(BlogReq blog,int userId) {
		return userRepo.findById(userId).map(user->{

			if(blogRepo.existsByTitle(blog.getTitle())) 
				throw new BlogAlreadyExitByTitleException("Title already Exist");

			if(blog.getTopics().length<1)
				throw new TopicsNotSpecifiedException("Failed to create a Blog");


			Blog save = blogRepo.save(mappedtoBlog(blog, new Blog(),user));

			user.getList().add(save);
			userRepo.save(user);


			return ResponseEntity.ok(responseStructure.setMessage("Blog Created Success")
					.setStutusCode(HttpStatus.OK.value()).setData(mappedToBlogResponse(save, new BlogResponse())));

		}).orElseThrow(()->new UserNotFoundByIdException("User Id Not Found"));
	}




	private Blog mappedtoBlog(BlogReq blogReq, Blog blog,User user)
	{
		blog.setTitle(blogReq.getTitle());
		blog.setAbout(blogReq.getAbout());
		blog.setTopics(blogReq.getTopics());
		blog.setUser(user);
		ContributionPanel save = contributionPanelRepo.save(new ContributionPanel());
		blog.setContributionPanel(save);
		return blog;
	}
	private BlogResponse mappedToBlogResponse(Blog blog, BlogResponse blogRes)
	{
		blogRes.setBlogId(blog.getBlogId());
		blogRes.setTitle(blog.getTitle());
		blogRes.setTopics(blog.getTopics());
		blogRes.setAbout(blog.getAbout());
		return blogRes;
	}



	@Override
	public ResponseEntity<Boolean> checkBlogTitleAvailability(String blogTitle) {

		return new ResponseEntity<Boolean>(blogRepo.existsByTitle(blogTitle),HttpStatus.FOUND);
	}



	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId) {
		return blogRepo.findById(blogId).map(blog->{
			return ResponseEntity.ok(responseStructure.setMessage("Blog Found Success").setStutusCode(HttpStatus.OK.value())
					.setData(mappedToBlogResponse(blog, new BlogResponse())));
		})
				.orElseThrow(()->new BlogNotFoundByIdException("Invalid Blog Id"));
	}




	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogData(BlogReq blogReq, int blogId) {
		return blogRepo.findById(blogId).map(blog->{

			blog.setTitle(blogReq.getTitle());
			blog.setAbout(blogReq.getAbout());
			blog.setTopics(blogReq.getTopics());
			blog.setUser(blog.getUser());
			Blog save = blogRepo.save(blog);

			return ResponseEntity.ok(responseStructure.setMessage("Update Successfully").setStutusCode(HttpStatus.OK.value())
					.setData(mappedToBlogResponse(save, new BlogResponse())));
		})
				.orElseThrow(()->new BlogNotFoundByIdException("Invalid BlogId"));
	}




	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributors(int userId, int panelId) {

		String email=SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepo.findByEmail(email).map(owner->{

			return	contributionPanelRepo.findById(panelId).map(panel->{
				if(!blogRepo.existsByUserAndContributionPanel(owner,panel))
					throw new IllegalAccessRequestException("Failed to add Contributor");

				return	userRepo.findById(userId).map(contributor->{

					panel.getList().add(contributor);
					contributionPanelRepo.save(panel);

					return ResponseEntity.ok(responseStructures
							.setMessage("Successfully Added Contributer")
							.setStutusCode(HttpStatus.OK.value())
							.setData(new ContributionPanelResponse().setPanelId(panel.getPanelId())));

				}).orElseThrow(()->new UserNotFoundByIdException("User Not Found"));

			})
					.orElseThrow(()-> new InvalidPanelIdException("Contributor PanelId Not Found "));

		}).get();


	}




	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> removeUserFromContributorPanel(int userId,
			int panelId) {

		String email=SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepo.findByEmail(email).map(owner->{

			return	contributionPanelRepo.findById(panelId).map(panel->{
				if(!blogRepo.existsByUserAndContributionPanel(owner,panel))
					throw new IllegalAccessRequestException("Failed to add Contributor");

				return	userRepo.findById(userId).map(contributor->{

					panel.getList().remove(contributor);

					return ResponseEntity.ok(responseStructures
							.setMessage("Successfully Added Contributer")
							.setStutusCode(HttpStatus.OK.value())
							.setData(new ContributionPanelResponse().setPanelId(panel.getPanelId())));

				}).orElseThrow(()->new UserNotFoundByIdException("User Not Found"));

			})
					.orElseThrow(()-> new InvalidPanelIdException("Contributor PanelId Not Found "));

		}).get();

	}


}
