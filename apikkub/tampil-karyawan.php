<?php 
	include "koneksi.php";
	
	$query = mysqli_query($connect, "SELECT * FROM tb_user p join tb_jabatan w on p.id_jabatan=w.id_jabatan  ");
	
	$json = array();
	
	while($row = mysqli_fetch_assoc($query)){
		$json[] = $row;
	}
	
	$rest = array(
		'error' => false,
		'message' => "message",
		'semuakaryawan' => $json
	); 
	
	echo json_encode($rest);
	
	mysqli_close($connect);
	
?>