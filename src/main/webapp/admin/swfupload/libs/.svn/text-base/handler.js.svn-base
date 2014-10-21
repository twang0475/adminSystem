/*
 Queue Plug-in

 Features:
 *Adds a cancelQueue() method for cancelling the entire queue.
 *All queued files are uploaded when startUpload() is called.
 *If false is returned from uploadComplete then the queue upload is stopped.
 If false is not returned (strict comparison) then the queue upload is continued.
 *Adds a QueueComplete event that is fired when all the queued files have finished uploading.
 Set the event handler with the queue_complete_handler setting.

 */

var SWFUpload;
if (typeof(SWFUpload) === "function") {
	SWFUpload.queue = {};

	SWFUpload.prototype.initSettings = (function (oldInitSettings) {
		return function () {
			if (typeof(oldInitSettings) === "function") {
				oldInitSettings.call(this);
			}

			this.queueSettings = {};

			this.queueSettings.queue_cancelled_flag = false;
			this.queueSettings.queue_upload_count = 0;

			this.queueSettings.user_upload_complete_handler = this.settings.upload_complete_handler;
			this.queueSettings.user_upload_start_handler = this.settings.upload_start_handler;
			this.settings.upload_complete_handler = SWFUpload.queue.uploadCompleteHandler;
			this.settings.upload_start_handler = SWFUpload.queue.uploadStartHandler;

			this.settings.queue_complete_handler = this.settings.queue_complete_handler || null;
		};
	})(SWFUpload.prototype.initSettings);

	SWFUpload.prototype.startUpload = function (fileID) {
		this.queueSettings.queue_cancelled_flag = false;
		this.callFlash("StartUpload", [fileID]);
	};

	SWFUpload.prototype.cancelQueue = function () {
		this.queueSettings.queue_cancelled_flag = true;
		this.stopUpload();

		var stats = this.getStats();
		while (stats.files_queued > 0) {
			this.cancelUpload();
			stats = this.getStats();
		}
	};

	SWFUpload.queue.uploadStartHandler = function (file) {
		var returnValue;
		if (typeof(this.queueSettings.user_upload_start_handler) === "function") {
			returnValue = this.queueSettings.user_upload_start_handler.call(this, file);
		}

		// To prevent upload a real "FALSE" value must be returned, otherwise default to a real "TRUE" value.
		returnValue = (returnValue === false) ? false : true;

		this.queueSettings.queue_cancelled_flag = !returnValue;

		return returnValue;
	};

	SWFUpload.queue.uploadCompleteHandler = function (file) {
		var user_upload_complete_handler = this.queueSettings.user_upload_complete_handler;
		var continueUpload;

		if (file.filestatus === SWFUpload.FILE_STATUS.COMPLETE) {
			this.queueSettings.queue_upload_count++;
		}

		if (typeof(user_upload_complete_handler) === "function") {
			continueUpload = (user_upload_complete_handler.call(this, file) === false) ? false : true;
		} else if (file.filestatus === SWFUpload.FILE_STATUS.QUEUED) {
			// If the file was stopped and re-queued don't restart the upload
			continueUpload = false;
		} else {
			continueUpload = true;
		}

		if (continueUpload) {
			var stats = this.getStats();
			if (stats.files_queued > 0 && this.queueSettings.queue_cancelled_flag === false) {
				this.startUpload();
			} else if (this.queueSettings.queue_cancelled_flag === false) {
				this.queueEvent("queue_complete_handler", [this.queueSettings.queue_upload_count]);
				this.queueSettings.queue_upload_count = 0;
			} else {
				this.queueSettings.queue_cancelled_flag = false;
				this.queueSettings.queue_upload_count = 0;
			}
		}
	};
}

