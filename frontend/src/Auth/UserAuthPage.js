import React from "react";
import Button from "react-bootstrap/Button";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import { useState } from "react";
import { Row, Col } from "react-bootstrap";
import SignIn from "./SignIn";
import SignUp from "./SignUp";
import ForgotPassword from "./ForgotPassword";

const UserAuthPage = () => {
  const [showSignIn, setShowSignIn] = useState(true);
  const [showSignUp, setShowSignUp] = useState(false);
  const [showResetPassword, setShowResetPassword] = useState(false);

  return (
    <div className="mb-5">
      <Row>
        <ButtonGroup aria-label="Basic example">
          <Button
            variant="secondary"
            onClick={() => {
              setShowSignIn(true);
              setShowSignUp(false);
              setShowResetPassword(false);
            }}
          >
            Sign In
          </Button>
          <Button
            variant="secondary"
            onClick={() => {
              setShowSignIn(false);
              setShowSignUp(true);
              setShowResetPassword(false);
            }}
          >
            Sign Up
          </Button>
          <Button
            variant="secondary"
            onClick={() => {
              setShowSignIn(false);
              setShowSignUp(false);
              setShowResetPassword(true);
            }}
          >
            Reset Password
          </Button>
        </ButtonGroup>
      </Row>
      <Row>
        {showSignIn && <SignIn />}
        {showSignUp && <SignUp />}
        {showResetPassword && <ForgotPassword />}
      </Row>
    </div>
  );
};

export default UserAuthPage;
