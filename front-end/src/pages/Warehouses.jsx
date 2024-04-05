import { Grid, GridContainer, ModalHeading, ModalToggleButton, Modal } from '@trussworks/react-uswds';
import InventoryTable from '../components/InventoryTable';
import { useEffect, useRef, useState } from 'react';
import InventoryForm from '../components/InventoryForm';
import InventoryUpdateForm from '../components/InventoryUpdateForm';
import WarehouseTable from '../components/WarehouseTable';
import WarehouseForm from '../components/WarehouseForm';
import WarehouseUpdateForm from '../components/WarehouseUpdateForm';

export default function Warehouses({ userCredentials }) {

    const url = "http://localhost:8080/warehouses/account"

    // const o = {
    //     id: 1
    // }

    const [warehouses, setWarehouses] = useState([]);
    const [update, setUpdate] = useState(false);

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
                setWarehouses(returnedData);
            })
            .catch(err => console.error(err));
    }, [update]);

    return (
        <>
            <GridContainer>
                <Grid row>

                    <Grid col={30}>
                        <h1 className="text-centered">Warehouses</h1>
                    </Grid>

                    <Grid col={10}>
                        <ModalToggleButton className="bg-accent-cool-darker" modalRef={modalRef} opener>New Warehouse</ModalToggleButton>
                    </Grid>

                </Grid>

                <Grid row>

                    <Grid col={30}>
                        <WarehouseTable tableData={warehouses} modalRefUpdate={modalRefUpdate} setUpdate={setUpdate} />
                    </Grid>
                </Grid>
            </GridContainer>
            <Modal id="warehouse-form-modal" ref={modalRef} aria-labelledby="modal-2-heading" aria-describedby="modal-2-description">
                <ModalHeading id="warehouse-form-modal-heading"> Enter New Warehouse Details</ModalHeading>
                <WarehouseForm setUpdate={setUpdate} userCredentials={userCredentials} />
            </Modal>
            <Modal id="warehouse-update-form-modal" ref={modalRefUpdate} aria-labelledby="modal-1-heading" aria-describedby="modal-1-description">
                <ModalHeading id="warehouse-update-form-heading">Update Warehouse Details</ModalHeading>
                <WarehouseUpdateForm setUpdate={setUpdate} userCredentials={userCredentials} />
            </Modal>
        </>
    )
}
