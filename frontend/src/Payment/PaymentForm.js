import React, { useEffect } from "react";
import { userContext } from "../App";
import { useContext, useState } from "react";
import { Form, Row, Col } from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Axios from "../axios";
import ENDPOINTS from "../endpoints";

const PaymentForm = ({ checkForPayment }) => {
  const { user, auction } = useContext(userContext);

  const [cardNum, setCardNum] = useState(0);
  const [cardholderName, setCardholderName] = useState("");
  const [expiryDate, setExpiryDate] = useState();
  const [cvv, setCvv] = useState(0);
  const [totalAmount, setTotalAmount] = useState(auction.currentPrice);
  const [checked, setChecked] = useState(false);
  const submitPayment = () => {
    const req = {
      cardNum: cardNum,
      cardholderName: cardholderName,
      expiryDate: expiryDate,
      cvv: cvv,
      amount: totalAmount,
      userID: user.id,
      catalogItemID: auction.itemID,
    };

    Axios.post(ENDPOINTS.PAYMENT.ADDPAYMENT, req)
      .then((res) => {
        console.log("Payment sent", res.data);
        checkForPayment();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //Adjust total price according to the checked option.
  useEffect(() => {
    if (checked === true) {
      setTotalAmount(auction.currentPrice + auction.expeditedShippingCost);
      console.log(auction.currentPrice + auction.expeditedShippingCost);
    } else {
      setTotalAmount(auction.currentPrice);
      console.log(auction.currentPrice);
    }
  }, [checked]);

  return (
    <div>
      <Form>
        <Form.Label>Total Amount: {totalAmount}</Form.Label>
        <Form.Check
          label={
            "+" + auction.expeditedShippingCost + " for expedited shipping"
          }
          value={checked}
          onClick={(e) => {
            console.log(!checked);
            setChecked(!checked);
          }}
        />
      </Form>
      <Form>
        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Card Number</Form.Label>
            <Col>
              <Form.Control
                type="text"
                placeholder=""
                value={cardNum}
                onChange={(e) => {
                  setCardNum(e.target.value);
                }}
              />
            </Col>
          </Form.Group>
        </Row>
        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Card Holder Name</Form.Label>
            <Col>
              <Form.Control
                type="text"
                placeholder=""
                value={cardholderName}
                onChange={(e) => {
                  setCardholderName(e.target.value);
                }}
              />
            </Col>
          </Form.Group>
        </Row>
        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>CVV</Form.Label>
            <Col>
              <Form.Control
                type="int"
                placeholder=""
                value={cvv}
                onChange={(e) => {
                  setCvv(e.target.value);
                }}
              />
            </Col>
          </Form.Group>
        </Row>
        <Row>
          <Form.Group as={Col} className="mb-3">
            <Form.Label>Expiry Date</Form.Label>
            <Col>
              <Form.Control
                type="date"
                placeholder=""
                value={expiryDate}
                onChange={(e) => {
                  setExpiryDate(e.target.value);
                }}
              />
            </Col>
          </Form.Group>
        </Row>
        <br></br>
        <Button variant="primary" onClick={submitPayment}>
          Submit
        </Button>
      </Form>
    </div>
  );
};

export default PaymentForm;
