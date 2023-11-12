import React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Col, Row } from "react-bootstrap";
import Alert from "react-bootstrap/Alert";
import { useState } from "react";
import Axios from "../axios";
import ENDPOINTS from "../endpoints";

const SignUp = () => {
  const [showError, setShowError] = useState(false);
  const [showSuccess, setShowSuccess] = useState(false);

  const [username, setUsername] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [streetAddress, setStreetAddress] = useState("");
  const [streetNumber, setStreetNumber] = useState(0);
  const [city, setCity] = useState("");
  const [country, setCountry] = useState("");
  const [postalCode, setPostalCode] = useState("");
  const [password, setPassword] = useState("");
  const [province, setProvince] = useState("");

  const handleSubmission = () => {
    const data = {
      userName: username,
      firstName: firstName,
      lastName: lastName,
      streetAddress: streetAddress,
      streetNumber: streetNumber,
      city: city,
      country: country,
      postalCode: postalCode,
      password: password,
      province: province,
    };
    console.log(data);

    Axios.post(ENDPOINTS.USER.CREATE, data)
      .then((res) => {
        setShowSuccess(true);
        setShowError(false);

        console.log("Server Response: " + res.data);
      })
      .catch((err) => {
        console.log(err);
        setShowError(true);
        setShowSuccess(false);
      });
  };

  return (
    <div>
      <h1>Sign Up</h1>
      <br />

      {showError && (
        <Alert variant={"danger"}>
          Make sure all fields are filled out and password follows password
          requirements. <br />
          Password Requirements: min 8 characters, should contain one lowercase,
          uppercase and special character. <br />
          No 2 accounts can share the same username.
        </Alert>
      )}
      {showSuccess && <Alert variant={"success"}>Successful sign up</Alert>}
      <Form>
        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Username</Form.Label>
            <Form.Control
              type="text"
              placeholder=""
              value={username}
              onChange={(e) => {
                setUsername(e.target.value);
              }}
            />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>First Name</Form.Label>
            <Form.Control
              type="text"
              placeholder=""
              value={firstName}
              onChange={(e) => {
                setFirstName(e.target.value);
              }}
            />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>Last Name</Form.Label>
            <Form.Control
              type="text"
              placeholder=""
              value={lastName}
              onChange={(e) => {
                setLastName(e.target.value);
              }}
            />
          </Form.Group>
        </Row>

        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Street Name</Form.Label>
            <Form.Control
              type="text"
              placeholder=""
              value={streetAddress}
              onChange={(e) => {
                setStreetAddress(e.target.value);
              }}
            />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>Street Number</Form.Label>
            <Form.Control
              type="number"
              placeholder=""
              value={streetNumber}
              onChange={(e) => {
                setStreetNumber(e.target.value);
              }}
            />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>City</Form.Label>
            <Form.Control
              type="text"
              placeholder=""
              value={city}
              onChange={(e) => {
                setCity(e.target.value);
              }}
            />
          </Form.Group>
        </Row>

        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Province</Form.Label>
            <Form.Control
              type="text"
              placeholder=""
              value={province}
              onChange={(e) => {
                setProvince(e.target.value);
              }}
            />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>Country</Form.Label>
            <Form.Control
              type="text"
              placeholder=""
              value={country}
              onChange={(e) => {
                setCountry(e.target.value);
              }}
            />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>Postal Code</Form.Label>
            <Form.Control
              type="text"
              placeholder=""
              value={postalCode}
              onChange={(e) => {
                setPostalCode(e.target.value);
              }}
            />
          </Form.Group>
        </Row>
        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder=""
              value={password}
              onChange={(e) => {
                setPassword(e.target.value);
              }}
            />
          </Form.Group>
        </Row>

        <br></br>
        <Button variant="primary" onClick={handleSubmission}>
          Submit
        </Button>
      </Form>
    </div>
  );
};

export default SignUp;
