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

				if (item.issuKnd === 'BUG') {
					trTag.append('<th><span class="badge badge-danger" th:text="버그">버그</span></th>');
				} else if (item.issuKnd === 'IMPR') {
					trTag.append('<th><span class="badge badge-warning" th:text="개선사항">개선사항</span></th>');
				} else if (item.issuKnd === 'FUNC') {
					trTag.append('<th><span class="badge badge-info" th:text="새기능">새기능</span></th>');
				} else if (item.issuKnd === 'BUSS') {
					trTag.append('<th><span class="badge badge-light" th:text="업무">업무</span></th>');
				}
				trTag.append('<td>' + item.issuCntn + '</td>');
				trTag.append('<td>' + item.membId + '</td>');

				if (item.issuSt === 'solve') {
					trTag.append('<th><span class="badge badge-pill badge-success" th:text="해결">해결</span></th>');
				} else if (item.issuSt === 'unsolve') {
					trTag.append('<th><span class="badge badge-pill badge-dark" th:text="미해결">미해결</span></th>');
				} else if (item.issuSt === 'unsolvable') {
					trTag.append('<th><span class="badge badge-pill badge-secondary" th:text="해결불가">해결불가</span></th>');
				}

				if (item.issuRank === '매우 높음') {
					trTag.append('<th><span class="badge badge-primary" th:text="매우높음">매우높음</span></th>');
				} else if (item.issuRank === '높음') {
					trTag.append('<th><span class="badge badge-secondary" th:text="높음">높음</span></th>');
				} else if (item.issuRank === '보통') {
					trTag.append('<th><span class="badge badge-success" th:text="보통">보통</span></th>');
				} else if (item.issuRank === '낮음') {
					trTag.append('<th><span class="badge badge-warning" th:text="낮음">낮음</span></th>');
				} else if (item.issuRank === '매우 낮음') {
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