<?php 
	include 'connect.php';
	$eror = false;
	$folder	= '../uploads/';
	$file_type	= array('jpg','jpeg','png','gif','bmp','doc','docx','xls','xlsx','sql','JPG','JPEG','PNG','GIF','BMP','DOC','XLS','XLSX','SQL','mp4','MP4','mkv','MKV','3gp','3GP');
	$max_size	= 10000000;
	if(isset($_POST['simpan'])){
	$judul = $_POST['judul'];
	$nama = $_POST['nama'];
	$video = $_POST['link'];
	$gambar = $_POST['gambar'];
		$catat = mysql_query('INSERT INTO perkat(id_kategori,judul_perkat,gambar_perkat,video_perkat)
		values ("'.$nama.'","'.$judul.'","'.$gambar.'","'.$video.'")');
	
		if ($catat) {
			header('Location:http://31.220.53.18/hafizhnaufal/kajian/dashboardperkatdansemua.php');
		}else{
			echo '<p>Data gagal disimpan</p>';
			echo '<p><a href="dashboardperkatdansemua.php">Kembali</a></p>';
		}
	}
 ?>