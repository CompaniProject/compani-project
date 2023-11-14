/**
	reply, project-feedback에서 공통으로 사용하는 함수
**/

	function toggleBodyDisplay(insertBody, isComment){
	    insertBody.css('display','');
	    insertBody.find('.media-body p').css('display',(isComment) ? '' :'none');
	    insertBody.find('.edit-area').css('display',(isComment) ? 'none' :'');
	    insertBody.find('.btn-area').css('display',(isComment) ? '' :'none');
	}

	// get comment, content, editBtn, submitBtn to Toggle
    // update textarea - copy to content text
    function toggleTags(event){
        let comment = $(event.target).closest('.media');
        let content = comment.find('.media-body p');
        let edit = comment.find('.edit-area');
        let submitBtn = comment.find('.submitBtn');
        let checkbox = comment.find('label');
        let cntArea = comment.find('.edit-cntn-count');
	    
        content.val(comment.find('textarea').val());
        
        content.toggle();
        edit.toggle();
        submitBtn.toggle();
        checkbox.hide();
        cntArea.hide();
        
	    comment.find('textarea').val(comment.find('.mb-0').text());
    }
    
    //------------ Date formatting
    function timestamp(convertDate){
        let today = new Date(convertDate);
        today.setHours(today.getHours() + 9);
        return today.toISOString().replace('T', ' ').substring(0, 16);
    }
    
   	// -----------------------------------------------------cntn counting event

	function commentCntnCount(event){
		let text_count = $(this).val().length;
		if (text_count >= text_limit){
			Swal.fire({
                icon: 'error',
                text: '피드백을 '+ text_limit + '자 이하로 입력해 주세요.',
            });
		}
		
		let txt = text_count + "/" + text_limit;
		$(event.data.idTag).text(txt);
	}
    
    // ------------------------------------------------ reply
    
    function deleteCommentAlert(event){
    	 Swal.fire({
                    text: "정말로 삭제 하시겠습니까?",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: '확인',
                    cancelButtonText: '취소'
                }).then((result) => {
                		if (result.isConfirmed){
                			deleteComment(event)
                		} else{
                			return false;
                		}
                	})
    }

	function replyDeleteHTML(event){
		let curBody = $(event.target).closest('.media');
		
		curBody.find('.replyInsertBtn').remove();
		curBody.find('.replyDeleteBtn').remove();
		curBody.css('display','none');
	}
	