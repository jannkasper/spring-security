import {LOGIN_FAILURE, LOGIN_SUCCESS, SET_AUTH_TOKEN} from "./types";

const initialState = {
  authToken: '',
  customer: {},
  isResponse: false,
  error: '',
}

const authenticationReducer = (state = initialState, action) => {
  switch (action.type) {
    case SET_AUTH_TOKEN:
      return Object.assign({}, state, {
        authToken: action.payload
      })
    case LOGIN_SUCCESS:
      return Object.assign({}, state, {
        customer: action.payload,
        isResponse: true,
        error: ''
      })
    case LOGIN_FAILURE:
      return Object.assign({}, state, {
        isResponse: false,
        error: action.error
      })
    default:
      return state
  }
}

export default authenticationReducer
