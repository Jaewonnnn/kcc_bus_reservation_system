$(document).ready(function() {
    const itemsPerPage = 8; // 페이지당 항목 수
    const $tableBody = $('#table-body');
    const $pagination = $('#page-number');
    const $prevButton = $('.prev');
    const $nextButton = $('.next');

    let currentPage = 1;
    const totalItems = $tableBody.children('tr').length; // 총 항목 수
    const totalPages = Math.ceil(totalItems / itemsPerPage); // 총 페이지 수

    function showPage(page) {
        $tableBody.children('tr').hide(); // 모든 항목 숨기기
        const start = (page - 1) * itemsPerPage; // 시작 인덱스
        const end = start + itemsPerPage; // 끝 인덱스
        $tableBody.children('tr').slice(start, end).show(); // 현재 페이지의 항목만 표시
    }

    function updatePagination() {
        $pagination.empty(); // 기존 페이지 번호 삭제
        for (let i = 1; i <= totalPages; i++) {
            const $pageItem = $('<li class="page-item"></li>');
            const $pageLink = $(`<a class="page-link" href="#">${i}</a>`);
            $pageLink.on('click', function(e) {
                e.preventDefault();
                currentPage = i;
                showPage(currentPage);
                updatePagination();
            });
            $pageItem.append($pageLink);
            $pagination.append($pageItem);
        }
    }

    $prevButton.on('click', function(e) {
        e.preventDefault();
        if (currentPage > 1) {
            currentPage--;
            showPage(currentPage);
            updatePagination();
        }
    });

    $nextButton.on('click', function(e) {
        e.preventDefault();
        if (currentPage < totalPages) {
            currentPage++;
            showPage(currentPage);
            updatePagination();
        }
    });

    // 초기 페이지 로드
    showPage(currentPage);
    updatePagination();
});