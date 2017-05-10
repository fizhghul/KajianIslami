<?php 
	include 'connect.php';
	$eror = false;
	$folder	= '../uploads/';
	$file_type	= array('jpg','jpeg','png','gif','bmp','doc','docx','xls','xlsx','sql','JPG','JPEG','PNG','GIF','BMP','DOC','XLS','XLSX','SQL');
	$max_size	= 10000000;
	if(isset($_POST['simpan'])){
	$nama = $_POST['nama'];
	$file_name = $_FILES['gambar']['name'];
	$file_size = $_FILES['gambar']['size'];
	$explode = explode('.',$file_name);
	$extensi = $explode[count($explode)-1];

		if(!in_array($extensi,$file_type)){
			$eror = true;
			$pesan .= '- Type file tidak sesuai<br />';
		}
		if($file_size > $max_size){
			$eror = true;
			$pesan .= '- Ukuran file melebihi batas maximum<br />';
		}
		if($eror == true){
			echo '<div id="eror">'.$pesan.'</div>';
		}
		else{
			if(move_uploaded_file($_FILES['gambar']['tmp_name'], $folder.$file_name)){
				$catat = mysql_query('INSERT INTO list_ust(nama_kategori,gambar_kategori)
				 values ("'.$nama.'", "'.$file_name.'")');
				header('Location:http://31.220.53.18/hafizhnaufal/kajian/dashboardkategori.php');
			} else{
				echo '<p>Data gagal disimpan</p>';
				echo '<p><a href="dashboardkategori.php">Kembali</a></p>';
			}
		}
	}
 ?>