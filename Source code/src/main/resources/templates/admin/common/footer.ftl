<div id="loading" style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#F9F9F9;text-align :center;padding-top:20%;">
     <img src="/admin/easyui/images/load-page.gif" width="10%">
     <h1><font color="#15428B">Đang tải....</font></h1>
</div>
</body>
<script>
    var pc; 
    //Đừng đặt nó ở mức $ (function () {}); trung bình
    $.parser.onComplete = function () {
        if (pc) clearTimeout(pc);
        pc = setTimeout(closes, 1000);
    } 

    function closes() {
        $('#loading').fadeOut('normal', function () {
            $(this).remove();
        });
    }
</script>