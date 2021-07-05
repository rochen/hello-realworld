package com.studio.asylum.api.query;

import java.util.List;

import com.studio.asylum.api.data.ArticleData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class GetArticlesResult {

    private List<ArticleData> articles;
    private Integer articlesCount;

}
