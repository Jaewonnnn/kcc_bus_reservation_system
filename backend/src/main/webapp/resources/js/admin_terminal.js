document.addEventListener("DOMContentLoaded", function () {
    terminalPagenation();
    searchTerminal();
    terminalUpdate();
    insertTerminal();
});

function terminalPagenation() {
    const rowsPerPage = 8;
    const tableBody = document.getElementById('table-body');
    const paginationContainer = document.getElementById('pagination');
    const rows = Array.from(tableBody.getElementsByTagName('tr'));

    const totalPages = Math.ceil(rows.length / rowsPerPage);

    function renderTable(page) {
        tableBody.innerHTML = '';
        const start = (page - 1) * rowsPerPage;
        const end = start + rowsPerPage;
        const paginatedRows = rows.slice(start, end);
        paginatedRows.forEach(row => tableBody.appendChild(row));
    }

    function renderPagination() {
        paginationContainer.innerHTML = '';
        const ul = document.createElement('ul');
        ul.className = 'pagination';

        // 이전 버튼
        const prev = document.createElement('li');
        prev.className = 'page-item';
        prev.innerHTML = '<a class="page-link prev" href="#"> <<</a>';
        prev.addEventListener('click', () => {
            currentPage = Math.max(1, currentPage - 1);
            updatePagination();
        });
        ul.appendChild(prev);

        // 페이지 번호 링크 및 생략 기호
        const pageNumbers = [];
        if (totalPages <= 7) {
            // 페이지가 7개 이하일 때는 모든 페이지 번호를 표시
            for (let i = 1; i <= totalPages; i++) {
                pageNumbers.push(i);
            }
        } else {
            // 첫 페이지, 마지막 페이지, 현재 페이지를 기준으로 몇 페이지를 표시
            pageNumbers.push(1);
            if (currentPage > 4) {
                pageNumbers.push('...');
            }
            for (let i = Math.max(2, currentPage - 2); i <= Math.min(totalPages - 1, currentPage + 2); i++) {
                pageNumbers.push(i);
            }
            if (currentPage < totalPages - 3) {
                pageNumbers.push('...');
            }
            pageNumbers.push(totalPages);
        }

        pageNumbers.forEach(pageNumber => {
            const li = document.createElement('li');
            li.className = 'page-item';
            if (pageNumber === '...') {
                li.classList.add('disabled');
                li.innerHTML = '<span class="page-link">...</span>';
            } else {
                li.innerHTML = `<a class="page-link" href="#">${pageNumber}</a>`;
                li.addEventListener('click', () => {
                    currentPage = pageNumber;
                    updatePagination();
                });
            }
            ul.appendChild(li);
        });

        // 다음 버튼
        const next = document.createElement('li');
        next.className = 'page-item';
        next.innerHTML = '<a class="page-link next" href="#"> >></a>';
        next.addEventListener('click', () => {
            currentPage = Math.min(totalPages, currentPage + 1);
            updatePagination();
        });
        ul.appendChild(next);

        paginationContainer.appendChild(ul);
    }

    function updatePagination() {
        renderTable(currentPage);
        renderPagination();
    }

    let currentPage = 1;
    updatePagination();
}


function searchTerminal(){
    const searchBox = document.getElementById('search-box');
    const tableRows = document.querySelectorAll('table tbody tr');

    searchBox.addEventListener('input', function() {
        const searchTerm = searchBox.value.toLowerCase();

        tableRows.forEach(row => {
            const terminalName = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
            if (terminalName.includes(searchTerm)) {
                row.style.display = ''; // Show row
            } else {
                row.style.display = 'none'; // Hide row
            }
        });
    });
}


