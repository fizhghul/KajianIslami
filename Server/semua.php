<?php
include 'connect.php';
$sql = mysql_query('SELECT * from perkat order by judul_perkat asc') or die(mysql_error());
$response = array();
if (mysql_num_rows($sql)>0){
    $response['perkat'] = array();
    while ($row=mysql_fetch_array($sql)){
        $data = array();
        $data['id_perkat'] = $row['id_perkat'];
        $data['judul_perkat'] = $row['judul_perkat'];
        $data['gambar_perkat'] = $row['gambar_perkat'];
        $data['video_perkat'] = $row['video_perkat'];
        $data['audio_perkat'] = $row['audio_perkat'];
        array_push($response['perkat'],$data);
    }
    $response['result'] = 1;
    $response['msg'] = "Semua data perkat";
    echo json_encode($response);
} else {
    $response['result'] = 0;
    $response['msg'] = "Tidak ada data kategori";
}
?>