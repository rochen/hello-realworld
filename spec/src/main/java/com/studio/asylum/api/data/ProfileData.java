package com.studio.asylum.api.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileData {
    private String username;
    private String bio;
    private String image;
    private boolean following;
}
