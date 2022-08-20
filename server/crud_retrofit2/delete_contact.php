<?php
include 'connection.php';

class contact
{
}

$id = $_POST['id'];


$query = "DELETE FROM contacts WHERE id = '$id'";

$result = mysqli_query($koneksi, $query);

if ($result) {
    $response = new contact();
    $response->status = "success";
    die(json_encode($response));
} else {
    $response = new contact();
    $response->status = "success";
    die(json_encode($response));
}
