<%-- 
    Document   : registrarse
    Created on : 10-jul-2021, 20:46:40
    Author     : mirei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./userAccess_component/userAccess.css" rel="stylesheet" type="text/css">
        <title>&nbsp;</title>
        <script type="text/javascript">
            function validar_clave(){
                var data = document.form1.pass.value;
                var data1 = document.form1.pass2.value;
                if(data === data1){
                    return true;
                }else{
                    alert("Las contraseñas deben de coincidir.");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <p class="bold h3 text-center mt-2 mb-3 p-2">Registre de nou usuari</p>
        
        <form name="form1" method="post" onsubmit="ProcesarForm(this, 'RegistroUsuario', 'cuerpo'); return false">
            <div class="container w-50 mt-4">
                <div class="form-floating mb-3 text-center">    
                    <button type="button" class="float-right btn btn-outline-dark btn-sm btn-outline-success" onclick="Cargar('userAccess_component/registrarseProd.jsp', 'cuerpo')" >Vull ser productor</button>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingUsuari" placeholder="Usuari" name="usuario" required autofocus>
                    <label for="floatingUsuari">Usuari</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control form-control-sm" id="floatingEmail" placeholder="nom@example.com" name="email" required>
                    <label for="floatingEmail">Direcció email</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control form-control-sm" id="floatingPassword" placeholder="Password" name="pass" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                           title="Deu contindre al menys un numero, una lletra majúscula i minúscula amb 8 caràcters o més" required>
                    <label for="floatingPassword">Contrasenya</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control form-control-sm" id="floatingPassword2" placeholder="Password" name="pass2" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                           onchange="validar_clave()" title="Deu contindre al menys un numero, una lletra majúscula i minúscula amb 8 caràcters o més" required>
                    <label for="floatingPassword2">Repetir Contrasenya</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="tel" class="form-control" id="floatingTelefon" placeholder="Telefon" name="telefono" pattern="[0-9]{9}" required>
                    <label for="floatingTelefon">Telèfon</label>
                </div>
                <div class="mb-2 text-center">
                    <input class="btn btn-dark me-4" type="submit" value="Registrar-se">
                    <input class="btn btn-outline-dark" type="reset" value="Cancelar" onclick="Cargar('./userAccess_component/userAccess.jsp', 'cuerpo')"/>
                </div>
            </div>
        </form>
    </body>
</html>
