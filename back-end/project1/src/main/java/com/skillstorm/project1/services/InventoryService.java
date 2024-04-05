package com.skillstorm.project1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.Exceptions.CapacityExceededException;
import com.skillstorm.project1.Exceptions.ResourceNotFoundException;
import com.skillstorm.project1.models.Account;
import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.InventoryRepository;
import com.skillstorm.project1.repositories.WarehouseRepository;

@Service
public class InventoryService {
    
    @Autowired
    InventoryRepository inventoryRepo;

    @Autowired
    WarehouseRepository warehouseRepo;

    public List<Inventory> findAll() {
        return inventoryRepo.findAll();
    }
    
    public List<Inventory> findAllFromAccount(Account acct) {
        return inventoryRepo.findInventoryOwnedByAccount(acct);
    }

    public Inventory findById(int id) throws ResourceNotFoundException {
        Optional<Inventory> foundInventory = inventoryRepo.findById(id);

        if (foundInventory.isPresent()) {
            return foundInventory.get();
        } else {
            throw new ResourceNotFoundException("Inventory");
        }
    }


    public boolean exceedsCapacity(Inventory inv, int warehouseId) {
        Optional<Warehouse> warehouse = warehouseRepo.findById(warehouseId);
        int currentCount = warehouse.get().getInventory().size();
        System.out.println(warehouse.get().getCapacity());

        return currentCount >= warehouse.get().getCapacity();
    }

    public Inventory saveInventory(Inventory inv, int warehouseId) throws CapacityExceededException {
        if (exceedsCapacity(inv, warehouseId)) {
            throw new CapacityExceededException();
        } else {
            inv.setWarehouse(new Warehouse(warehouseId));
            return inventoryRepo.save(inv);
        }
    }

    public int updateInventory(Inventory inv) {
        return inventoryRepo.updateInventory(inv.getItemName(), inv.getId());
    }

    public void deleteInventory(int id) throws ResourceNotFoundException {
        Inventory foundInventory = findById(id);
        inventoryRepo.delete(foundInventory);
    }
}
