<?php

function getPort($url){
        if ($url[5] == '/'){
                return substr($url,1,4);
        }
        return substr($url,1,5);
}

function nettoie($url) {
        $pos = 4;
        if ($url[5] == '/'){
                $pos = 5;
        }
        return substr($url,$pos);
}

const URL = 'localhost';
$PORT = getPort($_SERVER['PATH_INFO']);
$url = 'http://'.URL.':'.$PORT.nettoie($_SERVER['PATH_INFO']);
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
curl_setopt($s, CURLOPT_RETURNTRANSFER, true);
curl_setopt($s, CURLOPT_HTTPHEADER, $headers);
curl_setopt($s, CURLOPT_HEADER, false);
$sorti = curl_exec($s);
$ct = curl_getinfo($s, CURLINFO_CONTENT_TYPE);
header('Access-Control-Allow-Origin: *');
header("Access-control-allow-headers: origin, content-type, accept, authorization");
header("Access-control-allow-methods: GET, POST, OPTIONS");
header('Content-type: '.$ct);
print $sorti;

curl_close($s);
?>
