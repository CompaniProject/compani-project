//검색 조건 (제목, 작성자, 전체) 
$('.issue-selectBox').on('click', function () {
	$('.issue-select-ul').css('display', 'block');
});
//검색 조건 (제목, 작성자, 전체) 
$('.member-selectBox').on('click', function () {
	$('.member-select-ul').css('display', 'block');
});

// 현재 페이지에 current 클래스 붙이기!
$('.cur_page').each(function () {
	var page = $(this).text();

	if (page == 1) {
		$(this).addClass('current').css('color', '#ffffff');
	} else {
		$(this).removeClass('current').css('color', '#76838F');
	}
});

// disabled 일 때 , onclick 지우기
$(".disabled").css("color", "#8C959F").removeAttr("onclick");

$('.issue-select-option').on('click', function (e) {
	var selectValue = e.currentTarget.textContent;
	$('.issue-selected-value').text(selectValue);
	$('#keyword').val(selectValue);
	$('.issue-select-ul').css('display', 'none');
	e.stopPropagation();
});

$('.member-select-option').on('click', function (e) {
	var selectValue = e.currentTarget.textContent;
	$('.member-selected-value').text(selectValue);
	$('#keyword').val(selectValue);
	$('.member-select-ul').css('display', 'none');
	e.stopPropagation();
});


// ajax 
function issuePaging(pageNum) {
	var keywordVal = $('#keyword').val();
	var searchVal = $('#search').val();
	var issueLists = $('#issueLists');
	var pagination = $('.pagination');

	$.ajax('/ModalAjaxIssueList?pageNum=' + pageNum + '&keyword='
		+ keywordVal + '&search=' + searchVal,
		{
			type: 'GET'
		}).done(function (data) {
			issueLists.empty();
			$.each(data.issues, function (idx, item) {
				var trTag = $('<tr/>').attr('onclick', 'viewIssueInfo(' + item.issuNo + ')');

				if (item.issuKnd == '0L1') {
					trTag.append('<th><span class="badge badge-danger" th:text="버그">버그</span></th>');
				} else if (item.issuKnd == '0L2') {
					trTag.append('<th><span class="badge badge-warning" th:text="개선사항">개선사항</span></th>');
				} else if (item.issuKnd == '0L3') {
					trTag.append('<th><span class="badge badge-info" th:text="새기능">새기능</span></th>');
				} else if (item.issuKnd == '0L4') {
					trTag.append('<th><span class="badge badge-light" th:text="업무">업무</span></th>');
				}
				trTag.append('<td>' + item.issuTtl + '</td>');
				trTag.append('<td>' + item.membId + '</td>');

				if (item.issuSt == '0E1') {
					trTag.append('<th><span class="badge badge-pill badge-success" th:text="해결">해결</span></th>');
				} else if (item.issuSt == '0E2') {
					trTag.append('<th><span class="badge badge-pill badge-dark" th:text="미해결">미해결</span></th>');
				} else if (item.issuSt == '0E3') {
					trTag.append('<th><span class="badge badge-pill badge-secondary" th:text="해결불가">해결불가</span></th>');
				}

				if (item.issuRank == '0G1') {
					trTag.append('<th><span class="badge badge-primary" th:text="매우높음">매우높음</span></th>');
				} else if (item.issuRank == '0G2') {
					trTag.append('<th><span class="badge badge-secondary" th:text="높음">높음</span></th>');
				} else if (item.issuRank == '0G3') {
					trTag.append('<th><span class="badge badge-success" th:text="보통">보통</span></th>');
				} else if (item.issuRank == '0G4') {
					trTag.append('<th><span class="badge badge-warning" th:text="낮음">낮음</span></th>');
				} else if (item.issuRank == '0G5') {
					trTag.append('<th><span class="badge badge-danger" th:text="매우낮음">매우낮음</span></th>');
				}

				issueLists.append(trTag);

				/* 여기까지 tbody에 데이터 넣는 것 */

			})
			// 페이징 업데이트..
			updatePaging(data.issue);


			var prePageNum = data.issue.prePage;
			var nextPageNum = data.issue.nextPage;
			var curPageNum = data.issue.pageNum;
			var navFirstPage = data.issue.navigateFirstPage;
			var navLastPage = data.issue.navigateLastPage;
			var lastPage = data.issue.pages;

			// 현재 페이지에 current 클래스 붙이기!
			$('.cur_page').each(function () {
				var page = $(this).text();

				if (page == curPageNum) {
					$(this).addClass('current').css('color', '#ffffff');
				} else {
					$(this).removeClass('current').css('color', '#76838F');
				}
			});

			// 현재 페이지 업데이트.
			$('#previous_page').attr('onclick',
				'issuePaging(' + prePageNum + ')');
			$('#next_page').attr('onclick',
				'issuePaging(' + nextPageNum + ')');

			// previous, next disabled 설정 + onclick 속성 제거
			if (prePageNum == 0) {
				$('#previous_page').addClass('disabled').css("color", "#8C959F").removeAttr("onclick");
			} else {
				$('#previous_page').css("color", "#0969DA");
			};

			if (nextPageNum == 0) {
				$('#next_page').addClass('disabled').css("color", "#8C959F").removeAttr("onclick");
			} else {
				$('#next_page').css("color", "#0969DA");
			};


		}).fail(function () {
			alert('실패');
		});
};

