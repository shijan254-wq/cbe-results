// ============================================
// CBE RESULTS - STATISTICS.JS
// ============================================

const API_URL = 'http://localhost:8082';

function getToken() {
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

async function loadStatistics() {
    const token = getToken();
    if (!token) return;

    const loadingMessage = document.getElementById('loadingMessage');
    const errorMessage = document.getElementById('errorMessage');
    const container = document.getElementById('statsContainer');

    try {
        const res = await fetch(`${API_URL}/api/statistics/takwimu`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        const text = await res.text();
        let data;
        try { data = JSON.parse(text); } catch (e) { data = null; }

        loadingMessage.style.display = 'none';

        if (!data) {
            errorMessage.textContent = 'Imeshindwa kupata takwimu';
            errorMessage.style.display = 'block';
            return;
        }

        container.style.display = 'block';

        document.getElementById('jumlaWanafunzi').textContent = data.jumlaWanafunzi || 0;
        document.getElementById('jumlaKozi').textContent = data.jumlaKozi || 0;
        document.getElementById('jumlaMatokeo').textContent = data.jumlaMatokeo || 0;
        document.getElementById('jumlaMalipo').textContent = data.jumlaMalipo || 0;
        document.getElementById('wastaniAlama').textContent = data.wastaniAlama || 0;
        document.getElementById('waliolipa').textContent = data.wanafunziWaliolipa || 0;
        document.getElementById('hawajalipa').textContent = data.wanafunziHawajalipa || 0;
        document.getElementById('jumlaWalimu').textContent = data.jumlaWalimu || 0;

    } catch (e) {
        loadingMessage.style.display = 'none';
        errorMessage.textContent = e.message;
        errorMessage.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
    loadStatistics();
});