package com.studio.asylum.api.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter

@JsonRootName("article")
public class UpdateArticle implements Command<UpdateArticleResult> {

    @With
    @JsonIgnore
    private String currentUsername;

    @With
    @JsonIgnore
    private String slug;

    private String title;

    private String description;

    private String body;

}