function updatePaging(paging) {
	var pagination = $('.pagination');
	pagination.empty();

	var pagesPer = 8;  // 한 번에 보이는 페이지 수
	var endPage = (Math.ceil(paging.pageNum / pagesPer) * pagesPer);  // 끝 페이지
	var realEndPage = paging.pages;
	var startPage = (endPage - pagesPer) + 1;

	if (endPage > realEndPage) {
		endPage = realEndPage;
	};

	for (var i = startPage; i <= endPage; i++) {
		var aTag = $('<a/>');
		aTag.addClass('cur_page').text(i);
		aTag.attr('onclick', 'issuePaging(' + i + ')');
		pagination.append(aTag);
	};

	if (startPage >= 1) {
		var prevTag = $('<a/>');
		prevTag.attr('id', 'previous_page');
		prevTag.text('Previous');
		prevTag.attr('onclick', 'issuePaging(' + (paging.prePage) + ')');
		pagination.prepend(prevTag);
	};

	if (endPage <= paging.pages) {
		var nextTag = $('<a/>');
		nextTag.attr('id', 'next_page');
		nextTag.text('Next');
		nextTag.attr('onclick', 'issuePaging(' + (paging.nextPage) + ')');
		pagination.append(nextTag);
	};
};

/* 페이징 끝 */

/* 이슈 등록 폼 시작 */

// "등록" 버튼 활성화 or 비활성화 (제목, 종류, 중요도) 3개의 값이 다 들어오는 경우 활성화 된다.
function checkInput() {
	var issuKndVal = $('#issuKndVal').text();
	var issuRankVal = $('#issuRankVal').text();
	var issuTtlVal = $('#issuTtlVal').val();

	if (issuKndVal !== '선택' && issuRankVal !== '선택' && issuTtlVal !== '') {
		$('.completeWriting').addClass('registrable');
	} else {
		$('.completeWriting').removeClass('registrable');
	}
}


$('#issuTtlVal').on('change', function () {
	checkInput();
});

// 글쓰기 버튼
$('.postWritingBox').on('click', function () {
	$('.modalIssueInsertForm').css('display', 'block');
	$('.workModalContentBack').css('display', 'none');
});

// toastuieditor
const editor = new toastui.Editor({
	el: document.querySelector('#editor'), // 에디터를 적용할 요소 (컨테이너)
	height: '400px', // 에디터 영역의 높이 값 (OOOpx || auto)
	initialEditType: 'wysiwyg', // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
	initialValue: '내용을 입력해 주세요.', // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
	previewStyle: 'vertical' // 마크다운 프리뷰 스타일 (tab || vertical)
});

// 글쓰기 취소 버튼
$('.modalIssueInsertForm').on('click', '.cancelWriting', function () {
	$('#cancelConfirmationModal').modal('show');

	// ' 확인' 버튼이 클릭 시
	$('#confirmCancel').on('click', function () {
		// 입력 필드 초기화
		$('#issuKndVal').text('선택');
		$('#issuRankVal').text('선택');
		$('#issuTtlVal').val('');
		editor.setHTML('');
		$('#issueFileList').empty();
		checkFile();
		checkInput();

		$('#cancelConfirmationModal').modal('hide');
		$('.modalIssueInsertForm').css('display', 'none');
		$('.workModalContentBack').css('display', 'block');
		issuePaging(1);
	});

	// '취소' 버튼 클릭 시
	$('#cancelCancel').on('click', function () {
		$('#cancelConfirmationModal').modal('hide');
	});
});

// 제목 입력하는 textarea 클릭시 focus
$('.modalIssueNameUpdate').on('click', function () {
	$('.issueNameBack').addClass('focus');
});
// textarea 벗어날 때 focus 클래스 삭제하기.
$('.modalIssueNameUpdate').on('blur', function () {
	$('.issueNameBack').removeClass('focus');
});
// 엔터 누를 때 줄바꿈 방지하기..
$('.modalIssueNameUpdate').on('keydown', function (e) {
	if (e.keyCode == 13) {
		e.preventDefault();
	}
});



$('.completeWriting').on('click', function () {
	// "등록" 버튼이 활성화 상태일 때만 실행됨
	if ($(this).hasClass('registrable')) {
		savePost();
	} else {
		$('#checkingInput').modal('show');
		$('#checkingInput').css('display', 'block');
		// 확인 버튼 클릭 시
		$('#confirmCheckingInput').on('click', function () {
			$('#checkingInput').modal('hide');
			$('#checkingInput').css('display', 'none');
		});
	}

});

