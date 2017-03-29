-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.25-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura para tabla ventas.detalleventa
CREATE TABLE IF NOT EXISTS `detalleventa` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codVenta` int(11) NOT NULL,
  `codProducto` int(11) NOT NULL,
  `cantidad` tinyint(4) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_DetalleVenta_Producto` (`codProducto`),
  KEY `FK_DetalleVenta_Venta` (`codVenta`),
  CONSTRAINT `FK_DetalleVenta_Producto` FOREIGN KEY (`codProducto`) REFERENCES `producto` (`codigo`),
  CONSTRAINT `FK_DetalleVenta_Venta` FOREIGN KEY (`codVenta`) REFERENCES `venta` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ventas.detalleventa: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `detalleventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleventa` ENABLE KEYS */;

-- Volcando estructura para tabla ventas.persona
CREATE TABLE IF NOT EXISTS `persona` (
  `codigo` tinyint(4) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL DEFAULT '0',
  `sexo` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ventas.persona: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` (`codigo`, `nombre`, `sexo`) VALUES
	(1, 'lucodev', 'M');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;

-- Volcando estructura para tabla ventas.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL DEFAULT '0',
  `precio` decimal(7,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ventas.producto: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`codigo`, `nombre`, `precio`) VALUES
	(1, 'Notebook', 350.00),
	(2, 'SmartTV', 1000.00),
	(3, 'Smartphone', 450.00);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Volcando estructura para tabla ventas.venta
CREATE TABLE IF NOT EXISTS `venta` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `codPersona` tinyint(4) NOT NULL,
  `monto` decimal(7,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_Venta_Persona` (`codPersona`),
  CONSTRAINT `FK_Venta_Persona` FOREIGN KEY (`codPersona`) REFERENCES `persona` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla ventas.venta: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
