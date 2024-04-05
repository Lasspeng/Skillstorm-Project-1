import { Button, Form, Label, TextInput } from "@trussworks/react-uswds";

export default function InventoryUpdateForm({setUpdate}) {

    function handleSubmit(event) {
        event.preventDefault();

        const url = "http://localhost:8080/inventory"
        const data = new FormData(event.target);
        const updatedInventory = {
            id: data.get("inventoryId"),
            itemName: data.get("inventoryName")
        }

        fetch(url, {
            method: "PUT",
            headers: { "Content-Type": "application/json"},
            body: JSON.stringify(updatedInventory)
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
        <Form onSubmit={handleSubmit}>
            <Label htmlFor="item-id-input">Item Id</Label>
            <TextInput id="item-id-input" name="inventoryId" type="number" />

            <Label htmlFor="item-name-input">Item Name</Label>
            <TextInput id="item-name-input" name="inventoryName" type="text" />

            <Button type="submit" data-close-modal="true">Submit</Button>
        </Form>
    )
}
