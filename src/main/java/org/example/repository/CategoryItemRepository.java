package org.example.repository;

import org.example.domain.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

@Deprecated
public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {

}
