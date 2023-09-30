package edu.robertmo.newsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequestDto {
    private String category;
    private String title;
    private String content;
    private Date date;
    private String secondaryTitle;
    private String mainImg;
    private String mainImgDescription;
    private String mainImgCredit;
    private String secondImg;
    private String secondImgDescription;
    private String secondImgCredit;
}
