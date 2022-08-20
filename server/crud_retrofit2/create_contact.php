<?php
include 'connection.php';
class contact
{
}

$address = $_POST['address'];
$name = $_POST['name'];
$phone_number = $_POST['phone_number'];

$query = "INSERT INTO contacts (name, phone_number, address) 
VALUES ('$name', '$phone_number', '$address')";
$result = mysqli_query($koneksi, $query);


if ($result) {
    $response = new contact();
    $response->status = "success";
    die(json_encode($response));
} else {
    $response = new contact();
    $response->status = "failed";
    die(json_encode($response));
}
