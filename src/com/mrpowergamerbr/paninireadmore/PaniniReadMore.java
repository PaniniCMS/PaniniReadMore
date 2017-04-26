package com.mrpowergamerbr.paninireadmore;

import com.paninicms.plugin.PaniniPlugin;
import com.paninicms.plugin.event.Listener;
import com.paninicms.plugin.event.SubscribeEvent;
import com.paninicms.plugin.event.blog.GetPostsEvent;
import com.paninicms.utils.blog.Post;

public class PaniniReadMore extends PaniniPlugin implements Listener {
	@Override
	public void onEnable() {
		System.out.println("PaniniReadMore by MrPowerGamerBR");
		this.registerListener(this);
	}
	
	@SubscribeEvent
	public void onGetPost(GetPostsEvent ev) { // When a post is loaded (getAllPosts(...))
		for (Post post : ev.getLoadedPosts()) { // We are going to do a foreach on every loaded post
			String htmlContent = post.content(); // Get the HTML content of it
			post.softMetadata().put("hasSummary", htmlContent.contains("<!--more-->")); // And store on our softMetadata HashMap... "does this post contains a read more tag?"
			// Templates can access the softMetadata map by using post.softMetadata.value
			// For PaniniReadMore, we should use post.softMetadata.hasSummary on our template
			if (htmlContent.contains("<!--more-->")) {
				post.softMetadata().put("summary", htmlContent.split("<!--more-->")[0]); // Now we are going to store EVERYTHING before our more tag to the "summary" variable
				// We can use post.softMetadata.summary on our template to get the post's summary. Yay! We are done here.
			} else {
				post.softMetadata().put("summary", htmlContent); // And we also store the entire htmlContent if there isn't an read more tag
			}
		}
	}
}