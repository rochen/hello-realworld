package com.studio.asylum.api.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetComment implements Query<GetCommentResult> {

    private String currentUsername;
    private String slug;
    private Long id;

}
