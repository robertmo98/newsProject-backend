package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.request.CommentRequestDto;
import edu.robertmo.newsproject.dto.response.CommentResponseDto;
import edu.robertmo.newsproject.entity.Comment;
import edu.robertmo.newsproject.error.BadRequestException;
import edu.robertmo.newsproject.error.ResourceNotFoundException;
import edu.robertmo.newsproject.repository.ArticleRepository;
import edu.robertmo.newsproject.repository.CommentRepository;
import edu.robertmo.newsproject.repository.RoleRepository;
import edu.robertmo.newsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final UserDetailsServiceImpl authService;

    @Override
    public CommentResponseDto createComment(long articleId, CommentRequestDto dto, Authentication authentication) {
        var article = articleRepository.findById(articleId).orElseThrow(
                () -> new ResourceNotFoundException("article", articleId)
        );

        var user = userRepository.findByUsernameIgnoreCase(authentication.getName()).orElseThrow();
        Comment comment = modelMapper.map(dto, Comment.class);
        comment.setArticle(article);
        comment.setUser(user);
        comment.setDate(LocalDate.now());

        var saved = commentRepository.save(comment);
        return modelMapper.map(saved, CommentResponseDto.class);
    }

    @Override
    public List<CommentResponseDto> findCommentsByArticleId(long articleId, Authentication authentication) {
        if(!articleRepository.existsById(articleId)) {
            throw new ResourceNotFoundException("article", articleId);
        }

        return commentRepository
                .findCommentsByArticleId(articleId)
                .stream()
                .map(
                        c -> modelMapper.map(c, CommentResponseDto.class)
                ).toList();
    }

    @Override
    public CommentResponseDto updateComment(long commentId, CommentRequestDto dto, Authentication authentication) {
        var commentFromDb = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", commentId));

        userHasPermissionToEditComment(authentication, commentFromDb);

        commentFromDb.setContent(dto.getContent());

        var saved = commentRepository.save(commentFromDb);

        return modelMapper.map(saved, CommentResponseDto.class);
    }

    @Override
    public CommentResponseDto deleteCommentById(long commentId, Authentication authentication) {
        var saved =
                commentRepository
                        .findById(commentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Comment", commentId));

        userHasPermissionToEditComment(authentication, saved);


        commentRepository.deleteById(commentId);

        return modelMapper.map(saved, CommentResponseDto.class);
    }

    private void userHasPermissionToEditComment(Authentication authentication, Comment commentFromDb) {
        var user = commentFromDb.getUser();

        var adminRole = roleRepository.findByNameIgnoreCase("ROLE_ADMIN").orElseThrow();

        var isAdmin = authService.isAdmin(authentication.getName());
        var userOwnsComment = user.getUsername().equalsIgnoreCase(authentication.getName());

        if(!isAdmin && !userOwnsComment) {
            throw new BadRequestException("user", "Comment must belong the editing user");
        }
    }
}
