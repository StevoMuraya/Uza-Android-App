<?php 

if (isset($_POST['search'])){

    $search = filter_var($_POST['search'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
        require('config.php');

        $stmt = $db_handle->prepare("SELECT * FROM products WHERE product_name LIKE '%$search%' AND quantity > 0");
        $stmt->execute();
        $stmt->bind_result($product_id,$product_name,$category,$description,$quantity,$original_price,$price,$seller_name,$rating,$percentage_strike,$product_image,$date_added);
        $categories = array();

        while($stmt->fetch()){
        	$temp = array();
        	$temp['product_id'] = $product_id;
        	$temp['product_name'] = $product_name;
        	$temp['category'] = $category;
        	$temp['description'] = $description;
        	$temp['quantity'] = $quantity;
        	$temp['original_price'] = $original_price;
        	$temp['price'] = $price;
        	$temp['seller_name'] = $seller_name;
        	$temp['rating'] = $rating;
        	$temp['percentage_strike'] = $percentage_strike;
        	$temp['product_image'] = $product_image;
        	$temp['date_added'] = $date_added;

        	array_push($categories, $temp);
        }
        echo json_encode($categories);
        mysqli_close($db_handle);
    }
 ?>
 