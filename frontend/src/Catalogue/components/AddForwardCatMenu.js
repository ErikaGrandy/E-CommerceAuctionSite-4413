import React from "react";

import { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import { Row, Col } from "react-bootstrap";

function DutchAuctionheaders() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Add Forward Auction
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>New Catalog Item</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Row className="mb-2">
              <Form.Label as={Col}>Name</Form.Label>
              <Col>
                <Form.Control type="text" />
              </Col>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Current Price</Form.Label>
              <Form.Control as={Col} type="text" />
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Auction Type</Form.Label>
              <Col>
                <Form.Select size="sm">
                  <option value="forward">Forward Bidding</option>
                  <option value="dutch">Dutch Bidding</option>
                </Form.Select>
              </Col>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Duration</Form.Label>
              <Form.Control as={Col} type="text" />
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Shipping Duration</Form.Label>
              <Form.Control as={Col} type="text" />
            </Row>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancel
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Submit
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default DutchAuctionheaders;
