package com.studio.asylum.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UnfavoriteArticle implements Command<UnfavoriteArticleResult> {

    private String currentUsername;
    private String slug;

}
