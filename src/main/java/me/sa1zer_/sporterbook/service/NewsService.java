package me.sa1zer_.sporterbook.service;

import me.sa1zer_.sporterbook.domain.model.News;
import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.payload.handler.ServiceUpdateByRequestHandler;
import me.sa1zer_.sporterbook.payload.request.admin.UpdateNewRequest;

import java.util.List;

public interface NewsService extends ServiceUpdateByRequestHandler<News, UpdateNewRequest> {
    News findById(Long id);

    News findByTItle(String title);

    List<News> findALl();

    List<News> findALlSimilar(String s);

    News createNew();

    News save(News news);

    void delete(News news);
}
