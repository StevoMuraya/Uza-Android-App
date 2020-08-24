<?php 
if ($_SERVER['REQUEST_METHOD'] =='POST'){
	$quantity				= filter_var($_POST['quantity'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$product_id				= filter_var($_POST['product_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	
	require_once('config.php');

	$sql = "UPDATE products SET quantity = '$quantity' WHERE product_id = '$product_id'";
	if (mysqli_query($db_handle,$sql)) {
		echo "Successfully Updated Product";
	}else{
		echo "Failed to Update Product";
	}
	mysqli_close($db_handle);
}else{
	echo 'Error in capturing data';
} 
?>