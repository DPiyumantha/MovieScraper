import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));

export default function UserForm({user, usernameid}) {
    const [username, setUsername] = useState(usernameid);
    const [firstName, setFirstName] = useState(user.firstName || "");
    const [lastName, setLastName] = useState(user.lastName);
    const [email, setEmail] = useState(user.userEmail);
  const classes = useStyles();
console.log(user)
  return (
    <form className={classes.root} noValidate autoComplete="off" style={{width:"500px"}}>
      <TextField id="outlined-basic" label="Username - Auto Generated" variant="outlined" value={username} /><br/>
      <TextField id="outlined-basic" label="First Name" variant="outlined" value={firstName} onChange={val=>setFirstName(val.target.value)} /><br/>
      <TextField id="outlined-basic" label="Last Name" variant="outlined" value={lastName} onChange={val=>setLastName(val.target.value)} /><br/>
      <TextField id="outlined-basic" label="Email" variant="outlined" value={email} onChange={val=>setEmail(val.target.value)} /><br/>
    </form>
  );
}
