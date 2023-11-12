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

function ForwardDash() {
  const data = ["Bid 1", "Bid 2", "Bid 3"];

  const { user, auction } = useContext(userContext);

  const [timer, setTimer] = useState(false);
  const [timerAmount, setTimerAmount] = useState();
  const [pastBids, setPastBids] = useState();
  const [highest, setHighest] = useState(["", 0]);
  const [startBidding, setStartBidding] = useState(false);
  const [bidAmount, setBidAmount] = useState(0);
  //Determines whether the dash board can be viewed
  useEffect(() => {
    if (
      auction !== null &&
      auction.name !== "" &&
      user !== null &&
      user.userName !== "" &&
      ZonedDateTime.now().isBefore(ZonedDateTime.parse(auction.endTime))
    ) {
      setStartBidding(true);
    } else {
      setStartBidding(false);
    }
  }, []);

  const refreshBids = () => {
    Axios.get(ENDPOINTS.BID.GETBIDFROMCATID, {
      params: {
        id: auction.itemID,
      },
    })
      .then((res) => {
        setPastBids(res.data);
      })
      .catch((err) => {
        console.log(err);
      });

    Axios.get(ENDPOINTS.BID.GETSTATUS, {
      params: {
        id: auction.itemID,
      },
    })
      .then((res) => {
        setHighest([res.data.highestBidderID, res.data.highestBid]);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //Preps dashboard props such as timer and past bids history
  useEffect(() => {
    if (startBidding === true) {
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
      refreshBids();
    }
  }, [startBidding]);

  const submitBid = (bid) => {
    Axios.post(ENDPOINTS.BID.SENDBID, {
      userID: user.id,
      amount: bidAmount,
      catalogItemID: auction.itemID,
    })
      .then((res) => {
        refreshBids();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div>
      {startBidding && (
        <>
          <h1> Bid Dashboard</h1>
          <Row className="mb-3">
            {timer && <Timer expiryTimestamp={timerAmount} />}
          </Row>
          <Row>
            <Row>
              <h2>Auction Item: {auction.name}</h2>
            </Row>
            <Row>
              <h5>Description: {auction.description}</h5>
            </Row>
          </Row>
          <Row className="center">
            <Col className="border border-3 p-3">
              <Row>
                <h3>
                  Highest Bidder ID:{" "}
                  {highest[0] !== "" ? highest[0] : auction.highestBidderID}
                </h3>
              </Row>
              <Row>
                <h3>
                  Highest Amount:{" "}
                  {highest[1] !== 0 ? highest[1] : auction.currentPrice}
                </h3>
              </Row>
            </Col>
            <Col className="border border-3 p-3">
              <Row className="mb-3">
                <Form>
                  <Row>
                    <Form.Label as={Col}>
                      <h3>Bid Amount</h3>
                    </Form.Label>
                    <Col>
                      <Form.Control
                        type="number"
                        value={bidAmount}
                        onChange={(e) => {
                          setBidAmount(e.target.value);
                        }}
                      />
                    </Col>
                  </Row>
                  <Button variant="primary" onClick={submitBid}>
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
                {pastBids !== undefined &&
                  pastBids.map((row) => {
                    return (
                      <ListGroup.Item>
                        User {row.userID} | {row.amount}
                      </ListGroup.Item>
                    );
                  })}
              </ListGroup>
            </Col>
          </Row>
        </>
      )}
    </div>
  );
}

export default ForwardDash;
