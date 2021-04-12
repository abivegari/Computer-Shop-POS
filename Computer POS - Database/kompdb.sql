/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 10.1.30-MariaDB : Database - kompdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kompdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `kompdb`;

/*Table structure for table `barang` */

DROP TABLE IF EXISTS `barang`;

CREATE TABLE `barang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `lama_garansi` varchar(20) DEFAULT NULL,
  `id_kategori` int(11) DEFAULT NULL,
  `id_merk` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kategori` (`id_kategori`),
  KEY `id_merk` (`id_merk`),
  CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `barang_ibfk_2` FOREIGN KEY (`id_merk`) REFERENCES `merk` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `barang` */

insert  into `barang`(`id`,`nama`,`harga`,`stok`,`lama_garansi`,`id_kategori`,`id_merk`) values 
(1,'GTX 1060',4000000,3,'3 Tahun',3,1),
(2,'GTX 1070',6000000,5,'3 Tahun',3,1),
(3,'Core i5',3000000,5,'3 Tahun',1,2),
(4,'Core i7',4000000,4,'3 Tahun',1,2),
(5,'8GB DDR4',1000000,5,'2 Tahun',2,3),
(6,'16GB DDR4',2000000,4,'2 Tahun',2,3);

/*Table structure for table `barang_cacat` */

DROP TABLE IF EXISTS `barang_cacat`;

CREATE TABLE `barang_cacat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_barang` varchar(30) DEFAULT NULL,
  `tgl_retur` date DEFAULT NULL,
  `deskripsi` varchar(25) DEFAULT NULL,
  `id_penjualan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_penjualan` (`id_penjualan`),
  CONSTRAINT `barang_cacat_ibfk_1` FOREIGN KEY (`id_penjualan`) REFERENCES `penjualan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `barang_cacat` */

insert  into `barang_cacat`(`id`,`nama_barang`,`tgl_retur`,`deskripsi`,`id_penjualan`) values 
(1,'GTX 1070','2018-12-04','Fan Rusak',1);

/*Table structure for table `detail_penjualan` */

DROP TABLE IF EXISTS `detail_penjualan`;

CREATE TABLE `detail_penjualan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jumlah` int(11) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `tgl_batas_garansi` date DEFAULT NULL,
  `id_penjualan` int(11) DEFAULT NULL,
  `id_barang` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_penjualan` (`id_penjualan`),
  KEY `id_barang` (`id_barang`),
  CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`id_penjualan`) REFERENCES `penjualan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `detail_penjualan` */

insert  into `detail_penjualan`(`id`,`jumlah`,`harga`,`tgl_batas_garansi`,`id_penjualan`,`id_barang`) values 
(1,2,8000000,'2018-12-11',1,1),
(2,1,4000000,'2018-12-11',2,4),
(3,1,2000000,'2018-12-11',2,6);

/*Table structure for table `jabatan` */

DROP TABLE IF EXISTS `jabatan`;

CREATE TABLE `jabatan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `jabatan` */

insert  into `jabatan`(`id`,`nama`) values 
(1,'Admin'),
(2,'Kasir'),
(3,'Gudang');

/*Table structure for table `kategori` */

DROP TABLE IF EXISTS `kategori`;

CREATE TABLE `kategori` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `kategori` */

insert  into `kategori`(`id`,`nama`) values 
(1,'Processor'),
(2,'RAM'),
(3,'VGA');

/*Table structure for table `merk` */

DROP TABLE IF EXISTS `merk`;

CREATE TABLE `merk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merk` varchar(20) DEFAULT NULL,
  `distributor` varchar(20) DEFAULT NULL,
  `alamat_dist` varchar(30) DEFAULT NULL,
  `no_telp_dist` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `merk` */

insert  into `merk`(`id`,`merk`,`distributor`,`alamat_dist`,`no_telp_dist`) values 
(1,'Nvidia','Nvidia Indonesia','Jakarta','0856'),
(2,'Intel','Intel Indonesia','Jakarta','0856'),
(3,'Kingston','Kingston Indonesia','Jakarta','0856');

/*Table structure for table `pegawai` */

DROP TABLE IF EXISTS `pegawai`;

CREATE TABLE `pegawai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_depan` varchar(20) DEFAULT NULL,
  `nama_belakang` varchar(20) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `id_jabatan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pegawai_ibfk_1` (`id_jabatan`),
  CONSTRAINT `pegawai_ibfk_1` FOREIGN KEY (`id_jabatan`) REFERENCES `jabatan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `pegawai` */

insert  into `pegawai`(`id`,`nama_depan`,`nama_belakang`,`username`,`password`,`alamat`,`no_telp`,`id_jabatan`) values 
(1,'Abi','Vegari','admin','123','Cimahi','0856',1),
(2,'Wiby','Prawira','kasir','123','Bandung','0856',2),
(3,'Rangga','Adikusuma','gudang','123','Bandung','0856',3);

/*Table structure for table `penjualan` */

DROP TABLE IF EXISTS `penjualan`;

CREATE TABLE `penjualan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tgl_penjualan` date DEFAULT NULL,
  `total` double DEFAULT NULL,
  `id_pegawai` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pegawai` (`id_pegawai`),
  CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `penjualan` */

insert  into `penjualan`(`id`,`tgl_penjualan`,`total`,`id_pegawai`) values 
(1,'2018-12-04',8000000,1),
(2,'2018-12-04',6000000,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