function savePost() {

	var formData = new FormData();
	formData.append('issuTtl', $('#issuTtlVal').val());


	// 중요도 text 값 확인 후 data로 넣기 !
	if ($('#issuRankVal').text() == '매우높음') {
		formData.append('issuRank', '0G1');
	} else if ($('#issuRankVal').text() == '높음') {
		formData.append('issuRank', '0G2');
	} else if ($('#issuRankVal').text() == '보통') {
		formData.append('issuRank', '0G3');
	} else if ($('#issuRankVal').text() == '낮음') {
		formData.append('issuRank', '0G4');
	} else if ($('#issuRankVal').text() == '매우낮음') {
		formData.append('issuRank', '0G5');
	}
	// 종류 text 값 확인 후 data로 넣기 !
	if ($('#issuKndVal').text() == '버그') {
		formData.append('issuKnd', '0L1');
	} else if ($('#issuKndVal').text() == '개선사항') {
		formData.append('issuKnd', '0L2');
	} else if ($('#issuKndVal').text() == '새기능') {
		formData.append('issuKnd', '0L3');
	} else if ($('#issuKndVal').text() == '업무') {
		formData.append('issuKnd', '0L4');
	}

	let content = editor.getHTML();
	formData.append('issuCntn', content);

	var inputFile = document.querySelector('.real-upload');
	var fileInfo = inputFile.files;
	console.log(fileInfo.length + "길이는 !!!!!");

	for (i = 0; i < fileInfo.length; i++) {
		formData.append("files", fileInfo[i]);
	};

	$.ajax({
		url: '/ModalAjaxIssueInsert',
		type: 'POST',
		processData: false,	//기본값은 true, ajax 통신을 통해 데이터를 전송할 때, 기본적으로 key와 value값을 Query String으로 변환해서 보냅니다.
		contentType: false,	// multipart/form-data타입을 사용하기위해 false 로 지정합니다.
		data: formData,
		success: function () {
			alert("등록에 성공 !!");
			$('.modalIssueInsertForm').css('display', 'none');
			$('.workModalContentBack').css('display', 'block');
			issuePaging(1);
		},
		error: function (reject) {
			alert("등록에 실패");
			console.log(reject);
		}
	});
};


// 드랍다운 
$('.dropdown-item').on('click', function () {
	$(this).parent().siblings().attr('class', $(this).children().attr('class'));
	$(this).parent().siblings().text($(this).text());
	checkInput();
});


// 파일 업로드 클릭
const realUpload = document.querySelector('.real-upload');
const upload = document.querySelector('.LoadBtn.upload');

upload.addEventListener('click', () => realUpload.click());

checkFile();

// 업로드 된 파일이 없을 경우 ..
function checkFile() {
	if ($('#issueFileList tr').length == 0) {
		var trTag = $('<tr/>').attr('class', 'fileEmpty');
		var fileEmptyTag = $('<td/>').text('등록된 파일은 여기에 저장됩니다').css('color', '#A8A8A8');
		var emptyTdTag = $('<td/>');
		var emptyTdTags = $('<td/>');
		trTag.append(fileEmptyTag);
		trTag.append(emptyTdTag);
		trTag.append(emptyTdTags);
		$('#issueFileList').append(trTag);
	};
};

// 파일 삭제 버튼 누를 시 ..
function removeFile(element) {
	removeFileFromDataTransfer(element);
	var tr = $(element).closest('tr');
	tr.remove();

	checkFile();
}


// 파일 사이즈 변환하기
function getByteSize(size) {
	const byteUnits = ["KB", "MB", "GB", "TB"];
	for (let i = 0; i < byteUnits.length; i++) {
		size = Math.floor(size / 1024);
		if (size < 1024) return size.toFixed(1) + byteUnits[i];
	}
};

// 파일 추가
function addIssueFile(element) {
	const file = element.files[0];
	// 1.  파일 선택 창에서 취소 버튼이 클릭되는 경우
	if (!file) {
		return false;
	}

	// 2. 파일 크기가 10MB을 초과하는 경우
	const fileSize = Math.floor(file.size / 1024 / 1024);

	if (fileSize > 10) {
		alert('10MB 이하의 파일로 업로드해 주세요.');
		return false;
	}

	if (file) {
		$('.fileEmpty').remove();
		var trTag = $('<tr/>');
		var fileNameTag = $('<td/>').text(file.name);
		var fileSizeTag = $('<td/>').text(getByteSize(file.size));
		var deleteFileTag = $('<td/>').attr('class', 'deleteFile').attr('onclick', 'removeFile(this);');
		trTag.append(fileNameTag);
		trTag.append(fileSizeTag);
		trTag.append(deleteFileTag);

		$('#issueFileList').append(trTag);

	}
};

const dataTransfer = new DataTransfer();

$("#files").change(function () {

	let fileArr = document.getElementById("files").files

	if (fileArr != null && fileArr.length > 0) {

		// =====DataTransfer 파일 관리========
		for (var i = 0; i < fileArr.length; i++) {
			dataTransfer.items.add(fileArr[i])
		}
		/*	console.log("dataTransfer =>",dataTransfer.files)
		   console.log("input FIles =>", document.getElementById("files").files)*/
		document.getElementById("files").files = dataTransfer.files;
		// ==========================================

	}

})

