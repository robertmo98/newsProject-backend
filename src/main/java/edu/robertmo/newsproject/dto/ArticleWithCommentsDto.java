package edu.robertmo.newsproject.dto;

import edu.robertmo.newsproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleWithCommentsDto {
    private Long id;
    private String category;
    private String title;
    private String content;
    private LocalDate date;
    private String secondaryTitle;
    private String mainImg;
    private String mainImgDescription;
    private String mainImgCredit;
    private String secondImg;
    private String secondImgDescription;
    private String secondImgCredit;

    private List<Comment> comments;
}
