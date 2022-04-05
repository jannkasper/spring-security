import React, { useState } from "react";
import './App.css';

const App = ({ login, getAccount }) => {

  const [state , setState] = useState({
    email : "",
    password : ""
  })

  const handleChange = (e) => {
    const {id , value} = e.target
    setState(prevState => ({
      ...prevState,
      [id] : value
    }))
  }

  const handleLoginClick = (e) => {
    e.preventDefault();
    login({
      email: state.email,
      password: state.password,
    })
  }

  return (
    <div className="App">
      <input type="email"
             className="form-control"
             id="email"
             placeholder="Enter email"
             value={state.email}
             onChange={handleChange}
      />
      <input type="password"
             className="form-control"
             id="password"
             placeholder="Password"
             value={state.password}
             onChange={handleChange}
      />
      <button type="submit" onClick={handleLoginClick}>
        Login
      </button>
      <button type="submit" onClick={() => getAccount()}>
        Account after login
      </button>
    </div>
  );
}

export default App;
