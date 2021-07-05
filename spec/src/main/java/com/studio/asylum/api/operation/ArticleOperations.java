package com.studio.asylum.api.operation;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.studio.asylum.api.command.AddComment;
import com.studio.asylum.api.command.AddCommentResult;
import com.studio.asylum.api.command.CreateArticle;
import com.studio.asylum.api.command.CreateArticleResult;
import com.studio.asylum.api.command.FavoriteArticleResult;
import com.studio.asylum.api.command.UnfavoriteArticleResult;
import com.studio.asylum.api.command.UpdateArticle;
import com.studio.asylum.api.command.UpdateArticleResult;
import com.studio.asylum.api.query.GetArticleResult;
import com.studio.asylum.api.query.GetArticlesResult;
import com.studio.asylum.api.query.GetCommentsResult;
import com.studio.asylum.api.query.GetFeedResult;


public interface ArticleOperations {

    @GetMapping("/articles{?tag,author,favorited,limit,offset}")
    ResponseEntity<GetArticlesResult> findByFilters(@RequestParam @Nullable String tag,
                                    @RequestParam @Nullable String author,
                                    @RequestParam @Nullable String favorited,
                                    @RequestParam(defaultValue = "20") Integer limit,
                                    @RequestParam(defaultValue = "0") Integer offset);

    @PostMapping("/articles")
    ResponseEntity<CreateArticleResult> create(@Valid @RequestBody CreateArticle command);

    @GetMapping("/articles/feed")
    ResponseEntity<GetFeedResult> feed(@RequestParam(defaultValue = "20") Integer limit,
                       @RequestParam(defaultValue = "0") Integer offset);

    @GetMapping("/articles/{slug}")
    ResponseEntity<GetArticleResult> findBySlug(@PathVariable("slug") String slug);

    @PutMapping("/articles/{slug}")
    ResponseEntity<UpdateArticleResult> updateBySlug(@PathVariable("slug") String slug, @Valid @RequestBody UpdateArticle command);

    @DeleteMapping("/articles/{slug}")
    void deleteBySlug(@PathVariable("slug") String slug);

    @PostMapping("/articles/{slug}/favorite")
    ResponseEntity<FavoriteArticleResult> favorite(@PathVariable("slug") String slug);

    @DeleteMapping("/articles/{slug}/favorite")
    ResponseEntity<UnfavoriteArticleResult> unfavorite(@PathVariable("slug") String slug);

    @GetMapping("/articles/{slug}/comments")
    ResponseEntity<GetCommentsResult> findAllComments(@PathVariable("slug") String slug);

    @PostMapping("/articles/{slug}/comments")
    ResponseEntity<AddCommentResult> addComment(@PathVariable("slug") String slug, @Valid @RequestBody AddComment command);

    @DeleteMapping("/articles/{slug}/comments/{id}")
    void deleteComment(@PathVariable("slug") String slug, @PathVariable("id") Long id);

}
