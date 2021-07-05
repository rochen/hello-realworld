package com.studio.asylum.api.command;

import com.studio.asylum.api.data.UserData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateUserResult {

	private UserData user;
}
