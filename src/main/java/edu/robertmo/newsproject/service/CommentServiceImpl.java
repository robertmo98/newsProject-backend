package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.CommentRequestDto;
import edu.robertmo.newsproject.dto.CommentResponseDto;
import edu.robertmo.newsproject.entity.Comment;
import edu.robertmo.newsproject.error.ResourceNotFoundException;
import edu.robertmo.newsproject.repository.ArticleRepository;
import edu.robertmo.newsproject.repository.CommentRepository;
import edu.robertmo.newsproject.repository.RoleRepository;
import edu.robertmo.newsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Override
    public CommentResponseDto createComment(long articleId, CommentRequestDto dto, Authentication authentication) {
        var article = articleRepository.findById(articleId).orElseThrow(
                () -> new ResourceNotFoundException("article", articleId)
        );

        var user = userRepository.findByUsernameIgnoreCase(authentication.getName()).orElseThrow();
        Comment comment = modelMapper.map(dto, Comment.class);
        comment.setArticle(article);
        comment.setUser(user);

        var saved = commentRepository.save(comment);
        return modelMapper.map(saved, CommentResponseDto.class);
    }

    @Override
    public List<CommentResponseDto> findCommentsByArticleId(long articleId) {
        return null;
    }

    @Override
    public CommentResponseDto updateComment(long commentId, CommentRequestDto dto, Authentication authentication) {
        return null;
    }

    @Override
    public CommentResponseDto deleteCommentById(long commentId, Authentication authentication) {
        return null;
    }
}
