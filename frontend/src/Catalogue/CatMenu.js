import React from "react";
import { Button } from "react-bootstrap";
import Table from "react-bootstrap/Table";
import Form from "react-bootstrap/Form";
import CatItemMenu from "./CatItemMenu";

export const CatMenu = () => {
  const headers = [
    "Item ID",
    "Name",
    "Current Price",
    "Auction Type",
    "Highest Bidder",
    "Remaining Time",
    "Shipping Time",
    "Select",
  ];

  const data = [
    {
      name: "PS4",
      currentPrice: 1000,
      auctionType: "Forward",
      highestBidder: "Walid",
      endTime: "3:00pm",
      shippingTime: "4:00pm",
      description: "A video game console.",
    },
    {
      name: "PS5",
      currentPrice: 1234,
      auctionType: "Dutch",
      highestBidder: "",
      endTime: "3:10pm",
      shippingTime: "4:00pm",
      description: "A video game console.",
    },
    {
      name: "Laptop",
      currentPrice: 699,
      auctionType: "Forward",
      highestBidder: "",
      endTime: "3:01pm",
      shippingTime: "4:00pm",
      description: "A calculator",
    },
  ];
  return (
    <div>
      <h1>Catalogue</h1>
      <br></br>
      <CatItemMenu />
      <Form>
        <Table striped>
          <thead>
            <tr>
              {headers.map((header) => {
                return <th>{header}</th>;
              })}
            </tr>
          </thead>
          <tbody>
            {data.map((row, i) => {
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
