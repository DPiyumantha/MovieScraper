export const validateNewUser = (newUser)=>{
    if(newUser.username.length>0 && newUser.password.length>0 && validateEmail(newUser.email) ){
        return true;
    }
}

export function validateEmail(email) {
    // use this [a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

 export const validateUsername = (username)=>{
    return username.length>3
 }

 export const validatePassword = (password)=>{
    return password.length>7
 }