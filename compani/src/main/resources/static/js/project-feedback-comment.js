/**
 *  porject feedback(comment) - Insert, Delete(Change Delete State), Update
 */

	function toggleBodyDisplay(insertBody, isComment){
        insertBody.css('display','');
        insertBody.find('.media-body p').css('display',(isComment) ? '' :'none');
        insertBody.find('.edit-area').css('display',(isComment) ? 'none' :'');
        insertBody.find('.btn-area').css('display',(isComment) ? '' :'none');
	}
		
	//----------------------- Project Feedback(Comment) Insert Start
	function createInsertObj(){
		let curBody = $(event.target).closest('.media');
        
        let value = $('#inputCntn').val();
        let obj = {};
        obj["prjtFdbkCntn"] = value;
        obj["prjtNo"] = prjtNo;
        obj["prjtFdbkUpno"] = curBody.data('parentNo');
        
        $('#inputCntn').val('');
        
        return obj;
	}
	
    function insertComment(){
    	let obj = createInsertObj();
        insertAjax(obj);
    }
    
    function insertAjax(obj){
        $.ajax({
            url:'/project/feedback',
            type: 'post',
            contentType: "application/json",
            data:JSON.stringify(obj)
        })
        .done(data => {insertCommentHTML(data)})
        .fail(err => {});
    }
    
    function insertCommentHTML(data){
        // create tag
        let body = $('#commentBody');
        let insertBody = $('#insertBody').clone();
        
        // input content values
        insertBody.data('level',1);
        insertBody.css('margin-left','');

        insertBody.data('no',data.prjtFdbkNo);
        insertBody.find('.media-body h5').text(membNm);
        insertBody.find('.media-body p').text(data.prjtFdbkCntn);
        insertBody.find('textarea').text(data.prjtFdbkCntn);
        insertBody.find('#date-area').text(timestamp(data.prjtFdbkDt));
		
		toggleBodyDisplay(insertBody,true);
        
        // insert comment tag to comment body
        body.append(insertBody);
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
    
    // get comment, content, editBtn, submitBtn to Toggle
    // update textarea - copy to content text
    function toggleTags(event){
        let comment = $(event.target).closest('.media');
        let content = comment.find('.media-body p');
        let edit = comment.find('.edit-area');
        let submitBtn = comment.find('.submitBtn');
        
        content.val(comment.find('textarea').val());
        
        content.toggle();
        edit.toggle();
        submitBtn.toggle();
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
    $(document).on('click','.delBtn',deleteComment);
    $(document).on('click','.editBtn',toggleTags);
    $(document).on('click','.submitBtn',editSubmit);
    // ------------------------------onclick Event End
    
    //------------ Date formatting
    function timestamp(convertDate){
        let today = new Date(convertDate);
        today.setHours(today.getHours() + 9);
        return today.toISOString().replace('T', ' ').substring(0, 16);
    }
    