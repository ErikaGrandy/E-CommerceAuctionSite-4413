import React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Col, Row } from "react-bootstrap";
import Alert from "react-bootstrap/Alert";
import { useState } from "react";
import Axios from "../axios";
import ENDPOINTS from "../endpoints";

const ForgotPassword = () => {
  const [showError, setShowError] = useState(false);
  const [showSuccess, setShowSuccess] = useState(false);

  const [username, setUsername] = useState("");
  const [streetNumber, setStreetNumber] = useState(0);
  const [password, setPassword] = useState("");

  const handleSubmission = () => {
    const requestData = {
      username: username,
      streetNumber: streetNumber,
      password: password,
    };

    if (username === "" || password === "") {
      setShowError(true);
      setShowSuccess(false);
    } else {
      Axios.get(ENDPOINTS.USER.FORGOTPASSWORD, {
        params: {
          username: username,
          password: password,
          streetNumber: streetNumber,
        },
      })
        .then((res) => {
          setShowSuccess(true);
          setShowError(false);

          console.log("Server Response: ", res.data);
        })
        .catch((err) => {
          console.log(err);
          setShowError(true);
          setShowSuccess(false);
        });
    }
  };

  return (
    <div>
      <h1>Password Reset</h1>
      <br />
      {showError && (
        <Alert variant={"danger"}>
          Wrong user info. Either the username or the street number are
          incorrect. Or you could have left username or password fields empty.
        </Alert>
      )}
      {showSuccess && <Alert variant={"success"}>Successful login</Alert>}
      <Form>
        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Username</Form.Label>
            <Form.Control
              type="text"
              placeholder="walido123"
              value={username}
              onChange={(e) => {
                setUsername(e.target.value);
              }}
            />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>Street Number</Form.Label>
            <Form.Control
              type="number"
              min="0"
              placeholder=""
              value={streetNumber}
              onChange={(e) => {
                setStreetNumber(e.target.value);
              }}
            />
          </Form.Group>
        </Row>
        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="verySecretPassword"
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

export default ForgotPassword;
