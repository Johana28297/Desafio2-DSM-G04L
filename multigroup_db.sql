-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 29-05-2025 a las 00:18:50
-- Versión del servidor: 9.1.0
-- Versión de PHP: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `multigroup_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `document` varchar(20) NOT NULL,
  `type` enum('NATURAL','JURIDICA') NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `created_by` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `inactivated_at` timestamp NULL DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `document` (`document`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `clients`
--

INSERT INTO `clients` (`id`, `name`, `document`, `type`, `phone`, `email`, `address`, `status`, `created_by`, `created_at`, `updated_at`, `inactivated_at`, `company`) VALUES
(1, 'Rodrigo Rivas', '284672834-3', 'NATURAL', '2748-2947', 'ER222434@alumno.edu.udb', 'San Salvador, Soyapango', 'ACTIVE', NULL, '2025-05-26 15:52:49', NULL, NULL, NULL),
(2, 'Ernesto Antonio', '123456789-3', 'NATURAL', '2748-6474', 'EE20395867@alumno.edu.udb', 'La Union, La paz', 'INACTIVE', NULL, '2025-05-26 17:04:17', NULL, '2025-05-26 17:32:33', NULL),
(3, 'Alvaro Mota', '284677684-0', 'NATURAL', '9578-3945', 'JD382759@alumno.edu.udb', 'Cabanas, Zona Rosa', 'INACTIVE', NULL, '2025-05-26 17:28:48', NULL, '2025-05-26 17:32:36', NULL),
(4, 'Fernando Flores', '203846374-3', 'JURIDICA', '2745-2957', 'FF290704@alumno.edu.udb', 'San Jose, Guayabal', 'ACTIVE', NULL, '2025-05-26 21:49:27', NULL, NULL, NULL),
(5, 'Edgar Rivas', '274354657-2', 'NATURAL', '2748-1287', 'ER284673@gmail.com', 'San Salvador, Ciudad Delgado', 'ACTIVE', NULL, '2025-05-28 23:43:57', NULL, NULL, NULL),
(6, 'Yeymi Mejia', '847392653-3', 'NATURAL', '8473-7483', 'YM384629@gmail.com', 'Santa Ana, Coatepeque', 'ACTIVE', NULL, '2025-05-28 23:45:27', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `document` varchar(20) NOT NULL,
  `type` enum('NATURAL','JURIDICA') NOT NULL,
  `contract_type` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `created_by` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `inactivated_at` timestamp NULL DEFAULT NULL,
  `specialty` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `document` (`document`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `employees`
--

INSERT INTO `employees` (`id`, `name`, `document`, `type`, `contract_type`, `phone`, `email`, `address`, `status`, `created_by`, `created_at`, `updated_at`, `inactivated_at`, `specialty`) VALUES
(1, 'Alba Anaya', '283846784-2', 'NATURAL', 'PERMANENT', '2058-2958', 'AA204857@alumno.edu.udb', 'La Paz, Chalatenango', 'INACTIVE', NULL, '2025-05-26 19:49:40', NULL, '2025-05-26 19:49:58', NULL),
(2, 'Marcos Aguirre', '2738462945-2', 'JURIDICA', 'HOURLY', '2784-3294', 'MA234583@gmail.com', 'Soyapango, Las Margaritas', 'INACTIVE', NULL, '2025-05-26 20:42:28', NULL, '2025-05-28 23:57:03', NULL),
(3, 'Joel Ernesto', '282847684-0', 'NATURAL', 'PERMANENT', '7450-8080', 'JE392845@gmail.com', '', 'ACTIVE', NULL, '2025-05-26 21:38:57', NULL, NULL, NULL),
(4, 'Yeymi Johana', '284729375-2', 'JURIDICA', 'POR_HORAS', '2874-4574', 'YJ284739@gmail.com', 'La Paz, El Rosario', 'ACTIVE', NULL, '2025-05-28 23:53:35', NULL, NULL, NULL),
(5, 'Edgar Jose', '385472374-1', 'JURIDICA', 'POR_HORAS', '2837-1832', 'EJ293847@gmail.com', 'San Vicente, Santa Clara', 'ACTIVE', NULL, '2025-05-28 23:54:56', NULL, NULL, NULL),
(6, 'Hector Guillen', '948375285-2', 'NATURAL', 'PERMANENTE', '2846-7456', 'HG372647@gmail.com', 'La Unión, Pasaquina', 'ACTIVE', NULL, '2025-05-28 23:56:48', NULL, NULL, NULL),
(7, 'Gladis Miranda', '382748934-3', 'JURIDICA', 'PERMANENTE', '4758-3852', 'GM368492@gmail.com', 'San Miguel, Sesori', 'ACTIVE', NULL, '2025-05-28 23:58:13', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `quotations`
--

DROP TABLE IF EXISTS `quotations`;
CREATE TABLE IF NOT EXISTS `quotations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int NOT NULL,
  `total_cost` decimal(15,2) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `tentative_start_date` date DEFAULT NULL,
  `tentative_end_date` date DEFAULT NULL,
  `total_hours` int DEFAULT '0',
  `assignment_cost` decimal(10,2) DEFAULT '0.00',
  `additional_costs` decimal(10,2) DEFAULT '0.00',
  `total` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `client_id` (`client_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `quotations`
--

INSERT INTO `quotations` (`id`, `client_id`, `total_cost`, `start_date`, `end_date`, `created_by`, `created_at`, `tentative_start_date`, `tentative_end_date`, `total_hours`, `assignment_cost`, `additional_costs`, `total`) VALUES
(1, 3, NULL, NULL, NULL, 1, '2025-05-27 21:40:45', '2025-05-29', '2025-06-07', 0, 0.00, 0.00, 0.00),
(2, 4, NULL, NULL, NULL, 1, '2025-05-27 21:56:44', '2025-05-29', '2025-06-07', 0, 0.00, 0.00, 0.00),
(3, 4, NULL, NULL, NULL, 1, '2025-05-27 22:18:57', '2025-05-29', '2025-06-07', 0, 0.00, 0.00, 0.00),
(4, 5, NULL, NULL, NULL, 1, '2025-05-28 23:59:45', '2025-05-30', '2025-06-03', 0, 0.00, 0.00, 0.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `quotation_activities`
--

DROP TABLE IF EXISTS `quotation_activities`;
CREATE TABLE IF NOT EXISTS `quotation_activities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quotation_id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text,
  `start_datetime` datetime NOT NULL,
  `end_datetime` datetime NOT NULL,
  `estimated_hours` int NOT NULL,
  `employee_id` int NOT NULL,
  `hourly_cost` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `quotation_id` (`quotation_id`),
  KEY `employee_id` (`employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('ADMIN','EMPLOYEE','CLIENT') NOT NULL,
  `related_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `related_id`, `created_at`) VALUES
(2, 'admin', 'admin123', 'ADMIN', NULL, '2025-05-24 22:03:00'),
(12, 'Johana', 'DF240080', 'ADMIN', NULL, '2025-05-29 00:11:17'),
(8, 'Yohana', 'DF240080', 'EMPLOYEE', 4, '2025-05-28 23:53:35'),
(6, 'Rodrigo', '1234', 'ADMIN', NULL, '2025-05-26 20:04:05'),
(7, 'Joel', '7771', 'EMPLOYEE', 3, '2025-05-26 21:38:57'),
(9, 'Edgar', 'MH243098', 'EMPLOYEE', 5, '2025-05-28 23:54:56'),
(11, 'Gladis', 'RM191684', 'EMPLOYEE', 7, '2025-05-28 23:58:13'),
(16, 'Serrano', 'MH243098', 'ADMIN', NULL, '2025-05-29 00:14:34'),
(14, 'Rivas', 'RM191684', 'ADMIN', NULL, '2025-05-29 00:13:15'),
(15, 'Mejia', 'GS190405', 'ADMIN', NULL, '2025-05-29 00:13:59');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
