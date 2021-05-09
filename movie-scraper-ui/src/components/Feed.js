import { useState, useEffect } from "react";
import axios from "axios";
import MovieTable from "./MovieTable";
import { handle401 } from "../helpers/error";

function Feed() {
  const [movieRecords, setMovieRecords] = useState({});
  const [user, setUser] = useState({});

  useEffect(async () => {
    const userResult = await axios(
      "http://localhost:8990/user/user-api/users/user"
    ).catch((err) => {
      if (err.response && err.response.status == 401) handle401();
    });
    if (userResult) setUser(userResult.data);
  }, []);
  useEffect(async () => {
    if (user.id) {
      const result = await axios(
        "http://localhost:8990/movie-repository/movies/" + user.id
      );
      setMovieRecords(result.data);
    }
  }, [user]);
  return (
    <div className="Feed">
      <MovieTable moviedata={movieRecords} />
    </div>
  );
}

export default Feed;
