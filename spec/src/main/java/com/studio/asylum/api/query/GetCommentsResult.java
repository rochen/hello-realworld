package com.studio.asylum.api.query;

import java.util.List;

import com.studio.asylum.api.data.CommentData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class GetCommentsResult {

    private List<CommentData> comments;

}
