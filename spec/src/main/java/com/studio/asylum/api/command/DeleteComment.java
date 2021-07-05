package com.studio.asylum.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteComment implements Command<DeleteCommentResult> {

    private String currentUsername;
    private String slug;
    private Long id;

}
