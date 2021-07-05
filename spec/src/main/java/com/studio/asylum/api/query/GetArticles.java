package com.studio.asylum.api.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetArticles implements Query<GetArticlesResult> {

    private String currentUsername;
    private String tag;
    private String author;
    private String favorited;
    private Integer limit;
    private Integer offset;

}
