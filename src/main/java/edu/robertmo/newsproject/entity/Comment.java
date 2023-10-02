package edu.robertmo.newsproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
}
