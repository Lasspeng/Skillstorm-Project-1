import { Grid, GridContainer, ModalHeading, ModalToggleButton, Modal } from '@trussworks/react-uswds';
import InventoryTable from '../components/InventoryTable';
import { useEffect, useRef, useState } from 'react';
import InventoryForm from '../components/InventoryForm';
import InventoryUpdateForm from '../components/InventoryUpdateForm';

export default function Inventory({ userCredentials }) {

    const url = "http://localhost:8080/warehouses/account";

    // const o = {
    //     id: 1
    // }
    
    const [inventory, setInventory] = useState([]);
    const [update, setUpdate] = useState(false);
    const [selectedItem, setSelectedItem] = useState({ id: 0})

    const modalRef = useRef(null);
    const modalRefUpdate = useRef(null);

    useEffect(() => {
        fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(userCredentials)
        })
        .then(data => data.json())
        .then(returnedData => {

            const arr = [];

            returnedData.forEach((warehouse) => {

                let currCapacity = `${warehouse.inventory.length}/${warehouse.capacity}`

                warehouse.inventory.forEach((item) => {
                    const obj = {
                        "id": item.id,
                        "itemName": item.itemName,
                        "warehouseName": warehouse.warehouseName,
                        "warehouseId": warehouse.id,
                        "currentCapacity": currCapacity
                    }
                    console.log(obj);
                    arr.push(obj);
                })
            })
            setInventory(arr);
        })
        .catch(err => console.error(err));
    }, [update]);
    
    function handleNewInventory(newItem) {
        setInventory((old) => [...old, newItem]);
    }

    return (
        <>
            <GridContainer>
                <Grid row>

                    <Grid col={30}>
                        <h1 className="text-centered">Inventory</h1>
                    </Grid>

                    <Grid col={10}>
                        <ModalToggleButton className="bg-accent-cool-darker" modalRef={modalRef} opener>New Inventory Item</ModalToggleButton>
                        {/* <InventoryForm handleNewInventory={handleNewInventory} setUpdate={setUpdate} /> */}
                    </Grid>

                </Grid>

                <Grid row>

                    <Grid col={100}>
                        <InventoryTable tableData={inventory} modalRefUpdate={modalRefUpdate} selectedItem={setSelectedItem} setUpdate={setUpdate} />
                    </Grid>
                </Grid>
            </GridContainer>
            <Modal id="inventory-form-modal" ref={modalRef} aria-labelledby="modal-2-heading" aria-describedby="modal-2-description">
                <ModalHeading id="inventory-form-modal-heading"> Enter New Item Details</ModalHeading>
                <InventoryForm handleNewInventory={handleNewInventory} setUpdate={setUpdate} />
            </Modal>
            <Modal id="inventory-update-form-modal" ref={modalRefUpdate} aria-labelledby="modal-1-heading" aria-describedby="modal-1-description">
                <ModalHeading id="inventory-update-form-heading">Update Item Details</ModalHeading>
                <InventoryUpdateForm setUpdate={setUpdate} selectedItem={selectedItem} />
            </Modal>
        </>
    )
}
