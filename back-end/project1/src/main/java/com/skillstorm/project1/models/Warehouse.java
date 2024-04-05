package com.skillstorm.project1.models;

import java.util.List;
import java.util.Objects;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Warehouse")
public class Warehouse {
    
    @Id
    @Column(name = "id", nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account ownerAccount;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    // @JsonIgnore
    private List<Inventory> inventory;


    public Warehouse() {
    }

    public Warehouse(int id) {
        this.id = id;
    }

    public Warehouse(Integer id, String warehouseName, Account ownerAccount, int capacity, List<Inventory> inventory) {
        this.id = id;
        this.warehouseName = warehouseName;
        this.ownerAccount = ownerAccount;
        this.capacity = capacity;
        this.inventory = inventory;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return this.warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Account getOwnerAccount() {
        return this.ownerAccount;
    }

    public void setOwnerAccount(Account ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Inventory> getInventory() {
        return this.inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

    public Warehouse id(Integer id) {
        setId(id);
        return this;
    }

    public Warehouse warehouseName(String warehouseName) {
        setWarehouseName(warehouseName);
        return this;
    }

    public Warehouse ownerAccount(Account ownerAccount) {
        setOwnerAccount(ownerAccount);
        return this;
    }

    public Warehouse capacity(int capacity) {
        setCapacity(capacity);
        return this;
    }

    public Warehouse inventory(List<Inventory> inventory) {
        setInventory(inventory);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Warehouse)) {
            return false;
        }
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(id, warehouse.id) && Objects.equals(warehouseName, warehouse.warehouseName) && Objects.equals(ownerAccount, warehouse.ownerAccount) && capacity == warehouse.capacity && Objects.equals(inventory, warehouse.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, warehouseName, ownerAccount, capacity, inventory);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", warehouseName='" + getWarehouseName() + "'" +
            ", ownerAccount='" + getOwnerAccount() + "'" +
            ", capacity='" + getCapacity() + "'" +
            ", inventory='" + getInventory() + "'" +
            "}";
    }

}
