<?php
const URL = 'localhost'; 
const PORT = 9998; 
$url = 'http://'.URL.':'.PORT.$_SERVER['PATH_INFO'];
$headers = array();
foreach (getallheaders() as $name => $value) {
    $headers[] = "$name: $value";
}
$s = curl_init();
if ($_SERVER['REQUEST_METHOD'] !== 'GET'){
	$params = file_get_contents('php://input');
	curl_setopt($s, CURLOPT_POST, true);
    curl_setopt($s, CURLOPT_POSTFIELDS, $params);
}	
curl_setopt($s, CURLOPT_URL, $url);
curl_setopt($s, CURLOPT_RETURNTRANSFER, false);
curl_setopt($s, CURLOPT_HTTPHEADER, $headers);
header('Access-Control-Allow-Origin: *');
header("Access-control-allow-headers: origin, content-type, accept, authorization");
header("Access-control-allow-methods: GET, POST, OPTIONS"); 
curl_exec($s);
?>