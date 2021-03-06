package com.todoist.mediaparser.mediaentity;

import com.todoist.mediaparser.util.HttpStack;

import java.io.IOException;
import java.util.regex.Pattern;

public class ScreenrEntity extends BaseOEmbedMediaEntity {
    private static Pattern sMatchingPattern;

    ScreenrEntity(String url) {
        super(url);
    }

    @Override
    protected void doConfigure(HttpStack httpStack) throws IOException {
        super.doConfigure(httpStack);

        if ("text/html".equals(mUnderlyingContentType)) {
            mUnderlyingContentType = "video/*";
        }
    }

    @Override
    protected Pattern getMatchingPattern() {
        if (sMatchingPattern == null) {
            sMatchingPattern = Pattern.compile("https?://(?:www\\.)?screenr\\.com/\\w+/?", Pattern.CASE_INSENSITIVE);
        }
        return sMatchingPattern;
    }

    @Override
    protected String getOEmbedUrlTemplate() {
        return "http://www.screenr.com/api/oembed.json?url=%s";
    }
}
