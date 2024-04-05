
import { Table, ModalToggleButton, Button } from "@trussworks/react-uswds";


export default function InventoryTable({ tableData, modalRefUpdate, setUpdate }) {

    // function handleClick(item) {
    //     console.log(item)
    //     setSelectedItem((old) => old.id = item.id);
    // }

    const url = "http://localhost:8080/inventory";

    function handleDelete(id) {
        fetch(`${url}/${id}`, {
            method: "DELETE"
        })
        .then(data => setUpdate((old) => !old))
        .catch(err => console.error(err));
    }

    return (
        <>
            <Table striped bordered={false} fullWidth className="bg-accent-cool-light">
                <thead>
                    <tr>
                        <th>Item Id</th>
                        <th>Item Name</th>
                        <th>Warehouse Name</th>
                        <th>Warehouse Id</th>
                        <th>Warehouse Capacity</th>
                    </tr>
                </thead>
                <tbody>
                    {tableData.map((item) => {
                        return (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.itemName}</td>
                                <td>{item.warehouseName}</td>
                                <td>{item.warehouseId}</td>
                                <td>{item.currentCapacity}</td>
                                <td><ModalToggleButton modalRef={modalRefUpdate} opener>Update</ModalToggleButton></td>
                                <td><Button type="button" secondary onClick={() => handleDelete(item.id)}>
                                    Delete
                                </Button></td>
                            </tr>
                        );
                    })}
                </tbody>

            </Table>
        </>
    )
}
