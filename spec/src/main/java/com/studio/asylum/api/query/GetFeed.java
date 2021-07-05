package com.studio.asylum.api.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetFeed implements Query<GetFeedResult> {

    private String currentUsername;
    private Integer limit;
    private Integer offset;

}
