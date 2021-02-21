import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {useState} from 'react';
import './styles.css';
const useStyles = makeStyles({
  table: {
    minWidth: 650,
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
    <TableContainer component={Paper} >
      <Table className={classes.table, "movie- table" } aria-label="simple table">
        <TableHead>
          <TableRow >
            <TableCell >Name</TableCell>
            <TableCell >Link</TableCell>
            <TableCell >Rating</TableCell>
            <TableCell >Genres</TableCell>
          </TableRow>
        </TableHead>
        <TableBody >
          {moviedata.length>0? moviedata.map((movie) => (
            movie.link.includes("imdb.com")||<TableRow key={movie.name}>
              <TableCell >{movie.name}</TableCell>
              <TableCell ><a href={movie.link}>Visit Web Site</a></TableCell>
              <TableCell >{movie.imdb.includes('/')? movie.imdb: "N/A"}</TableCell>
              <TableCell >{movie.genres.map(genre=>genre.name+" ")}</TableCell>
            </TableRow>
          
          )):<></>}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
