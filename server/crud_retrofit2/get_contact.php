<?php

include 'connection.php';

class emp
{
}

$query = "Select * from contacts order by id";
$result = mysqli_query($koneksi, $query);

$arraydata = array();

while ($baris = mysqli_fetch_assoc($result)) {
    $arraydata[] = $baris;
}
echo json_encode($arraydata);
