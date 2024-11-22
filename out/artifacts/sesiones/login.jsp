<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manejo de cookies</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Manejo de cookies</h1>

    <!-- Formulario de Login -->
    <div class="row justify-content-center mt-4">
        <div class="col-md-6">
            <h2 class="text-center">Iniciar sesión</h2>
            <form action="/sesiones/loginservlet" method="POST">
                <div class="mb-3">
                    <label for="username" class="form-label">Usuario:</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Ingresa tu usuario" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña:</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Ingresa tu contraseña" required>
                </div>

                <button type="submit" class="btn btn-danger w-100">Iniciar sesión</button>
            </form>
        </div>
    </div>

    <div class="mt-4 text-center">
        <a href="index.html" class="btn btn-link">Volver</a>


    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-XgtLBubkPA2cxSe6tTYKqT2gGZj5zD9+2J+1K2Ra95IzhrBaFycrSzztS/Nu38/X" crossorigin="anonymous"></script>
</body>
</html>
