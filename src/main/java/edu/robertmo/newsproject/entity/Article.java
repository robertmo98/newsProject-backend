package edu.robertmo.newsproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String category;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Date date;

    @NotNull
    private String secondaryTitle;

    @NotNull
    private String mainImg;
    @NotNull
    private String mainImgDescription;
    @NotNull
    private String mainImgCredit;
    @NotNull
    private String secondImg;
    @NotNull
    private String secondImgDescription;
    @NotNull
    private String secondImgCredit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(
            mappedBy = "article",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Comment> comments = new HashSet<>();
}
