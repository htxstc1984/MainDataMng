package com.baobaotao.service;

import com.baobaotao.dao.ForumDao;
import com.baobaotao.dao.PostDao;
import com.baobaotao.dao.TopicDao;
import com.baobaotao.domain.Forum;
import com.baobaotao.domain.Topic;

public class BbtForumImpl implements BbtForum {
	
	private ForumDao forumDao;
	private TopicDao topicDao;
	private PostDao postDao;
	
	public BbtForumImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTopic(Topic topic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForum(Forum forum) {
		// TODO Auto-generated method stub

	}

	@Override
	public Forum getForum(int forumId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getForumNum() {
		// TODO Auto-generated method stub
		return 0;
	}

}
