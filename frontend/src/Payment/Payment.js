import React, { useContext, useEffect, useState } from "react";
import { userContext } from "../App";
import PaymentForm from "./PaymentForm";
import Axios from "../axios";
import ENDPOINTS from "../endpoints";

const Payment = ({ pastBids }) => {
  const { user, auction } = useContext(userContext);

  const [paid, setPaid] = useState(false);
  const [paymentReceipt, setPaymentReceipt] = useState();

  const checkForPayment = () => {
    Axios.get(ENDPOINTS.PAYMENT.CHECKPAYMENT, {
      params: { userID: user.id, itemID: auction.itemID },
    })
      .then((res) => {
        setPaid(true);
        setPaymentReceipt(res.data);
      })
      .catch((err) => {
        console.log(err);
        setPaid(false);
      });
  };

  const PaymentAndShipmentReceipt = () => {
    return (
      <>
        <div>
          <h5>Payment Receipt</h5>
          {JSON.stringify(paymentReceipt)}
        </div>
        <div>
          <h5>Shipment Receipt</h5>
          <p>Item will be shipped in {auction.shippingTime} days</p>
        </div>
      </>
    );
  };

  //Check if item has been paid for
  //True -> Show receipt
  //False -> Prompt to pay
  useEffect(() => {
    checkForPayment();
  }, []);

  return (
    <div>
      <h1>Payment Info</h1>
      {auction.highestBidderID === user.id &&
        ((!paid && <PaymentForm checkForPayment={checkForPayment} />) ||
          (paid && PaymentAndShipmentReceipt()))}
    </div>
  );
};

export default Payment;
