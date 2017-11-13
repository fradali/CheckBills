<?php
header('Content-Type: text/json');

define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','bills');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
$sql = "select * from billspayed";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('id'=>$row[0],
'name'=>$row[1],
'datepayment'=>$row[2]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>