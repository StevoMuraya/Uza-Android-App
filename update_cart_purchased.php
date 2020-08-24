<?php 
if ($_SERVER['REQUEST_METHOD'] =='POST'){
	$cart_id				= filter_var($_POST['cart_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$user_id				= filter_var($_POST['user_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	
	require_once('config.php');

	$sql = "UPDATE cart SET purchase = 1 WHERE cart_id = '$cart_id' AND user_id = '$user_id'";
	if (mysqli_query($db_handle,$sql)) {
		echo "Successfully Updated Cart";
	}else{
		echo "Failed to Update Cart";
	}
	mysqli_close($db_handle);
}else{
	echo 'Error in capturing data';
} 
?>