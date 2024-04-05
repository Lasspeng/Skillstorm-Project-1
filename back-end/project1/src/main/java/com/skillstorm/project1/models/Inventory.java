package com.skillstorm.project1.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Inventory")
public class Inventory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "item_name")
    @NotNull
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnore
    private Warehouse warehouse;


    public Inventory() {
    }

    public Inventory(String itemName, Warehouse warehouse) {
        this.itemName = itemName;
        this.warehouse = warehouse;
    }

    public Inventory(Integer id, String itemName, Warehouse warehouse) {
        this.id = id;
        this.itemName = itemName;
        this.warehouse = warehouse;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Inventory id(Integer id) {
        setId(id);
        return this;
    }

    public Inventory itemName(String itemName) {
        setItemName(itemName);
        return this;
    }

    public Inventory warehouse(Warehouse warehouse) {
        setWarehouse(warehouse);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Inventory)) {
            return false;
        }
        Inventory inventory = (Inventory) o;
        return Objects.equals(id, inventory.id) && Objects.equals(itemName, inventory.itemName) && Objects.equals(warehouse, inventory.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, warehouse);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", warehouse='" + getWarehouse() + "'" +
            "}";
    }

}
