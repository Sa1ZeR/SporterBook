package me.sa1zer_.sporterbook.service.impl;

import me.sa1zer_.sporterbook.domain.model.News;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.exception.NewsNotFoundException;
import me.sa1zer_.sporterbook.payload.request.admin.UpdateNewRequest;
import me.sa1zer_.sporterbook.repository.NewsRepository;
import me.sa1zer_.sporterbook.service.NewsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository repository;

    @Value("${common.news-file-dir}")
    private String newsImgPath;

    public NewsServiceImpl(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    public News findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NewsNotFoundException(String.format("News with id %s not found!", id)));
    }

    @Override
    public News findByTItle(String title) {
        return repository.findByTitle(title).orElseThrow(() ->
                new NewsNotFoundException(String.format("News with title %s not found!", title)));
    }

    @Override
    public List<News> findALl() {
        return repository.findAll();
    }

    @Override
    public List<News> findALlSimilar(String s) {
        return repository.findByTitleContaining(s);
    }

    @Override
    public News createNew() {
        return new News();
    }

    @Override
    public News save(News news) {
        return repository.save(news);
    }

    @Override
    public void delete(News news) {
        repository.delete(news);
    }

    @Override
    public News updateByRequest(UpdateNewRequest request, User... user) {
        News news;
        if(request.getId() != null)
            news = findById(request.getId());
        else {
            news = createNew();
            news.setAuthor(user[0]);
        }

        news.setText(request.getText());
        news.setTitle(request.getTitle());

        if(request.getImg() != null) {
            String fileName = news.getImg() != null ? news.getImg() :
                    UUID.randomUUID().toString() + ".png";

            File dir = new File(newsImgPath);
            if (!dir.exists()) {
                dir.mkdir();
                dir.mkdirs();
            }

            try {
                request.getImg().transferTo(new File(dir.getAbsolutePath() + File.separator + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            news.setImg(fileName);
        }

        return save(news);
    }
}
