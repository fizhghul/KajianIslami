<?php 
$server = "localhost";
$user = "hafizhnaufal";
$pass = "q10";
$database = "db_admin_hafizhnaufal";
$kon = mysql_connect($server,$user,$pass) or die(mysql_errno("Gagal"));
mysql_select_db($database) or die (mysql_errno("Gak ada database yang lo pilih!"));
?>