function removeFileFromDataTransfer(element) {
	var parent = $(element).parent().parent();
	var children = $(parent).children();
	var index = children.index($(element).parent());

	if (index != -1) {
		dataTransfer.items.remove(index);
		document.getElementById("files").files = dataTransfer.files;
	}
}
/* 이슈 등록 폼 END */
// toastuiviewer
const viewer = toastui.Editor.factory({
	el: document.querySelector('#viewer'),
	viewer: true,
	height: '600px'
});
/* 이슈 상세 보기 START */
// 날짜 변환
function dateFormatting(date) {

	let parseDate = new Date(date);

	let year = parseDate.getFullYear();
	let month = ('0' + (parseDate.getMonth() + 1)).slice(-2);
	let day = ('0' + parseDate.getDate()).slice(-2);
	return year + '-' + month + '-' + day;
}

// 글상세보기에서 글목록으로 돌아가기 버튼
function returnIssueList() {
	// 입력 필드 초기화
	$('#issuReplyCntn').val('');
	$('#updateReplyComment').val('');
	$('#issueInfoFiles').empty();
	$('.modalIssueInfoForm').css('display', 'none');
	$('.workModalContentBack').css('display', 'block');
	$('.issueInfoFileList').css('display', 'none');
	$('.issueInfoCommentList').css('display', 'none');
	issuePaging(1);
}

function viewIssueInfo(id) {

	var issuNo = id;
	var modals = $('.modalIssueInfoForm');
	modals.find('.modalIssueName').val('');
	modals.find('.m-b-3').text('');
	modals.find('.m-b-2').text('');
	modals.find('.issueRankInfo').empty();
	modals.find('.issueKndInfo').empty();
	modals.find('.issueStInfo').empty();

	$.ajax('/ModalIssueInfo?issuNo=' + issuNo)
		.done(function (data) {
			var issueInfo = data.issueInfo;
			var issueFile = data.issueFile;
			var issueInfoFiles = $('#issueInfoFiles');

			if (issueFile.length != 0) {
				issueInfoFiles.empty();
			} else {
				issueInfoFiles.empty();
				var itrTag = $('<tr/>').addClass('InfoFilesEmpty');
				var itdTag = $('<td/>').text('등록된 파일이 없습니다').css('color', '#A8A8A8');
				itrTag.append(itdTag);
				issueInfoFiles.append(itrTag);
			};

			$('.modalIssueName').val(issueInfo.issuTtl).data('issuNo', issuNo);
			$('.m-b-3').text(issueInfo.membId);

			$('.m-b-2').text(dateFormatting(issueInfo.issuDt));
			viewer.setMarkdown(issueInfo.issuCntn);
			countReply(issuNo);
			countFile(issuNo);
			// 종류 
			var kndVal = $('.issueKndInfo');
			if (issueInfo.issuKnd == '0L1') {
				kndVal.append('<p>종류</p><div class="badge badge-danger" style="margin-bottom: 15px; ">버그</div>');
			} else if (issueInfo.issuKnd == '0L2') {
				kndVal.append('<p>종류</p><div class="badge badge-warning" style="margin-bottom: 15px; ">개선사항</div>');
			} else if (issueInfo.issuKnd == '0L3') {
				kndVal.append('<p>종류</p><div class="badge badge-info" style="margin-bottom: 15px; ">새기능</div>');
			} else if (issueInfo.issuKnd == '0L4') {
				kndVal.append('<p>종류</p><div class="badge badge-light" style="margin-bottom: 15px; ">업무</div>');
			}
			// 상태
			if (issueInfo.issuSt == '0E1') {
				$('.issueStInfo').append('<p>상태</p><div class="badge badge-pill badge-success" style="margin-bottom: 15px; ">해결</div>');
			} else if (issueInfo.issuSt == '0E2') {
				$('.issueStInfo').append('<p>상태</p><div class="badge badge-pill badge-dark" style="margin-bottom: 15px; ">미해결</div>');
			} else if (issueInfo.issuSt == '0E3') {
				$('.issueStInfo').append('<p>상태</p><div class="badge badge-pill badge-secondary" style="margin-bottom: 15px; ">해결불가</div>');
			}

			if (issueInfo.issuRank == '0G1') {
				$('.issueRankInfo').append('<p>중요도</p><div class="badge badge-pill badge-primary" style="margin-bottom: 15px; ">매우높음</div>');
			} else if (issueInfo.issuRank == '0G2') {
				$('.issueRankInfo').append('<p>중요도</p><div class="badge badge-pill badge-secondary" style="margin-bottom: 15px; ">높음</div>');
			} else if (issueInfo.issuRank == '0G3') {
				$('.issueRankInfo').append('<p>중요도</p><div class="badge badge-pill badge-success" style="margin-bottom: 15px; ">보통</div>');
			} else if (issueInfo.issuRank == '0G4') {
				$('.issueRankInfo').append('<p>중요도</p><div class="badge badge-pill badge-warning" style="margin-bottom: 15px; ">낮음</div>');
			} else if (issueInfo.issuRank == '0G5') {
				$('.issueRankInfo').append('<p>중요도</p><div class="badge badge-pill badge-danger" style="margin-bottom: 15px; ">매우낮음</div>');
			}

			// 첨부된 파일 리스트.												
			$.each(issueFile, function (idx, item) {
				var trTag = $('<tr/>').data('fileNo', item.issuFileNo);

				var fileNameTag = $('<td/>').text(item.issuFileNm);
				var fileSizeTag = $('<td/>').text(getByteSize(item.issuFileSize));
				var downloadFileTag = $('<td/>').attr('class', 'downloadFile').attr("onclick", "fileDownloading(" + issuNo + "," + item.issuFileNo + ")");
				trTag.append(fileNameTag);
				trTag.append(fileSizeTag);
				trTag.append(downloadFileTag);

				issueInfoFiles.append(trTag);
			});
			countFile(issuNo);

		})
		.fail(function (error) {
			console.log(error);
		})

	$('.modalIssueInfoForm').css('display', 'block');
	$('.workModalContentBack').css('display', 'none');
};

