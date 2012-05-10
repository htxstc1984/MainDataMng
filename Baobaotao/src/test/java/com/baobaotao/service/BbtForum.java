package com.baobaotao.service;

import com.baobaotao.domain.Forum;
import com.baobaotao.domain.Topic;

public interface BbtForum {
	void addTopic(Topic topic);

	void updateForum(Forum forum);

	Forum getForum(int forumId);

	int getForumNum();
}
