import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import axios from "axios";
import "../styles.css";
import { FormControl, Button } from "@material-ui/core";
import { handle401 } from "../helpers/error";
import Chip from "@material-ui/core/Chip";
import { InputAdornment, IconButton } from "@material-ui/core";
import Visibility from "@material-ui/icons/Visibility";
import VisibilityOff from "@material-ui/icons/VisibilityOff";
import ErrorMessageSimple from "./ErrorMessageSimple";
import { Bcrypter } from "../helpers/Bcrypter";
import * as apis from "../constants/MovieScraperAPI";
import { newUser as newUserInit } from "../constants/initData";
import {
  validateNewUser,
  validateEmail,
  validateUsername,
  validatePassword,
} from "../helpers/validators";
const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
      width: "57ch",
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
  const [isErrorCreatingUser, setIsErrorCreatingUser] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");
  const [isValidated, setIsValidated] = useState(true);
  const [isValidEmail, setIsValidEmail] = useState(true);
  const [isValidPassword, setIsValidPassword] = useState(true);
  const [isMatchingPassword, setIsMatchingPassword] = useState(true);
  const [confirmPassword, setConfirmPassword] = useState("");
  const [newUser, setNewUser] = useState(newUserInit);

  const classes = useStyles();
  useEffect(async () => {}, []);

  const matchPassword = (event) => {
    setNewUser({ ...newUser, password: event.target.value });

    if (validatePassword(event.target.value)) {
      setIsValidPassword(true);
    } else {
      setIsValidPassword(false);
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
    if (validateNewUser(newUser)) {
      if (validateUsername(newUser.username)) {
        var hash = Bcrypter(newUser.password);
        await setNewUser({ ...newUser, password: `{bcrypt}${hash}` });
        const savedUser = await axios({
          method: "post",
          url: apis.AUTHORIZATION_SERVER,
          data: { ...newUser, password: `{bcrypt}${hash}` },
        }).catch((err) => {
          setIsErrorCreatingUser(true);
          setErrorMsg(err.response.data.errTitle);
        });
        console.log(savedUser);
        if (savedUser && savedUser.data) {
          setIsUserCreated(true);
        }
        setNewUser(newUserInit);
      } else {
        setIsErrorCreatingUser(true);
        setErrorMsg("Username must be longer than 3 charachters");
      }
    } else {
      setIsErrorCreatingUser(true);
      setErrorMsg("Fill all the fields");
    }
  };

  return (
    <div>
      {isErrorCreatingUser ? (
        <div>
          <ErrorMessageSimple
            reset={() => setIsErrorCreatingUser(false)}
            errorMsg={errorMsg}
          />
        </div>
      ) : (
        <FormControl fullwidth={true}>
          {!isUserCreated && (
            <form
              className={classes.root}
              noValidate
              autoComplete="off"
              style={{ width: "500px" }}
            >
              <TextField
                id="username"
                label="Username"
                variant="outlined"
                value={newUser.username}
                onChange={(event) =>
                  setNewUser({ ...newUser, username: event.target.value })
                }
              />
              <br />
              <TextField
                id="email"
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
                onChange={matchPassword}
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
              {!isValidPassword && (
                <p className="errorMsg">
                  Password must include at least 8 charachters
                </p>
              )}

              <br />
              <Button fullwidth={false} color="primary" onClick={onSave}>
                Save
              </Button>
            </form>
          )}

          {isUserCreated && (
            <h3 style={{ color: "green" }}>
              Successfully registered! Please log in.
            </h3>
          )}
        </FormControl>
      )}
    </div>
  );
}