function fileDownloading(issuNo, issuFileNo) {
	location.href = '/issues/' + issuNo + '/files/' + issuFileNo + '/download';
}


$(document).on('click', '.p-t-15', function () {
	if ($('.issueInfoFileList').css("display") == "none") {
		$('.issueInfoFileList').css('display', 'block');
	} else {
		$('.issueInfoFileList').css('display', 'none');
	}

	$('.issueInfoCommentList').css('display', 'none');
});

$(document).on('click', '.p-t-16', function () {
	if ($('.issueInfoCommentList').css("display") == "none") {
		$('.issueInfoCommentList').css('display', 'block');
		findAllReply();
	} else {
		$('.issueInfoCommentList').css('display', 'none');
	}

	$('.issueInfoFileList').css('display', 'none');
});

// 댓글 길이 카운팅
function countingLength(content) {
	if (content.value.length > 300) {
		alert('댓글을 300자 이하로 입력해 주세요.');
		content.value = content.value.substring(0, 300);
		content.focus();
	}
	document.getElementById('counter').innerText = content.value.length + '/300자';
}

// 댓글 저장
function saveReply() {
	const issueReplyCntn = document.getElementById('issueReplyCntn');
	const issuNo = $('.modalIssueName').data('issuNo');

	const params = {
		issuNo: issuNo,
		issuRplyCntn: issueReplyCntn.value,
		membId: 'ojunkwon'
	}

	$.ajax({
		url: '/issues/' + issuNo + '/reply',
		type: 'post',
		contentType: 'application/json;',
		data: JSON.stringify(params)
	})
		.done(function (data) {
			findAllReply();
			countReply(issuNo);
		})
		.fail(function (error) {
			console.log(error);
		})
}

// 전체 댓글 조회
function findAllReply() {
	const issuNo = $('.modalIssueName').data('issuNo');

	$.ajax({
		url: '/issues/' + issuNo + '/reply',
		type: 'GET'
	})
		.done(function (response) {

			// 1. 조회된 데이터가 없는 경우
			if (!response.length) {
				document.querySelector('.IssueReviews').innerHTML = '<div class="cm_none"><p>등록된 댓글이 없습니다.</p></div>';
				return false;
			}

			// 2. 렌더링 할 HTML을 저장할 변수
			let commentHtml = '';

			// 3. 댓글 HTML 추가
			response.forEach(row => {
				commentHtml += `                            
                            <div class="IssueReviews">
							<div class="issue__reply__item">
								<div class="issue__reply__item__pic">
									<img src="images/users/2.jpg" alt="">
								</div>
								<div class="issue__reply__item__text">
									<h6>${row.membId} - <span>${dateFormatting(row.issuRplyDt)}</span>
									<a id="edit_btn" onclick="openReplyUpdateForm(${row.issuRplyNo})"> <img class="toolsIcon"
										src="https://community.akamai.steamstatic.com/public/images//sharedfiles/icons/icon_edit.png" 
										style="margin-left: 170px; ">
										수정
									</a>
									<a id="del_btn" onclick="deleteReply(${row.issuRplyNo});"> <img class="toolsIcon"
										src="https://community.akamai.steamstatic.com/public/images//sharedfiles/icons/icon_delete.png">
										삭제
									</a>
									
									</h6>
									
									<div id="IssueReplyEditForm" style="display: none;">
											<textarea id="updateReplyComment" class="reply_edit_text_area" maxlength="300" rows="2"
												cols="30" placeholder="수정할 내용을 입력"></textarea>
											<button type="button" class="button condensed save" id="replyUpdateBtn">저장</button>
											<button type="button" class="button condensed cancel"
												onclick="closeReplyUpdateForm();">취소</button>
									</div>
									
									
									<p id="replyText">${row.issuRplyCntn}</p>
								</div>
							</div>
                        `;

				// 4. class가 "IssueReviews"인 요소를 찾아 HTML을 렌더링
				document.querySelector('.IssueReviews').innerHTML = commentHtml;

			})
			countReply(issuNo);
		})
		.fail(function (err) {
			console.log(err)
		})
}

