package com.stc.managingFiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.managingFiles.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
