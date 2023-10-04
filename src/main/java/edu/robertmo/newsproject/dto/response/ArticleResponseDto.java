package edu.robertmo.newsproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponseDto {
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

}
