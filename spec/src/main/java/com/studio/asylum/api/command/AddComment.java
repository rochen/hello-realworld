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

@JsonRootName("comment")
public class AddComment implements Command<AddCommentResult> {

    @With
    @JsonIgnore
    private String slug;

    @With
    @JsonIgnore
    private String currentUsername;

    private String body;

}
