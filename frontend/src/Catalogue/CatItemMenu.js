import React from "react";
import axios from "axios"
import { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import { Row, Col } from "react-bootstrap";

function CatItemMenu() {
  const [formValue, setformValue] = useState({
	name: '',
    currentPrice: '',
    auctionType: '',
    duration: '',
    shippingDuration: '',
  });
  const handleChange = (event) => {
	  setformValue({
			 ...formValue, 
			 [event.target.name]:event.target.value // identifies the name, and its value from the form
		  });
  }
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
 

  const handleSubmit = event => {
	  // maybe first validate entries and data from the formvalue
  axios.post('http://localhost:8080/BidSphere/Catalog/add', { formValue })
      .then(res=>{
        console.log(res);
        console.log(res.data);
        window.location = "/retrieve" //This line of code will redirect you once the submission is succeed
      })
    
  };
 

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Add Catalogue Item
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>New Catalog Item</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Row className="mb-2">
              <Form.Label as={Col}>Name</Form.Label> 
              <Form.Control  type="text" name="name" defaultValue={formValue.name} onChange={handleChange} />
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Current Price</Form.Label>
              <Form.Control type="text" name="currentPrice" defaultValue={formValue.currentPrice} onChange={handleChange}/>
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
              <Form.Control  type="text" name="duration" defaultValue={formValue.duration} onChange={handleChange}/>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Shipping Duration</Form.Label>
              <Form.Control  type="text" name="shippingDuration" defaultValue={formValue.shippingDuration} onChange={handleChange}/>
            </Row>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancel
          </Button>
          <Button variant="primary" onClick={handleSubmit}>
            Submit
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default CatItemMenu;
