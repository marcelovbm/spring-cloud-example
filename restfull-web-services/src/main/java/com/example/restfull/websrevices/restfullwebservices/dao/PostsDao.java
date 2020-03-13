package com.example.restfull.websrevices.restfullwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.restfull.websrevices.restfullwebservices.model.PostsModel;

@Component
public class PostsDao {

	private static List<PostsModel> posts = new ArrayList<>();
	private int postsCount = 3;

	static {
		posts.add(new PostsModel(1, "Primeiro post do projeto", new Date(), 1));
		posts.add(new PostsModel(2, "Segundo post do projeto", new Date(), 1));
		posts.add(new PostsModel(3, "Terceiro post do projeto", new Date(), 2));

	}

	public List<PostsModel> findAll(Integer userId) {
		return posts.stream().filter(post -> userId.equals(post.getUserId())).collect(Collectors.toList());
	}

	public PostsModel findPosts(Integer userId, Integer postsId) {
		return posts.stream().filter(post -> userId.equals(post.getUserId()) && postsId.equals(post.getId())).findAny()
				.orElse(null);
	}

	public PostsModel save(PostsModel postsModel) {

		if (postsModel.getId() == null)
			postsModel.setId(++postsCount);

		posts.add(postsModel);

		return postsModel;
	}

}
