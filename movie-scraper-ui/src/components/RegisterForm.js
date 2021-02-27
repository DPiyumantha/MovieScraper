import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import axios from "axios";
import { FormControl, Button } from "@material-ui/core";
import { handle401 } from "../helpers.js/error";
import Chip from '@material-ui/core/Chip';
const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
      width: "50ch",
    },

  },
  root2: {
    display: 'flex',
    justifyContent: 'center',
    flexWrap: 'wrap',
    '& > *': {
      margin: theme.spacing(0.5),
    },
  },
}));

export default function RegisterForm() {

  const [newUser, setNewUser] = useState(
    {
      "username":"",
      "password":"",
      "email":"",
      "enabled":1,
      "accountNonExpired":1,
      "credentialsNonExpired":1,
      "accountNonLocked":1,
      "roles":[
         {
            "id":2,
            "name":"ROLE_operator",
            "permissions":[
               {
                  "id":1,
                  "name":"create_profile"
               },
               {
                  "id":3,
                  "name":"update_profile"
               },
               {
                  "id":2,
                  "name":"read_profile"
               }
            ]
         }
      ]
   }

  );
 
  const classes = useStyles();
  useEffect(async () => {
    
  }, []);


  const getGenres = async ()=>{
    const genreRes = await axios(
      "http://localhost:8990/user/genre-api/genres"
    )
    
  }

  const onSave = async () => {
    
  };

  const isIncludedGenres=()=>{
    
  }

  const isIncludedWebsites=()=>{
  }

  const handleClickGenres=()=>{
    
  }

  const handleClickWebsites=()=>{
}

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
          label="Username"
          variant="outlined"
        //   value={username}
        //   disabled
        />
        <br />
        <TextField
          id="outlined-basic"
          label="Email"
          variant="outlined"
        //   value={firstName}
        //   onChange={(val) => setFirstName(val.target.value)}
          required
        />
        <br />
        <TextField
          id="outlined-basic"
          label="Password"
          variant="outlined"
          required
        //   value={lastName}
        //   onChange={(val) => setLastName(val.target.value)}
        />
        <br />
        <TextField
          id="outlined-basic"
          label="Confirm Password"
          variant="outlined"
        //   value={email}
        //   onChange={(val) => setEmail(val.target.value)}
          required
        />
        
        <br />

    
      </form>
      <Button fullwidth={false} color="primary" 
      onClick={onSave}
      // onClick={()=>console.table(selectedWebsites)}
    
    
      >
        Save
      </Button>
    </FormControl>
  );
}
