$(document).ready(function() {

    $("#register").click(function() {
        var form_name = $("#reg_name").val();
        var form_email = $("#reg_email").val();
        var form_password = $("#reg_password").val();
        var form_cpassword = $("#reg_cpassword").val();
        if (form_name == '' || form_email == '' || form_password == '' || form_cpassword == '') {
            alert("Not all fields were filled.");
        } else if ((form_password.length) < 8) {
            alert("Password too short, should be at least 8 chars.");
        } else if (!(form_password).match(form_cpassword)) {
            alert("Your passwords don't match.");
        } else {
            $.post("/user", {
        username: form_name,
        email: form_email,
        password: form_password
        }, function(data) {
            document.getElementById("register-form").reset();
            registerToggle();
        })
    }});

    $("#show-register-bttn").click(registerToggle);

     var registerToggle = function() {
            $("#register-div").slideToggle();
        }
});