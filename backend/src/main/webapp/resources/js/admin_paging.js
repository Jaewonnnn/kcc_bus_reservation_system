let currentPage = 1;
const rowsPerPage = 10;
let totalRows = 0;


const tableBody = document.querySelector('#table-body');
const scheduleList = Array.from(tableBody.querySelectorAll('tr'));

function getTotalPages() {
    return Math.ceil(scheduleList.length / rowsPerPage);
}

function displayTableData(page) {
    const startIndex = (page - 1) * rowsPerPage;
    const endIndex = startIndex + rowsPerPage;

    // 테이블 본문 업데이트
    tableBody.innerHTML = '';

    const rowsToShow = scheduleList.slice(startIndex, endIndex);

    rowsToShow.forEach(row => {
        tableBody.appendChild(row);
    });

    updatePagination();
}

// 페이지네이션 업데이트
function updatePagination() {
    const pageContainer = document.getElementById('page-number');
    const totalPages = getTotalPages();

    pageContainer.innerHTML = '';

    const pageGroupSize = 10;
    const currentPageGroup = Math.floor((currentPage - 1) / pageGroupSize);
    const startPage = currentPageGroup * pageGroupSize + 1;
    const endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

    if (currentPageGroup > 0) {
        const prevGroupItem = document.createElement('li');
        prevGroupItem.classList.add('page-item');
        prevGroupItem.innerHTML = `<a class="page-link" href="#"><<</a>`;
        prevGroupItem.addEventListener('click', function () {
            currentPage = startPage - 1;
            displayTableData(currentPage);
        });
        pageContainer.appendChild(prevGroupItem);
    }

    for (let i = startPage; i <= endPage; i++) {
        const pageItem = document.createElement('li');
        pageItem.classList.add('page-item');
        pageItem.innerHTML = `<a class="page-link" href="#">${i}</a>`;

        // 현재 페이지 강조
        if (i === currentPage) {
            pageItem.classList.add('active');
        }

        pageItem.addEventListener('click', function () {
            currentPage = i;
            displayTableData(currentPage);
        });

        pageContainer.appendChild(pageItem);
    }

    if (endPage < totalPages) {
        const nextGroupItem = document.createElement('li');
        nextGroupItem.classList.add('page-item');
        nextGroupItem.innerHTML = `<a class="page-link" href="#">>></a>`;
        nextGroupItem.addEventListener('click', function () {
            currentPage = endPage + 1;
            displayTableData(currentPage);
        });
        pageContainer.appendChild(nextGroupItem);
    }
}

document.querySelector('.prev').addEventListener('click', function () {
    if (currentPage > 1) {
        currentPage--;
        displayTableData(currentPage);
    }
});

document.querySelector('.next').addEventListener('click', function () {
    if (currentPage < getTotalPages()) {
        currentPage++;
        displayTableData(currentPage);
    }
});

function initializeTable() {
    totalRows = scheduleList.length;
    displayTableData(currentPage);
}

initializeTable();
