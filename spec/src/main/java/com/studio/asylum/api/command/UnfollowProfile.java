package com.studio.asylum.api.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnfollowProfile implements Command<UnfollowProfileResult> {

    private String username;
    private String fansname;

}
