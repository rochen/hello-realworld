package com.studio.asylum.api.operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.studio.asylum.api.query.GetTagsResult;


public interface TagOperations {

    @GetMapping("/tags")
    ResponseEntity<GetTagsResult> findAll();

}
