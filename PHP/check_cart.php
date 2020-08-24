<?php 
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $product_id      = filter_var($_POST['product_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
        $user_id      = filter_var($_POST['user_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);

        require_once('config.php');

        $sql = "SELECT * FROM cart WHERE product_id = '$product_id' AND user_id = '$user_id' AND purchase = 0";
        $response = mysqli_query($db_handle,$sql);
        $result = array();
        $result['cart'] = array();

        if (mysqli_num_rows($response) == 1) {
            $row = mysqli_fetch_assoc($response);
            $index['cart_id'] = $row['cart_id'];
            $index['user_id'] = $row['user_id'];
            $index['product_id'] = $row['product_id'];
            $index['product_name'] = $row['product_name'];
            $index['category'] = $row['category'];
            $index['description'] = $row['description'];
            $index['quantity'] = $row['quantity'];
            $index['original_price'] = $row['original_price'];
            $index['price'] = $row['price'];
            $index['seller_name'] = $row['seller_name'];
            $index['rating'] = $row['rating'];
            $index['percentage_strike'] = $row['percentage_strike'];
            $index['product_image'] = $row['product_image'];
            $index['date_added'] = $row['date_added'];

            array_push($result['cart'], $index);
            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            mysqli_close($db_handle);
        } else{
            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($db_handle);
        }
    }
 ?>