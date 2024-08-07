-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.40 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla unidad2.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `ID` tinyint(3) unsigned NOT NULL,
  `NOMBRE` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `DIRECCION` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `POBLACION` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `TELEF` varchar(20) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `NIF` varchar(10) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla unidad2.clientes: ~4 rows (aproximadamente)
INSERT INTO `clientes` (`ID`, `NOMBRE`, `DIRECCION`, `POBLACION`, `TELEF`, `NIF`) VALUES
	(1, 'MARIA SERRANO', 'C/Las Flores 23', 'Guadalajara', '949876655', '34343434L'),
	(2, 'PEDRO BRAVO', 'C/Galiano 6', 'Guadalajara', '949256376', '2256880E'),
	(3, 'MANUEL SERRA', 'Av Atance 24', 'Guadalajara', '949800090', '1234567E'),
	(4, 'ALICIA PÉREZ', 'C/La Azucena 123', 'Talavera', '925678090', '56564564J');

-- Volcando estructura para tabla unidad2.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `ID` mediumint(8) unsigned NOT NULL,
  `DESCRIPCION` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `STOCKACTUAL` mediumint(8) unsigned DEFAULT '0',
  `STOCKMINIMO` mediumint(8) unsigned DEFAULT '0',
  `PVP` decimal(8,2) DEFAULT '0.00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla unidad2.productos: ~5 rows (aproximadamente)
INSERT INTO `productos` (`ID`, `DESCRIPCION`, `STOCKACTUAL`, `STOCKMINIMO`, `PVP`) VALUES
	(4, 'Diccionario Maria Moliner 2 tomos', 55, 5, 43.00),
	(5, 'Impresora HP Deskjet F370', 10, 1, 30.65),
	(6, 'Pen Drive 8 Gigas', 52, 5, 7.00),
	(7, 'Ratón óptico inalámbrico Logitecht', 14, 2, 15.00),
	(8, 'El señor de los anillos, 3 DVDs', 8, 2, 25.00);

-- Volcando estructura para tabla unidad2.ventas
CREATE TABLE IF NOT EXISTS `ventas` (
  `IDVENTA` smallint(5) unsigned NOT NULL,
  `FECHAVENTA` date NOT NULL,
  `IDCLIENTE` tinyint(3) unsigned NOT NULL,
  `IDPRODUCTO` mediumint(8) unsigned NOT NULL,
  `CANTIDAD` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`IDVENTA`),
  KEY `VENTAS_PROD` (`IDPRODUCTO`),
  KEY `VENTAS_CLI` (`IDCLIENTE`),
  CONSTRAINT `VENTAS_CLI` FOREIGN KEY (`IDCLIENTE`) REFERENCES `clientes` (`ID`),
  CONSTRAINT `VENTAS_PROD` FOREIGN KEY (`IDPRODUCTO`) REFERENCES `productos` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla unidad2.ventas: ~5 rows (aproximadamente)
INSERT INTO `ventas` (`IDVENTA`, `FECHAVENTA`, `IDCLIENTE`, `IDPRODUCTO`, `CANTIDAD`) VALUES
	(1, '2012-07-16', 1, 4, 3),
	(2, '2012-07-17', 4, 5, 2),
	(3, '2012-07-19', 2, 5, 1),
	(4, '2012-08-20', 1, 6, 5),
	(5, '2012-08-22', 3, 4, 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
