import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import Table from "react-bootstrap/Table";
import Form from "react-bootstrap/Form";
import CatItemMenu from "./CatItemMenu";
import Axios from "../axios";
import ENDPOINTS from "../endpoints";
import AddForwardCatMenu from "./components/AddForwardCatMenu";

export const CatMenu = () => {
  const headers = [
    "Item ID",
    "Name",
    "Current Price",
    "Auction Type",
    "Highest Bidder",
    "End Time",
    "Shipping Time",
    "Select",
  ];

  const [forwardData, setForwardData] = useState([]);

  const [refresh, toggleRefresh] = useState(false);

  useEffect(() => {
    Axios.get(ENDPOINTS.CATALOGUE.GETALLITEMS)
      .then((res) => {
        setForwardData(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffect(() => {
    Axios.get(ENDPOINTS.CATALOGUE.GETALLITEMS)
      .then((res) => {
        setForwardData(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [refresh]);

  const data = [];
  return (
    <div>
      <h1>Catalogue</h1>
      <br></br>

      <Form>
        <div>
          <h1>Forwards Auctions</h1>
          <AddForwardCatMenu refresh={refresh} toggleRefresh={toggleRefresh} />
          <Table striped>
            <thead>
              <tr>
                {headers.map((header) => {
                  return <th>{header}</th>;
                })}
              </tr>
            </thead>
            <tbody>
              {forwardData.map((row, i) => {
                return (
                  <tr>
                    <th>n/a</th>
                    <th>{row.name}</th>
                    <th>{row.currentPrice}</th>
                    <th>{row.auctionType}</th>
                    <th>{row.highestBidder}</th>
                    <th>{row.endTime}</th>
                    <th>{row.shippingTime}</th>
                    <th>
                      <Form.Check type={"radio"} name="group1" label={i} />
                    </th>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        </div>

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
    </div>
  );
};
