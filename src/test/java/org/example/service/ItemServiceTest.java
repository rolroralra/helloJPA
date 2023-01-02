package org.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.example.domain.Item;
import org.example.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @DisplayName("아이템 저장")
    @Test
    void save() {
        // Given
        Item item = Item.builder()
            .name("item01")
            .stockQuantity(100)
            .price(20000)
            .build();

        // When
        Long savedId = itemService.save(item);

        // Then
        Optional<Item> findById = itemRepository.findById(savedId);
        assertThat(findById)
            .isPresent()
            .get()
            .isEqualTo(item);
    }

    @DisplayName("아이템 목록 조회")
    @Test
    void findAll() {
        // Given
        Item item = Item.builder()
            .name("item01")
            .stockQuantity(100)
            .price(20000)
            .build();

        itemService.save(item);

        // When
        Page<Item> findAll = itemService.findAll(PageRequest.of(0, 10));

        // Then
        assertThat(findAll)
            .isNotEmpty()
            .hasSizeGreaterThanOrEqualTo(1);
    }

    @DisplayName("아이템 상세 조회")
    @Test
    void findOne() {
        // Given
        Item item = Item.builder()
            .name("item01")
            .stockQuantity(100)
            .price(20000)
            .build();

        itemService.save(item);

        // When
        Optional<Item> findOne = itemService.findOne(item.getId());

        // Then
        assertThat(findOne)
            .isPresent()
            .get()
            .isEqualTo(item);
    }
}
