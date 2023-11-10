// 파일 사이즈 변환하기
function getByteSize(size) {
	const byteUnits = ["KB", "MB", "GB", "TB"];
	for (let i = 0; i < byteUnits.length; i++) {
		size = Math.floor(size / 1024);
		if (size < 1024) return size.toFixed(1) + byteUnits[i];
	}
}

// 날짜 변환
function dateFormatting(date) {

	let parseDate = new Date(date);

	let year = parseDate.getFullYear();
	let month = ('0' + (parseDate.getMonth() + 1)).slice(-2);
	let day = ('0' + parseDate.getDate()).slice(-2);
	return year + '-' + month + '-' + day;
}