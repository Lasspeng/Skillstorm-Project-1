import { Table, ModalToggleButton, Button } from "@trussworks/react-uswds";

export default function WarehouseTable({ tableData, modalRefUpdate, setUpdate }) {

    const url = "http://localhost:8080/warehouses"

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
                        <th>Warehouse Id</th>
                        <th>Warehouse Name</th>
                        <th>Warehouse Capacity</th>
                    </tr>
                </thead>
                <tbody>
                    {tableData.map((item) => {
                        return (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.warehouseName}</td>
                                <td>{item.capacity}</td>
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
