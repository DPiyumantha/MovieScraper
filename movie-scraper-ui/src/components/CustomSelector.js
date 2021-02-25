import React, { useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import Chip from '@material-ui/core/Chip';


const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    justifyContent: 'center',
    flexWrap: 'wrap',
    '& > *': {
      margin: theme.spacing(0.5),
    },
  },
}));


export default function CustomSelector(props){ 
  
  const classes = useStyles();
  const options = props.genres;

  const [selected, setSelected] = useState(props.selectedGenres);

  
  
  
  return (

<div className={classes.root}>

  {options.map(option=>{
    return <Chip
    color={props.isIncluded(option)?"secondary":"primary"}
    
    label={option.name}
    onClick={()=>props.handleClick(option)}
    variant="outlined"
  />
  })}


</div>
  );


};