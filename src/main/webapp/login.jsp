<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html>
    <body>
        <div>
            <h1>
               Iniciar sesion
            </h1>

            <button
                onclick = "location.href='/Akinator'"
            >
                Regresar
            </button>
        </div>

        <form action="/Akinator/login" method="POST">
            <div>
                <label>
                    Correo
                </label>
                <input
                    type="email" name="correo" required>
                </input>
            </div>

            <div>
                <label>
                    Contraseña
                </label>
                <input
                    type="password" name="password" required>
                </input>
            </div>

            <button
                type="submit"
            >
                Log In
            </button>
        </form>

        <p>
            No tienes cuenta? Registrate ahora
        </p>
    </body>
</html>
