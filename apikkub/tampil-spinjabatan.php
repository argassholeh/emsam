<?php 
	include "koneksi.php";
	
	$query = mysqli_query($connect, "SELECT id_jabatan, jabatan FROM tb_jabatan ORDER BY id_jabatan ");
	
	
	$json = array();
	
	while($row = mysqli_fetch_assoc($query)){
		$json[] = $row;
		// $json[]  = $row['jenis_kategori'];
		// $tampil = json_decode($row['penilaian'], true);
	}
	
	$rest = array(
		'error' => false,
		'message' => "message",
		'semuajabatan' => $json
	); 
	
	echo json_encode($rest);
	
	mysqli_close($connect);
	
?>