import React, { useContext } from "react";
import ForwardDash from "./ForwardDash";
import { userContext } from "../App";
import DutchDash from "./DutchDash";
const MainDash = () => {
  const { auction } = useContext(userContext);

  return (
    <div>
      {" "}
      {auction !== null &&
        auction.name !== "" &&
        auction.auctionType === "Forward" && <ForwardDash />}
      {auction !== null &&
        auction.name !== "" &&
        auction.auctionType === "Dutch" && <DutchDash />}
    </div>
  );
};

export default MainDash;
