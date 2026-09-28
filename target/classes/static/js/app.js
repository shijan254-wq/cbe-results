// ============================================
// CBE RESULTS - APP.JS
// ============================================

const API_URL = 'http://localhost:8082';

function checkAuth() {
    const token = localStorage.getItem('token');
    if (!token) {
        window.location.href = '/login.html';
        return null;
    }
    return token;
}

function setupLogout() {
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', () => {
            localStorage.clear();
            window.location.href = '/login.html';
        });
    }
}

function setupDashboard() {
    const userName = document.getElementById('userName');
    const welcomeName = document.getElementById('welcomeName');
    const jinaKamili = localStorage.getItem('jinaKamili') || 'Mwanafunzi';

    if (userName) userName.textContent = jinaKamili;
    if (welcomeName) welcomeName.textContent = jinaKamili;
}

async function loadResults() {
    const token = checkAuth();
    if (!token) return;

    const loadingMessage = document.getElementById('loadingMessage');
    const errorMessage = document.getElementById('errorMessage');
    const resultsContainer = document.getElementById('resultsContainer');
    const resultsBody = document.getElementById('resultsBody');
    const studentInfo = document.getElementById('studentInfo');

    const jinaKamili = localStorage.getItem('jinaKamili') || 'Mwanafunzi';
    if (studentInfo) studentInfo.textContent = `${jinaKamili} - Matokeo ya Mitihani`;

    try {
        const response = await fetch(`${API_URL}/api/matokeo/yangu`, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (response.status === 401 || response.status === 403) {
            localStorage.clear();
            window.location.href = '/login.html';
            return;
        }

        const text = await response.text();
        let matokeo;
        try { matokeo = JSON.parse(text); } catch (e) { matokeo = []; }

        loadingMessage.style.display = 'none';

        if (!Array.isArray(matokeo) || matokeo.length === 0) {
            errorMessage.textContent = 'Hakuna matokeo yaliyopatikana';
            errorMessage.style.display = 'block';
            return;
        }

        resultsContainer.style.display = 'block';
        resultsBody.innerHTML = '';

        let totalAlama = 0;
        let totalCredits = 0;
        let totalPoints = 0;

        matokeo.forEach(m => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td><strong>${m.code}</strong></td>
                <td>${m.jinaLaKozi}</td>
                <td>${m.credits}</td>
                <td>${m.alama}</td>
                <td class="grade-${m.grade}">${m.grade}</td>
                <td>${m.mwaka}</td>
                <td>${m.semester}</td>
            `;
            resultsBody.appendChild(row);

            totalAlama += m.alama;
            totalCredits += m.credits;
            totalPoints += getGradePoint(m.grade) * m.credits;
        });

        document.getElementById('totalCourses').textContent = matokeo.length;
        document.getElementById('averageScore').textContent = (totalAlama / matokeo.length).toFixed(1);
        document.getElementById('gpa').textContent = (totalPoints / totalCredits).toFixed(2);

    } catch (error) {
        loadingMessage.style.display = 'none';
        errorMessage.textContent = error.message;
        errorMessage.style.display = 'block';
    }
}

function getGradePoint(grade) {
    const points = { 'A': 5, 'B': 4, 'C': 3, 'D': 2, 'F': 0 };
    return points[grade] || 0;
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
    const path = window.location.pathname;

    if (path.includes('dashboard')) {
        checkAuth();
        setupDashboard();
    } else if (path.includes('academic')) {
        checkAuth();
        loadResults();
    }
});