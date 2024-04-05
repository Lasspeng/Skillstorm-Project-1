package com.skillstorm.project1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.Exceptions.ResourceNotFoundException;
import com.skillstorm.project1.models.Account;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.WarehouseRepository;

@Service
public class WarehouseService {
    
    @Autowired
    WarehouseRepository warehouseRepo;

    public List<Warehouse> findAll() {
        return warehouseRepo.findAll();
    }

    public List<Warehouse> findAllByAccountId(Account acct) {
        return warehouseRepo.findByOwnerAccount(acct);
    }

    public Warehouse findById(int id) throws ResourceNotFoundException {
        Optional<Warehouse> foundWarehouse = warehouseRepo.findById(id);

        if (foundWarehouse.isPresent()) {
            return foundWarehouse.get();
        } else {
            throw new ResourceNotFoundException("Warehouse");
        }
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    public void deleteWarehouse(int id) throws ResourceNotFoundException {
        Warehouse foundWarehouse = findById(id);
        warehouseRepo.delete(foundWarehouse);
    }
}
