/**
 * 调用后台批量删除方法
 */
function deleteBatch() {
	$("#mainForm").attr("action","DeleteBatchServlet.action");
	$("#mainForm").submit();
}

function changeCurrentPage(currentPage) {
    $("#currentPage").val(currentPage);
    $("#mainForm").submit();
}
