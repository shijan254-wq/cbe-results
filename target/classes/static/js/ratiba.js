// ============================================
// CBE RESULTS - RATIBA.JS
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

async function loadRatiba() {
    const token = checkAuth();
    if (!token) return;

    const loadingMessage = document.getElementById('loadingMessage');
    const errorMessage = document.getElementById('errorMessage');
    const container = document.getElementById('ratibaContainer');
    const body = document.getElementById('ratibaBody');

    try {
        const res = await fetch(`${API_URL}/api/ratiba/yote`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        const text = await res.text();
        let data;
        try { data = JSON.parse(text); } catch (e) { data = []; }

        loadingMessage.style.display = 'none';

        if (!Array.isArray(data) || data.length === 0) {
            errorMessage.textContent = 'Hakuna ratiba';
            errorMessage.style.display = 'block';
            return;
        }

        container.style.display = 'block';
        body.innerHTML = '';

        data.forEach(r => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td><strong>${r.siku}</strong></td>
                <td>${r.mudaWaKwanza} - ${r.mudaWaMwisho}</td>
                <td>${r.code}</td>
                <td>${r.jinaLaKozi}</td>
                <td>${r.darasa || '-'}</td>
                <td>${r.mwalimu || '-'}</td>
            `;
            body.appendChild(row);
        });
    } catch (e) {
        loadingMessage.style.display = 'none';
        errorMessage.textContent = e.message;
        errorMessage.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
    loadRatiba();
});