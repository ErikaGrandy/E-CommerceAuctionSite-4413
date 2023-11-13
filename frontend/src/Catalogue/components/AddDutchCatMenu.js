import React, { useContext } from "react";

import { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import { Row, Col, Alert } from "react-bootstrap";

import ZonedDateTime from "../../joda";
import Axios from "../../axios";
import ENDPOINTS from "../../endpoints";
import { userContext } from "../../App";

//To DO
// Clear out the form content after submission

function AddDutchCatMenu({ refresh, toggleRefresh }) {
  const { user } = useContext(userContext);
  const [show, setShow] = useState(false);

  const [showError, setShowError] = useState(false);
  const [showSuccess, setShowSucess] = useState(false);

  const auctionType = "Dutch";

  const handleClose = () => {
    setShow(false);
    toggleRefresh(!refresh);
  };

  const handleSubmit = () => {
    console.log(getFormJSON());

    if (
      name == "" ||
      description == "" ||
      currentPrice == 0 ||
      decrementStep == 0 ||
      lowestPrice == 0 ||
      shippingCost == 0 ||
      duration == 0 ||
      shippingDuration == 0
    ) {
      setShowError(true);
    } else {
      setShowError(false);

      Axios.post(ENDPOINTS.DUTCHITEM.ADDITEM, getFormJSON())
        .then((res) => {
          if (res.status == 200) {
            setShowSucess(true);
          } else {
            setShowError(true);
          }
        })
        .catch((err) => {
          console.log(err);
        });

      toggleRefresh(!refresh);
    }
  };

  const handleShow = () => setShow(true);

  const [name, setName] = useState("");
  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const [description, setDescription] = useState("");
  const handleDescriptionChange = (e) => {
    setDescription(e.target.value);
  };

  const [currentPrice, setCurrentPrice] = useState(0);
  const handleCurrentPriceChange = (e) => {
    setCurrentPrice(e.target.value);
  };

  const [decrementStep, setDecrementStep] = useState(0);
  const handleDecrementStepChange = (e) => {
    setDecrementStep(e.target.value);
  };

  const [lowestPrice, setLowestPrice] = useState(0);
  const handleLowestPriceChange = (e) => {
    setLowestPrice(e.target.value);
  };

  const [shippingCost, setShippingCost] = useState(0);
  const handleShippingCostChange = (e) => {
    setShippingCost(e.target.value);
  };

  const [duration, setDuration] = useState(0);
  const handleDurationChange = (e) => {
    setDuration(e.target.value);
  };

  const [shippingDuration, setShippingDuration] = useState(0);
  const handleShippingDurationChange = (e) => {
    setShippingDuration(e.target.value);
  };

  const getFormJSON = () => {
    const endTime = ZonedDateTime.now().plusMinutes(duration).toString();
    return {
      name: name,
      price: currentPrice,
      buyerID: 0,
      decrementStep: decrementStep,
      lowestPrice: lowestPrice,
      auctionType: auctionType,
      endTime: endTime,
      shippingTime: shippingDuration,
      description: description,
      expeditedShippingCost: shippingCost,
      available: true,
      sellerID: user.id,
    };
  };

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Add Dutch Auction
      </Button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>New Catalog Item</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {showError && (
            <Alert variant={"danger"}>
              Error adding Item. Please make sure all fields are filled out with
              none left to be empty or 0.
            </Alert>
          )}
          {showSuccess && (
            <Alert variant={"success"}>
              Succesfully added the auction item.
            </Alert>
          )}
          <Form>
            <Row className="mb-2">
              <Form.Label as={Col}>Name</Form.Label>
              <Col>
                <Form.Control
                  type="text"
                  placeholder="Bird"
                  value={name}
                  onChange={handleNameChange}
                />
              </Col>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Description</Form.Label>
              <Col>
                <Form.Control
                  type="text"
                  placeholder="It flies"
                  value={description}
                  onChange={handleDescriptionChange}
                />
              </Col>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Current Price</Form.Label>
              <Col>
                <Form.Control
                  type="number"
                  placeholder="200"
                  value={currentPrice}
                  onChange={handleCurrentPriceChange}
                />
              </Col>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Decrement Step (Price Deduction)</Form.Label>
              <Col>
                <Form.Control
                  type="number"
                  placeholder="200"
                  value={decrementStep}
                  onChange={handleDecrementStepChange}
                />
              </Col>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>
                Base Price (Lowest Price achievable after x Decrements)
              </Form.Label>
              <Col>
                <Form.Control
                  type="number"
                  placeholder="200"
                  value={lowestPrice}
                  onChange={handleLowestPriceChange}
                />
              </Col>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Expedited Shipping Cost</Form.Label>
              <Col>
                <Form.Control
                  type="number"
                  placeholder="12"
                  value={shippingCost}
                  onChange={handleShippingCostChange}
                />
              </Col>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Duration (in minutes)</Form.Label>
              <Col>
                <Form.Control
                  type="number"
                  placeholder="5"
                  value={duration}
                  onChange={handleDurationChange}
                />
              </Col>
            </Row>
            <Row className="mb-2">
              <Form.Label as={Col}>Shipping Time (In days)</Form.Label>
              <Col>
                <Form.Control
                  type="number"
                  placeholder="3"
                  value={shippingDuration}
                  onChange={handleShippingDurationChange}
                />
              </Col>
            </Row>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleSubmit}>
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

export default AddDutchCatMenu;
