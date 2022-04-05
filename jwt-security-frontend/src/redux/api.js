async function createError (response) {
  const error = new Error(`${await response.text()} (${response.url})`)
  // console.log(response, response.status)
  error.status = response.status
  return error
}

function getAuthorizationHeaders (rootState) {
  const headers = {
    'X-Requested-With': 'XMLHttpRequest'
  }

  const token = rootState.authentication.authToken
  const user = JSON.parse(window.sessionStorage.getItem('userdetails'));

  if (token) {
    headers['Authorization'] = token
  } else if (user && user.password && user.email) {
    headers['Authorization'] = 'Basic ' + btoa(user.email + ':' + user.password);
  }

  // let xsrf = window.sessionStorage.getItem('XSRF-TOKEN');
  // if(xsrf) {
  //   headers['X-XSRF-TOKEN'] = xsrf
  // }

  return headers
}

export async function GET (rootState, url, options) {

  const response = await fetch(url, {headers: getAuthorizationHeaders(rootState), ...options})

  if (!response.ok) {
    throw await createError(response)
  }

  return response
}

/**
 *
 * @param rootState
 * @param url
 * @param body {Object}
 * @param isFile
 * @returns {Promise<Response<any, Record<string, any>, number>>}
 */
export async function POST (rootState, url, body, isFile = false) {
  const options = {
    headers: getAuthorizationHeaders(rootState),
    credentials: 'include',
    method: 'POST'
  }

  if (body) {
    options.body = isFile ? body : JSON.stringify(body)
  }

  if (!isFile) {
    options.headers['Content-Type'] = 'application/json;charset=UTF-8'
  } else {
    options.enctype = 'multipart/form-data'
  }

  console.info(`Requesting api @${url}`, new Date().toUTCString(), new Date().valueOf())
  const response = await fetch(url, options)

  if (!response.ok) {
    throw await createError(response)
  }

  return response
}
