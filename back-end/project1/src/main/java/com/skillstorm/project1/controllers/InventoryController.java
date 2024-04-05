package com.skillstorm.project1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.Exceptions.CapacityExceededException;
import com.skillstorm.project1.Exceptions.ResourceNotFoundException;
import com.skillstorm.project1.models.Account;
import com.skillstorm.project1.models.Inventory;
import com.skillstorm.project1.services.InventoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/inventory")
@CrossOrigin
@Tag(name = "Inventory Controller Endpoints")
public class InventoryController {
    
    @Autowired
    InventoryService inventoryService;

    @GetMapping
    @Operation(summary = "Retrieve all inventory")
    public ResponseEntity<List<Inventory>> findAll() {
        List<Inventory> inventory = inventoryService.findAll();
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PostMapping("/account")
    @Operation(summary = "Retrieve all inventory owned by a given account")
    public ResponseEntity<List<Inventory>> findAllFromAccount(@RequestBody Account acct) {
        List<Inventory> inventory = inventoryService.findAllFromAccount(acct);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PostMapping("/{warehouseId}")
    @Operation(summary = "Create new inventory if targeted warehouse is not at capacity")
    public ResponseEntity<Object> createInventory(@RequestBody Inventory inv, @PathVariable int warehouseId) {
        
        try {
            Inventory newInv = inventoryService.saveInventory(inv, warehouseId);
            return new ResponseEntity<>(newInv, HttpStatus.OK);

        } catch (CapacityExceededException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @Operation(summary = "Modify existing inventory")
    public ResponseEntity<Integer> updateInventory(@RequestBody Inventory inv) {
            int updated = inventoryService.updateInventory(inv);
            return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete inventory")
    public ResponseEntity<Object> deleteInventory(@PathVariable int id) {
        try {
            inventoryService.deleteInventory(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
