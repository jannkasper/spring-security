import {
  LOGIN_FAILURE,
  LOGIN_SUCCESS,
  SET_AUTH_TOKEN
} from "./types";
import {GET, POST} from "../api";

export const setAuthToken = (payload) => ({
  type: SET_AUTH_TOKEN,
  payload
})

export const loginSuccess = () => ({
  type: LOGIN_SUCCESS,
});
export const loginFailed = error => ({
  type: LOGIN_FAILURE,
  error
});

export const login = payload => async (dispatch, getState) => {
  const state = getState()
  try {
    const user = {
      email: payload.email,
      password: payload.password,
    }
    await window.sessionStorage.setItem("userdetails", JSON.stringify(user));
    const response = await GET(state,'http://localhost:8080/user',  { observe: 'response',withCredentials: true })

    const model = await response.json();
    model.authStatus = 'AUTH';
    window.sessionStorage.setItem("userdetails", JSON.stringify(model));

    await dispatch(loginSuccess())
    await dispatch(setAuthToken(response.headers.get('Authorization')))

  } catch (error) {
    console.log('ERROR', error)
    dispatch(loginFailed(error))
  }
}

export const getAccount = () => async (dispatch, getState) => {
  const state = getState()
  try {
    const response = await GET(state,'http://localhost:8080/account',  { observe: 'response', withCredentials: true })
    console.log(await response.text())
  } catch (error) {
    console.log('ERROR', error)
    dispatch(loginFailed(error))
  }
}
