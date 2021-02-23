import logo from "./logo.svg";
import "./App.css";
import { useState } from "react";
import OAuth2Login from "react-simple-oauth2-login";

function LoginPage() {


  const onSuccess = (res) => {

    localStorage.setItem("token", res.access_token);
    console.log("saving token..."+localStorage.getItem("token"))
    window.location.reload(true);
  };
  const onFailure = (response) => {
    console.error(response);
  };
  return (
    <div className="login-page">
      <h1>Movie Scraper</h1>

      <br />
      <OAuth2Login
        authorizationUrl="http://localhost:9191/oauth/authorize"
        responseType="token"
        clientId="mobile"
        redirectUri="http://localhost:3000"
        onSuccess={onSuccess}
        onFailure={onFailure}
      />
    </div>
  );
}

export default LoginPage;
