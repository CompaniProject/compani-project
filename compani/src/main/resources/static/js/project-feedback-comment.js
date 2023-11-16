/**
 *  porject feedback(comment) - Insert, Delete(Change Delete State), Update
 */

		
	//----------------------- Project Feedback(Comment) Insert Start
	function createInsertObj(isComment = true){
		let curBody = $(event.target).closest('.media');
        
        let cntn = (isComment) ? $('#inputCntn') : curBody.find("textarea");
        
        let value = cntn.val();
        let obj = {};
        obj["prjtFdbkCntn"] = value;
        obj["prjtNo"] = prjtNo;
        obj["prjtFdbkUpno"] = curBody.data('parentNo');
        
        cntn.val('');
        $("#input-cntn-count").text("0/" + text_limit);
        
        return obj;
	}
	
    function insertComment(){
    	let obj = createInsertObj();
    	if (obj.prjtFdbkCntn == ""){
    		Swal.fire({
                icon: 'error',
                text: '내용을 입력해주세요.',
            });
            return;
    	}
        insertAjax(obj);
    }
    
    function insertAjax(obj,isComment = true){
        $.ajax({
            url:'/project/feedback',
            type: 'post',
            contentType: "application/json",
            data:JSON.stringify(obj)
        })
        .done(data => {insertCommentHTML(data,isComment)})
        .fail(err => {});
    }
    
    function insertCommentHTML(data, isComment){
        // create tag
        let body = (isComment) ? $('#commentBody') : $('#insertBody');
        let insertBody = $('#insertBody').clone();

        let parLevel = (isComment) ? 0 : body.data('level');
	    let emval = (parLevel) + 'em';

        // input content values
        let level = parLevel + 1;
        insertBody.data('level',level);
        insertBody.css('margin-left',emval);

        insertBody.data('no',data.prjtFdbkNo);
        insertBody.find('.media-body h5').text(membNm);
        insertBody.find('.media-body p').text(data.prjtFdbkCntn);
        insertBody.find('textarea').text(data.prjtFdbkCntn);
        insertBody.find('#date-area').text(timestamp(data.prjtFdbkDt));
		insertBody.attr('id','');
		
		insertBody.find('.replyInsertBtn').remove();
		insertBody.find('.replyDeleteBtn').remove();
		
		toggleBodyDisplay(insertBody,true);
        
        // insert comment tag to comment body
        if (isComment){
            body.append(insertBody);
        } else {
            body.after(insertBody);
        }

    }
    
    $('#insertBtn').on('click', insertComment);
    
    // ---------------------------------------Project Feedback(Comment) Insert End
    
    // date, content change
    function changeComment(data, comment){
        if (data.prjtFdbkShow == null){
            comment.find(".media-body p").text(data.prjtFdbkCntn);
            comment.find("#date-area").text(timestamp(data.prjtFdbkDt));
        } else {
            comment.find(".media-body p").text('삭제된 내용입니다.');
            comment.find(".media-body p").show();
            comment.find(".btn-area").remove();
            comment.find("textarea").css('display','none');
        }
    }
    

    function editSubmit(event){
        let comment = $(event.target).closest('.media');
        let textCntn = comment.find("textarea");
        
        let obj = {};
        obj["prjtFdbkCntn"] = textCntn.val();
        obj["prjtFdbkNo"] = comment.data('no');
        updateAjax(obj, comment);
        
        toggleTags(event);
    }
    
    // ----------------------------------Delete Comment
    function deleteComment(event){
        let comment = $(event.target).closest('.media');
        
        let obj = {};
        obj["prjtFdbkShow"] = '0N2';		// Delete State Change
        obj["prjtFdbkNo"] = comment.data('no');
        
        updateAjax(obj,comment);
    }
    // ----------------------------------Delete Comment 
    
    // ----------------------------------Update Ajax
    // update - using edit, delete
    function updateAjax(obj, comment){
        $.ajax({
            url:'/project/feedback',
            type: 'put',
            contentType: "application/json",
            data:JSON.stringify(obj)
        })
        .done(data => {changeComment(data, comment)})
        .fail(err => {});
    }
    // -------------------------------Update Ajax End
    
    // -------------------------------onclick Event
    $(document).off('click','.delBtn').on('click','.delBtn',deleteCommentAlert);
    $(document).off('click','.submitBtn').on('click','.submitBtn',editSubmit);
    $(document).off('click','.editBtn').on('click','.editBtn',toggleTags);
    // ------------------------------onclick Event End
    
    // ------------------------------------------------
        
	function replyInsert(event){
		let curBody = $(event.target).closest('.media');
		obj = createInsertObj(false);
		
		if (obj.prjtFdbkCntn == ""){
    		Swal.fire({
                icon: 'error',
                text: '내용을 입력해주세요.',
            });
    		return;
    	}
		
		insertAjax(obj,false);
		
		// hide insert area
		replyDeleteHTML(event);
	}
	
	function replyInsertAreaHTML(event){
		// remove count area size
		$('.edit-cntn-count').text("0/" + text_limit);
	
	    // create tag
		let curBody = $(event.target).closest('.media');
	    let insertBody = $('#insertBody');
	    
	    // remove Btn
		insertBody.find('.replyInsertBtn').remove();
		insertBody.find('.replyDeleteBtn').remove();
	    
		// level = 부모의 level 값 : after로 insert 하기 때문
	    let level = curBody.data('level');
	    let emval = level + 'em';
	
	    // input content values
	    insertBody.data('level',level);
	    insertBody.data('parentNo',curBody.data('no'));
	    insertBody.css('margin-left',emval);
	    insertBody.find('.media-body h5').text(membNm);
	    toggleBodyDisplay(insertBody,false);
	
	    insertBody.find("textarea").val('');
	    insertBody.append($('.replyInsertBtn').first().clone());
	    insertBody.append($('.replyDeleteBtn').first().clone());
	    
	    // insert comment tag to comment body
	    curBody.after(insertBody);
	}


	$(document).on('click','.replyBtn',replyInsertAreaHTML);
	$(document).on('click','.replyInsertBtn',replyInsert);
	$(document).on('click','.replyDeleteBtn',replyDeleteHTML);
	$(document).on('click','#InsertBtn',insertComment);
	
	// -----------------------------------------------------cntn counting event

	$('#inputCntn').on("keyup",{idTag:"#input-cntn-count"},commentCntnCount);
	$(document).on("keyup",".editCntn",{idTag:".edit-cntn-count"},commentCntnCount);
    