-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 21, 2021 at 01:00 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_kkub`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_jabatan`
--

CREATE TABLE IF NOT EXISTS `tb_jabatan` (
  `id_jabatan` int(11) NOT NULL,
  `jabatan` varchar(55) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jabatan`
--

INSERT INTO `tb_jabatan` (`id_jabatan`, `jabatan`) VALUES
(1, 'Paramedis Klinik'),
(2, 'Pemeliharaan Lampu');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE IF NOT EXISTS `tb_user` (
  `id_user` int(11) NOT NULL,
  `nama_pekerja` varchar(30) NOT NULL,
  `username` varchar(15) NOT NULL,
  `id_jabatan` int(11) NOT NULL,
  `tgl_mulaitugas` date NOT NULL,
  `ttl` varchar(55) NOT NULL,
  `kartu_pengenal` enum('KTP','SIM') NOT NULL,
  `nomor_pengenal` char(20) NOT NULL,
  `agama` varchar(20) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `status` enum('PKWT') NOT NULL,
  `level` enum('admin','user') NOT NULL,
  `pendidikan` varchar(30) NOT NULL,
  `keterampilan` varchar(55) NOT NULL,
  `no_bpjskesehatan` char(55) NOT NULL,
  `no_bpjsketenagakerjaan` char(55) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `alamat` text NOT NULL,
  `foto` text,
  `password` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `nama_pekerja`, `username`, `id_jabatan`, `tgl_mulaitugas`, `ttl`, `kartu_pengenal`, `nomor_pengenal`, `agama`, `jenis_kelamin`, `status`, `level`, `pendidikan`, `keterampilan`, `no_bpjskesehatan`, `no_bpjsketenagakerjaan`, `no_hp`, `alamat`, `foto`, `password`) VALUES
(1, 'PUJI HARTONO', 'pujihartono', 1, '2017-01-01', 'Probolinggo, 03 Juli 1987', 'KTP', '3513110307870001', 'ISLAM', 'L', 'PKWT', 'admin', 'SMA', 'SERTIFIKAT PARAMEDIS', '10032304247', '0001139158506', '0812-9661-5867', 'Dsn. Koloran RT 019 RW 005 Kotaanyar Probolinggo', '', '25d55ad283aa400af464c76d713c07ad'),
(2, 'FAUZI / Help Yunior', 'fauzi', 2, '2012-04-15', 'Probolinggo, 24-11-1987', 'KTP', '3513112411870001', 'Islam', 'L', 'PKWT', 'user', 'SLTA', 'SERTIFIKAT TEKNISI LISTRIK', '12009351201', '0001144926663', '085257368017', 'Dsn. II Utara, Ds sambirampak Kidul-kotaanyar', '', '25d55ad283aa400af464c76d713c07ad');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_jabatan`
--
ALTER TABLE `tb_jabatan`
  ADD PRIMARY KEY (`id_jabatan`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_jabatan`
--
ALTER TABLE `tb_jabatan`
  MODIFY `id_jabatan` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
