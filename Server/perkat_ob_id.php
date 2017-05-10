<?php
include 'connect.php';
 if (isset($_POST['id_kategori'])){
     $id_kategori = $_POST['id_kategori'];
     $sql = mysql_query("SELECT list_ust.*,perkat.* from list_ust,perkat WHERE perkat.id_kategori=list_ust.id_kategori &&
        perkat.id_kategori ='$id_kategori' order by id_perkat desc")or die(mysql_error());

    $response = array();
    if (mysql_num_rows($sql) > 0){
        $response['album'] = array();
        while ($row = mysql_fetch_array($sql)){
            $data = array();
            $data['id_perkat'] = $row['id_perkat'];
            $data['judul_perkat'] = $row['judul_perkat'];
            $data['gambar_perkat'] = $row['gambar_perkat'];
            $data['video_perkat'] = $row['video_perkat'];
            $data['audio_perkat'] = $row['audio_perkat'];
            array_push($response['album'],$data);
        }
            $response['result'] = 1;
            $response['msg'] = "semua data di perkat";
            echo json_encode($response);
        } else {
            $response['result'] = 0;
            $response['msg'] = "Request time out";
            echo json_encode($response);
        }
    }
?>