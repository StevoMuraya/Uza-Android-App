<?php 

if (isset($_POST['user_id'])){
    $user_id = filter_var($_POST['user_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);

    require('config.php');

    $stmt = $db_handle->prepare("SELECT * FROM cart WHERE user_id = '$user_id' AND purchase = 0");
    $stmt->execute();
    $stmt->bind_result($cart_id,$product_id,$user_id,$product_name,$category,$description,$quantity,
    $original_price,$price,$seller_name,$rating,$percentage_strike,$product_image,$date_added,$purchase);
    $categories = array();

    while($stmt->fetch()){
        $temp = array();
        $temp['cart_id'] = $cart_id;
        $temp['product_id'] = $product_id;
        $temp['user_id'] = $user_id;
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
        $temp['purchase'] = $purchase;

        array_push($categories, $temp);
    }
    echo json_encode($categories);
    mysqli_close($db_handle);
}
 ?>
 