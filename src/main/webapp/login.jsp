<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html>
    <head>
        <link rel="stylesheet" href="styles.css" >
    </head>



    <body>
        <div class="parent">
            <h1>
               Iniciar sesion
            </h1>

            <p style="display: ${sessionScope.usuarioLoggeado == false ? 'block' : 'none'}">
                Correo o contraseña equivocados. Intenta de nuevo.
            </p>

            <form action="/Akinator/login" method="POST">
                <div>
                    <label>
                        Correo
                    </label>
                    <input
                        type="email"
                        name="correo" required>
                    </input>
                </div>

                <div>
                    <label>
                        Contraseña
                    </label>
                    <input
                        type="password"
                        name="password" required>
                    </input>
                </div>

                <button id="bttnLogin"
                    type="submit"
                >
                    Log In
                </button>

                <button id="regresar"
                    onclick = "location.href='/Akinator'"
                >
                    Regresar
                </button>
            </form>

            <button
                id="registrar"
                class="register"
                onclick = "location.href='/Akinator/register'"
            >
                "No tienes cuenta? Registrate ahora"
            </button>
        </div>
    </body>
</html>
