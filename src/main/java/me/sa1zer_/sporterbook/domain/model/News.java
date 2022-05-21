package me.sa1zer_.sporterbook.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.sa1zer_.sporterbook.domain.model.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = {"title", "text", "author"}, callSuper = true)
@Entity
@Data
@Table(name = "api_news")
public class News extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private LocalDateTime created;

    private String img;

    @PrePersist
    public void onCreated() {
        created = LocalDateTime.now();
    }
}
