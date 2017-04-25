package com.mrpowergamerbr.paninireadmore;

import com.paninicms.plugin.PaniniPlugin;
import com.paninicms.plugin.event.GetPostsEvent;
import com.paninicms.utils.blog.Post;

public class PaniniReadMore extends PaniniPlugin {
	@Override
	public void onEnable() {
		System.out.println("PaniniReadMore by MrPowerGamerBR");
	}
	
	@Override
	public void onGetPost(GetPostsEvent ev) {
		for (Post post : ev.getLoadedPosts()) {
			String htmlContent = post.content();
			post.softMetadata().put("hasSummary", htmlContent.contains("<!--more-->"));
			if (htmlContent.contains("<!--more-->")) {
				post.softMetadata().put("summary", htmlContent.split("<!--more-->")[0]);
			} else {
				post.softMetadata().put("summary", htmlContent);
			}
		}
	}
}