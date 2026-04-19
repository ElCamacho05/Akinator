<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html>
    <head>
        <link rel="stylesheet" href="styles.css" >
    </head>

    <body>
        <div class="parent">
            <h1>
               Registrate!
            </h1>

            <form action="/Akinator/register" method="POST">
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
                    Registrarme
                </button>

                <button id="regresar"
                    onclick = "location.href='/Akinator/login'"
                >
                    Regresar
                </button>
            </form>
        </div>
    </body>
</html>
