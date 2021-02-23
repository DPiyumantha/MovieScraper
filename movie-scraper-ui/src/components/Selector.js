import React, { useState } from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import ListItemText from '@material-ui/core/ListItemText';
import Select from '@material-ui/core/Select';
import Checkbox from '@material-ui/core/Checkbox';
import Chip from '@material-ui/core/Chip';

const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
    maxWidth: 300,
  },
  chips: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  chip: {
    margin: 2,
  },
  noLabel: {
    marginTop: theme.spacing(3),
  },
}));

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};


function getStyles(genre, selectedGenres, theme) {
  return {
    fontWeight:
    selectedGenres.map(function(e) { return e.name; }).indexOf(genre.name)===-1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

export default function Selector(props) {

    const [selectedGenres, setSelectedGenres] = useState(props.selectedGenres);


  const classes = useStyles();
  const theme = useTheme();
  
const OnChangeHandler=(event)=>{
    props.handleChange(event); 
    setSelectedGenres(event.target.value);
}
  

  return (
    <div>
      
      
      <FormControl className={classes.formControl}>
        <InputLabel id="demo-mutiple-chip-label">{props.label}</InputLabel>
        <Select
          labelId="demo-mutiple-chip-label"
          id="demo-mutiple-chip"
          multiple
          value={selectedGenres}
          onChange={OnChangeHandler}
          input={<Input id="select-multiple-chip" />}
          renderValue={(selectedGenres) => (
            <div className={classes.chips}>
              {selectedGenres.map((value) => (
                <Chip key={value.id} label={value.name} className={classes.chip} />
              ))}
            </div>
          )}
          MenuProps={MenuProps}
        >
          {props.genres.map((genre) => (
            <MenuItem key={genre.id} value={genre} style={getStyles(genre, selectedGenres, theme)}>
              {genre.name}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      
      
    </div>
  );
}
