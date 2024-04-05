import { Button, Form, Label, TextInput } from "@trussworks/react-uswds";

export default function WarehouseUpdateForm({ setUpdate, userCredentials }) {

    function handleSubmit(event) {
        event.preventDefault();

        const url = "http://localhost:8080/warehouses"
        const data = new FormData(event.target);
        const updatedWarehouse = {
            id: data.get("warehouseId"),
            warehouseName: data.get("warehouseName"),
            ownerAccount: {
                id: userCredentials.id
            },
            capacity: data.get("warehouseCapacity")
        }
        console.log(updatedWarehouse);

        fetch (url, {
            method: "PUT",
            headers: { "Content-Type": "application/json"},
            body: JSON.stringify(updatedWarehouse)
        })
        .then(data => data.json())
        .then(returnedData => {
            setUpdate((old) => !old);
            event.target.reset();
        })
        .catch(err => {
            console.error(err);
            event.target.reset();
        });
    }

    return (
        <>
            <Form onSubmit={handleSubmit}>
            <Label htmlFor="warehouse-id-input">Warehouse Id</Label>
            <TextInput id="warehouse-id-input" name="warehouseId" type="number" />

            <Label htmlFor="warehouse-name-input">Warehouse Name</Label>
            <TextInput id="warehouse-name-input" name="warehouseName" type="text" />

            <Label htmlFor="warehouse-capacity-input">Warehouse Capacity</Label>
            <TextInput id="warehouse-capacity-input" name="warehouseCapacity" type="number" />

            <Button type="submit" data-close-modal="true">Submit</Button>
        </Form>
        </>
    )
}
