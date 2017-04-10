<?php
 	require_once("config.php");
	$username = $_GET['user'];
        $password = $_GET['pass'];
	$conn=mysql_connect($db_hostname, $db_user, $db_password) or die(mysql_error());
	mysql_query("SET NAMES utf8", $conn);
	mysql_select_db($db_name) or die(mysql_error());
	$pass=md5($_GET[pass]);
	$sql = "SELECT * FROM user WHERE username = $username and password = $password " ;
	$dbquery=mysql_query($sql) or die(mysql_error());
	$num_rows=mysql_num_rows($dbquery);
	
	$json = array();
	for ($i=1;$i<=$num_rows;$i++){
		$result=mysql_fetch_array($dbquery);
		$arr = array("user_id" => $result['user_id'],
                             "username" => $result['username'],
                             "prefix" => $result['prefix'],
                             "name" => $result['name'],
                             "lname" => $result['lname'],
                             "password" => $result['password'],
                             "status_status_id" => $result['status_status_id'],
                             "department_department_id" => $result['department_department_id'],
                             "department_faculty_faculty_id" => $result['department_faculty_faculty_id'],);
		array_push($json,$arr);
	}
	
    echo json_encode($json);
	mysql_close($conn);
?>