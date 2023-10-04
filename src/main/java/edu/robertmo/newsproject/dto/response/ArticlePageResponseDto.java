package edu.robertmo.newsproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageResponseDto {
    private List<ArticleWithCommentsDto> results;
    private int totalPages;
    private long totalArticles;
    private boolean isLast;
    private boolean isFirst;
    /**
     * current page
     */
    private int pageNo;
    /**
     * current page size
     */
    private int pageSize;
}
