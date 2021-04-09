import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Selector from "./Selector";
import axios from "axios";
import { FormControl, Button } from "@material-ui/core";
import { handle401 } from "../helpers/error";
import ReactSelect from "./ReactSelect";
import CustomSelector from "./CustomSelector";
import MultiSelect from "react-multi-select-component";
import Chip from "@material-ui/core/Chip";
import "../styles.css";
import { USER_SERVICE_GENRE, USER_SERVICE_USER, USER_SERVICE_WEBSITE, SCRAPING_SERVICE } from "../constants/MovieScraperAPI";
import {validateEmail} from '../helpers/validators'
const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
      width: "50ch",
    },
  },
  root2: {
    display: "flex",
    justifyContent: "center",
    flexWrap: "wrap",
    "& > *": {
      margin: theme.spacing(0.5),
    },
  },
}));

export default function UserForm({ userObj, usernameid }) {
  console.log("UserForm userObj : ", userObj);
  const [user, setUser] = useState(userObj);
  const [isOldUser, setIsOldUser] = useState(userObj.userEmail ? true : false);
  console.log("IsOld :", isOldUser);
  const [username, setUsername] = useState(usernameid);
  const [firstName, setFirstName] = useState(user.firstName || "");
  const [lastName, setLastName] = useState(user.lastName);
  const [email, setEmail] = useState(user.userEmail);
  const [genres, setGenres] = useState([]);
  const [selectedGenres, setSelectedGenres] = React.useState(
    user.genres ? user.genres : []
  );
  const [websites, setWebsites] = useState([]);
  const [isValidEmail, setIsValidEmail] = useState(true);
  const [selectedWebsites, setSelectedWebsites] = React.useState(
    user.webSites ? user.webSites : []
  );
 const [isErrorOccured, setIsErrorOccured] = useState({"failed":false, "message":""});
  const classes = useStyles();
  console.log(user);
  useEffect(async () => {
    console.log("In useEffect");
    const websiteRes = await axios(
      USER_SERVICE_WEBSITE+"/websites"
    ).catch((err) => {
      if (err.response.status == 401) handle401();
    });
    if (websiteRes) setWebsites(websiteRes.data);

    getGenres();
  }, []);

  const getGenres = async () => {
    const genreRes = await axios(USER_SERVICE_GENRE+"/genres");
    setGenres(genreRes.data);
  };

  const onSave = async () => {
    const dataObj = {
      username: usernameid,
      firstName: firstName,
      lastName: lastName,
      userEmail: email,
      imgUrl: "url",
      genres: selectedGenres,
      webSites: selectedWebsites,
    }
    if (isOldUser) {
      console.log("saving old user");
      const savedUser = await axios({
        method: "patch",
        url: USER_SERVICE_USER+"/users",
        data: {
          id: user.id,
          ...dataObj
        },
      }).catch((err) =>{setIsErrorOccured({"failed":true, "message":"Something went wrong. Please try again later. Updated details not saved."});
         console.log(err)});
      if (savedUser) {
        setUser(savedUser);
        const res = await axios({
          method: "post",
          url: SCRAPING_SERVICE+"/scrape",
          data: {
            id: user.id,
            ...dataObj
          },
        }).catch((err) => {setIsErrorOccured({"failed":true, "message":"Something went wrong. Scraping did not started. "}); console.log(err)});
      }
      window.location.reload(true);
    } else {
      const savedUser = await axios({
        method: "post",
        url: USER_SERVICE_USER+"/users",
        data: dataObj
      }).catch((err) => {setIsErrorOccured({"failed":true, "message":"Something went wrong. Please try again later. Updated details not saved."});console.log(err)});
      if (savedUser) {
        setUser(savedUser);
        const res = await axios({
          method: "post",
          url: SCRAPING_SERVICE+"/scrape",
          data: {
            username: usernameid,
            firstName: firstName,
            lastName: lastName,
            userEmail: email,
            imgUrl: "url",
            genres: selectedGenres,
            webSites: selectedWebsites,
          },
        }).catch((err) => {setIsErrorOccured({"failed":true, "message":"Something went wrong. Please try again later. Updated details not saved."});console.log(err)});
      }
      // window.location.reload(true);
      setTimeout(()=>window.location.reload(true),2000)
    }
  };

  const matchEmail = (event) => {
    setEmail(event.target.value );

    if (validateEmail(event.target.value)) {
      setIsValidEmail(true);
    } else {
      setIsValidEmail(false);
    }
  };

  const isIncludedGenres = (obj) => {
    console.log("isIncludedGenres");
    let pos = selectedGenres
      .map(function (e) {
        return e.id;
      })
      .indexOf(obj.id);
    if (pos == -1) {
      return false;
    } else {
      return true;
    }
  };

  const isIncludedWebsites = (obj) => {
    console.log("isIncludedWebsites");
    let pos = selectedWebsites
      .map(function (e) {
        return e.websiteId;
      })
      .indexOf(obj.websiteId);
    if (pos == -1) {
      return false;
    } else {
      return true;
    }
  };

  const handleClickGenres = (genre) => {
    if (isIncludedGenres(genre)) {
      setSelectedGenres(selectedGenres.filter((e) => e.id !== genre.id));
    } else {
      setSelectedGenres([...selectedGenres, genre]);
    }
  };

  const handleClickWebsites = (genre) => {
    if (isIncludedWebsites(genre)) {
      setSelectedWebsites(
        selectedWebsites.filter((e) => e.websiteId !== genre.websiteId)
      );
    } else {
      setSelectedWebsites([...selectedWebsites, genre]);
    }
  };

  return (
    <FormControl fullwidth={true}>
      <form
        className={classes.root}
        noValidate
        autoComplete="off"
        style={{ width: "500px" }}
      >
        <TextField
          id="outlined-basic"
          label="Username - Sorry this can't be changed :P"
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
          label="Email - You get your movie updates to this address"
          variant="outlined"
          value={email}
          onChange={matchEmail}
          required
        />
        {!isValidEmail && <p className="errorMsg">Email not valid</p>}
        <br />
        Select Genres
        {genres.length > 0 ? (
          <CustomSelector
            selectedGenres={selectedGenres}
            genres={genres}
            handleClick={handleClickGenres}
            isIncluded={isIncludedGenres}
          />
        ) : (
          <p className="errorMsg">
            Gosh! DB error. Check your connection please.
          </p>
        )}
        <br />
        Select Websites
        {websites.length>0?<CustomSelector
          selectedGenres={selectedWebsites}
          genres={websites}
          handleClick={handleClickWebsites}
          isIncluded={isIncludedWebsites}
        />:
        <p className="errorMsg">
            Gosh! DB error. Check your connection please.
          </p>
        }
      </form>
      <Button
        fullwidth={false}
        color="primary"
        onClick={onSave}
        // onClick={()=>console.table(selectedWebsites)}
      >
        Save
      </Button>
    </FormControl>
  );
}
