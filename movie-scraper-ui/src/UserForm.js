import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Selector from "./components/Selector";
import axios from "axios";
import { FormControl, Button } from "@material-ui/core";
import { handle401 } from "./helpers.js/error";
import ReactSelect from "./components/ReactSelect";
import CustomSelector from "./components/CustomSelector";
import MultiSelect from "react-multi-select-component";
import Chip from "@material-ui/core/Chip";
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
  const [genres, setGenres] = useState([{}]);
  const [selectedGenres, setSelectedGenres] = React.useState(
    user.genres ? user.genres : []
  );
  const [websites, setWebsites] = useState([]);
  const [selectedWebsites, setSelectedWebsites] = React.useState(
    user.webSites ? user.webSites : []
  );
  const [currentUser, setCurrentUser] = useState({});
  const [updatedUser, setUpdatedUser] = React.useState([]);
  const classes = useStyles();
  console.log(user);
  useEffect(async () => {
    console.log("In useEffect");
    const websiteRes = await axios(
      "http://localhost:8990/user/website-api/websites"
    ).catch((err) => {
      if (err.response.status == 401) handle401();
    });
    if (websiteRes) setWebsites(websiteRes.data);

    getGenres();
  }, []);

  const getGenres = async () => {
    const genreRes = await axios("http://localhost:8990/user/genre-api/genres");
    setGenres(genreRes.data);
  };

  const onSave = async () => {
    if (isOldUser) {
      console.log("saving old user");
      const savedUser = await axios({
        method: "patch",
        url: "http://localhost:8990/user/user-api/users",
        data: {
          id: user.id,
          username: usernameid,
          firstName: firstName,
          lastName: lastName,
          userEmail: email,
          imgUrl: "url",
          genres: selectedGenres,
          webSites: selectedWebsites,
        },
      }).catch((err) => console.log(err));
      if (savedUser) {
        setUser(savedUser);
        const res = await axios({
          method: "post",
          url: "http://localhost:8990/scraping/scrape",
          data: {
            id: user.id,
            username: usernameid,
            firstName: firstName,
            lastName: lastName,
            userEmail: email,
            imgUrl: "url",
            genres: selectedGenres,
            webSites: selectedWebsites,
          },
        }).catch((err) => console.log(err));
      }
      window.location.reload(true);
    } else {
      const savedUser = await axios({
        method: "post",
        url: "http://localhost:8990/user/user-api/users",
        data: {
          username: usernameid,
          firstName: firstName,
          lastName: lastName,
          userEmail: email,
          imgUrl: "url",
          genres: selectedGenres,
          webSites: selectedWebsites,
        },
      }).catch((err) => console.log(err));
      if (savedUser) {
        setUser(savedUser);
        const res = await axios({
          method: "post",
          url: "http://localhost:8990/scraping/scrape",
          data: {
            username: usernameid,
            firstName: firstName,
            lastName: lastName,
            userEmail: email,
            imgUrl: "url",
            genres: selectedGenres,
            webSites: selectedWebsites,
          },
        }).catch((err) => console.log(err));
      }
      window.location.reload(true);
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
        Select Genres
        <CustomSelector
          selectedGenres={selectedGenres}
          genres={genres}
          handleClick={handleClickGenres}
          isIncluded={isIncludedGenres}
        />
        <br />
        Select Websites
        <CustomSelector
          selectedGenres={selectedWebsites}
          genres={websites}
          handleClick={handleClickWebsites}
          isIncluded={isIncludedWebsites}
        />
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
