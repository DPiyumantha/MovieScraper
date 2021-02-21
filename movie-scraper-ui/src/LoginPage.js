import logo from './logo.svg';
import './App.css';
import { useState } from "react";

import OAuth2Login from 'react-simple-oauth2-login';



const onSuccess = response => {
    console.log(response);}
const onFailure = response => {console.error(response)};


function LoginPage() {
    const [auth_token, setAuthToken] = useState("");
  return (
    <div className="login-page">
     <h1>Movie Scraper</h1>
     <br/>
     <OAuth2Login
          authorizationUrl="http://localhost:9191/oauth/authorize"
          responseType="token"
          clientId="mobile"
          redirectUri="http://localhost:3000"
          onSuccess={res=>{setAuthToken(res.access_token); localStorage.setItem("token", res.access_token); window.location.reload(false);}}
          onFailure={onFailure}
        />
    </div>
  );
}

export default LoginPage;
