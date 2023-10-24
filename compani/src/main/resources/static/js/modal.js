//검색 조건 (제목, 작성자, 전체) 
$('.issue-selectBox').on('click', function () {
	$('.issue-select-ul').css('display', 'block');
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
				var trTag = $('<tr/>');

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

				if (item.issuSt == 'OE1') {
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
			updatePaging(data.issu);


			var prePageNum = data.issu.prePage;
			var nextPageNum = data.issu.nextPage;
			var curPageNum = data.issu.pageNum;
			var navFirstPage = data.issu.navigateFirstPage;
			var navLastPage = data.issu.navigateLastPage;
			var lastPage = data.issu.pages;

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

		// toastuieditor
		const editor = new toastui.Editor({
			el: document.querySelector('#editor'), // 에디터를 적용할 요소 (컨테이너)
			height: '400px', // 에디터 영역의 높이 값 (OOOpx || auto)
			initialEditType: 'wysiwyg', // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
			initialValue: '내용을 입력해 주세요.', // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
			previewStyle: 'vertical' // 마크다운 프리뷰 스타일 (tab || vertical)
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
					alert ("등록에 성공 !!");
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
			/* 	console.log("dataTransfer =>",dataTransfer.files)
	            console.log("input FIles =>", document.getElementById("files").files) */
				document.getElementById("files").files = dataTransfer.files;
				// ==========================================

			}

		})
		
		function removeFileFromDataTransfer(element) {
			var parent =$(element).parent().parent();
/* 			console.log(element);
			console.log(parent); */
			var children =$(parent).children();
		//	console.log(children);
			
			var index = children.index($(element).parent());
	
			if ( index != -1) {
			dataTransfer.items.remove(index);			
			document.getElementById("files").files = dataTransfer.files;
			}
		}
/* 이슈 등록 폼 END */