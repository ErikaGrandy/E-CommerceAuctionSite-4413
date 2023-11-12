const ENDPOINTS = {
  CATALOGUE: {
    GETALLITEMS: "Catalog/getAll",
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
};

export default ENDPOINTS;
