package com.studio.asylum.api.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetArticle implements Query<GetArticleResult> {

    private String currentUsername;
    private String slug;

}
