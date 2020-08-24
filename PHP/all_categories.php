<?php 
        require('config.php');

        $stmt = $db_handle->prepare("SELECT * FROM product_categories ORDER BY category_name ASC");
        $stmt->execute();
        $stmt->bind_result($category_id,$category_name);
        $categories = array();

        while($stmt->fetch()){
        	$temp = array();
        	$temp['category_id'] = $category_id;
        	$temp['category_name'] = $category_name;

        	array_push($categories, $temp);
        }
        echo json_encode($categories);
        mysqli_close($db_handle);
 ?>