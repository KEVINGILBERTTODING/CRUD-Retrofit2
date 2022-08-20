<?php

include 'connection.php';

class contact
{
}


$id = $_POST['id'];
$address = $_POST['address'];
$name = $_POST['name'];
$phone_number = $_POST['phone_number'];

$query = "UPDATE contacts SET name ='$name', address = '$address', phone_number = '$phone_number'
where id = '$id'";

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
