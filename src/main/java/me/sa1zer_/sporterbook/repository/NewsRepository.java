package me.sa1zer_.sporterbook.repository;

import me.sa1zer_.sporterbook.domain.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByTitle(String t);

    List<News> findByTitleContaining(@Param("title")String title);
}
