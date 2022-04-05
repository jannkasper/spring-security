import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from "react-redux";
import './index.css';
import App from './components/app';
import reportWebVitals from './reportWebVitals';
import configureStore from "./redux";
// import { createBrowserHistory } from 'history'

// We use hash history because this example is going to be hosted statically.
// Normally you would use browser history.
// const history = createBrowserHistory()

const store = configureStore()


ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
