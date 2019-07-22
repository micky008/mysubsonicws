<script type="text/javascript">

function getPlayRoute(uuid){
        return 'https://micky.ovh/test/rest/musiques/stream/' + uuid;
}

function play(idFolder) {
        if (idFolder != undefined && idFolder.length > 0) {
            var uuid = document.getElementById('sel_' + idFolder).selectedOptions[0].id;
            document.getElementById('audio_' + idFolder).src = getPlayRoute(uuid);
            return;
        }

}

function myended(uuidFolder){
        var select = document.getElementById('sel_' + uuidFolder);
        var si = select.selectedIndex;
        si++;
        if (si > select.length) {
            si = 0;
            return;
        }
        select.selectedIndex = si;
        play(uuidFolder);
}

</script>
