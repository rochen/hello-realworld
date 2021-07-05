package com.studio.asylum.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteArticle implements Command<DeleteArticleResult> {

    private String currentUsername;
    private String slug;

}
