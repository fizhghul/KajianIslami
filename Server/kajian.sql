-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 09, 2017 at 10:22 PM
-- Server version: 5.5.38
-- PHP Version: 5.4.4-14+deb7u14

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kajian`
--

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `list_ust` (
  `id_kategori` int(255) NOT NULL AUTO_INCREMENT,
  `nama_kategori` varchar(255) NOT NULL,
  `gambar_kategori` varchar(255) NOT NULL,
  PRIMARY KEY (`id_kategori`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `list_ust` (`id_kategori`, `nama_kategori`, `gambar_kategori`) VALUES
(1, 'Ustadz Abdullah Zaen ', 'http://31.220.53.18/hafizhnaufal/uploads/abdullah-zaen.jpg'),
(2, 'Ustadz Ahmad Zainuddin Al-Banjary', 'http://31.220.53.18/hafizhnaufal/uploads/Ustadz-Ahmad-Zainuddin-Al-Banjary-Hafizhahullah.jpg'),
(3, 'Ustadz Syafiq Reza Basalamah', 'http://31.220.53.18/hafizhnaufal/uploads/pesisirnews_Ustadz-Syafiq-Reza-Basalamah-Akan-Isi-Kajian-Tabligh-Akbar-di-Aceh--Ini-Jadwalnya----.jpg'),
(4, 'Ustadz Khalid Basalamah', 'http://31.220.53.18/hafizhnaufal/uploads/dosabesar.png'),
(5, 'Ustadz Badru Salam', 'http://31.220.53.18/hafizhnaufal/uploads/hqdefault.jpg'),
(6, 'Ustadz Firanda Andirja', 'http://31.220.53.18/hafizhnaufal/uploads/Firanda-Andirja-MA.png'),
(7, 'Ustadz Ammi Nur Baits', 'http://31.220.53.18/hafizhnaufal/uploads/budak-dunia-ustad-ammi-nur-baits-650f-640x360-00008.jpg'),
(8, 'Ustadz Erwandi Tarmizi', 'http://31.220.53.18/hafizhnaufal/uploads/ZyFIhl7V.jpeg'),
(9, 'Ustadz Maududi Abdullah', 'http://31.220.53.18/hafizhnaufal/uploads/hqdefaulttt.jpg'),
(10, 'Ustadz Fachrudin Nu''man', 'http://31.220.53.18/hafizhnaufal/uploads/maxresdefaultt.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `perkat`
--

CREATE TABLE IF NOT EXISTS `perkat` (
  `id_perkat` int(255) NOT NULL AUTO_INCREMENT,
  `id_kategori` int(255) NOT NULL,
  `judul_perkat` varchar(255) NOT NULL,
  `gambar_perkat` varchar(255) NOT NULL,
  `video_perkat` varchar(255) NOT NULL,
  `audio_perkat` varchar(255) NOT NULL,
  PRIMARY KEY (`id_perkat`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `perkat`
--

INSERT INTO `perkat` (`id_perkat`, `id_kategori`, `judul_perkat`, `gambar_perkat`, `video_perkat`, `audio_perkat`) VALUES
(1, 1, 'Kunci Rizky Berkah', 'http://31.220.53.18/hafizhnaufal/uploads/abdullah-zaen.jpg', '9TTZoar_Wq8', 'http://fiizbghul.com/audio/Ustadz_Abdullah_Zaen_MA-Ceramah_Singkat.mp3'),
(2, 1, 'Kenapa-Ustadz', 'http://31.220.53.18/hafizhnaufal/uploads/abdullah-zaen.jpg', 'jW7YkStl41M', 'http://fiizbghul.com/audio/Kenapa-Ustadz.mp3'),
(3, 1, 'Omongan Orang', 'http://31.220.53.18/hafizhnaufal/uploads/abdullah-zaen.jpg', 'CP0wgQE9NuA', 'http://fiizbghul.com/audio/Cara_Agar_Tidak_Malas_&_Mengantuk_Saat_Shalat_Taha-Ustadz_Khalid_Basalamah.mp3'),
(4, 2, '7 Amalan Bermanfaat Setelah Kematian', 'http://31.220.53.18/hafizhnaufal/uploads/Ustadz-Ahmad-Zainuddin-Al-Banjary-Hafizhahullah.jpg', '34QJV5Cdbg8', ''),
(5, 2, 'Mengenal Seluk Beluk Bid ah', 'http://31.220.53.18/hafizhnaufal/uploads/Ustadz-Ahmad-Zainuddin-Al-Banjary-Hafizhahullah.jpg', 'I47gBvybiQQ', ''),
(6, 3, 'Batasan Kita Mengejar Dunia', 'http://31.220.53.18/hafizhnaufal/uploads/pesisirnews_Ustadz-Syafiq-Reza-Basalamah-Akan-Isi-Kajian-Tabligh-Akbar-di-Aceh--Ini-Jadwalnya----.jpg', 'k1xN2izSxWY', ''),
(7, 3, 'Sumber Kebahagiaan Seseorang Didunia', 'http://31.220.53.18/hafizhnaufal/uploads/pesisirnews_Ustadz-Syafiq-Reza-Basalamah-Akan-Isi-Kajian-Tabligh-Akbar-di-Aceh--Ini-Jadwalnya----.jpg', 'uMjONPUNjHc', ''),
(8, 3, 'Cantik karena cinta', 'http://31.220.53.18/hafizhnaufal/uploads/pesisirnews_Ustadz-Syafiq-Reza-Basalamah-Akan-Isi-Kajian-Tabligh-Akbar-di-Aceh--Ini-Jadwalnya----.jpg', '1R8hwSbLpMI', ''),
(9, 2, 'Siapakah Penghina Al-Quran?', 'http://31.220.53.18/hafizhnaufal/uploads/Ustadz-Ahmad-Zainuddin-Al-Banjary-Hafizhahullah.jpg', 'q_XgAzOpnno', ''),
(10, 4, 'Bisakah Aku Kaya ?', 'http://31.220.53.18/hafizhnaufal/uploads/dosabesar.png', '5Yu47tJekXY', ''),
(11, 4, 'Inilah Penyebab Rezeki Kita Mampet', 'http://31.220.53.18/hafizhnaufal/uploads/dosabesar.png', '6eedAGz1qnM', ''),
(12, 4, 'Kunci Menjadi Kaya', 'http://31.220.53.18/hafizhnaufal/uploads/dosabesar.png', '_BV5zC7d81g', ''),
(13, 5, 'Kiamat Diambang Pintu', 'http://31.220.53.18/hafizhnaufal/uploads/hqdefault.jpg', 'Aabtnkjhv2s', ''),
(14, 5, 'Amalan Yang Paling Berat Timbangan nya', 'http://31.220.53.18/hafizhnaufal/uploads/hqdefault.jpg', 'zCJFd2fydZU', ''),
(15, 5, '18 Golongan Yang Di Doakan Malaikat', 'http://31.220.53.18/hafizhnaufal/uploads/hqdefault.jpg', 'oSAMDNAujZE', ''),
(16, 6, 'Pluralisme pada Zaman Nabi Shallallahu ''alaihi Wa Sallam', 'http://31.220.53.18/hafizhnaufal/uploads/Firanda-Andirja-MA.png', 'Vi9-Bp2Cdg0', ''),
(17, 6, 'Amalan Shalih Bukan Pembayar untuk Masuk Surga', 'http://31.220.53.18/hafizhnaufal/uploads/Firanda-Andirja-MA.png', 'ra4Oh52ocOA', ''),
(18, 6, 'Do''a Bisa Merubah Takdir?', 'http://31.220.53.18/hafizhnaufal/uploads/Firanda-Andirja-MA.png', 'P6AvG4M9t2I', ''),
(19, 7, 'Ritual Salaman Setelah Sholat', 'http://31.220.53.18/hafizhnaufal/uploads/budak-dunia-ustad-ammi-nur-baits-650f-640x360-00008.jpg', 'sLgL1a7cVyw', ''),
(20, 7, 'Jual Beli Pulsa Itu Riba? ', 'http://31.220.53.18/hafizhnaufal/uploads/budak-dunia-ustad-ammi-nur-baits-650f-640x360-00008.jpg', 'iq4PShqd1XY', ''),
(21, 7, 'Andai Rokok Ada di Zaman Nabi', 'http://31.220.53.18/hafizhnaufal/uploads/budak-dunia-ustad-ammi-nur-baits-650f-640x360-00008.jpg', 'IPvo_BRPfec', ''),
(22, 8, 'Hukum Paytren', 'http://31.220.53.18/hafizhnaufal/uploads/ZyFIhl7V.jpeg', '4SZmLcYPj4E', ''),
(23, 8, 'Kata Siapa Waktu Adalah Uang? ', 'http://31.220.53.18/hafizhnaufal/uploads/ZyFIhl7V.jpeg', 'nt6JACx55xI', ''),
(24, 8, 'Lelaki yang Jantan', 'http://31.220.53.18/hafizhnaufal/uploads/ZyFIhl7V.jpeg', '6z4JobVlM_I', ''),
(25, 9, 'Jangan Merasa Diri Sudah Sholeh', 'http://31.220.53.18/hafizhnaufal/uploads/hqdefault (1).jpg', 'gvOJ8-0wp_s', ''),
(26, 9, 'Teringat Dosa', 'http://31.220.53.18/hafizhnaufal/uploads/hqdefault (1).jpg', 'MWnfqBh1uW0', ''),
(27, 9, 'Apa itu Ikhlas ?', 'http://31.220.53.18/hafizhnaufal/uploads/hqdefault (1).jpg', 'z_aYsk9Fajo', ''),
(28, 10, 'Contoh Syirik Ucapan', 'http://31.220.53.18/hafizhnaufal/uploads/maxresdefaultt.jpg', 'a-Wu1vQEj6I', ''),
(29, 10, 'Tips Memantapkan Iman', 'http://31.220.53.18/hafizhnaufal/uploads/maxresdefaultt.jpg', 'ncm91FEHkBE', ''),
(30, 10, 'Hutang Terlebih Dahulu Ataukah Berqurban ?', 'http://31.220.53.18/hafizhnaufal/uploads/maxresdefaultt.jpg', 'u1J6Gj_vzls', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
