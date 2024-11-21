package org.example.online_shop.services.impl;

import org.example.online_shop.dto.PostDto;
import org.example.online_shop.entities.PostEntity;
import org.example.online_shop.mappers.impl.PostMapper;
import org.example.online_shop.models.PostModel;
import org.example.online_shop.repositories.IPostRepository;
import org.example.online_shop.services.IPostService;
import org.example.online_shop.utils.specifications.PostSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostService implements IPostService {
    private final IPostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(IPostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll(Specification.where(PostSpecifications.isActive())).stream().map(postMapper::toDTO).toList();
    }

    @Override
    public PostDto findById(Long id) {
        return null;
    }

    @Override
    public int save(PostModel postModel) {
        PostEntity postEntity = postModel.getPostId() != null ? postRepository.findById(postModel.getPostId()).orElse(null): null;
        if (postEntity != null){
            PostEntity post = null;
            String imageUrl = null;
            try {
                post = postMapper.toEntity(postModel);
                imageUrl = uploadImage(postModel.getImageUrl());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            post.setImageUrl(imageUrl);
            postRepository.save(post);
            return 2;
        }else {
            try {
                PostEntity post = postMapper.toEntity(postModel);
                String imageUrl = uploadImage(postModel.getImageUrl());
                post.setImageUrl(imageUrl);
                postRepository.save(post);
                return 1;
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }

        }
    }

    @Override
    public int delete(Long id) {
        PostEntity post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setStatus(false);
            postRepository.save(post);
            return 1;
        }
        return 0;
    }

    private String uploadImage(MultipartFile file) throws IOException {
        Path path = Paths.get("build/resources/main/static/uploads");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        String uniqueFileName =  LocalDate.now() +"_"+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);
        String imageUrl = "/uploads/" + uniqueFileName;
        return imageUrl;
    }
}
