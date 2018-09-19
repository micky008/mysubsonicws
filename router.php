<?php
const URL = 'localhost'; 
const PORT = 9998; 
$url = 'http://'.URL.':'.PORT.$_SERVER['PATH_INFO'];
$headers = array();
foreach (getallheaders() as $name => $value) {
    $headers[] = "$name: $value";
}
$s = curl_init();
//curl_setopt($s, CURLOPT_HEADER, true);
if ($_SERVER['REQUEST_METHOD'] !== 'GET'){
	$params = file_get_contents('php://input');
	curl_setopt($s, CURLOPT_POST, true);
    curl_setopt($s, CURLOPT_POSTFIELDS, $params);
}	
curl_setopt($s, CURLOPT_URL, $url);
curl_setopt($s, CURLOPT_RETURNTRANSFER, true);
curl_setopt($s, CURLOPT_HTTPHEADER, $headers);
$out = curl_exec($s);
$headerContentType = curl_getinfo($s, CURLINFO_CONTENT_TYPE);
$httpcode = curl_getinfo($s, CURLINFO_HTTP_CODE);
curl_close($s);
header('HTTP/1.1 '.$httpcode);
header ('Content-Type: '.$headerContentType);
echo $out;
?>