// 댓글 수정 div open
function openReplyUpdateForm(id) {

	const issuNo = $('.modalIssueName').data('issuNo');
	const issuRplyNo = id;
	$.ajax({
		url: '/issues/' + issuNo + '/reply/' + issuRplyNo,
		type: 'get'
	})
		.done(function (response) {
			var editForm = document.getElementById('IssueReplyEditForm');
			var replyText = document.getElementById('replyText');

			if (editForm.style.display === 'none') {
				editForm.style.display = 'block';
				replyText.style.display = 'none';

				document.getElementById('updateReplyComment').value = response.issuRplyCntn;
				document.getElementById('replyUpdateBtn').setAttribute('onclick', `updateReply(${id})`);
			} else {
				editForm.style.display = 'none';
				replyText.style.display = 'block';
			}
		})

		.fail(function (err) {
			console.log(err)
		})

}

// 댓글 수정 div close
function closeReplyUpdateForm() {
	document.getElementById('updateReplyComment').value = '';
	document.getElementById('replyUpdateBtn').removeAttribute('onclick');

	document.getElementById('IssueReplyEditForm').style.display = 'none';
	document.getElementById('replyText').style.display = 'block';
}

// 댓글 수정 ajax
function updateReply(id) {
	const issuNo = $('.modalIssueName').data('issuNo');
	const issuRplyNo = id;
	const writer = 'ojunkwon';
	const content = document.getElementById('updateReplyComment');

	const params = {
		issuRplyNo: id,
		issuNo: issuNo,
		issuRplyCntn: content.value,
		membId: writer
	}

	$.ajax({
		url: '/issues/' + issuNo + '/reply/' + issuRplyNo,
		type: 'put',
		contentType: 'application/json;',
		data: JSON.stringify(params)
	})
		.done(function (response) {
			alert('수정되었습니다.');
			findAllReply();
			closeReplyUpdateForm();
			countReply(issuNo);
		})

		.fail(function (err) {
			console.log(err)
		})
}

// 댓글 삭제
function deleteReply(id) {

	if (confirm('선택하신 댓글을 삭제할까요?')) {

		const issuNo = $('.modalIssueName').data('issuNo');
		const issuRplyNo = id;

		$.ajax({
			url: '/issues/' + issuNo + '/reply/' + issuRplyNo,
			type: 'delete'
		})
			.done(function (response) {
				alert('삭제가 완료되었습니다');
				findAllReply();
				countReply(issuNo);
			})

			.fail(function (err) {
				console.log(err)
			})
	}
}

// 댓글 카운트
function countReply(issuNo) {
	$.ajax({
		url: '/issues/' + issuNo + '/reply/count',
		type: 'get'
	})
		.done(function (response) {
			$('#countReply').text('(' + response + ')');
		})
		.fail(function (err) {
			console.log(err)
		})
}

// 파일 카운트
function countFile(issuNo) {
	$.ajax({
		url: '/issues/' + issuNo + '/files/count',
		type: 'get'
	})
		.done(function (response) {
			$('#countFile').text('(' + response + ')');
		})
		.fail(function (err) {
			console.log(err)
		})
}
/* 이슈 상세 보기 END */

/* 이슈 수정 FORM */

// toastuieditor
const editorEditing = new toastui.Editor({
	el: document.querySelector('#editorEditing'), // 에디터를 적용할 요소 (컨테이너)
	height: '400px', // 에디터 영역의 높이 값 (OOOpx || auto)
	initialEditType: 'wysiwyg', // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
	initialValue: '내용을 입력해 주세요.', // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
	previewStyle: 'vertical' // 마크다운 프리뷰 스타일 (tab || vertical)
});


// 이슈 수정 폼
function switchEditForm() {

	const issuNo = $('.modalIssueName').data('issuNo');

	$.ajax({
		url: '/ModalIssueInfo?issuNo=' + issuNo,
		type: 'get'
	})
		.done(function (response) {
			var issueEditForm = document.querySelector('.modalIssueEditForm');
			var infoForm = document.querySelector('.modalIssueInfoForm');
			var kndVal = document.getElementById('editIssuKndVal');
			var stVal = document.getElementById('editIssuStVal');
			var rankVal = document.getElementById('editIssuRankVal');

			if (issueEditForm.style.display === 'none') {
				issueEditForm.style.display = 'block';
				infoForm.style.display = 'none';

				document.getElementById('editIssuTtlVal').value = response.issueInfo.issuTtl;
				editorEditing.setHTML(response.issueInfo.issuCntn);
				if (response.issueInfo.issuKnd == '0L1') {
					kndVal.className = 'badge badge-danger';
					kndVal.innerHTML = '버그';
				} else if (response.issueInfo.issuKnd == '0L2') {
					kndVal.className = 'badge badge-warning';
					kndVal.innerHTML = '개선사항';
				} else if (response.issueInfo.issuKnd == '0L3') {
					kndVal.className = 'badge badge-info';
					kndVal.innerHTML = '새기능';
				} else if (response.issueInfo.issuKnd == '0L4') {
					kndVal.className = 'badge badge-light';
					kndVal.innerHTML = '업무';
				}

				if (response.issueInfo.issuSt == '0E1') {
					stVal.className = 'badge badge-pill badge-success';
					stVal.innerHTML = '해결';
				} else if (response.issueInfo.issuSt == '0E2') {
					stVal.className = 'badge badge-pill badge-dark';
					stVal.innerHTML = '미해결';
				} else if (response.issueInfo.issuSt == '0E3') {
					stVal.className = 'badge badge-pill badge-secondary';
					stVal.innerHTML = '해결불가';
				}

				if (response.issueInfo.issuRank == '0G1') {
					rankVal.className = 'badge badge-primary';
					rankVal.innerHTML = '매우높음';
				} else if (response.issueInfo.issuRank == '0G2') {
					rankVal.className = 'badge badge-secondary';
					rankVal.innerHTML = '높음';
				} else if (response.issueInfo.issuRank == '0G3') {
					rankVal.className = 'badge badge-success';
					rankVal.innerHTML = '보통';
				} else if (response.issueInfo.issuRank == '0G4') {
					rankVal.className = 'badge badge-warning';
					rankVal.innerHTML = '낮음';
				} else if (response.issueInfo.issuRank == '0G5') {
					rankVal.className = 'badge badge-danger';
					rankVal.innerHTML = '매우낮음';
				}

				checkEditInput();
				findAllFile();
			} else {
				issueEditForm.style.display = 'none';
				infoForm.style.display = 'block';
			}
		})

		.fail(function (err) {
			console.log(err)
		})
}

