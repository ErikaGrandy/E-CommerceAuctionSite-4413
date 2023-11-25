import React, { useContext, useEffect, useState } from "react";
import { userContext } from "../App";
import PaymentForm from "./PaymentForm";
import Axios from "../axios";
import ENDPOINTS from "../endpoints";

const Payment = ({ updateAuction }) => {
  const { user, auction } = useContext(userContext);

  const [paid, setPaid] = useState(false);
  const [paymentReceipt, setPaymentReceipt] = useState();

  const checkForPayment = () => {
    const params =
      auction.auctionType === "Forward"
        ? { userID: user.id, itemID: auction.itemID }
        : { userID: user.id, itemID: auction.itemID + 100 };
    Axios.get(ENDPOINTS.PAYMENT.CHECKPAYMENT, {
      params: params,
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
          <ul>
            <li>UserID: {paymentReceipt.userID}</li>
            <li>CatalogItemID: {paymentReceipt.catalogItemID}</li>
            <li>Amount Paid: {paymentReceipt.amount}</li>
            <li>
              Card Details: {paymentReceipt.cardholderName} |{" "}
              {paymentReceipt.cardNum} | {paymentReceipt.expiryDate} |{" "}
              {paymentReceipt.cvv}
            </li>
          </ul>
          {/* {JSON.stringify(paymentReceipt)} */}
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
    updateAuction();
    checkForPayment();
  }, []);

  return (
    <div>
      <h1>Payment Info</h1>
      {auction.auctionType === "Forward" &&
        auction.highestBidderID === user.id &&
        ((!paid && <PaymentForm checkForPayment={checkForPayment} />) ||
          (paid && PaymentAndShipmentReceipt()))}

      {auction.auctionType === "Dutch" &&
        auction.buyerID === user.id &&
        ((!paid && <PaymentForm checkForPayment={checkForPayment} />) ||
          (paid && PaymentAndShipmentReceipt()))}
    </div>
  );
};

export default Payment;
