package com.skillstorm.project1.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.Account;
import com.skillstorm.project1.models.Inventory;

import jakarta.transaction.Transactional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i FROM Inventory i JOIN i.warehouse w WHERE w.ownerAccount = ?1")
    public List<Inventory> findInventoryOwnedByAccount(Account acct);

    @Query("UPDATE Inventory i set i.itemName = ?1 WHERE id = ?2")
    @Modifying
    @Transactional
    public int updateInventory(String name, int id);
}
