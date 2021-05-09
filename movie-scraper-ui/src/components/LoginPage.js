
import "../App.css";
import { useState, useRef } from "react";
import OAuth2Login from "react-simple-oauth2-login";
import { makeStyles } from "@material-ui/core/styles";
import { Button } from "@material-ui/core";
import Logo from '../assets/Untitlggvgvfyed-1.png'
import React from 'react'
import Register from "./Register";
import { SportsRugbySharp } from "@material-ui/icons";
const useStyles = makeStyles((theme) => ({
  btn:{
      display:'none'
  }
}));


function LoginPage() {
  const classes = useStyles();
  const regBtn = useRef(null);

  const onSuccess = (res) => {
    localStorage.setItem("token", res.access_token);
    console.log("saving token..." + localStorage.getItem("token"));
    window.location.reload(true);
  };
  const onFailure = (response) => {
    console.error(response);
  };
  return (
    <div className="login-page" >
      <img style={{width:325}} src={Logo}></img>
     {/* <h1 style={{color:"#4103fc"}}>Movie Scraper</h1> */}
      <h3 style={{color:"#fff"}}>
              Movies from all your favorite websites now available in one place!
            </h3>
      <br />
      {/* <Button  color="primary" onClick={()=>console.log("G")}>
          Register
        </Button> */}
        
        <Register/>
      <Button style={{marginTop:5}} variant="contained" color="primary" onClick={()=>regBtn.current.onBtnClick()}>
          Login
        </Button>
     
      {<OAuth2Login
      ref={regBtn}
       className={classes.btn}
        authorizationUrl="http://localhost:9191/oauth/authorize"
        responseType="token"
        clientId="MovieScraper"
        redirectUri="http://localhost:3000"
        onSuccess={onSuccess}
        onFailure={onFailure}
      />}
    </div>
  );
}

export default LoginPage;
