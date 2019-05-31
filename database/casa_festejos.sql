-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-05-2019 a las 02:50:22
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `casa_festejos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad_cliente`
--

CREATE TABLE `actividad_cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `hora` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `actividad_cliente`
--

INSERT INTO `actividad_cliente` (`id`, `nombre`, `descripcion`, `hora`) VALUES
(2, 'Activiadadxd', 'Romper piñata xd', '12:30:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo_admin`
--

CREATE TABLE `articulo_admin` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `precio` int(22) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `articulo_admin`
--

INSERT INTO `articulo_admin` (`id`, `nombre`, `tipo`, `precio`) VALUES
(1, 'Silla Ejecutiva', 'SILLA', 2000),
(2, 'Mesa Redonda', 'MESA', 10000),
(3, 'Vino rojo', 'BEBIDA', 20000),
(4, 'Caviar', 'PLATILLO', 25000),
(5, 'Cumpleaños', 'MONTAJE', 200000),
(6, 'Boda', 'MONTAJE', 200000),
(7, 'Quinceaños', 'MONTAJE', 350000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo_cliente`
--

CREATE TABLE `articulo_cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `costo` int(22) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `articulo_cliente`
--

INSERT INTO `articulo_cliente` (`id`, `nombre`, `tipo`, `cantidad`, `costo`) VALUES
(2, 'Silla Ejecutiva', 'SILLA', 4, 2000),
(2, 'Mesa Redonda', 'MESA', 2, 10000),
(2, 'Cumpleaños', 'MONTAJE', 1, 10000),
(2, 'Caviar', 'PLATILLO', 2, 25000),
(2, 'Vino rojo', 'BEBIDA', 2, 20000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responsable`
--

CREATE TABLE `responsable` (
  `cedula` int(22) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `telefono` varchar(22) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `responsable`
--

INSERT INTO `responsable` (`cedula`, `nombre`, `apellido`, `telefono`, `email`) VALUES
(2, 'David', 'Sachica', '32312312', 'xd@gmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad_cliente`
--
ALTER TABLE `actividad_cliente`
  ADD KEY `id` (`id`);

--
-- Indices de la tabla `articulo_admin`
--
ALTER TABLE `articulo_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `articulo_cliente`
--
ALTER TABLE `articulo_cliente`
  ADD KEY `id` (`id`);

--
-- Indices de la tabla `responsable`
--
ALTER TABLE `responsable`
  ADD PRIMARY KEY (`cedula`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad_cliente`
--
ALTER TABLE `actividad_cliente`
  ADD CONSTRAINT `actividad_cliente_ibfk_1` FOREIGN KEY (`id`) REFERENCES `responsable` (`cedula`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `articulo_cliente`
--
ALTER TABLE `articulo_cliente`
  ADD CONSTRAINT `articulo_cliente_ibfk_1` FOREIGN KEY (`id`) REFERENCES `responsable` (`cedula`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
