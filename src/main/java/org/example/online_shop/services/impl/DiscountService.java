package org.example.online_shop.services.impl;

import org.example.online_shop.dto.DiscountDto;
import org.example.online_shop.entities.DiscountEntity;
import org.example.online_shop.mappers.impl.DiscountMapper;
import org.example.online_shop.models.DiscountModel;
import org.example.online_shop.repositories.IDiscountRepository;
import org.example.online_shop.utils.specifications.DiscountSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Service
public class IDiscountService implements org.example.online_shop.services.IDiscountService {
    private final IDiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Autowired
    public IDiscountService(IDiscountRepository discountRepository, DiscountMapper discountMapper) {
        this.discountRepository = discountRepository;
        this.discountMapper = discountMapper;
    }

    @Override
    public List<DiscountDto> findAll() {
        return discountRepository.findAll(Specification.where(DiscountSpecifications.isActive())).stream().map(discountMapper::toDTO).toList();
    }

    @Override
    public DiscountDto findById(Long id) {
        return discountMapper.toDTO(Objects.requireNonNull(discountRepository.findById(id).orElse(null)));
    }

    @Override
    public int save(DiscountModel discountModel) {
        DiscountEntity currentDiscount = discountModel.getDiscountId() != null
                ? discountRepository.findById(discountModel.getDiscountId()).orElse(null)
                : null;
        if (currentDiscount != null) {
            discountRepository.save(mapNonNullFieldsToEntity(discountModel, currentDiscount));
            return 2;
        } else {
            discountRepository.save(discountMapper.toEntity(discountModel));
            return 1;
        }
    }

    @Override
    public int delete(Long id) {
        DiscountEntity discountEntity = discountRepository.findById(id).orElse(null);
        if (discountEntity != null) {
            discountEntity.setStatus(false);
            discountRepository.save(discountEntity);
            return 1;
        }
        return 0;
    }

    private DiscountEntity mapNonNullFieldsToEntity(DiscountModel discountModel, DiscountEntity discountEntity) {
        for (Field userField : discountModel.getClass().getDeclaredFields()) {
            try {
                Field dtoField = discountEntity.getClass().getDeclaredField(userField.getName());
                userField.setAccessible(true);
                Object value = userField.get(discountModel);

                if (value != null) {
                    dtoField.setAccessible(true);
                    dtoField.set(discountEntity, value);
                }
            } catch (NoSuchFieldException e) {
                System.out.println("Field missing in ProfileDto: " + userField.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return discountEntity;
    }
}
