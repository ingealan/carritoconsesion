<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manejo de cookies</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <style>
        body {
            background: linear-gradient(45deg, #2980b9, #8e44ad, #16a085);
            background-size: 400% 400%;
            animation: gradientAnimation 10s ease infinite;
            font-family: 'Arial', sans-serif;
            color: #ecf0f1;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        @keyframes gradientAnimation {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .container {
            border-radius: 15px;
            padding: 30px;
        }

        h1, h2 {
            color: #ecf0f1;
            text-shadow: 2px 2px 6px rgba(0, 0, 0, 0.3);
        }

        .btn-danger {
            background-color: #e74c3c;
            border-color: #e74c3c;
            transition: background-color 0.3s ease;
        }

        .btn-danger:hover {
            background-color: #c0392b;
            border-color: #c0392b;
        }

        .btn-link {
            color: #ecf0f1;
            text-decoration: none;
        }

        .btn-link:hover {
            color: #3498db;
        }

        .form-control {
            border-radius: 10px;
            border: 1px solid #3498db;
            background-color: #34495e;
            color: #ecf0f1;
        }

        .form-control:focus {
            border-color: #3498db;
            box-shadow: 0 0 8px rgba(52, 152, 219, 0.5);
        }
    </style>
</head>
<body>
<div class="container">
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
