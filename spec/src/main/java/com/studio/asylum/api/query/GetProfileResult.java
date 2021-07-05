package com.studio.asylum.api.query;

import com.studio.asylum.api.data.ProfileData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetProfileResult {

	private ProfileData profile;
}
