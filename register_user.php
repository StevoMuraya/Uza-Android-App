<?php 
if ($_SERVER['REQUEST_METHOD'] =='POST'){
	$fname				= filter_var($_POST['fname'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$lname				= filter_var($_POST['lname'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$email				= filter_var($_POST['email'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$phone				= filter_var($_POST['phone'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$county				= filter_var($_POST['county'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$password			= filter_var($_POST['password'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	
	$encrypted_password = md5($password);
	require_once('config.php');


	$sql = "INSERT INTO app_users(fname,lname,email,phone,password,county,date_time_reg) VALUES ('$fname','$lname','$email','$phone','$encrypted_password','$county',now())";
	if (mysqli_query($db_handle,$sql)) {
		echo "Successfully Registered";
	}else{
		echo "Failed to Register";
	}
	mysqli_close($db_handle);
}else{
	echo 'Error in capturing data';
} 
?>