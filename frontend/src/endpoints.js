const ENDPOINTS = {
  CATALOGUE: {
    GETALLITEMS: "Catalog/getAll",
    GETITEM: "/Catalog/get",
    ADDITEM: "Catalog/add",
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