// "수정" 버튼 활성화 or 비활성화 (제목, 종류, 중요도) 3개의 값이 다 들어오는 경우 활성화 된다.
function checkEditInput() {
	var issuTtlVal = $('#editIssuTtlVal').val();

	if (issuTtlVal == '') {
		$('.completeEditing').removeClass('registrable');
	} else {
		$('.completeEditing').addClass('registrable');
	}
}

$('#editIssuTtlVal').on('change', function () {
	checkEditInput();
});

// ajax 이슈 수정
function updateIssue() {
	var formData = new FormData();

	var issuNo = $('.modalIssueName').data('issuNo');
	formData.append('issuNo', issuNo);

	var title = $('#editIssuTtlVal').val();
	formData.append('issuTtl', title);

	var content = editorEditing.getHTML();
	formData.append('issuCntn', content);

	var kind = $('#editIssuKndVal').text();
	var rank = $('#editIssuRankVal').text();
	var status = $('#editIssuStVal').text();

	// 중요도 text 값 확인 후 data로 넣기 !
	if (rank == '매우높음') {
		rank = '0G1';
	} else if (rank == '높음') {
		rank = '0G2';
	} else if (rank == '보통') {
		rank = '0G3';
	} else if (rank == '낮음') {
		rank = '0G4';
	} else if (rank == '매우낮음') {
		rank = '0G5';
	}
	// 종류 text 값 확인 후 data로 넣기 !
	if (kind == '버그') {
		kind = '0L1';
	} else if (kind == '개선사항') {
		kind = '0L2';
	} else if (kind == '새기능') {
		kind = '0L3';
	} else if (kind == '업무') {
		kind = '0L4';
	}

	// 상태 text 값 확인 후 data로 넣기 !
	if (status == '해결') {
		status = '0E1';
	} else if (status == '미해결') {
		status = '0E2';
	} else if (status == '해결불가') {
		status = '0E3';
	}
	formData.append('issuRank', rank);
	formData.append('issuKnd', kind);
	formData.append('issuSt', status);

	var inputFiles = document.querySelector('.editReal-upload');
	var filesInfo = inputFiles.files;

	for (i = 0; i < filesInfo.length; i++) {
		formData.append("editFiles", filesInfo[i]);
	}
	formData.append('removeFileIds', removeFileId.getAll().join());
	
	$.ajax({
		url: '/ModalIssueUpdate',
		type: 'post',
		processData: false,	//기본값은 true, ajax 통신을 통해 데이터를 전송할 때, 기본적으로 key와 value값을 Query String으로 변환해서 보냅니다.
		contentType: false,	// multipart/form-data타입을 사용하기위해 false 로 지정합니다.
		data: formData
	})
		.done(function (response) {
			alert('수정되었습니다');
			$('.modalIssueEditForm').css('display', 'none');
			$('.modalIssueInfoForm').css('display', 'block');
			viewIssueInfo(response.issuNo);
			removeFileId.removeAll();
			editDataTransfer.clearData();
		})
		.fail(function (err) {
			console.log(err)
		})
}

// 이슈 수정하기 취소 버튼
$('.modalIssueEditForm').on('click', '.cancelEditing', function () {
	$('#cancelEditConfirmationModal').modal('show');

	// ' 확인' 버튼이 클릭 시
	$('#confirmEditCancel').on('click', function () {
		// 입력 필드 초기화

		$('#issuTtlVal').val('');
		editorEditing.setHTML('');
        editDataTransfer.clearData();
		$('#cancelEditConfirmationModal').modal('hide');
		$('.modalIssueEditForm').css('display', 'none');
		$('.modalIssueInfoForm').css('display', 'block');
	});

	// '취소' 버튼 클릭 시
	$('#cancelCancel').on('click', function () {
		$('#cancelEditConfirmationModal').modal('hide');
	});
});

