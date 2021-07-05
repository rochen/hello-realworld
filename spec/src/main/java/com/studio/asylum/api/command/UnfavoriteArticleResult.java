package com.studio.asylum.api.command;

import com.studio.asylum.api.data.ArticleData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Getter
public class UnfavoriteArticleResult {

    private ArticleData article;

}
