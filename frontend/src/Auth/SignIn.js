import React, { useContext } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Col, Row } from "react-bootstrap";
import Alert from "react-bootstrap/Alert";
import { useState } from "react";
import Axios from "../axios";
import ENDPOINTS from "../endpoints";
import { userContext } from "../App";

const SignIn = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const [showError, setShowError] = useState(false);
  const [showSuccess, setShowSuccess] = useState(false);
  const { user, setUser } = useContext(userContext);

  const HandleSubmission = () => {
    Axios.get(ENDPOINTS.USER.VERIFY, {
      params: {
        username: username,
        password: password,
      },
    })
      .then((res) => {
        setShowError(false);
        setShowSuccess(true);
        setUser(res.data);
      })
      .catch((err) => {
        console.log(err);
        setShowError(true);
        setShowSuccess(false);
      });
  };
  return (
    <div>
      <h1>Sign In</h1>
      <br />
      {showError && <Alert variant={"danger"}>Wrong user info</Alert>}
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
        <Button variant="primary" onClick={HandleSubmission}>
          Submit
        </Button>
      </Form>
    </div>
  );
};

export default SignIn;
