$(document).ready(function() {

    var registerFormFields = ["reg_name","reg_email","reg_password","reg_cpassword"];

    var registerToggle = function() {
        $("#register-div").slideToggle();
        $("#login-div").slideToggle();
    };

    var removeRegisterErrors = function(fields){
        for(var i = 0; i < fields.length; i++){
            document.getElementById(fields[i]).classList.remove("form-field-invalid");
        }
        $("#p-name-error").text("").hide();
        $("#p-email-error").text("").hide();
        $("#p-pass-error").text("").hide();
        $("#p-cpass-error").text("").hide();
    }

    $("#show-register-btn").click(registerToggle);
    $("#cancel-btn").click(function(){
        registerToggle();
        document.getElementById("register-form").reset();
        removeRegisterErrors(registerFormFields)
    });

    var formFieldsAreNotEmpty = function(fields){
        var fieldsNotEmpty = true;

        for(var i = 0; i < fields.length; i++){
            if ($("#" + fields[i]).val() == ""){
                document.getElementById(fields[i]).classList.add("form-field-invalid");
                fieldsNotEmpty = false;
            } else {
                document.getElementById(fields[i]).classList.remove("form-field-invalid");
            }
        }
        return fieldsNotEmpty;
    };

    var registerFormIsValid = function(){

        var isValid = true;

        if(!formFieldsAreNotEmpty(registerFormFields)){
            isValid = false;
        }

        if ($("#reg_password").val().length < 8) {
            document.getElementById("reg_password").classList.add("form-field-invalid");
            $("#p-pass-error").text("Password length is shorter than 8.").show();
            isValid = false;
        } else {
            document.getElementById("reg_password").classList.remove("form-field-invalid");
            $("#p-pass-error").text("").hide();
        }
        if (!($("#reg_password").val() === $("#reg_cpassword").val())) {
            document.getElementById("reg_cpassword").classList.add("form-field-invalid");
            $("#p-cpass-error").text("Passwords don't match.").show();
            isValid = false;
        } else {
            document.getElementById("reg_cpassword").classList.remove("form-field-invalid");
            $("#p-cpass-error").text("").hide();
        }

        return isValid;
    };

    $("#register-btn").click(function() {
        if(registerFormIsValid()){
            var newUser = {
                username: $("#reg_name").val(),
                email: $("#reg_email").val(),
                password: $("#reg_password").val()
            };

            $.ajax({
                type: "POST",
                url: "/user",
                data: newUser,
                success: function(data){
                    document.getElementById("register-form").reset();
                    registerToggle();
                },
                error: function(data){
                    document.getElementById("reg_name").classList.add("form-field-invalid");
                    $("#p-name-error").text(data.responseJSON.message).show();
                }
            })
        }
    });
});