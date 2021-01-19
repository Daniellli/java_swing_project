//Written By Nitesh
function CheckPassword() {
    var password = document.getElementById("password");
    var rePassword = document.getElementById("rePassword");
    if (password.value !== "" && password.value !== null && password.value === rePassword.value) {
        password.style.background = "white";
        rePassword.style.background = "white";
        return true;
    }   
    else{
        password.style.background = "lightpink";
        rePassword.style.background = "lightpink";
        return false;
    }
}


