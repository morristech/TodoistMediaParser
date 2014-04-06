package com.todoist.mediaparser.mediaentity;

import java.io.IOException;
import java.util.regex.Pattern;

public class TedEntity extends BaseOEmbedMediaEntity {
	private static Pattern sMatchingPattern;

	TedEntity(String url) {
		super(url);
	}

	@Override
	protected void doConfigure() throws IOException {
		super.doConfigure();

		if("text/html".equals(mUnderlyingContentType))
			mUnderlyingContentType = "video/*";
	}

	@Override
	protected Pattern getMatchingPattern() {
		if(sMatchingPattern == null) {
			sMatchingPattern = Pattern.compile(
					"https?://(?:www\\.)?ted\\.com/talks/[\\w]+",
					Pattern.CASE_INSENSITIVE
			);
		}
		return sMatchingPattern;
	}

	@Override
	protected String getOEmbedUrlTemplate() {
		return "http://www.ted.com/talks/oembed.json?url=%s";
	}
}
