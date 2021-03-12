

const bcrypt = require("bcryptjs");
var salt = bcrypt.genSaltSync(10);

 export const Bcrypter = (pass)=>{
    return bcrypt.hashSync(pass, salt);
    // await bcrypt.genSalt(10, function (err, salt) {
    //   bcrypt.hash("B4c0//", salt, function (err, hash) {});
    // });
}