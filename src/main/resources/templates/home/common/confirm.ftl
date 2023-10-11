<section id="specific-scope">

	<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
		<form id="edit-form" method="post">
			<input type="hidden" name="id" id="edit-id">
			<h2>Nhập ngày giờ phỏng vấn</h2>
			<input type="datetime-local" id="dateTimePicker" name="interview" pattern="\d{4}-\d{2}-\d{2}T\d{2}:\d{2}" placeholder="yyyy-mm-ddThh:mm" >
		</form>
	
		<link rel="stylesheet" type="text/css" href="/admin/easyui/easyui/1.3.4/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="/admin/easyui/css/wu.css" />
		<link rel="stylesheet" type="text/css" href="/admin/easyui/css/icon.css" />
		<script type="text/javascript" src="/admin/easyui/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="/admin/easyui/easyui/1.3.4/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/admin/easyui/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
	 <script>
        // Get a reference to the datetime-local input field
        const dateTimePicker = document.getElementById('dateTimePicker');

        var currentDate = new Date();
				var defaultDatetime = currentDate.toISOString().slice(0, 16);

        // Set the min attribute of the input field to the current date and time
        dateTimePicker.setAttribute('min', defaultDatetime);
    </script>
	</div>
</section>