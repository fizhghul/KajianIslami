<?php
include 'connect.php';
$sql = mysql_query('SELECT * from list_ust order by id_kategori desc') or die(mysql_error());
$response = array();
if (mysql_num_rows($sql)>0){
    $response['kategori'] = array();
    while ($row=mysql_fetch_array($sql)){
        $data = array();
        $data['id_kategori'] = $row['id_kategori'];
        $data['nama_kategori'] = $row['nama_kategori'];
        $data['gambar_kategori'] = $row['gambar_kategori'];
        array_push($response['kategori'],$data);
    }
    $response['result'] = 1;
    $response['msg'] = "Semua data kategori";
    echo json_encode($response);
} else {
    $response['result'] = 0;
    $response['msg'] = "Tidak ada data kategori";
}
?>