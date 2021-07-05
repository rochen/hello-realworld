package com.studio.asylum.api.command;

import com.studio.asylum.api.data.CommentData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Getter
public class AddCommentResult {

    private CommentData comment;

}
