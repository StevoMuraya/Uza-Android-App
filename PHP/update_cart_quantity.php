<?php 
if ($_SERVER['REQUEST_METHOD'] =='POST'){
	$quantity				= filter_var($_POST['quantity'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$cart_id				= filter_var($_POST['cart_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$user_id				= filter_var($_POST['user_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	
	require_once('config.php');

	$sql = "UPDATE cart SET quantity = '$quantity' WHERE cart_id = '$cart_id' AND user_id = '$user_id'";
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