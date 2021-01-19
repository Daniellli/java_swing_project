//Written by Nitesh
function validatePassword() {
    var loginId = document.getElementById("loginId");
    var ret = document.getElementById("container").innerText;
    if(ret.toString().trim()==="not available"){
        alert("Login ID already taken, choose another ID..");
        loginId.style.background = "lightpink";
        return false;
    }
    else{
        loginId.style.background = "white";
    }
    var password = document.getElementById("password");
    var rePassword = document.getElementById("rePassword");
    if (password.value !== "" && password.value !== null && password.value === rePassword.value) {
        password.style.background = "white";
        rePassword.style.background = "white";
        return true;
    }   
    else{
        alert("Password should not be empty !! Password and Re-Password must match !!")
        password.style.background = "lightpink";
        rePassword.style.background = "lightpink";
        return false;
    }
}
function verifyUser() {
    var loginId = document.getElementById("loginId");
    var ret = document.getElementById("container").innerText;
    if(ret.toString().trim()==="not available"){
        alert("Login ID already taken, choose another ID..");
        loginId.style.background = "lightpink";
    }
    else{
        alert("Login ID is okay..");
        loginId.style.background = "white";
    }
    return false;
}
function checkUser(loginId){
    var loc = "/LoginModule/servlet/VerifyUser?loginId=" + loginId.value;
    //$('#container').load(loc);
    var container = document.getElementById("container");
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function(){
        if(this.status===200){
            container.innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open('GET',loc,true);
    xmlhttp.send();
}


