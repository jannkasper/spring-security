import App from "./App";
import { connect } from "react-redux";
import {login} from "../../redux/authentication/action";


const mapStateToProps = state => {
  return {
    customer: state.authentication.customer
  }
}

const mapDispatchToProps = {
  login,
}

export default connect(mapStateToProps, mapDispatchToProps)(App)
