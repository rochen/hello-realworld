package com.studio.asylum.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FavoriteArticle implements Command<FavoriteArticleResult> {

    private String currentUsername;
    private String slug;

}
