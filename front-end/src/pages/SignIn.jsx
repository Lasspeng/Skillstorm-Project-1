import { GridContainer, Grid, Label, TextInput, Form, Fieldset, Button, GovBanner } from "@trussworks/react-uswds";
import { useNavigate } from "react-router-dom";

export default function SignIn({ setUserCredentials }) {

    const navigate = useNavigate();

    function authenticate(event) {
        event.preventDefault();

        const url = "http://localhost:8080/accounts/auth"
        const data = new FormData(event.target);
        const user = {
            username: data.get("username"),
            password: data.get("password")
        }

        fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json "},
            body: JSON.stringify(user)
        })
        .then(data => data.json())
        .then(returnedData => {
            setUserCredentials((old) => returnedData);
            console.log(returnedData)
            navigate("/inventory");
        })
        .catch(err => alert("An account with those credentials was not found"));
    }

    return (
        <>
            {/* <a className="usa-skipnav" href="#main-content">
                Skip to main content
            </a>

            <GovBanner /> */}
            {/* <Header extended>
                <div className="usa-navbar">
                    <Title id="extended-logo">
                        <a href="/" title="Home" aria-label="Home">
                            Warehouse and Inventory CRUD App
                        </a>
                    </Title>
                </div>
            </Header> */}

            <main id="main-content">
                <div className="bg-base-lightest">
                    <GridContainer className="usa-section">
                        <Grid row={true} className="flex-justify-center">
                            <Grid col={12} tablet={{
                                col: 8
                            }} desktop={{
                                col: 6
                            }}>
                                <div className="bg-white padding-y-3 padding-x-5 border border-base-lighter">
                                    {/* <h1 className="margin-bottom-0">Sign in</h1> */}
                                    <Form onSubmit={authenticate}>
                                        <Fieldset legend="Sign in" legendStyle="large">
                                            <Label htmlFor="username">Username</Label>
                                            <TextInput id="username" name="username" type="text" autoCorrect="off" autoCapitalize="off" required={true} />

                                            <Label htmlFor="password-sign-in">Password</Label>
                                            <TextInput id="password-sign-in" name="password" autoCorrect="off" autoCapitalize="off" required={true} type="password"/>

                                            {/* <button title="Show password" type="button" className="usa-show-password" aria-controls="password-sign-in" onClick={() => setShowPassword(showPassword => !showPassword)}>
                                                {showPassword ? 'Hide password' : 'Show password'}
                                            </button> */}

                                            <Button type="submit">Sign in</Button>

                                            {/* <p>
                                                <Link href="javascript:void();">Forgot password?</Link>
                                            </p> */}
                                        </Fieldset>
                                    </Form>
                                </div>
                            </Grid>
                        </Grid>
                    </GridContainer>
                </div>
            </main>
        </>
    )
}
