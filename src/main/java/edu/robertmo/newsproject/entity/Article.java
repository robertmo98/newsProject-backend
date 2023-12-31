package edu.robertmo.newsproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
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
    @Column(columnDefinition="TEXT")
    private String content;

    private LocalDate date;

    private String secondaryTitle;

    /** url for image **/
    private String mainImg;

    private String mainImgDescription;

    private String mainImgCredit;

    private String secondImg;

    private String secondImgDescription;

    private String secondImgCredit;

    @OneToMany(
            mappedBy = "article",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne (
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
