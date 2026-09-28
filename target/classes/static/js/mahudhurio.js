// ============================================
// CBE RESULTS - MAHUDHURIO.JS
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
    const btn = document.getElementById('logoutBtn');
    if (btn) btn.addEventListener('click', () => {
        localStorage.clear();
        window.location.href = '/login.html';
    });
}

async function loadMahudhurio() {
    const token = checkAuth();
    if (!token) return;

    const loadingMessage = document.getElementById('loadingMessage');
    const errorMessage = document.getElementById('errorMessage');
    const container = document.getElementById('mahudhurioContainer');
    const body = document.getElementById('mahudhurioBody');

    try {
        const res = await fetch(`${API_URL}/api/mahudhurio/yangu`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        const text = await res.text();
        let data;
        try { data = JSON.parse(text); } catch (e) { data = []; }

        loadingMessage.style.display = 'none';

        if (!Array.isArray(data) || data.length === 0) {
            errorMessage.textContent = 'Hakuna mahudhurio';
            errorMessage.style.display = 'block';
            return;
        }

        container.style.display = 'block';
        body.innerHTML = '';

        let aliyepo = 0;
        data.forEach(m => {
            if (m.hali === 'ALIYEPO') aliyepo++;
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${m.tarehe}</td>
                <td>${m.code}</td>
                <td>${m.jinaLaKozi}</td>
                <td class="hali-${m.hali}">${m.hali}</td>
            `;
            body.appendChild(row);
        });

        document.getElementById('totalMahudhurio').textContent = data.length;
        document.getElementById('totalAliyepo').textContent = aliyepo;
        document.getElementById('asilimia').textContent = ((aliyepo / data.length) * 100).toFixed(1) + '%';

    } catch (e) {
        loadingMessage.style.display = 'none';
        errorMessage.textContent = e.message;
        errorMessage.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
    loadMahudhurio();
});