package com.example.restfull.websrevices.restfullwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restfull.websrevices.restfullwebservices.dao.PostsDao;
import com.example.restfull.websrevices.restfullwebservices.exception.PostsNotFoundException;
import com.example.restfull.websrevices.restfullwebservices.model.PostsModel;

@RestController
@RequestMapping(path = "/users/{userId}/posts", produces = "application/json")
public class PostsController {

	@Autowired
	private PostsDao postsDao = new PostsDao();

	@GetMapping()
	public List<PostsModel> returnAllPosts(@PathVariable Integer userId) throws PostsNotFoundException {
		List<PostsModel> postsList = postsDao.findAll(userId);

		if (postsList != null && postsList.isEmpty())
			throw new PostsNotFoundException("Posts not found for user " + userId);

		return postsList;
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<Object> save(@RequestBody PostsModel postsModel) {
		PostsModel postsCreated = postsDao.save(postsModel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(postsCreated.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping(path = "/{postsId}")
	public PostsModel returnPostsDetail(@PathVariable Integer userId, @PathVariable Integer postsId)
			throws PostsNotFoundException {

		PostsModel postsModel = postsDao.findPosts(userId, postsId);

		if (postsModel == null) {
			throw new PostsNotFoundException("Posts " + postsId + " not found for user " + userId);
		}

		return postsModel;

	}

}
