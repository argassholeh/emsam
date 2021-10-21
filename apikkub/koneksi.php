<?php
    session_start();
    error_reporting(0);
    date_default_timezone_set('Asia/Jakarta');

    $host = 'localhost';
    $user = 'root';
    $pass = '';
    $db = 'db_kkub';

    $connect = mysqli_connect($host, $user, $pass, $db) or die ('Gagal menghubungkan ke Database!');
?>
