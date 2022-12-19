package org.example.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.domain.Item;
import org.example.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Long save(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Optional<Item> findOne(Long itemId) {
        return itemRepository.findById(itemId);
    }
}
