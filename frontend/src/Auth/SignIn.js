import React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Col, Row } from "react-bootstrap";
import Alert from "react-bootstrap/Alert";

const SignIn = () => {
  return (
    <div>
      <h1>Sign Up</h1>
      <br />
      <Form>
        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Username</Form.Label>
            <Form.Control type="text" placeholder="" />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>First Name</Form.Label>
            <Form.Control type="text" placeholder="" />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>Last Name</Form.Label>
            <Form.Control type="text" placeholder="" />
          </Form.Group>
        </Row>

        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Street Name</Form.Label>
            <Form.Control type="text" placeholder="" />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>Street Number</Form.Label>
            <Form.Control type="text" placeholder="" />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>City</Form.Label>
            <Form.Control type="text" placeholder="" />
          </Form.Group>
        </Row>

        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>City</Form.Label>
            <Form.Control type="text" placeholder="" />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>Country</Form.Label>
            <Form.Control type="text" placeholder="" />
          </Form.Group>

          <Form.Group as={Col} className="mb-3">
            <Form.Label>Postal Code</Form.Label>
            <Form.Control type="text" placeholder="" />
          </Form.Group>
        </Row>

        <br></br>
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </div>
  );
};

export default SignIn;
