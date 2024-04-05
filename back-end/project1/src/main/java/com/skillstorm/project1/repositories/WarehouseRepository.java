package com.skillstorm.project1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.models.Account;
import com.skillstorm.project1.models.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    
    @Query("SELECT w FROM Warehouse w JOIN w.ownerAccount WHERE w.ownerAccount = ?1")
    List<Warehouse> findByOwnerAccount(Account acct);

    int countById(Integer id);
}
