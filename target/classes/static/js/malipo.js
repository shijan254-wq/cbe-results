// ============================================
// CBE RESULTS - MALIPO.JS
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

async function loadMalipo() {
    const token = checkAuth();
    if (!token) return;

    const loadingMessage = document.getElementById('loadingMessage');
    const errorMessage = document.getElementById('errorMessage');
    const container = document.getElementById('malipoContainer');
    const body = document.getElementById('malipoBody');
    const studentInfo = document.getElementById('studentInfo');

    const jinaKamili = localStorage.getItem('jinaKamili') || 'Mwanafunzi';
    if (studentInfo) studentInfo.textContent = `${jinaKamili} - Taarifa za Malipo`;

    try {
        const res = await fetch(`${API_URL}/api/malipo/yangu`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        const text = await res.text();
        let malipo;
        try { malipo = JSON.parse(text); } catch (e) { malipo = []; }

        loadingMessage.style.display = 'none';

        if (!Array.isArray(malipo) || malipo.length === 0) {
            errorMessage.textContent = 'Hakuna malipo yaliyopatikana';
            errorMessage.style.display = 'block';
            return;
        }

        container.style.display = 'block';
        body.innerHTML = '';

        let totalKiasi = 0;
        let hali = '-';

        malipo.forEach(m => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td><strong>${m.risiti || '-'}</strong></td>
                <td>${m.ainaYaMalipo || '-'}</td>
                <td>TSh ${Number(m.kiasi).toLocaleString()}</td>
                <td>${m.tareheYaMalipo || '-'}</td>
                <td><span class="hali-${m.hali}">${m.hali}</span></td>
            `;
            body.appendChild(row);

            totalKiasi += Number(m.kiasi);
            hali = m.hali;
        });

        document.getElementById('totalMalipo').textContent = malipo.length;
        document.getElementById('kiasiKilicholipwa').textContent = 'TSh ' + totalKiasi.toLocaleString();
        document.getElementById('haliMalipo').textContent = hali;

    } catch (e) {
        loadingMessage.style.display = 'none';
        errorMessage.textContent = e.message;
        errorMessage.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
    loadMalipo();
});