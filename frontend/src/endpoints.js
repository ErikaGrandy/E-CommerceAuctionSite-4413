const basePayment = "http://localhost:8083";
const baseUser = "http://localhost:8082";
const baseForward = "http://localhost:8081";
const baseDutch = "http://localhost:8085";

const ENDPOINTS = {
  CATALOGUE: {
    GETALLITEMS: baseForward + "/Catalog/getAll",
    GETITEM: baseForward + "/Catalog/get",
    ADDITEM: baseForward + "/Catalog/add",
  },
  DUTCHITEM: {
    GETALLITEMS: baseDutch + "/DutchCatalog/getAll",
    ADDITEM: baseDutch + "/DutchCatalog/add",
    GETITEM: baseDutch + "/DutchCatalog/get",
    DECREMENTPRICE: baseDutch + "/DutchCatalog/updatePrice",
  },
  DUTCHBID: {
    BUYITEM: baseDutch + "/DutchBid/buy",
  },
  USER: {
    VERIFY: baseUser + "/Users/verifyUser",
    CREATE: baseUser + "/Users/createUser",
    FORGOTPASSWORD: baseUser + "/Users/forgotPassword",
  },
  BID: {
    GETBIDFROMCATID: baseForward + "/Bid/getByCatalogue",
    SENDBID: baseForward + "/forwardAuction/send",
    GETSTATUS: baseForward + "/forwardAuction/getStatus",
  },
  PAYMENT: {
    ADDPAYMENT: basePayment + "/Payment/Add",
    CHECKPAYMENT: basePayment + "Payment/getbyUserIDItemID",
  },
};

export default ENDPOINTS;
