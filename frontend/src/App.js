import { Container } from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import SignUp from "./Auth/SignUp";
import { CatMenu } from "./Catalogue/CatMenu";
import ForwardDash from "./bidding/ForwardDash";
import SignIn from "./Auth/SignIn";
import ForgotPassword from "./Auth/ForgotPassword";
import UserAuthPage from "./Auth/UserAuthPage";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { createContext, useEffect, useState } from "react";
import MainDash from "./bidding/MainDash";

export const userContext = createContext();

function App() {
  const [user, setUser] = useState({
    id: 0,
    userName: "",
    password: "",
    firstName: "",
    lastName: "",
    streetNumber: 0,
    streetAddress: "",
    city: "",
    province: "",
    postalCode: "",
    country: "",
  });

  const [auction, setAuction] = useState({
    itemID: 0,
    name: "",
    currentPrice: 0,
    auctionType: "",
    highestBidderID: 0,
    endTime: "",
    shippingTime: 0,
    description: "",
    expeditedShippingCost: 0,
  });

  const [showSignIn, setShowSignIn] = useState(true);
  const [showSignUp, setShowSignUp] = useState(false);
  const [showResetPassword, setShowResetPassword] = useState(false);
  const [showCatalogues, setShowCatalogues] = useState(false);
  const [showMainDash, setShowMainDash] = useState(false);

  const SignInView = () => {
    setShowSignIn(true);
    setShowSignUp(false);
    setShowResetPassword(false);
    setShowCatalogues(false);
    setShowMainDash(false);
  };
  const SignUpView = () => {
    setShowSignIn(false);
    setShowSignUp(true);
    setShowResetPassword(false);
    setShowCatalogues(false);
    setShowMainDash(false);
  };
  const resetPasswordView = () => {
    setShowSignIn(false);
    setShowSignUp(false);
    setShowResetPassword(true);
    setShowCatalogues(false);
    setShowMainDash(false);
  };
  const showCatalogueView = () => {
    setShowSignIn(false);
    setShowSignUp(false);
    setShowResetPassword(false);
    setShowCatalogues(true);
    setShowMainDash(false);
  };

  const showBidDashboard = () => {
    setShowSignIn(false);
    setShowSignUp(false);
    setShowResetPassword(false);
    setShowCatalogues(false);
    setShowMainDash(true);
  };

  useEffect(() => {
    console.log("Auction: ", auction);
    console.log("User: ", user);
  }, [auction, user]);

  return (
    <div className="App">
      <userContext.Provider
        value={{ user, setUser, auction, setAuction, showBidDashboard }}
      >
        <Navbar bg="dark" data-bs-theme="dark">
          <Container>
            <Navbar.Brand>Auction System</Navbar.Brand>
            <Nav>
              <Nav.Link onClick={SignInView}>Sign In</Nav.Link>
              <Nav.Link onClick={SignUpView} href="">
                Sign Up
              </Nav.Link>
              <Nav.Link onClick={resetPasswordView}>Reset Password</Nav.Link>

              <Nav.Link onClick={showCatalogueView}>Catalogues</Nav.Link>
              <Nav.Link onClick={showBidDashboard}>Bid Dash</Nav.Link>
            </Nav>
            {user == null ||
              (user.userName === "" && (
                <Navbar.Text className="justify-content-end">
                  Please Sign In
                </Navbar.Text>
              ))}
            {user != null && user.userName !== "" && (
              <Navbar.Text className="justify-content-end">
                Signed in as: {user.userName} | {user.id}
              </Navbar.Text>
            )}
          </Container>
        </Navbar>
        <Container>
          <Row>
            <Col>
              {showSignIn && <SignIn />}
              {showSignUp && <SignUp />}
              {showResetPassword && <ForgotPassword />}
              {user !== null && user.userName !== "" && showCatalogues && (
                <CatMenu />
              )}
              {(user === null || user.userName === "") && showCatalogues && (
                <h4>Please log in to start viewing auctions</h4>
              )}
              {user !== null && user.userName !== "" && showMainDash && (
                <MainDash />
              )}
            </Col>
          </Row>
        </Container>
      </userContext.Provider>
    </div>
  );
}

export default App;
