const ENDPOINTS = {
  CATALOGUE: {
    GETALLITEMS: "Catalog/getAll",
    GETITEM: "/Catalog/get",
    ADDITEM: "Catalog/add",
  },
  DUTCHITEM: {
    GETALLITEMS: "/DutchCatalog/getAll",
    ADDITEM: "/DutchCatalog/add",
    GETITEM: "/DutchCatalog/get",
    DECREMENTPRICE: "/DutchCatalog/updatePrice",
  },
  DUTCHBID: {
    BUYITEM: "/DutchBid/buy",
  },
  USER: {
    VERIFY: "/Users/verifyUser",
    CREATE: "/Users/createUser",
    FORGOTPASSWORD: "Users/forgotPassword",
  },
  BID: {
    GETBIDFROMCATID: "/Bid/getByCatalogue",
    SENDBID: "/forwardAuction/send",
    GETSTATUS: "/forwardAuction/getStatus",
  },
  PAYMENT: {
    ADDPAYMENT: "/Payment/Add",
    CHECKPAYMENT: "Payment/getbyUserIDItemID",
  },
};

export default ENDPOINTS;
