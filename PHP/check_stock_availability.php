<?php 

if (isset($_POST['product_id'])){
    $product_id = filter_var($_POST['product_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);

    require('config.php');

    $stmt = $db_handle->prepare("SELECT quantity FROM products WHERE product_id = '$product_id'");
    $stmt->execute();
    $stmt->bind_result($quantity);
    $categories = array();

    while($stmt->fetch()){
        $temp = array();
        $temp['quantity'] = $quantity;

        array_push($categories, $temp);
    }
    echo json_encode($categories);
    mysqli_close($db_handle);
}
 ?>
 