var uploadHandler = {
	fileDialogStart: function(){
		this.customSettings.processContainer.empty();
	},
	fileQueued: function(file){
		try{
			new uploadProcess(file, this.customSettings.processContainer);
		} catch(ex) {
			this.debug(ex);
		}
	},
	fileQueueError: function(file, errorCode, message){
		try {
			if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
				alert('选择的文件过多');
				this.debug("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
				return;
			}

			var progress = new uploadProcess(file, this.customSettings.processContainer);
			progress.setError();

			switch (errorCode) {
				case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
					progress.setStatus('文件体积过大');
					this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
					progress.setStatus('无法上传0字节文件');
					this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
					progress.setStatus('文件类型不允许上传');
					this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
					alert('选择的文件过多');
					break;
				default:
					if (file !== null) {
						progress.setStatus('未知错误');
					}
					this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
			}
			//this.cancelUpload(file.id, false);
		} catch (ex) {
			this.debug(ex);
		}
	},
	fileDialogComplete: function(numFilesSelected, numFilesQueued){
		try{
			if(numFilesQueued < numFilesSelected){
				alert('选择的文件体积过大或类型错误');
			}
			if(numFilesQueued !== 0){
				this.customSettings.modalNode.modal('show');
				this.setButtonDisabled(true);
				this.startUpload();
			}
		} catch(ex) {
			this.debug(ex);
		}
	},
	uploadStart: function(){},
	uploadProgress: function(file, bytesLoaded, bytesTotal){
		try {
			var percent = Math.ceil((bytesLoaded / bytesTotal) * 100),
				progress = new uploadProcess(file, this.customSettings.processContainer);
			progress.setProgress(percent);
		} catch (ex) {
			this.debug(ex);
		}
	},
	uploadError: function(file, errorCode, message){
		try {
			var progress = new uploadProcess(file, this.customSettings.processContainer);
			progress.setError();

			switch (errorCode) {
				case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
					progress.setStatus("上传出现错误: " + message);
					this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
					break;
				case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
					progress.setStatus("配置错误");
					this.debug("Error Code: No backend file, File name: " + file.name + ", Message: " + message);
					break;
				case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
					progress.setStatus("上传失败");
					this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.UPLOAD_ERROR.IO_ERROR:
					progress.setStatus("服务器I/O错误");
					this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
					break;
				case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
					progress.setStatus("安全错误");
					this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
					break;
				case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
					progress.setStatus("上传超过限制");
					this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
					progress.setStatus("没有找到文件");
					this.debug("Error Code: The file was not found, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
					progress.setStatus("验证失败，取消上传");
					this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
				case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
					if (this.getStats().files_queued === 0) {
						//this.customSettings.modalNode.modal('hide');
						this.setButtonDisabled(false);
					}
					progress.setStatus("上传取消");
					progress.setCancelled();
					break;
				case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
					progress.setStatus("上传停止");
					break;
				default:
					progress.setStatus("未知错误: " + error_code);
					this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
					break;
			}
		} catch (ex) {
			this.debug(ex);
		}
	},
	uploadSuccess: function(file, serverData){
		try {
			var progress = new uploadProcess(file, this.customSettings.processContainer);
			progress.setComplete();
			progress.setStatus("上传成功");
			//console.dir(serverData);
			this.customSettings.callBack(file, serverData);
		} catch (ex) {
			this.debug(ex);
		}
	},
	uploadComplete: function(file){
		try{
			if (this.getStats().files_queued === 0) {
				//this.customSettings.modalNode.modal('hide');
				this.setButtonDisabled(false);

			} else {
				this.startUpload();
			}
			//console.dir(this.getStats());
		} catch(ex) {
			this.debug(ex);
		}
	}
};

function uploadProcess(file, container){
	var processNodeTpl = '<div class="alert alert-info" id="#processId#"><label>#fileName#<span class="fp-msg"></span></label><div class="progress"><div class="bar" style="width:0;"></div></div></div>'
	this.processId = file.id;
	this.processNode = $('#'+this.processId);

	if(!this.processNode[0]){
		this.processNode = $(processNodeTpl.replace(/#fileName#/ig, file.name).replace(/#processId#/ig, this.processId));
		container.append(this.processNode);
	}
	this.processMsg = this.processNode.find('.fp-msg');
	this.processBar = this.processNode.find('.bar');
	this.timer = null;
}
uploadProcess.prototype = {
	appear: function(){
		this.processNode.fadeIn('normal');
	},
	disappear: function(){
		this.processNode.fadeOut('normal');
	},
	reset: function(){
		this.processMsg.text('');
		this.processNode.toggleClass('alert-error').toggleClass('alert-info');
		this.processBar.width('');
	},
	setStatus: function(msg){
		this.processMsg.text(msg);
	},
	setComplete: function(){
		this.setProgress('100');
	},
	setError: function(){
		var _self = this;
		this.processNode.removeClass('alert-info').addClass('alert-error');
		this.timer = setTimeout(function(){
			_self.disappear();
		}, 5000);
	},
	setProgress: function(percentage){
		this.processBar.width(percentage + '%');
	}
};