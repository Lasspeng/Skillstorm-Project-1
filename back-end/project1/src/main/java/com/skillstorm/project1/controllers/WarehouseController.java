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

import com.skillstorm.project1.Exceptions.ResourceNotFoundException;
import com.skillstorm.project1.models.Account;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.services.WarehouseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin
@Tag(name = "Warehouse Controller Endpoints")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;
    
    @GetMapping
    @Operation(summary = "Retrieve all warehouses")
    public ResponseEntity<List<Warehouse>> findAll() {
        List<Warehouse> warehouses = warehouseService.findAll();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @PostMapping("/account")
    @Operation(summary = "Retrieve all warehouses owned by a given account/company")
    public ResponseEntity<List<Warehouse>> findAllByAccountId(@RequestBody Account acct) {
        List<Warehouse> warehouses = warehouseService.findAllByAccountId(acct);
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a warehouse with a given id")
    public ResponseEntity<Object> findWarehouse(@PathVariable int id) {

        try {
            Warehouse foundWarehouse = warehouseService.findById(id);
            return new ResponseEntity<>(foundWarehouse, HttpStatus.OK);

        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Create a new warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse newWarehouse = warehouseService.saveWarehouse(warehouse);
        return new ResponseEntity<>(newWarehouse, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Modify existing warehouse")
    public ResponseEntity<Warehouse> updateWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse updatedWarehouse = warehouseService.saveWarehouse(warehouse);
        return new ResponseEntity<>(updatedWarehouse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a warehouse")
    public ResponseEntity<Object> deleteWarehouse(@PathVariable int id) {

        try {
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.noContent().build();

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