$('.completeEditing').on('click', function () {
	// "수정" 버튼이 활성화 상태일 때만 실행됨
	if ($(this).hasClass('registrable')) {
		updateIssue();
	} else {
		$('#editCheckingInput').modal('show');
		$('#editCheckingInput').css('display', 'block');
		// 확인 버튼 클릭 시
		$('#confirmEditCheckingInput').on('click', function () {
			$('#editCheckingInput').modal('hide');
			$('#editCheckingInput').css('display', 'none');
		});
	}

});

// 이슈 삭제
function deleteIssue() {
	var issuNo = $('.modalIssueName').data('issuNo');
	// 경고창
	$('#delConfirmationModal').modal('show');

	$('#delCancel').on('click', function () {
		$('#delConfirmationModal').modal('hide');
	})

	$('#delConfirm').on('click', function () {
		$('#delConfirmationModal').modal('hide');
		$.ajax({
			url: '/ModalIssueDelete',
			type: 'post',
			data: { issuNo: issuNo } // 데이터를 객체 형식으로 전달
		})
			.done(function (response) {
				$('.modalIssueInfoForm').css('display', 'none');
				$('.workModalContentBack').css('display', 'block');
				issuePaging(1);
			})
			.fail(function (err) {
				console.log(err)
			})
	})
}

// 이슈 수정 창에서 파일 리스트 조회
function findAllFile() {
	var issuNo = $('.modalIssueName').data('issuNo');
	$('#editIssueFileList').empty();
	$.ajax({
		url: '/issues/' + issuNo + '/files',
		type: 'get'
	})
		.done(function (response) {
			$.each(response, function (idx, item) {

				var trTag = $('<tr/>').data('editFileNo', item.issuFileNo);
				console.log(item.issuFileNo + '입니다');
				var fileNameTag = $('<td/>').text(item.issuFileNm);
				var fileSizeTag = $('<td/>').text(getByteSize(item.issuFileSize));
				var deleteFileTag = $('<td/>').attr('class', 'deleteEditFile').attr('onclick', 'removeExistingFile(this,' + item.issuFileNo + ')'); // 기존꺼 삭제하는 함수.

				trTag.append(fileNameTag);
				trTag.append(fileSizeTag);
				trTag.append(deleteFileTag);

				$('#editIssueFileList').append(trTag);
			})
		})
		.fail(function (err) {
			console.log(err);
		})
}


// 수정 폼에서 파일 추가
const editRealUpload = document.querySelector('.editReal-upload');
const editUpload = document.querySelector('.editingLoadBtn.upload');
editUpload.addEventListener('click', () => editRealUpload.click());

function addIssueFileE(element) {
	const file = element.files[0];

	// 1. 파일 선택 창에서 취소 버튼이 클릭 되는 경우
	if (!file) {
		return false;
	}
	// 2. 파일 크기가 10MB을 초과하는 경우
	const editFileSize = Math.floor(file.size / 1024 / 1024);
	if (editFileSize > 10) {
		alert('10MB 이하의 파일로 업로드해 주세요.');
		return false;
	}

	if (file) {
		var trTag = $('<tr/>').addClass('added');
		var fileNameTag = $('<td/>').text(file.name);
		var fileSizeTag = $('<td/>').text(getByteSize(file.size));
		var deleteFileTag = $('<td/>').attr('class', 'deleteEditFile').attr('onclick', 'editRemoveFile(this);');

		trTag.append(fileNameTag);
		trTag.append(fileSizeTag);
		trTag.append(deleteFileTag);

		$('#editIssueFileList').append(trTag);
	}
}

const editDataTransfer = new DataTransfer();

$("#editFiles").change(function () {
	let fileArr = document.getElementById("editFiles").files

	if (fileArr != null && fileArr.length > 0) {
		// DataTransfer 파일 관리 //
		for (var i = 0; i < fileArr.length; i++) {
			editDataTransfer.items.add(fileArr[i])
			console.log("dataTransfer =>", editDataTransfer.files)
			console.log("input FIles =>", document.getElementById("editFiles").files)
		}
		document.getElementById('editFiles').files = editDataTransfer.files;
	}
})
// 수정 폼에서 새롭게 추가된 파일 삭제 시
function removeEditFileFromDataTransfer(element) {
	var addedFiles = $(".added");
	var index = addedFiles.index($(element).parent());
	console.log(index + "입니다");
	if (index != -1) {
		editDataTransfer.items.remove(index);
		document.getElementById("editFiles").files = editDataTransfer.files;
	}
}
// 수정 폼에서 새롭게 추가된 파일 삭제 시
function editRemoveFile(element) {
	removeEditFileFromDataTransfer(element);
	var tr = $(element).closest('tr');
	tr.remove();
}

// 파일 삭제 처리용 익명 함수
const removeFileId = (function () {
	const ids = [];
	return {
		add(id) {
			if (ids.includes(id)) {
				return false;
			}
			ids.push(id);
		},
		getAll() {
			return ids;
		},
		removeAll() {
			ids.length = 0;
		}
	}
}());

// 수정 폼 기존 파일 삭제
function removeExistingFile(element, id) {
	// 1. 삭제할 파일 id 추가
	if (id) {
		removeFileId.add(id);
	}
	// 2. 파일 영역 삭제
	var tr = $(element).closest('tr');
	tr.remove();
}
/* 이슈 수정 FORM END*/