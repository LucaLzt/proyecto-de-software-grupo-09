-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS steam;
USE steam;

-- Insertar juegos
INSERT INTO juegos (nombre, descripcion, precio, genero, imagen, desarrollador, requisitos_minimos, requisitos_recomendados) VALUES
('Counter-Strike 2', 'Shooter táctico competitivo gratuito', 0.0, 'FPS', 'https://cdn.cloudflare.steamstatic.com/steam/apps/730/header.jpg', 'Valve', 'OS: Windows 10, RAM: 8GB, GPU: GTX 970', 'OS: Windows 11, RAM: 16GB, GPU: RTX 2070'),
('Elden Ring', 'RPG de mundo abierto brutal', 2999.0, 'RPG', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1245620/header.jpg', 'FromSoftware', 'OS: Windows 10, RAM: 12GB, GPU: GTX 1060', 'OS: Windows 11, RAM: 16GB, GPU: RTX 2080'),
('Stardew Valley', 'Juego de granja y vida tranquila', 599.0, 'Simulacion', 'https://cdn.cloudflare.steamstatic.com/steam/apps/413150/header.jpg', 'ConcernedApe', 'OS: Windows 7, RAM: 2GB, GPU: 256MB', 'OS: Windows 10, RAM: 4GB, GPU: 512MB'),
('Hollow Knight', 'Metroidvania desafiante', 399.0, 'Accion', 'https://cdn.cloudflare.steamstatic.com/steam/apps/367520/header.jpg', 'Team Cherry', 'OS: Windows 7, RAM: 4GB, GPU: GTX 400', 'OS: Windows 10, RAM: 8GB, GPU: GTX 960'),
('Dota 2', 'MOBA competitivo gratuito', 0.0, 'MOBA', 'https://cdn.cloudflare.steamstatic.com/steam/apps/570/header.jpg', 'Valve', 'OS: Windows 7, RAM: 4GB, GPU: GTX 460', 'OS: Windows 10, RAM: 8GB, GPU: GTX 970'),
('GTA V', 'Mundo abierto y acción sin límites', 1999.0, 'Accion', 'https://cdn.cloudflare.steamstatic.com/steam/apps/271590/header.jpg', 'Rockstar Games', 'OS: Windows 10, RAM: 8GB, GPU: GTX 660', 'OS: Windows 11, RAM: 16GB, GPU: GTX 980'),
('Cyberpunk 2077', 'RPG futurista en Night City', 3499.0, 'RPG', 'https://cdn.cloudflare.steamstatic.com/steam/apps/1091500/header.jpg', 'CD Projekt Red', 'OS: Windows 10, RAM: 12GB, GPU: GTX 1060', 'OS: Windows 11, RAM: 16GB, GPU: RTX 3070'),
('Terraria', 'Aventura y construcción en 2D', 499.0, 'Aventura', 'https://cdn.cloudflare.steamstatic.com/steam/apps/105600/header.jpg', 'Re-Logic', 'OS: Windows 7, RAM: 2GB, GPU: 256MB', 'OS: Windows 10, RAM: 4GB, GPU: 512MB'),
('Portal 2', 'Puzzles con portales cooperativo', 799.0, 'Puzzle', 'https://cdn.cloudflare.steamstatic.com/steam/apps/620/header.jpg', 'Valve', 'OS: Windows 7, RAM: 4GB, GPU: GTX 460', 'OS: Windows 10, RAM: 8GB, GPU: GTX 970'),
('Left 4 Dead 2', 'Shooter cooperativo de zombies', 599.0, 'FPS', 'https://cdn.cloudflare.steamstatic.com/steam/apps/550/header.jpg', 'Valve', 'OS: Windows 7, RAM: 2GB, GPU: 256MB', 'OS: Windows 10, RAM: 4GB, GPU: GTX 960');