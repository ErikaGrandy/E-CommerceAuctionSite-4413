import { Container } from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import SignUp from "./Auth/SignUp";
import { CatMenu } from "./Catalogue/CatMenu";
import Dash from "./bidding/Dash";
import SignIn from "./Auth/SignIn";
import ForgotPassword from "./Auth/ForgotPassword";

function App() {
  return (
    <div className="App">
      <Container>
        <Row>
          <Col>
            {/* <SignIn /> */}
            {/* <SignUp /> */}
            {/* <ForgotPassword /> */}
            <CatMenu />
            {/* <Dash /> */}
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default App;
