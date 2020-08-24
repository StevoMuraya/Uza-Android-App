<?php 
if ($_SERVER['REQUEST_METHOD'] =='POST'){
	$cart_id				= filter_var($_POST['cart_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$product_id				= filter_var($_POST['product_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$user_id				= filter_var($_POST['user_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$product_name			= filter_var($_POST['product_name'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$product_quantity		= filter_var($_POST['product_quantity'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
    $price			        = filter_var($_POST['price'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
    $order_code			    = filter_var($_POST['order_code'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	
	require_once('config.php');

	$sql = "INSERT INTO purchases(cart_id,product_id,user_id,product_name,product_quantity,price,order_code,d_date) 
            VALUES ('$cart_id','$product_id','$user_id','$product_name','$product_quantity','$price','$order_code',now())";
	if (mysqli_query($db_handle,$sql)) {
		echo "Successfully Updated2";
	}else{
		echo "Failed to Updated2";
	}
	mysqli_close($db_handle);
}else{
	echo 'Error in capturing data';
} 
?>