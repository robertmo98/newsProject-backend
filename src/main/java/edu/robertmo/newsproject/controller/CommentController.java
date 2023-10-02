package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.CommentRequestDto;
import edu.robertmo.newsproject.dto.CommentResponseDto;
import edu.robertmo.newsproject.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/articles/{id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable(name = "id") long articleId,
            @RequestBody CommentRequestDto dto,
            UriComponentsBuilder uriBuilder,
            Authentication authentication
            ) {
        var saved = commentService.createComment(articleId, dto, authentication);
        var uri =
                uriBuilder
                        .path("/api/v1/articles/{article_id}/{comment_id}")
                        .buildAndExpand(articleId, saved.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(saved);
    }

}
