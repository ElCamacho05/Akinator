<html>
    <body>
        <h1>
            DAW-Kinator
        </h1>
        <h2>
            Bienvenido a Akinator, ${sessionScope.usuarioLoggeado}
        </h2>

        <h3>
            PIENSA EN UN PERSONAJE Y RESPONDE LAS PREGUNTAS...
        </h3>

        <p>
            (y adivinare tu personaje)
        </p>

        <p>
            Pregunta numero ${numP}: ${pregunta}
        </p>

        <form action="/Akinator/game" method="POST">
            <button id="SiBTTN" type="submit" name="respuesta" value="si" ${fin ? 'disabled' : ''}>
                Si
            </button>

            <button id="NoBTTN" type="submit" name="respuesta" value="no" ${fin ? 'disabled' : ''}>
                No
            </button>
        </form>

        <p id="resp" style="display: ${fin ? 'block' : 'none'}">
            Tu personaje es ${personaje}
        </p>

    </body>
</html>