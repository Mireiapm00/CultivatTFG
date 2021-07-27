<%-- 
    Document   : registrarseProd
    Created on : 25-jul-2021, 14:14:20
    Author     : mireia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>&nbsp;</title>
        <link href="./userAccess_component/userAccess.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <p class="bold h3 text-center mt-2 mb-3 p-2">Registre de nou productor</p>
        
        <form name="form1" method="post" onsubmit="ProcesarForm(this, 'RegistroUsuarioProductor', 'cuerpo'); return false">
            <div class="container w-50 mt-4">
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
                <div class="form-floating mt-5 mb-3">
                    <p class="bold h5 text-center">Dades del projecte</p>
                    <hr>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingNombreProyecto" placeholder="NombreProyecto" name="nombreProyecto" maxlength="64" required>
                    <label for="floatingNombreProyecto">Nom del projecte</label>
                </div>
                <div class="form-floating mb-3">
                    <select class="form-select" id="floatingCategoriaProyecto" aria-label="Selecciona una categoria" name="categoriaProyecto" required>
                        <option value="1" selected>Hortalisses i Fruita</option>
                        <option value="2">Ferments, Elaborats i Càtering</option>
                        <option value="3">Pa i productes de forn</option>
                        <option value="4">Mel</option>
                        <option value="5">Oli</option>
                    </select>
                    <label for="floatingCategoriaProyecto">Categoria del projecte</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="tel" class="form-control" id="floatingTelefonoProyecto" placeholder="TelefonoProjecto" name="telefonoProyecto" pattern="[0-9]{9}" required>
                    <label for="floatingTelefonoProyecto">Telèfon</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control form-control-sm" id="floatingEmailProyecto" placeholder="nom@example.com" name="emailProyecto" required>
                    <label for="floatingEmailProyecto">Direcció email</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingUbicacionProyecto" placeholder="UbicacionProyecto" name="ubicacionProyecto" maxlenght="64" required>
                    <label for="floatingUbicacionProyecto">Ubicació del projecte</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingWebProyecto" placeholder="WebProjecto" name="webProyecto" maxlength="64" required>
                    <label for="floatingWebProyecto">Web del projecte</label>
                </div>
                <div class="form-floating mb-3">
                    <textarea class="form-control" id="floatingRepartoProyecto" placeholder="RepartoProyecto" name="repartoProyecto" style="height: 100px" maxlength="256" required></textarea>
                    <label for="floatingRepartoProyecto">Repartiment</label>
                </div>
                <div class="form-floating mb-3">
                    <textarea class="form-control" id="floatingDescProyecto" placeholder="DescProyecto" name="descProyecto" style="height: 100px" maxlength="500" required></textarea>
                    <label for="floatingDescProyecto">Descripció del projecte</label>
                </div>
                
                <div class="mb-2 text-center">
                    <input class="btn btn-dark me-4" type="submit" value="Registrar-se">
                    <input class="btn btn-outline-dark" type="reset" value="Cancelar" onclick="Cargar('./userAccess_component/userAccess.jsp', 'cuerpo')"/>
                </div>
            </div>
        </form>
    </body>
</html>
