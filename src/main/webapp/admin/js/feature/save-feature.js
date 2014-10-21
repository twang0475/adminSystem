$(function(){
	var postData = {},
		needImage = $('#needImage'), 
		introduce = $('#introduce');
	var formItems = {
		feaName: $('#feaName'),
		titleLen: $('#titleLen'),
		id: $('#id'),
		action: $('#action'),
		parentId: $('#parentId')
		};
		
	function validateData(){
		postData={};
	   	for(key in formItems){
			if(formItems.hasOwnProperty(key)){
				var val = $.trim(formItems[key].val());
				if(val == ''){
					formItems[key].addClass('error-input');
					return false;
				} else {
					formItems[key].removeClass('error-input');
					postData[key] = val;
				}
			}
		}
		postData['introduce'] = $.trim(introduce.val());
		postData['needImage'] = needImage.is(':checked') ? '1' : '0';
		return true;
	}
	
	function submitData(postUrl){
		$.post(postUrl, postData, function(data){
			if(!data.result){
				var msg = data.err_msg;
				if($.type(msg) === 'string'){
					alert(msg);
				} else {
					for(var key in msg){
						if(msg.hasOwnProperty(key)){
							formItems[key] && showErrorMsg(formItems[key]);
						}
					}
				}
			}
			alert("操作成功!");
		}, 'json');
	}

	function showErrorMsg(msg){
		alert(msg);
	}
	
	$('.btn-submit-save').click(function(){
		if(validateData()){
			submitData('createfeature.do');
		}else{
			alert("请认真核实要填写的内容!");
		}
	});
	
	$('.btn-submit-update').click(function(){
		if(validateData()){
			submitData('updatefeature.do');
		}else{
			alert("请认真核实要填写的内容!");
		}
	});
});