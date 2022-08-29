const mostrar_pass =  document.querySelector("#mostrar_pass");
const pass1 = document.querySelector("#pass");
const pass2 = document.querySelector("#pass-repeat");

mostrar_pass.addEventListener('change', function () {
    if (mostrar_pass.checked == true ) {
        pass1.type = "text";
        pass2.type = "text";

    } else {
        pass1.type= "password";
        pass2.type = "password";
}
})

