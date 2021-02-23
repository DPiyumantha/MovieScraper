import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Selector from "./components/Selector";
import axios from "axios";
import { FormControl,Button } from "@material-ui/core";
import { handle401 } from "./helpers.js/error";
const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
      width: "50ch",
    },
  },
}));



export default function UserForm({ userObj, usernameid }) {
  console.log("UserForm userObj : ",userObj)
  const [user, setUser] = useState(userObj);
  const [isOldUser, setIsOldUser] = useState(userObj.userEmail?true:false);
  console.log("IsOld :", isOldUser)
  const [username, setUsername] = useState(usernameid);
  const [firstName, setFirstName] = useState(user.firstName || "");
  const [lastName, setLastName] = useState(user.lastName);
  const [email, setEmail] = useState(user.userEmail);
  const [genres, setGenres] = useState([]);
  const [selectedGenres, setSelectedGenres] = React.useState(user.genres?user.genres:[]);
  const [websites, setWebsites] = useState([]);
  const [selectedWebsites, setSelectedWebsites] = React.useState(user.webSites?user.webSites:[]);
  const [currentUser, setCurrentUser] = useState({});
  const [updatedUser, setUpdatedUser] = React.useState([]);
  const classes = useStyles();
  console.log(user);

  useEffect(async () => {
    const genreRes = await axios(
      "http://localhost:8990/user/genre-api/genres"
    ).catch(
      (err) => {if(err.response.status==401)handle401()}
      );
    if (genreRes) setGenres(genreRes.data);
  }, []);

  useEffect(async () => {
    const websiteRes = await axios(
      "http://localhost:8990/user/website-api/websites"
    ).catch((err) => {if(err.response.status==401)handle401()});
    if (websiteRes) setWebsites(websiteRes.data);
  }, []);


  const onSave= async()=>{
    if(isOldUser){
console.log("saving old user")
      const savedUser = await axios({
        method: 'patch',
        url: 'http://localhost:8990/user/user-api/users',
        data: {
          id: user.id,
          username: usernameid,
          firstName: firstName,
          lastName: lastName,
          userEmail: email,
          imgUrl:'url',
          genres: selectedGenres,
          webSites: selectedWebsites
        }
      }).catch(err=>console.log(err));
      if(savedUser)setUser(savedUser);
window.location.reload(true)
    }else{
      const savedUser = await axios({
        method: 'post',
        url: 'http://localhost:8990/user/user-api/users',
        data: {
          username: usernameid,
          firstName: firstName,
          lastName: lastName,
          userEmail: email,
          imgUrl:'url',
          genres: selectedGenres,
          webSites: selectedWebsites
        }
      }).catch(err=>console.log(err));
      if(savedUser)setUser(savedUser);
    }
  }

  const handleChangeGenres = (event) => {
    var __FOUND = selectedGenres.findIndex(function(genre, index) {
      if(genre.name == event.target.value.name)
        return true;
    });
    console.log(event.target.value)
    console.log(__FOUND)
    if(__FOUND>-1){
      console.log("Found!")
    }
    setSelectedGenres(event.target.value);
  };

  const handleChangeWebsites = (event) => {
    setSelectedWebsites(event.target.value);
  };

  return (
    <FormControl fullwidth>
      <form
        className={classes.root}
        noValidate
        autoComplete="off"
        style={{ width: "500px" }}
      >
        <TextField
          id="outlined-basic"
          label="Username - Auto Generated"
          variant="outlined"
          value={username}
          disabled
        />
        <br />
        <TextField
          id="outlined-basic"
          label="First Name"
          variant="outlined"
          value={firstName}
          onChange={(val) => setFirstName(val.target.value)}
          required
        />
        <br />
        <TextField
          id="outlined-basic"
          label="Last Name"
          variant="outlined"
          value={lastName}
          onChange={(val) => setLastName(val.target.value)}
        />
        <br />
        <TextField
          id="outlined-basic"
          label="Email"
          variant="outlined"
          value={email}
          onChange={(val) => setEmail(val.target.value)}
          required
        />
        <br />
        <Selector
          genres={genres}
          handleChange={handleChangeGenres}
          selectedGenres={selectedGenres}
          label={"Genres"}
        />
        <br/>
        <Selector
          genres={websites}
          handleChange={handleChangeWebsites}
          selectedGenres={selectedWebsites}
          label={"Web Sites"}
        />
      </form>
      <Button
      fullwidth={false}
                color="primary"
                onClick={onSave}
              >
                Save
              </Button>
    </FormControl>
  );
}
