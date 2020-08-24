<?php 

if (isset($_POST['user_id'])){
    $user_id = filter_var($_POST['user_id'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);

    require('config.php');

    $stmt = $db_handle->prepare("SELECT order_code,COUNT(*) AS 'items',d_date FROM purchases WHERE user_id = '$user_id' GROUP BY order_code ORDER BY order_code DESC");
    $stmt->execute();
    $stmt->bind_result($order_code,$items,$d_date);
    $orders = array();

    while($stmt->fetch()){
        $temp = array();
        $temp['order_code'] = $order_code;
        $temp['items'] = $items;
        $temp['d_date'] = $d_date;

        array_push($orders, $temp);
    }
    echo json_encode($orders);
    mysqli_close($db_handle);
}
 ?>
 