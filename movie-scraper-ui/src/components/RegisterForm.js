import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import axios from "axios";
import "../styles.css";
import { FormControl, Button } from "@material-ui/core";
import { handle401 } from "../helpers.js/error";
import Chip from "@material-ui/core/Chip";
import { InputAdornment, IconButton } from "@material-ui/core";
import Visibility from "@material-ui/icons/Visibility";
import VisibilityOff from "@material-ui/icons/VisibilityOff";
const bcrypt = require("bcryptjs");
var salt = bcrypt.genSaltSync(10);

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
      width: "60ch",
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

export default function RegisterForm() {
  const [isUserCreated, setIsUserCreated] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const handleClickShowPassword = () => setShowPassword(!showPassword);
  const handleMouseDownPassword = () => setShowPassword(!showPassword);
  const [isValidated, setIsValidated] = useState(true);
  const [isValidEmail, setIsValidEmail] = useState(true);
  const [isValidPassword, setIsValidPassword] = useState(true);
  const [isMatchingPassword, setIsMatchingPassword] = useState(true);
  const [confirmPassword, setConfirmPassword] = useState("");
  const [newUser, setNewUser] = useState({
    username: "",
    password: "",
    email: "",
    enabled: 1,
    accountNonExpired: 1,
    credentialsNonExpired: 1,
    accountNonLocked: 1,
    roles: [
      {
        id: 2,
        name: "ROLE_operator",
        permissions: [
          {
            id: 1,
            name: "create_profile",
          },
          {
            id: 3,
            name: "update_profile",
          },
          {
            id: 2,
            name: "read_profile",
          },
        ],
      },
    ],
  });

  const classes = useStyles();
  useEffect(async () => {}, []);

  function validateEmail(email) {
    // use this [a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

  const matchPassword = (event) => {
    if (newUser.password === confirmPassword) {
      setIsMatchingPassword(true);
    } else {
      setIsMatchingPassword(false);
    }
  };

  const matchEmail = (event) => {
    setNewUser({ ...newUser, email: event.target.value });

    if (validateEmail(event.target.value)) {
      setIsValidEmail(true);
    } else {
      setIsValidEmail(false);
    }
  };

  const onSave = async () => {
    var hash = bcrypt.hashSync(newUser.password, salt);
    await bcrypt.genSalt(10, function (err, salt) {
      bcrypt.hash("B4c0//", salt, function (err, hash) {});
    });

    await setNewUser({ ...newUser, password: `{bcrypt}${hash}` });
    console.log(newUser);
    const savedUser = await axios({
      method: "post",
      url: "http://localhost:8990/authorization/user/register",
      data: {...newUser, password:`{bcrypt}${hash}`},
    }).catch((err) => console.log(err));
console.log(savedUser)
if(savedUser&&savedUser.data){setIsUserCreated(true)}
    setNewUser({
      username: "",
      password: "",
      email: "",
      enabled: 1,
      accountNonExpired: 1,
      credentialsNonExpired: 1,
      accountNonLocked: 1,
      roles: [
        {
          id: 2,
          name: "ROLE_operator",
          permissions: [
            {
              id: 1,
              name: "create_profile",
            },
            {
              id: 3,
              name: "update_profile",
            },
            {
              id: 2,
              name: "read_profile",
            },
          ],
        },
      ],
    });
  };

  const isIncludedGenres = () => {};

  const isIncludedWebsites = () => {};

  const handleClickGenres = () => {};

  const handleClickWebsites = () => {};

  return (
    <FormControl fullwidth={true}>
      
     {!isUserCreated && <form
        className={classes.root}
        noValidate
        autoComplete="off"
        style={{ width: "500px" }}
      >
        
        <TextField
          id="outlined-basic"
          label="Username"
          variant="outlined"
          value={newUser.username}
          onChange={(event) =>
            setNewUser({ ...newUser, username: event.target.value })
          }
        />
        <br />
        <TextField
          id="outlined-basic"
          label="Email"
          variant="outlined"
          value={newUser.email}
          onChange={matchEmail}
          required
        />

        <br />
        {!isValidEmail && <p className="errorMsg">Email not valid</p>}
        <TextField
          label="Password"
          variant="outlined"
          type={showPassword ? "text" : "password"}
          onChange={(event) =>
            setNewUser({ ...newUser, password: event.target.value })
          }
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton
                  aria-label="toggle password visibility"
                  onClick={handleClickShowPassword}
                  onMouseDown={handleMouseDownPassword}
                >
                  {showPassword ? <Visibility /> : <VisibilityOff />}
                </IconButton>
              </InputAdornment>
            ),
          }}
        />
        

        <br />
        <Button
        fullwidth={false}
        color="primary"
        onClick={onSave}
        // onClick={()=>console.table(selectedWebsites)}
      >
        Save
      </Button>
      </form>}
      
      {isUserCreated && <p style={{color:'green'}}>Successfully registered! Please log in.</p>}
    </FormControl>
  );
}
