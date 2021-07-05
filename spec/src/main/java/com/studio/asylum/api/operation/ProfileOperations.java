package com.studio.asylum.api.operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.studio.asylum.api.command.FollowProfileResult;
import com.studio.asylum.api.command.UnfollowProfileResult;
import com.studio.asylum.api.query.GetProfileResult;


public interface ProfileOperations {

    @GetMapping("/profiles/{username}")
    ResponseEntity<GetProfileResult> findByUsername(@PathVariable("username") String username);

    @PostMapping("/profiles/{username}/follow")
    ResponseEntity<FollowProfileResult> follow(@PathVariable("username") String username);

    @DeleteMapping("/profiles/{username}/follow")
    ResponseEntity<UnfollowProfileResult> unfollow(@PathVariable("username") String username);

}
