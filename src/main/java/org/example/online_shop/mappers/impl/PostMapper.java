package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.PostDto;
import org.example.online_shop.entities.PostEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.PostModel;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements IBaseMapper<PostDto, PostModel, PostEntity> {

    @Override
    public PostDto toDTO(PostEntity entity) {
        return PostDto.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .imageUrl(entity.getImageUrl())
                .status(entity.getStatus())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    @Override
    public PostEntity toEntity(PostModel model) {
        return PostEntity.builder()
                .postId(model.getPostId())
                .title(model.getTitle())
                .content(model.getContent())
                .status(model.getStatus())
                .createdDate(model.getCreatedDate())
                .build();
    }
}
