package edu.robertmo.newsproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Long id;

    private String content;

    private Date date;

    // TODO: 29/09/2023 add User relationship 
    // TODO: 29/09/2023 add article relationship
}