function terminalUpdate() {
    // 모달 열기 및 데이터 채우기
    document.querySelectorAll('#table-body .btn-primary').forEach(button => {
        button.addEventListener('click', function() {
            const row = this.closest('tr');
            const terminalId = row.querySelector('#user-id').innerText;
            const terminalName = row.querySelector('#terminal-name').innerText;
            const address = row.querySelector('#terminal-address').innerText;
            const phoneNumber = row.querySelector('#terminal-tel').innerText;



            // 모달에 데이터 채우기
            document.querySelector('#staticBackdrop #terminalId').value = terminalId;
            document.querySelector('#staticBackdrop #terminalName').value = terminalName;
            document.querySelector('#staticBackdrop #address').value = address;
            document.querySelector('#staticBackdrop #phoneNumber').value = phoneNumber;

            console.log(terminalId);
        });
    });

    // 저장 버튼 클릭 시 터미널 업데이트
    document.querySelector('#saveTerminal').addEventListener('click', function() {
        const terminalId = document.querySelector('#staticBackdrop #terminalId').value;
        const terminalName = document.querySelector('#staticBackdrop #terminalName').value;
        const address = document.querySelector('#staticBackdrop #address').value;
        const phoneNumber = document.querySelector('#staticBackdrop #phoneNumber').value;

        const updateTerminalDto = {
            // terminalId: terminalId,
            terminalName: terminalName,
            address: address,
            tel: phoneNumber // 'tel' 필드를 사용해야 합니다.
        };

        fetch(`/admin/terminal/${terminalId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updateTerminalDto)  // 객체를 JSON 형식으로 변환
        })
            .then(response => {
                if (response.redirected) {
                    window.location.href = response.url;
                } else if (response.ok) {
                    window.location.reload();
                } else {
                    alert('터미널 업데이트에 실패했습니다.');
                }
            })
            .catch(error => console.error('오류:', error));
    });


    document.querySelectorAll('#delete-terminal').forEach(button => {
        button.addEventListener('click', function() {
            const terminalId = document.querySelector('#staticBackdrop #terminalId').value;

            // 확인 창 띄우기
            if (confirm('정말로 이 터미널을 삭제하시겠습니까?')) {
                fetch(`/admin/terminal/delete/${terminalId}`, {
                    method: 'PATCH', // 상태 업데이트를 위한 PATCH 메서드
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(response => {
                        if (response.redirected) {
                            window.location.href = response.url;
                        } else if (response.ok) {
                            window.location.reload();
                        } else {
                            alert('터미널 삭제에 실패했습니다.');
                        }
                    })
                    .catch(error => console.error('오류:', error));
            }
        });
    });
}


function generateUUID() {
    const letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const digits = '0123456789';

    // 앞 4글자 (대문자)
    let uuid = '';
    for (let i = 0; i < 4; i++) {
        uuid += letters.charAt(Math.floor(Math.random() * letters.length));
    }

    // 뒤 3글자 (숫자)
    for (let i = 0; i < 3; i++) {
        uuid += digits.charAt(Math.floor(Math.random() * digits.length));
    }

    return uuid;
}


function getRandomCityId() {
    return Math.floor(Math.random() * 100) + 1; // 1부터 100까지의 랜덤 숫자 생성
}

function insertTerminal() {
    const saveTerminalBtn = document.getElementById("saveTerminalBtn");

    saveTerminalBtn.addEventListener("click", async () => {
        // 모달 창에서 입력한 값을 가져옵니다.
        const terminalName = document.getElementById("insertTerminalName").value;
        const address = document.getElementById("insertTerminalAddress").value;
        const tel = document.getElementById("insertTerminalPhoneNumber").value;

        // 입력 값 유효성 검사 (간단한 예)
        if (!terminalName || !address || !tel) {
            alert("모든 필드를 입력해주세요.");
            return;
        }

        // 랜덤한 terminalId와 cityId 생성
        const terminalId = generateUUID(); // 랜덤 UUID 생성
        const cityId = getRandomCityId(); // 1부터 100까지의 랜덤 숫자 생성

        const terminalData = {
            terminalId: terminalId,
            cityId: cityId,
            terminalName: terminalName,
            address: address,
            tel: tel,
        };


        try {
            // 서버로 POST 요청 보내기
            const response = await fetch("/admin/terminal", {
                method: "POST", // HTTP 메서드
                headers: {
                    "Content-Type": "application/json", // JSON 형식으로 데이터를 보냅니다.
                },
                body: JSON.stringify(terminalData), // 데이터를 JSON 문자열로 변환합니다.
            });
            console.log("전송할 데이터:", JSON.stringify(terminalData));
            console.log(response);

            if (response.ok) {
                alert("터미널 등록이 완료되었습니다.");
                window.location.reload(); // 등록 완료 후 페이지를 새로고침합니다.
            } else {
                console.log(response);
                alert("터미널 등록 중 오류가 발생했습니다.");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("서버와의 통신 중 문제가 발생했습니다.");
        }
    });
}



