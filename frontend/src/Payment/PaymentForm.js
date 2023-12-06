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
  const [totalAmount, setTotalAmount] = useState();
  const [checked, setChecked] = useState(false);

  useEffect(() => {
    if (auction !== null && auction.auctionType === "Forward") {
      setTotalAmount(auction.currentPrice);
    } else if (auction !== null && auction.auctionType === "Dutch") {
      setTotalAmount(auction.price);
    }
  }, [auction]);

  const submitPayment = () => {
    const req =
      auction.auctionType === "Forward"
        ? {
            cardNum: cardNum,
            cardholderName: cardholderName,
            expiryDate: expiryDate,
            cvv: cvv,
            amount: totalAmount,
            userID: user.id,
            catalogItemID: auction.itemID,
            catalogItemType: "Forward",
          }
        : {
            cardNum: cardNum,
            cardholderName: cardholderName,
            expiryDate: expiryDate,
            cvv: cvv,
            amount: totalAmount,
            userID: user.id,
            catalogItemID: auction.itemID,
            catalogItemType: "Dutch",
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
    if (checked === true && auction.auctionType === "Forward") {
      setTotalAmount(auction.currentPrice + auction.expeditedShippingCost);
    } else if (checked === true && auction.auctionType === "Dutch") {
      setTotalAmount(auction.price + auction.expeditedShippingCost);
    } else if (checked === false && auction.auctionType === "Dutch") {
      setTotalAmount(auction.price);
    } else if (checked === false && auction.auctionType === "Forward") {
      setTotalAmount(auction.currentPrice);
    }
    console.log("Checked has been changed to a value of: ", checked);
  }, [checked]);

  return (
    <div>
      <Form>
        <Form.Label>Total Amount: {totalAmount}</Form.Label>
        <Form.Check
          label={
            "+" + auction.expeditedShippingCost + " for expedited shipping"
          }
          onChange={(e) => {
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
                type="number"
                min="0"
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
