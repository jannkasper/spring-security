import { createStore, applyMiddleware, compose, combineReducers } from "redux";
import thunk from "redux-thunk";
import authenticationReducer from "./authentication/reducer"


const enabledMiddlewares = [thunk];

const middlewares = applyMiddleware(...enabledMiddlewares);

const createRootReducer = () => {
  return combineReducers({
    authentication: authenticationReducer,
  })
}

const configureStore = (preloadedState) => {
  const store = createStore(
      createRootReducer(),
      preloadedState,
      compose(middlewares));

  return store;
}

export default configureStore
