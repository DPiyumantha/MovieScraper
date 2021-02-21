import { useState, useEffect } from "react";
import axios from 'axios';
import MovieTable from "./MovieTable";


function Feed() {

    const [movieRecords, setMovieRecords] = useState({});
    const [user, setUser] = useState({});

    useEffect(async () => {
        const userResult = await axios(
            'http://localhost:8990/user/user-api/users/user', 
          ).catch(err=>{
            // localStorage.removeItem("token");
          });
          if (userResult) setUser(userResult.data);
          

       
      }, []);
      useEffect(async () => {
       if(user.id){
        const result = await axios(
          'http://localhost:8990/movie-repository/movies/'+user.id,
        );
     setMovieRecords(result.data);
        }
      }, [user]);
    return (
      <div className="Feed">
        <MovieTable moviedata = {movieRecords}/>

        
      </div>
     
    );
  }
  
  export default Feed;