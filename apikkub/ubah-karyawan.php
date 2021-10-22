<?php 
    include "koneksi.php";
    $id_user = $_POST['id_user'];
    $nama_pekerja = $_POST['nama_pekerja']; 
    $id_jabatan = $_POST['id_jabatan']; 
    $tgl_mulaitugas = $_POST['tgl_mulaitugas']; 
    $ttl = $_POST['ttl']; 
    $kartu_pengenal = $_POST['kartu_pengenal']; 
    $nomor_pengenal = $_POST['nomor_pengenal']; 
    $agama = $_POST['agama'];
    $jenis_kelamin = $_POST['jenis_kelamin'];  
    $status = $_POST['status'];
    $level = $_POST['level'];
    $pendidikan = $_POST['pendidikan'];   
    $keterampilan = $_POST['keterampilan'];
    $no_bpjskesehatan = $_POST['no_bpjskesehatan'];
    $no_bpjsketenagakerjaan = $_POST['no_bpjsketenagakerjaan'];
    $nohp = $_POST['no_hp']; 
    $alamat = $_POST['alamat']; 
    $password = $_POST['password']; 

   
        $query = mysqli_query($connect, "SELECT * FROM tb_user WHERE id_user='$id_user' ");
      
        if(mysqli_num_rows($query) > 0){

            mysqli_query($connect, "UPDATE tb_user SET
             nama_pekerja='$nama_pekerja',
             id_jabatan='$id_jabatan',
             tgl_mulaitugas ='$tgl_mulaitugas',
             ttl = '$ttl',
             kartu_pengenal = '$kartu_pengenal',
             nomor_pengenal = '$nomor_pengenal',
             agama ='$agama',
             jenis_kelamin = '$jenis_kelamin',
             status = '$status',
             level = '$level',
             pendidikan = '$pendidikan',
             keterampilan = '$keterampilan',
             no_bpjskesehatan = '$no_bpjskesehatan',
             no_bpjsketenagakerjaan	 = '$no_bpjsketenagakerjaan',
             no_hp = '$nohp',
             alamat = '$alamat'
             WHERE id_user='$id_user' ");
           
            $response = array(
                'error' => false,
                'message' => "Berhasil di ubah"
            );
        }
        else{
            $response = array(
                'error' => true,
                'message' => "Gagal di ubah"
            );

        }
   
    
    echo json_encode($response);
    
    mysqli_close($connect);
    
?>
