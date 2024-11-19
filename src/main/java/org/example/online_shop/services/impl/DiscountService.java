package org.example.online_shop.services.impl;

import org.example.online_shop.dto.DiscountDto;
import org.example.online_shop.entities.DiscountEntity;
import org.example.online_shop.mappers.impl.DiscountMapper;
import org.example.online_shop.models.DiscountModel;
import org.example.online_shop.repositories.IDiscountRepository;
import org.example.online_shop.services.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Service
public class DiscountService implements IDiscountService {
    private final IDiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Autowired
    public DiscountService(IDiscountRepository discountRepository, DiscountMapper discountMapper) {
        this.discountRepository = discountRepository;
        this.discountMapper = discountMapper;
    }

    @Override
    public List<DiscountDto> findAll() {
        return discountRepository.findAll().stream().map(discountMapper::toDTO).toList();
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
        if (discountRepository.existsById(id)) {
            discountRepository.deleteById(id);
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
