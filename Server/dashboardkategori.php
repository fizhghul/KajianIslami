<!DOCTYPE html>
<?php 
	include 'connect.php';
	include 'proseskategori.php';
 ?>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Dashboard Hafiizh</title>
	<script src="ckeditor/ckeditor.js"></script>
	<link rel="stylesheet" href="Bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="Bootstrap/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="Bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<style>
  	.navbar {
    margin-bottom: 0;
    background-color: #0066cc;
    z-index: 9999;
    border: 0;
    font-size: 14px !important;
    line-height: 1.42857143 !important;
    letter-spacing: 1px;
    border-radius: 0;
}
	.navbar li a, .navbar .navbar-brand {
    color: #fff !important;
}

	.navbar-default .navbar-toggle {
    border-color: transparent;
    color: #fff !important;
}
	</style>
</head>
<body>
 <nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="">Dashboard hafiizh</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="">Welcome, Hafiizh</a></li>
      </ul>
    </div>
  </div>
</nav>

<br><br><br><br>
<form action="proseskategori.php" method="post" enctype="multipart/form-data">
<div class="container">
	<table class="table" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td width="100">Nama Ust</td>
		<td>
		<input type="text" name="nama" class="form-control" />
		</div>
		</td>
	</tr>
	<tr>
		<td width="100">Gambar</td>
		<td><input type="file" name="gambar" /></td>
	</tr>
</table>
<div class="container">
<input type="submit" class="btn btn-success" name="simpan" value="Simpan" />
<input type="reset" class="btn btn-danger" name="reset" value="Reset" />
</div>
</div>
</form>  
</body>
</html>