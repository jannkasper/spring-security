import App from "./App";
import { connect } from "react-redux";
import {login, getAccount} from "../../redux/authentication/action";


const mapStateToProps = state => {
  return {
    customer: state.authentication.customer
  }
}

const mapDispatchToProps = {
  login,
  getAccount
}

export default connect(mapStateToProps, mapDispatchToProps)(App)
