<?php
 	require_once("../config.inc.php");

	$conn=mysql_connect($db_hostname, $db_user, $db_password) or die(mysql_error());
	mysql_query("SET NAMES utf8", $conn);
	mysql_select_db($db_name) or die(mysql_error());
	
	$sql = "SELECT activity.id as id, activity.detail, activity.img, activity.name as name, activity.dtStart as dtStart, activity.dtEnd as dtEnd, activity.category, activity.location, activity.yy, activity.schoolYear, activity.major, format(TIME_TO_SEC(TIMEDIFF(dtStart,NOW()))/60,0) as timediff, teacher.fname as fname, teacher.lname as lname FROM `activity` INNER JOIN teacher ON activity.teacher = teacher.id WHERE activity.id = '$_GET[id]' ";
	$dbquery=mysql_query($sql) or die(mysql_error());
	$num_rows=mysql_num_rows($dbquery);
	$json = array();
	
	for ($i=1;$i<=$num_rows;$i++){
		$result=mysql_fetch_array($dbquery);
		$major=$result['major'];
		$major_array = split(",",$major);
		$major="";
		
		foreach($major_array as $ma){
			$sql2 = "SELECT * FROM major WHERE `id` = '$ma'";
			$dbquery2=mysql_query($sql2) or die(mysql_error());
			$result2=mysql_fetch_array($dbquery2);
			$major=$major." ".$result2['major'].",";
		}
		
		$arr = array("name" => $result['name'],
					"detail" => $result['detail'],
					"dtStart" => DateTimeThai($result['dtStart']),
					"dtEnd" => DateTimeThai($result['dtEnd']),
					"timediff" => $result['timediff'],
					"location" => $result['location'],
					"category" => $result['category'],
					"schoolYear" => $result['schoolYear'],
					"yy" => $result['yy'],
					"major" => $major,
					"img" => $result['img'],
					"teacher" => $result['fname']." ".$result['lname']
		);
		array_push($json,$arr);
	}
	
    echo json_encode($json);
	mysql_close($conn);
?>