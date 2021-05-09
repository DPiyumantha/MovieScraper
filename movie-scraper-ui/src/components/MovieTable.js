import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TextTyping from './TextTyping'
import Paper from '@material-ui/core/Paper';
import {useState} from 'react';
import '../styles.css';
const useStyles = makeStyles({
  table: {
    minWidth: 650,
    color:'green'
  },
});

function createData(name, calories, fat, carbs, protein) {
  return { name, calories, fat, carbs, protein };
}

const rows = [
  createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
  createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
  createData('Eclair', 262, 16.0, 24, 6.0),
  createData('Cupcake', 305, 3.7, 67, 4.3),
  createData('Gingerbread', 356, 16.0, 49, 3.9),
];

export default function MovieTable({moviedata}) {
const [movies, setMovies] = useState([]);
// setMovies(moviedata.get(0));

  const classes = useStyles();
  return (
     (moviedata.length>0)? <TableContainer component={Paper} >
      <Table className={classes.table, "movie-table" } aria-label="simple table">
        <TableHead>
          <TableRow>
          <TableCell className={"table-heading"} >Poster</TableCell>
            <TableCell >Name</TableCell>
            <TableCell >Year</TableCell>
            <TableCell >Rating</TableCell>
            <TableCell >Genres</TableCell>
          </TableRow>
        </TableHead>
        <TableBody >

          {console.log(moviedata)}
          {moviedata.length>0? moviedata.map((movie) => (
            movie.link.includes("imdb.com")||<TableRow style={{backgroundColor:'#002b80', color: 'white',}} key={movie.name}>
              <TableCell ><img src={movie.img} style={{width:100}}></img></TableCell>
              <TableCell ><a style={{color:'white', fontSize:'1.2em', textDecoration:'none'}} href={movie.link}>{movie.name}</a></TableCell>
              <TableCell style={{color:'white'}}>{movie.year}</TableCell>
              <TableCell style={{color:'white'}}>{movie.imdb.includes('/')?  "N/A":movie.imdb}</TableCell>
              <TableCell style={{color:'white'}}>{movie.genres.map(genre=>genre.name+" ")}</TableCell>
            </TableRow>
          
          )):<></>}
        </TableBody>
      </Table>
    </TableContainer>
    : <p>Complete your registration and refresh the app after few minutes to see the movie list or hit "Scrape now" <TextTyping/></p>
  
  );
}
