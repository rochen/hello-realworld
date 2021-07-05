package com.studio.asylum.api.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetComments implements Query<GetCommentsResult> {

    private String currentUsername;
    private String slug;

}
