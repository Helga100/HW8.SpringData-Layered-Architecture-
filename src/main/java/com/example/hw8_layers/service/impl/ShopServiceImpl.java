package com.example.hw8_layers.service.impl;

import com.example.hw8_layers.entity.Shop;
import com.example.hw8_layers.repository.ShopRepository;
import com.example.hw8_layers.service.ShopService;
import com.example.hw8_layers.service.exception.ShopNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;


    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop create(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public void delete(Long id) {
        if (!shopRepository.existsById(id)) {
            throw new ShopNotFoundException("Shop is not found");
        }
        shopRepository.deleteById(id);
    }

    @Override
    public List<Shop> findAllShops() {
        return (List<Shop>) shopRepository.findAll();
    }

    @Override
    public Shop findShopById(Long id) {
        return shopRepository.findById(id).orElseThrow(() -> new ShopNotFoundException("The shop is not found"));
    }

    @Override
    public Shop update(Long id, Shop shop) {
        shop.setId(id);
        return shopRepository.save(shop);
    }
}
