/*
-- Query: SELECT * FROM moviescraperuser.genre
LIMIT 0, 1000

-- Date: 2021-02-18 20:48
*/
INSERT INTO `genre` (`id`,`name`) VALUES (1,'Action');
INSERT INTO `genre` (`id`,`name`) VALUES (2,'Adventure');
INSERT INTO `genre` (`id`,`name`) VALUES (3,'Animation');
INSERT INTO `genre` (`id`,`name`) VALUES (4,'Biography');
INSERT INTO `genre` (`id`,`name`) VALUES (5,'Comedy');
INSERT INTO `genre` (`id`,`name`) VALUES (6,'Crime');
INSERT INTO `genre` (`id`,`name`) VALUES (7,'Documentry');
INSERT INTO `genre` (`id`,`name`) VALUES (8,'Drama');
INSERT INTO `genre` (`id`,`name`) VALUES (9,'Family');
INSERT INTO `genre` (`id`,`name`) VALUES (13,'Fantasy');
INSERT INTO `genre` (`id`,`name`) VALUES (14,'Film-Noir');
INSERT INTO `genre` (`id`,`name`) VALUES (15,'Game-Show');
INSERT INTO `genre` (`id`,`name`) VALUES (16,'History');
INSERT INTO `genre` (`id`,`name`) VALUES (17,'Horror');
INSERT INTO `genre` (`id`,`name`) VALUES (18,'Musical');
INSERT INTO `genre` (`id`,`name`) VALUES (19,'Mystery');
INSERT INTO `genre` (`id`,`name`) VALUES (20,'News');
INSERT INTO `genre` (`id`,`name`) VALUES (21,'Reality-TV');
INSERT INTO `genre` (`id`,`name`) VALUES (22,'Romance');
INSERT INTO `genre` (`id`,`name`) VALUES (23,'Sci-Fi');
INSERT INTO `genre` (`id`,`name`) VALUES (24,'Sport');
INSERT INTO `genre` (`id`,`name`) VALUES (25,'Talk-Show');
INSERT INTO `genre` (`id`,`name`) VALUES (26,'Thriller');
INSERT INTO `genre` (`id`,`name`) VALUES (27,'War');
INSERT INTO `genre` (`id`,`name`) VALUES (28,'Western');


INSERT INTO `website` (`websiteId`,`url`, `name`) VALUES (1,"https://yts.mx/browse-movies", 'YTS');
INSERT INTO `website` (`websiteId`,`url`, `name`) VALUES (2,"https://baiscopedownloads.co",'Baiscope Downloads');
INSERT INTO `website` (`websiteId`,`url`, `name`) VALUES (3,"https://xmovies8.pw/xmovies8", 'XMovies8');
INSERT INTO `website` (`websiteId`,`url`, `name`) VALUES (4,"https://www.ssoap2day.to/lastnews", 'Soap2Day');



{
   "username":"user1",
   "password":"user@123",
   "email":"user1@dimalka.lk",
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


delete FROM authdbnew.role_user where user_id=17;
delete FROM authdbnew.user where id=17;
delete FROM authdbnew.oauth_access_token where 1=1;
delete FROM authdbnew.oauth_refresh_token where 1=1;


docker run -p 3307:3306 -e MYSQL_ROOT_PASSWORD=1234 --network moviescraper-network --name mysql-con-project-service mysql:latest
docker run --network moviescraper-network -p 8081:8080 project-service-con


docker run --name ms-db-auth --network moviescraper-network -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=authDBnew -e MYSQL_USER=root -e MYSQL_PASSWORD=1234 -d mysql:8.0
























