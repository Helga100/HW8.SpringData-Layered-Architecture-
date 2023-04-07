package com.example.hw8_layers.web;

import com.example.hw8_layers.entity.Shop;
import com.example.hw8_layers.service.ShopService;
import com.example.hw8_layers.service.exception.ShopNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Shop> create(@RequestBody Shop shop) {
        if (shop == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        shopService.create(shop);
        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Shop> delete(@PathVariable("id") Long id) {
        try {
            shopService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ShopNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Shop>> findAllShops() {
        return new ResponseEntity<>(shopService.findAllShops(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getShopById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(shopService.findShopById(id), HttpStatus.OK);
        } catch (ShopNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable("id") Long id, @RequestBody Shop shop) {
        if (shop == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        shopService.update(id, shop);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }
}
