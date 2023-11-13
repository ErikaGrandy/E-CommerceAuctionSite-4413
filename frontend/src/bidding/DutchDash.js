import React, { useContext, useEffect } from "react";
import { Row, Col } from "react-bootstrap";
import { Form } from "react-bootstrap";

import { Button, ListGroup } from "react-bootstrap";
import { userContext } from "../App";
import { useState } from "react";
import Axios from "../axios";
import { ChronoUnit, ZonedDateTime } from "@js-joda/core";
import { useTimer } from "react-timer-hook";
import Timer from "./Timer";
import ENDPOINTS from "../endpoints";
import Payment from "../Payment/Payment";

function DutchDash() {
  const { user, auction, setAuction } = useContext(userContext);

  const [timer, setTimer] = useState(false);
  const [timerAmount, setTimerAmount] = useState();
  const [biddingActive, setBiddingActive] = useState(false);

  const refreshPrice = () => {
    Axios.get(ENDPOINTS.DUTCHITEM.GETITEM, {
      params: {
        id: auction.itemID,
      },
    })
      .then((res) => {
        setAuction(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const decrementPrice = () => {
    const params = {
      userID: user.id,
      itemID: auction.itemID,
    };
    console.log(params);
    Axios.put(ENDPOINTS.DUTCHITEM.DECREMENTPRICE, null, {
      params: params,
    })
      .then(() => {
        refreshPrice();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const buyItem = () => {
    const req = {
      userID: user.id,
      catalogItemID: auction.itemID,
    };

    Axios.post(ENDPOINTS.DUTCHBID.BUYITEM, req)
      .then(() => {
        setBiddingActive(false);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //Controls bid dashboard visibility
  useEffect(() => {
    if (
      auction !== null &&
      auction.name !== "" &&
      user !== null &&
      user.userName !== "" &&
      ZonedDateTime.now().isBefore(ZonedDateTime.parse(auction.endTime))
    ) {
      setBiddingActive(true);
    } else {
      setBiddingActive(false);
    }
    refreshPrice();
  }, []);

  //Preps dashboard timer
  useEffect(() => {
    if (biddingActive === true) {
      console.log(
        "Duration of the auction: ",
        ZonedDateTime.now().until(
          ZonedDateTime.parse(auction.endTime),
          ChronoUnit.SECONDS
        )
      );
      const time = new Date();
      time.setSeconds(
        time.getSeconds() +
          ZonedDateTime.now().until(
            ZonedDateTime.parse(auction.endTime),
            ChronoUnit.SECONDS
          )
      );
      setTimerAmount(time);
      setTimer(true);
      refreshPrice();
    }
  }, [biddingActive]);

  useEffect(() => {
    if (auction !== null && auction.available === false) {
      setBiddingActive(false);
    }
  }, [auction]);

  return (
    <div>
      {biddingActive && (
        <>
          <h1> Bid Dashboard</h1>
          <Row className="mb-3">
            {timer && (
              <Timer expiryTimestamp={timerAmount} trigger={setBiddingActive} />
            )}
          </Row>

          <Row>
            <p className="text-center fs-3">Auction Item: {auction.name}</p>

            <p className="text-center fs-3">
              Description: {auction.description}
            </p>

            <p className="text-center fs-3">Current Price: {auction.price}</p>
            <Row>
              {auction !== null &&
                user !== null &&
                auction.sellerID !== user.id && (
                  <Col align="center">
                    <Button onClick={buyItem}>Buy Item</Button>
                  </Col>
                )}

              {auction !== null &&
                user !== null &&
                auction.sellerID !== user.id && (
                  <Col align="center">
                    <Button
                      onClick={() => {
                        refreshPrice();
                      }}
                    >
                      Check for price update
                    </Button>
                  </Col>
                )}

              {auction !== null &&
                user !== null &&
                auction.sellerID === user.id && (
                  <Col align="center">
                    <Button onClick={decrementPrice}>Decrement Price</Button>
                  </Col>
                )}
            </Row>
          </Row>
          <br />
          <br />
        </>
      )}

      {!biddingActive && auction.buyerID === user.id && (
        <Payment updateAuction={refreshPrice} />
      )}

      {!biddingActive && (
        <Row>
          <Col>
            <h4>Auction Summary</h4>
            <h5>
              Price: {auction.price} | Sold to User with ID: {auction.buyerID}
            </h5>
            <br></br>
          </Col>
        </Row>
      )}
    </div>
  );
}

export default DutchDash;
