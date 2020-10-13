<button onclick="popupKakaoLogin()">KakaoLogin</button>
<script>

    var url = '${loginUrl}';
    url = url.replaceAll("&amp;", "&");

    function popupKakaoLogin() {
        //window.open('${loginUrl}', 'popupKakaoLogin', 'width=700,height=500,scrollbars=0,toolbar=0,menubar=no')
        window.open(url, 'popupKakaoLogin', 'width=700,height=500,scrollbars=0,toolbar=0,menubar=no')
    }
</script>