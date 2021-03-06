import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import { useSpring, animated } from "react-spring/web.cjs"; // web.cjs is required for IE 11 support
import { Button } from "@material-ui/core";
import UserForm from "./UserForm";
import axios from "axios";

import { USER_SERVICE_GENRE, USER_SERVICE_USER, USER_SERVICE_WEBSITE, SCRAPING_SERVICE } from "../constants/MovieScraperAPI";
const useStyles = makeStyles((theme) => ({
  modal: {
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: "1px solid #000",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
    width: "500px",
    height:"75vh",
    flex:1,
    overflowY: "scroll"
    
  },
}));

const Fade = React.forwardRef(function Fade(props, ref) {
  const { in: open, children, onEnter, onExited, ...other } = props;
  const style = useSpring({
    from: { opacity: 0 },
    to: { opacity: open ? 1 : 0 },
    onStart: () => {
      if (open && onEnter) {
        onEnter();
      }
    },
    onRest: () => {
      if (!open && onExited) {
        onExited();
      }
    },
  });

  return (
    <animated.div ref={ref} style={style} {...other}>
      {children}
    </animated.div>
  );
});

Fade.propTypes = {
  children: PropTypes.element,
  in: PropTypes.bool.isRequired,
  onEnter: PropTypes.func,
  onExited: PropTypes.func,
};

export default function UserDetails({ userObj, username }) {
  console.log("UserDetails : ", userObj)
  console.log("UserDetails : ", username)
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const [user, setUser] = useState(userObj);
  const [isErrorOccured, setIsErrorOccured] = useState({"failed":false, "message":""});
  const scrapeNow = async (user)=>{
console.log("Hi ",user)
const res = await axios({
  method: "post",
  url: SCRAPING_SERVICE+"/scrape",
  data: {
    ...user,
    websites:user.webSites
  },
}).catch((err) => {setIsErrorOccured({"failed":true, "message":"Something went wrong. Please try again later. Updated details not saved."});console.log(err)});

  }
  const handleOpen = () => {
    setOpen(true);
  };
console.log(user)
  const handleClose = () => {
    setOpen(false);
  };

  useEffect(() => {
    if(userObj){setUser(userObj);}
    else{
    setUser({});
    }
}, [userObj]);

  return (
    <div >
      {/* <button type="button" onClick={handleOpen}>
        react-spring
      </button> */}
      {user.firstName && username ? (
        <>
        <Button color="primary" onClick={()=>scrapeNow(user)}>
          Scrape Now
        </Button>
        <Button color="primary" onClick={handleOpen}>
          {user.firstName} @{username}
        </Button>
        </>
      ) : (
        <Button color="primary" onClick={handleOpen}>
          Complete Your Registration!
        </Button>
      )}
      <Modal
        aria-labelledby="spring-modal-title"
        aria-describedby="spring-modal-description"
        className={classes.modal}
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <div className={classes.paper}>
            <h2 id="spring-modal-title">User Details</h2>
            {/* <p id="spring-modal-description">react-spring animates me.</p> */}
            <UserForm  userObj={user ? user : {}} usernameid={username} />
          </div>
        </Fade>
      </Modal>
    </div>
  );
}
