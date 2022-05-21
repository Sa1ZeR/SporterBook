package me.sa1zer_.sporterbook.payload.facade;

import me.sa1zer_.sporterbook.domain.model.News;
import me.sa1zer_.sporterbook.payload.dto.NewsDto;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper implements Mapper<News, NewsDto>{
    @Override
    public NewsDto map(News from) {
        NewsDto newsDto = new NewsDto();

        newsDto.setId(from.getId());
        newsDto.setText(from.getText());
        newsDto.setTitle(from.getTitle());
        newsDto.setAuthor(from.getAuthor().getLogin());
        newsDto.setDate(from.getCreated());
        newsDto.setImg(from.getImg());

        return newsDto;
    }
}
