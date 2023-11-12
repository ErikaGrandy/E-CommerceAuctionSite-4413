import React, { useContext, useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import Table from "react-bootstrap/Table";
import Form from "react-bootstrap/Form";
import CatItemMenu from "./CatItemMenu";
import Axios from "../axios";
import ENDPOINTS from "../endpoints";
import AddForwardCatMenu from "./components/AddForwardCatMenu";
import { userContext } from "../App";
import { ZonedDateTime } from "@js-joda/core";

export const CatMenu = () => {
  const headers = [
    "Item ID",
    "Status",
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

  const { user, setUser, auction, setAuction } = useContext(userContext);

  const [selected, setSelected] = useState("");

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

  useEffect(() => {
    // console.log(forwardData);
  }, [forwardData]);

  const handleSubmission = () => {
    // console.log(JSON.parse(selected));
    setAuction(JSON.parse(selected));
  };

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
                const status = ZonedDateTime.now().isBefore(
                  ZonedDateTime.parse(row.endTime)
                );

                return (
                  <tr>
                    <th>{row.itemID}</th>
                    <th>{status ? "Active" : "Ended"}</th>
                    <th>{row.name}</th>
                    <th>{row.currentPrice}</th>
                    <th>{row.auctionType}</th>
                    <th>{row.highestBidderID}</th>
                    <th>{row.endTime}</th>
                    <th>{row.shippingTime}</th>
                    <th>
                      <Form.Check
                        type={"radio"}
                        name="group1"
                        label={i}
                        value={JSON.stringify(row)}
                        onClick={(e) => {
                          // console.log(e.target.value);
                          setSelected(e.target.value);
                        }}
                      />
                    </th>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        </div>

        <Button variant="primary" onClick={handleSubmission}>
          Bid
        </Button>
      </Form>
    </div>
  );
};
