import logo from "./logo.svg";
import Feed from "./Feed";
import "./styles.css";
import axios from "axios";
import Typography from "@material-ui/core/Typography";
import { Wave } from 'react-animated-text';
import Container from "@material-ui/core/Container";
import LoginPage from "./LoginPage";
import { useState, useEffect } from "react";
import { Button } from "@material-ui/core";
import UserDetails from "./UserDetails";

axios.defaults.headers.common["Authorization"] = "bearer " + localStorage.getItem("token");

function Home() {


  const [username, setUsername] = useState("");
  const [user, setUser] = useState({});


  useEffect(async () => {
    const userResult = await axios(
      "http://localhost:8990/user/user-api/users/username"
    ).catch((err) => {
      localStorage.removeItem("token");
    });
    if (userResult) {console.log("username :", userResult.data);setUsername(userResult.data);}
  }, []);

  useEffect(async () => {
    const userRes = await axios(
      "http://localhost:8990/user/user-api/users/user"
    ).catch((err) => {
      localStorage.removeItem("token");
    });
    if (userRes){console.log("user data",userRes.data); setUser(userRes.data);}
  }, []);

  return (
    <div className="App-header ">
      <Container fixed>
        {localStorage.getItem("token") && (
          <div className="home-banner">
            <h1>Movie Scraper</h1>
            
            <div style={{ alignItems: "right", display:'flex', flexDirection:'row', alignItems:'center' }}>

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
        {localStorage.token ? <Feed /> : <LoginPage />}
      </Container>
    </div>
  );
}

export default Home;
