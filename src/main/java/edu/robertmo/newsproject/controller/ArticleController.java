package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.request.ArticleUpdateRequestDto;
import edu.robertmo.newsproject.dto.response.ArticlePageResponseDto;
import edu.robertmo.newsproject.dto.request.ArticleRequestDto;
import edu.robertmo.newsproject.dto.response.ArticleResponseDto;
import edu.robertmo.newsproject.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@Tag(name = "Articles controller", description = "All the articles")
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class ArticleController {
    private final ArticleService articleService;


    @Operation(summary = "Create an article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ArticleResponseDto.class)
                            ))),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "Unauthorized"
            )
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> createArticle(
            @RequestBody @Valid ArticleRequestDto dto,
            UriComponentsBuilder uriBuilder,
            Authentication authentication) {

        var saved = articleService.createArticle(dto, authentication);
        var uri = uriBuilder.path("/api/v1/articles/{id}").buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }


    @Operation(summary = "Get all the articles")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ArticleResponseDto.class)
                            ))),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "Unauthorized"
            )
    })
    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles(Authentication authentication) {
        return ResponseEntity.ok(articleService.getAllArticles(authentication));
    }




    @Operation(summary = "Get an article by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the article",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticleResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(
            @NotNull @PathVariable @Valid @Parameter(description = "Id of the article to be searched") long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @Operation(summary = "Update an article by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article has been updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticleResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content)})
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> updateArticleById(
            @NotNull @Valid @PathVariable @Parameter(description = "Id of the article to be updated") long id,
            @Valid @RequestBody ArticleUpdateRequestDto dto) {
        return ResponseEntity.ok(articleService.updateArticleById(dto, id));
    }

    @Operation(summary = "Delete an article by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article has been deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticleResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> deleteArticleById(
            @NotNull @Valid @PathVariable @Parameter(description = "Id of the article to be deleted") long id) {
        return ResponseEntity.ok(articleService.deleteArticleById(id));
    }

    @Operation(summary = "Get articles by page, choosing the size of each page, and results sorting options")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticleResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content)})
    @GetMapping("/page")
    public ResponseEntity<ArticlePageResponseDto> getArticlesPage(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0")
            @Parameter(description = "The number of the page") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10")
            @Parameter(description = "Number of results per page") int pageSize,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc")
            @Parameter(description = "Sort direction- Asc/Desc") String sortDir,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id")
            @Parameter(description = "Sort alphabetically by article's attribute") String sortBy,
            Authentication authentication
    ) {
        return ResponseEntity.ok(articleService.getAllArticles(pageNo, pageSize, sortDir, sortBy, authentication));
    }


}
