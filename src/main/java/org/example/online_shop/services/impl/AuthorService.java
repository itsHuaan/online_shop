package org.example.online_shop.services.impl;

import org.example.online_shop.dto.AuthorDto;
import org.example.online_shop.entities.AuthorEntity;
import org.example.online_shop.entities.CategoryEntity;
import org.example.online_shop.mappers.impl.AuthorMapper;
import org.example.online_shop.models.AuthorModel;
import org.example.online_shop.repositories.IAuthorRepository;
import org.example.online_shop.services.IAuthorService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements IAuthorService {
    private final AuthorMapper authorMapper;
    private final IAuthorRepository authorRepository;

    public AuthorService(AuthorMapper authorMapper, IAuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDto> findAll() {
        return null;
    }

    @Override
    public AuthorDto findById(Long id) {
        return null;
    }

    @Override
    public int save(AuthorModel authorModel) {
        try{
            if (authorModel.getAuthorId() == null){
                AuthorEntity author1 = authorMapper.toEntity(authorModel);
                author1.setStatus(true);
                author1.setCreatedDate(LocalDateTime.now());
                authorRepository.save(author1);
                return 1;
            } else {
                Optional<AuthorEntity> author = authorRepository.findById(authorModel.getAuthorId());
                authorRepository.save(mapNonNullFieldsToEntity(authorModel, author.get()));
                return 2;
            }
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int delete(Long id) {
        try{
            Optional<AuthorEntity> author = authorRepository.findById(id);
            if (!author.isEmpty()){
                AuthorEntity author1 = author.get();
                author1.setStatus(false);
                authorRepository.save(author1);
                return 1;
            }else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }

    private AuthorEntity mapNonNullFieldsToEntity(AuthorModel authorModel, AuthorEntity authorEntity) {
        for (Field userField : authorModel.getClass().getDeclaredFields()) {
            try {
                Field dtoField = authorEntity.getClass().getDeclaredField(userField.getName());
                userField.setAccessible(true);
                Object value = userField.get(authorModel);

                if (value != null) {
                    dtoField.setAccessible(true);
                    dtoField.set(authorEntity, value);
                }
            } catch (NoSuchFieldException e) {
                System.out.println("Field missing in ProfileDto: " + userField.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return authorEntity;
    }
}
