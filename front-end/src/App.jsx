import '@trussworks/react-uswds/lib/index.css';
import '@trussworks/react-uswds/lib/uswds.css';

import Inventory from './pages/Inventory';
import Warehouses from './pages/Warehouses';
import SignIn from './pages/SignIn';

import { Title, Header, PrimaryNav, Grid } from '@trussworks/react-uswds';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import { useState } from 'react';


export default function App() {

  const navItems = [
    <Link to='/'>Sign In</Link>,
    <Link to='inventory'>Inventory</Link>,
    <Link to='warehouses'>Warehouses</Link>,
  ];

  const [userCredentials, setUserCredentials] = useState({ id: 0, name: ""});
    

  return (
    <>
      <Header basic={true}>
        <h1 className="bg-primary-lighter" style={{textAlign: "center"}}>Warehouse and Inventory API</h1>
      </Header>

      <BrowserRouter basename='/' className="text-center">
        <Grid>
          <PrimaryNav items={navItems}></PrimaryNav>
        </Grid>
        <Routes>
          <Route path='/' element={<SignIn setUserCredentials={setUserCredentials} />} />
          <Route path='inventory' element={<Inventory userCredentials={userCredentials} />} />
          <Route path='warehouses' element={<Warehouses userCredentials={userCredentials}/>} />
        </Routes>
      </BrowserRouter>
    </>
  )
}
