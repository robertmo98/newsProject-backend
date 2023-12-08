package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.request.CommentRequestDto;
import edu.robertmo.newsproject.dto.response.ArticleResponseDto;
import edu.robertmo.newsproject.dto.response.CommentResponseDto;
import edu.robertmo.newsproject.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@Tag(name = "Comments controller", description = "All the comments")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "Create a comment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CommentResponseDto.class)
                            ))),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "Unauthorized"
            )
    })
    @PostMapping("/articles/{id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable(name = "id")
            @Parameter(description = "Id of the article to relate the comment") long articleId,
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


    @Operation(summary = "Get all the comments of an article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CommentResponseDto.class)
                            ))),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "Unauthorized"
            )
    })
    @GetMapping("/articles/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findCommentsByArticleId(
            @PathVariable("id") @Parameter(description = "Id of the article") long articleId,
            Authentication authentication
    ) {
        return ResponseEntity.ok(commentService.findCommentsByArticleId(articleId, authentication));
    }


    @Operation(summary = "Update a comment by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CommentResponseDto.class)
                            ))),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "Unauthorized"
            )
    })
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateCommentById(
            @PathVariable("id") @Parameter(description = "Id of the comment to be updated") long commentId,
            @RequestBody CommentRequestDto dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(commentService.updateComment(commentId, dto, authentication));
    }


    @Operation(summary = "Delete a comment by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CommentResponseDto.class)
                            ))),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "Unauthorized"
            )
    })
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> deleteCommentById(
            @PathVariable("id") @Parameter(description = "Id of the comment to be deleted") long id,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                commentService.deleteCommentById(id, authentication)
        );
    }


}