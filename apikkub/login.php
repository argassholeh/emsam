<?php 
	include "koneksi.php";
    $username =$_POST['username'];
    $password = md5($_POST['password']);

    if (isset($username) && isset($password)) {
        $query = mysqli_query($connect, "SELECT * FROM tb_user WHERE username='$username' AND password='$password' ");
        
        if (mysqli_num_rows($query) > 0) {
            $value = mysqli_fetch_array($query);
            
            $query = mysqli_query($connect, $sql);
            $data = mysqli_fetch_array($query);
            
            
            $response["error"] = FALSE;
            $response["key"] = $value["id_user"];
            $response["user"]["username"] = $value["username"];
            $response["user"]["nama_pekerja"] = $value["nama_pekerja"];
            $response["user"]["level"] = $value["level"];
            $response["user"]["no_hp"] = $value["no_hp"];
            
            echo json_encode($response);
        }else{
            $response["error"] = TRUE;
            $response["error_msg"] = "Login gagal, username atau password salah";
            
            echo json_encode($response);
        }

        mysqli_close($connect);
        
    }else{
        $response["error"] = TRUE;
        $response["error_msg"] = "Parameter untuk username dan password ada yang kurang";
        echo json_encode($response);
    }