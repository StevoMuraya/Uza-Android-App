<?php 
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $email      = filter_var($_POST['email'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);
        $phone      = filter_var($_POST['phone'],FILTER_SANITIZE_STRING,FILTER_FLAG_STRIP_HIGH);

        require_once('config.php');

        $sql = "SELECT * FROM app_users WHERE email = '$email' OR phone = '$phone'";
        $response = mysqli_query($db_handle,$sql);
        $result = array();
        $result['login'] = array();

        if (mysqli_num_rows($response) == 1) {
            $row = mysqli_fetch_assoc($response);
            $index['user_id'] = $row['user_id'];
            $index['fname'] = $row['fname'];
            $index['lname'] = $row['lname'];
            $index['email'] = $row['email'];
            $index['phone'] = $row['phone'];
            $index['date_time_reg'] = $row['date_time_reg'];
            $index['county'] = $row['county'];

            array_push($result['login'], $index);
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