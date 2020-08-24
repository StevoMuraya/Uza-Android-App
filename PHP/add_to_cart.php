<?php 
if ($_SERVER['REQUEST_METHOD'] =='POST'){
	$user_id				= filter_var($_POST['user_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$product_id				= filter_var($_POST['product_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$product_name			= filter_var($_POST['product_name'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$category				= filter_var($_POST['category'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
    $description			= filter_var($_POST['description'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$original_price			= filter_var($_POST['original_price'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$price			        = filter_var($_POST['price'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$seller_name			= filter_var($_POST['seller_name'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$rating				    = filter_var($_POST['rating'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
    $percentage_strike		= filter_var($_POST['percentage_strike'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	$product_image			= filter_var($_POST['product_image'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
	
	require_once('config.php');


	$sql = "INSERT INTO cart(user_id,product_id,product_name,category,description,quantity,original_price,price,
                            seller_name,rating,percentage_strike,product_image,date_added) VALUES ('$user_id',
                            '$product_id','$product_name','$category','$description',1,'$original_price',
                            '$price','$seller_name','$rating','$percentage_strike','$product_image',now())";
	if (mysqli_query($db_handle,$sql)) {
		echo "Successfully Added";
	}else{
		echo "Failed to Add";
	}
	mysqli_close($db_handle);
}else{
	echo 'Error in capturing data';
} 
?>