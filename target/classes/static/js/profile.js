// ============================================
// CBE RESULTS - PROFILE.JS
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

async function loadProfile() {
    const token = checkAuth();
    if (!token) return;

    const loadingMessage = document.getElementById('loadingMessage');
    const errorMessage = document.getElementById('errorMessage');
    const container = document.getElementById('profileContainer');

    try {
        const res = await fetch(`${API_URL}/api/profile/yangu`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        const text = await res.text();
        let p;
        try { p = JSON.parse(text); } catch (e) { p = null; }

        loadingMessage.style.display = 'none';

        if (!p) {
            errorMessage.textContent = 'Imeshindwa kupata profile';
            errorMessage.style.display = 'block';
            return;
        }

        container.style.display = 'block';

        document.getElementById('profileJina').textContent = p.jinaKamili || '-';
        document.getElementById('profileNamba').textContent = p.nambaYaMwanafunzi || '-';
        document.getElementById('profileUsername').textContent = p.username || '-';
        document.getElementById('profileJinsia').textContent = p.jinsia === 'M' ? 'Mwanaume' : 'Mwanamke';
        document.getElementById('profileCourse').textContent = p.course || '-';
        document.getElementById('profileMwaka').textContent = p.mwakaWaKujiunga || '-';
        document.getElementById('profileRole').textContent = p.role || '-';

    } catch (e) {
        loadingMessage.style.display = 'none';
        errorMessage.textContent = e.message;
        errorMessage.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
    loadProfile();
});