package edu.robertmo.newsproject.dto.request;


import edu.robertmo.newsproject.validators.UniqueTitle;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleUpdateRequestDto {
    @NotNull
    @Size(min = 2, max = 16)
    private String category;
    @NotNull

    @NotNull
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
