import React from "react";
import { Row, Col } from "react-bootstrap";
import { Form } from "react-bootstrap";

import { Button, ListGroup } from "react-bootstrap";

function Dash() {
  const data = ["Bid 1", "Bid 2", "Bid 3"];

  return (
    <div>
      <h1> Bid Dashboard</h1>
      <Row className="center">
        <Col className="border border-3 p-3">
          <Row>
            <h2>Name</h2>
          </Row>
          <Row>
            <h5>description</h5>
          </Row>
          <br />
          <Row>
            <h3>Highest Bidder: </h3>
          </Row>
          <Row>
            <h3>Highest Amount: </h3>
          </Row>
        </Col>
        <Col className="border border-3 p-3">
          <Row className="mb-3">
            <h3>Remaining Time: 30s</h3>
          </Row>
          <Row className="mb-3">
            <Form>
              <Row>
                <Form.Label as={Col}>
                  <h3>Bid Amount</h3>
                </Form.Label>
                <Form.Control as={Col} type="text" />
              </Row>
              <Button
                variant="primary"
                type="submit"
                onClick={(e) => {
                  console.log(e);
                }}
              >
                Bid
              </Button>
            </Form>
          </Row>
        </Col>
      </Row>
      <br />
      <br />

      <Row>
        <Col>
          <h4>Submitted Bids</h4>
          <ListGroup>
            {data.map((row) => {
              return <ListGroup.Item>{row}</ListGroup.Item>;
            })}
          </ListGroup>
        </Col>
      </Row>
    </div>
  );
}

export default Dash;
