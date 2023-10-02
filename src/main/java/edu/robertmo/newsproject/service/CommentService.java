package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.CommentRequestDto;
import edu.robertmo.newsproject.dto.CommentResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(long articleId, CommentRequestDto dto, Authentication authentication);
    List<CommentResponseDto> findCommentsByArticleId(long articleId);
    CommentResponseDto updateComment(long commentId, CommentRequestDto dto, Authentication authentication);

    CommentResponseDto deleteCommentById(long commentId, Authentication authentication);
}
