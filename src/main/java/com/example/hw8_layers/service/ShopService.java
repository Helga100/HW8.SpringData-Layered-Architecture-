package com.example.hw8_layers.service;

import com.example.hw8_layers.entity.Shop;

import java.util.List;

public interface ShopService {
    Shop create(Shop shop);

    void delete(Long id);

    List<Shop> findAllShops();

    Shop findShopById(Long id);

    Shop update(Long id, Shop shop);
}
