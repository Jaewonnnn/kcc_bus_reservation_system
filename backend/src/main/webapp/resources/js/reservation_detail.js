function formatTime(dateString) {
    const date = new Date(dateString);
    if (isNaN(date.getTime())) {
        console.error('Invalid date:', dateString); // 유효하지 않은 날짜 문자열을 콘솔에 출력
        return 'Invalid Date';
    }
    return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: false });
}

function calculateDuration(startTime, endTime) {
    const start = new Date(startTime);
    const end = new Date(endTime);

    const durationMs = end - start;
    if (isNaN(durationMs)) {
        console.error('Invalid date(s) for duration calculation');
        return 'Invalid Duration';
    }

    const durationMinutes = Math.floor(durationMs / 60000); // 밀리초를 분으로 변환
    const hours = Math.floor(durationMinutes / 60);
    const minutes = durationMinutes % 60;

    return `${hours}시간 ${minutes}분`;
}

function populateReservationCourseList(schedules) {
    const listElement = document.getElementById('reservation_course_list');
    listElement.innerHTML = '';
    console.log(schedules)
    if (schedules.length > 0) {
        const firstSchedule = schedules[0];
        const duration = calculateDuration(firstSchedule.scheduleStartTime, firstSchedule.scheduleEndTime);
        updateDurationInfo(duration); // 소요시간 정보 업데이트
    }

    schedules.forEach(schedule => {
        const listItem = document.createElement('li');

        const detailList = document.createElement('ul');
        detailList.className = 'reservation_course_list_detail';

        const timeItem = document.createElement('li');
        timeItem.className = 'time';
        const startTime = formatTime(schedule.scheduleStartTime);
        timeItem.textContent = startTime;
        detailList.appendChild(timeItem);

        const companyItem = document.createElement('li');
        companyItem.className = 'company';
        companyItem.textContent = schedule.companyName;
        detailList.appendChild(companyItem);

        const gradeItem = document.createElement('li');
        gradeItem.className = 'grade';
        gradeItem.textContent = schedule.gradeName;
        detailList.appendChild(gradeItem);

        const seatItem = document.createElement('li');
        seatItem.className = 'seat';
        switch (schedule.gradeName) {
            case "고속":
            case "심야고속":
            case "일반":
            case "일반심야":
                seatItem.textContent = schedule.seatCount + "/45";
                break;
            case "우등":
            case "심야우등":
                seatItem.textContent = schedule.seatCount + "/28";
                break;
            case "프리미엄":
            case "심야프리미엄":
                seatItem.textContent = schedule.seatCount + "/21";
                break;
        }

        const goDate = document.querySelector('#reservation_course_info h2')
        goDate.textContent = schedule.scheduleStartTime.split(' ')[0]

        detailList.appendChild(seatItem);

        const button = document.createElement('button');
        button.className = 'reservation_course_list_detail_btn';
        button.innerHTML = `선택<i class="fa-solid fa-caret-right"></i>`;
        button.addEventListener('click', () => {
            location.href = '/reservation/schedule/seat/' +schedule.scheduleId
            updatePaymentInfo(schedule);
            const duration = calculateDuration(schedule.scheduleStartTime, schedule.scheduleEndTime);
            updateDurationInfo(duration); // 소요시간 정보 업데이트
        });

        listItem.appendChild(detailList);
        listItem.appendChild(button);

        listElement.appendChild(listItem);
    });
}

// 함수: 요금 정보를 업데이트하는 함수
function updatePaymentInfo(schedule) {
    const paymentList = document.getElementById('reservation_course_pay_list');
    paymentList.innerHTML = '';

    const grade1Item = document.createElement('li');
    const grade1Label = document.createElement('p');
    grade1Label.className = 'reservation_course_pay_grade';
    grade1Label.textContent = schedule.gradeName;
    const grade1Price = document.createElement('p');
    grade1Price.className = 'reservation_course_pay_price';
    grade1Price.textContent = schedule.gradePrice1 + '원';
    grade1Item.appendChild(grade1Label);
    grade1Item.appendChild(grade1Price);

    const grade2Item = document.createElement('li');
    const grade2Label = document.createElement('p');
    grade2Label.className = 'reservation_course_pay_grade';
    grade2Label.textContent = '프리미엄';
    const grade2Price = document.createElement('p');
    grade2Price.className = 'reservation_course_pay_price';
    grade2Price.textContent = schedule.gradePrice2 + '원';
    grade2Item.appendChild(grade2Label);
    grade2Item.appendChild(grade2Price);

    paymentList.appendChild(grade1Item);
    paymentList.appendChild(grade2Item);
}

// 함수: 소요시간 정보를 업데이트하는 함수
function updateDurationInfo(duration) {
    const durationElement = document.getElementById('reservation_course_time');
    durationElement.textContent = duration;
}

document.addEventListener('DOMContentLoaded', () => {
    populateReservationCourseList(schedules);
});
