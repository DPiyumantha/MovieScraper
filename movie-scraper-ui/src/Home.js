import logo from "./logo.svg";
import Feed from "./Feed";
import "./styles.css";
import axios from "axios";
import Typography from "@material-ui/core/Typography";
import { Wave } from "react-animated-text";
import Container from "@material-ui/core/Container";
import LoginPage from "./LoginPage";
import { useState, useEffect } from "react";
import { Button } from "@material-ui/core";
import UserDetails from "./UserDetails";
import { handle401 } from "./helpers.js/error";
import ServiceOffline from "./components/ServiceOffline";
import Background from "./assets/bg.png";
import Logo from './assets/Untitlggvgvfyed-1.png';
import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import IconButton from "@material-ui/core/IconButton";
import { makeStyles } from "@material-ui/core/styles";
import MenuIcon from "@material-ui/icons/Menu";
import SearchIcon from "@material-ui/icons/Search";
import MoreIcon from "@material-ui/icons/MoreVert";
axios.defaults.headers.common["Authorization"] =
  "bearer " + localStorage.getItem("token");

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  toolbar: {
    minHeight: 128,
    alignItems: "flex-start",
    paddingTop: theme.spacing(1),
    paddingBottom: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    alignSelf: "flex-end",
  },
}));

function Home() {
  const classes = useStyles();

  const [username, setUsername] = useState("");
  const [user, setUser] = useState({});
  // const [isErrorOccured, setIsErrorOccured] = useState(false)

  useEffect(async () => {
    if (localStorage.getItem("token")) {
      const userResult = await axios(
        "http://localhost:8990/user/user-api/users/username"
      ).catch((err) => {
        if (err.response && err.response.status == 401) handle401();
        // else setIsErrorOccured(true)
      });
      if (userResult) {
        console.log("username :", userResult.data);
        setUsername(userResult.data);
      }
    }
  }, []);

  useEffect(async () => {
    if (localStorage.getItem("token")) {
      const userRes = await axios(
        "http://localhost:8990/user/user-api/users/user"
      ).catch((err) => {
        if (err.response && err.response.status == 401) handle401();
        // else setIsErrorOccured(true)
      });
      if (userRes) {
        console.log("user data", userRes.data);
        setUser(userRes.data);
      }
    }
  }, []);

  return (
    <div
      className="App-header "
      style={{
        backgroundImage: `url(${Background})`,
        height: "100vh",
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        display: "flex",
        justifyContent: "center",
        flexWrap: "wrap",
        "& > *": {
          // margin: theme.spacing(0.5),
        },
      }}
    >
      <Container fixed style={{padding:0}}>
        {/* <AppBar position="static">
          <Toolbar className={classes.toolbar}>
    
            <Typography className={classes.title} variant="h5" noWrap>
              <h1>Movie Scraper</h1>
            </Typography>           
          </Toolbar>
        </AppBar> */}
        {localStorage.getItem("token") && (
          <div className="home-banner">
            <h1>Movie Scraper</h1>
            <br />
            
            <div
              style={{
                alignItems: "right",
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
              }}
            >
              <UserDetails userObj={user} username={username} />

              <Button
                color="secondary"
                onClick={() => {
                  localStorage.removeItem("token");
                  window.location.reload(false);
                }}
              >
                |->
              </Button>
            </div>
          </div>
        )}
        <br />
        {/* {isErrorOccured?<ServiceOffline/>:<></>} */}
        {localStorage.token ? <Feed /> : <LoginPage />}
      </Container>
    </div>
  );
}

export default Home;
