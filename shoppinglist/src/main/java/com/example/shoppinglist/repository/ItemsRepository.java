package com.example.shoppinglist.repository;

import com.example.shoppinglist.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long> {
}
