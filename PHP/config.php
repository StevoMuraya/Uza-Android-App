<?php
$server="localhost";
$user_name="root";
$password="";
$database="uza";
$db_handle=mysqli_connect($server,$user_name,$password);
$db_found=mysqli_select_db($db_handle,$database);

if(!$db_found)
{
	die("DATABASE not found");
} /*else{
	echo "Connection Success";
	}*/
?>