// ============================================
// CBE RESULTS - TAARIFA.JS
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

async function loadTaarifa() {
    const token = getToken();
    if (!token) return;

    const loadingMessage = document.getElementById('loadingMessage');
    const errorMessage = document.getElementById('errorMessage');
    const container = document.getElementById('taarifaContainer');

    try {
        const res = await fetch(`${API_URL}/api/taarifa/zote`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        const text = await res.text();
        let data;
        try { data = JSON.parse(text); } catch (e) { data = []; }

        loadingMessage.style.display = 'none';

        if (!Array.isArray(data) || data.length === 0) {
            errorMessage.textContent = 'Hakuna taarifa';
            errorMessage.style.display = 'block';
            return;
        }

        container.innerHTML = '';
        data.forEach(t => {
            container.innerHTML += `
                <div class="taarifa-card">
                    <div class="taarifa-header">
                        <span class="taarifa-aina taarifa-${t.aina}">${t.aina}</span>
                        <span class="taarifa-tarehe">${new Date(t.tarehe).toLocaleDateString()}</span>
                    </div>
                    <h3>${t.kichwa}</h3>
                    <p>${t.maudhui || ''}</p>
                    <div class="taarifa-footer">
                        <small>Imeandikwa na: ${t.aliyeandika || '-'}</small>
                    </div>
                </div>
            `;
        });
    } catch (e) {
        loadingMessage.style.display = 'none';
        errorMessage.textContent = e.message;
        errorMessage.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
    loadTaarifa();
});