package com.studio.asylum.api.command;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter

@JsonRootName("article")
public class CreateArticle implements Command<CreateArticleResult> {

    @With
    @JsonIgnore
    private String currentUsername;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String body;

    private List<String> tagList;

}
