import axios from "axios";

const Axios = axios.create({
  baseURL: "http://localhost:8081/",
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Content-Type": "application/json",
    "Access-Control-Allow-Methods": "GET;POST",
  },
});

export default Axios;
