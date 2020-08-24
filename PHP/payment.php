<?php 
if ($_SERVER['REQUEST_METHOD'] =='POST'){
	$user_id				= filter_var($_POST['user_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$payment_amount			= filter_var($_POST['payment_amount'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$payment_method			= filter_var($_POST['payment_method'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$payment_code			= filter_var($_POST['payment_code'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	
	require_once('config.php');

	$sql = "INSERT INTO payment(user_id,payment_amount,payment_method,payment_code,date_time) 
            VALUES ('$user_id','$payment_amount','$payment_method','$payment_code',now())";
	if (mysqli_query($db_handle,$sql)) {
		echo "Successfully Updated";
	}else{
		echo "Failed to Update";
	}
	mysqli_close($db_handle);
}else{
	echo 'Error in capturing data';
} 
?>