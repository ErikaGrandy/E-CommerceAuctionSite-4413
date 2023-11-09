import { Container } from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import SignIn from "./Auth/SignIn";
import { CatMenu } from "./Catalogue/CatMenu";

function App() {
  return (
    <div className="App">
      <Container>
        <Row>
          <Col>
            {/* <SignIn /> */}
            <CatMenu />
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default App;
