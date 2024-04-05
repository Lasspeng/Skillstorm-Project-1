import { Button, Form, Label, TextInput } from "@trussworks/react-uswds";

export default function InventoryForm({ handleNewInventory, setUpdate }) {

    function handleSubmit(event) {
        event.preventDefault();

        const url = "http://localhost:8080/inventory";
        const data = new FormData(event.target);
        const newInventory = {
            itemName: data.get("inventoryName"),
            warehouse: {
                id: data.get("warehouseId") 
            }
        }

        fetch(`${url}/${newInventory.warehouse.id}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newInventory)
        })
        .then(data => data.json())
        .then(returnedData => {
            // returnedData.warehouse.id = newInventory.warehouse.id;
            // handleNewInventory(returnedData);
            setUpdate((old) => !old);
            event.target.reset();
        })
        .catch(err => {
            alert("This warehouse is at capacity. New inventory could not be added");
            event.target.reset();
        });

    }

    return (
        <Form onSubmit={handleSubmit}>
            <Label htmlFor="item-name-input">Item Name</Label>
            <TextInput id="item-name-input" name="inventoryName" type="text" />

            <Label htmlFor="warehouse-id-input">Warehouse Id</Label>
            <TextInput id="warehouse-id-input" name="warehouseId" type="number" />

            <Button type="submit" data-close-modal="true">Submit</Button>
        </Form>
    )
}
