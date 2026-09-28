// ============================================
// CBE RESULTS - MWALIMU.JS
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

function showTab(tab) {
    document.querySelectorAll('.admin-content').forEach(el => el.style.display = 'none');
    document.querySelectorAll('.admin-tab').forEach(el => el.classList.remove('active'));
    document.getElementById(tab + 'Tab').style.display = 'block';
    if (window.event && window.event.target) window.event.target.classList.add('active');

    if (tab === 'wanafunzi') loadWanafunzi();
}

async function safeFetch(url, token) {
    try {
        const res = await fetch(url, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        console.log(`[${res.status}] ${url}`);

        const text = await res.text();

        if (!text || text.trim() === '') {
            console.warn('Response tupu:', url);
            return { ok: false, status: res.status, data: null, error: 'Response tupu' };
        }

        try {
            const data = JSON.parse(text);
            return { ok: res.ok, status: res.status, data: data, error: null };
        } catch (e) {
            console.error('JSON parse error:', text.substring(0, 200));
            return { ok: false, status: res.status, data: null, error: 'JSON parse error' };
        }
    } catch (e) {
        console.error('Fetch error:', e);
        return { ok: false, status: 0, data: null, error: e.message };
    }
}

async function loadSelects() {
    const token = getToken();
    if (!token) return;

    const matokeoRes = await safeFetch(`${API_URL}/api/matokeo/yote`, token);
    const koziRes = await safeFetch(`${API_URL}/api/admin/kozi`, token);

    const wanafunziMap = new Map();
    if (matokeoRes.ok && Array.isArray(matokeoRes.data)) {
        matokeoRes.data.forEach(m => {
            if (m.jinaKamili && !wanafunziMap.has(m.jinaKamili)) {
                wanafunziMap.set(m.jinaKamili, m);
            }
        });
    }
    const wanafunzi = Array.from(wanafunziMap.values());

    ['matMwanafunzi', 'mahMwanafunzi'].forEach(id => {
        const sel = document.getElementById(id);
        if (!sel) return;
        sel.innerHTML = '<option value="">Chagua Mwanafunzi</option>';
        wanafunzi.forEach(w => {
            sel.innerHTML += `<option value="${w.id}">${w.jinaKamili}</option>`;
        });
    });

    ['matKozi', 'mahKozi'].forEach(id => {
        const sel = document.getElementById(id);
        if (!sel) return;
        sel.innerHTML = '<option value="">Chagua Kozi</option>';
        if (koziRes.ok && Array.isArray(koziRes.data)) {
            koziRes.data.forEach(k => {
                sel.innerHTML += `<option value="${k.id}">${k.code} - ${k.jinaLaKozi}</option>`;
            });
        }
    });
}

async function addMatokeo() {
    const token = getToken();
    if (!token) return;

    const params = new URLSearchParams({
        mwanafunziId: document.getElementById('matMwanafunzi').value,
        koziId: document.getElementById('matKozi').value,
        alama: document.getElementById('matAlama').value,
        semester: document.getElementById('matSemester').value,
        mwaka: document.getElementById('matMwaka').value
    });

    try {
        const res = await fetch(`${API_URL}/api/mwalimu/matokeo?${params}`, {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${token}` }
        });
        if (!res.ok) throw new Error('Imeshindwa kuweka matokeo');
        alert('Matokeo yamewekwa!');
    } catch (e) {
        alert(e.message);
    }
}

async function addMahudhurio() {
    const token = getToken();
    if (!token) return;

    const params = new URLSearchParams({
        mwanafunziId: document.getElementById('mahMwanafunzi').value,
        koziId: document.getElementById('mahKozi').value,
        hali: document.getElementById('mahHali').value,
        tarehe: document.getElementById('mahTarehe').value
    });

    try {
        const res = await fetch(`${API_URL}/api/mwalimu/mahudhurio?${params}`, {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${token}` }
        });
        if (!res.ok) throw new Error('Imeshindwa kuweka mahudhurio');
        alert('Mahudhurio yamewekwa!');
    } catch (e) {
        alert(e.message);
    }
}

async function loadWanafunzi() {
    const token = getToken();
    if (!token) return;

    const list = document.getElementById('wanafunziList');
    list.innerHTML = '<p>Inapakia...</p>';

    const res = await safeFetch(`${API_URL}/api/matokeo/yote`, token);

    if (!res.ok) {
        list.innerHTML = `<p class="error-message">Error ${res.status}: ${res.error || 'Imeshindwa'}</p>`;
        return;
    }

    if (!Array.isArray(res.data)) {
        list.innerHTML = '<p class="error-message">API haikurudisha array</p>';
        return;
    }

    const wanafunziMap = new Map();
    res.data.forEach(m => {
        if (m.jinaKamili && !wanafunziMap.has(m.jinaKamili)) {
            wanafunziMap.set(m.jinaKamili, {
                jinaKamili: m.jinaKamili,
                nambaYaMwanafunzi: m.nambaYaMwanafunzi || '-',
                course: m.course || '-'
            });
        }
    });

    const wanafunzi = Array.from(wanafunziMap.values());

    list.innerHTML = '<h3>Wanafunzi (' + wanafunzi.length + ')</h3>';
    if (wanafunzi.length === 0) {
        list.innerHTML += '<p>Hakuna wanafunzi</p>';
        return;
    }
    wanafunzi.forEach(w => {
        list.innerHTML += `
            <div class="admin-item">
                <strong>${w.jinaKamili}</strong> - ${w.nambaYaMwanafunzi}
                <span class="admin-badge">${w.course}</span>
            </div>
        `;
    });
}

async function searchStudent() {
    const token = getToken();
    if (!token) return;

    const namba = document.getElementById('searchInput').value.trim();
    if (!namba) {
        alert('Weka namba ya mwanafunzi');
        return;
    }

    const list = document.getElementById('wanafunziList');
    list.innerHTML = '<p>Inatafuta...</p>';

    const res = await safeFetch(`${API_URL}/api/matokeo/tafuta?namba=${namba}`, token);

    if (!res.ok || !Array.isArray(res.data) || res.data.length === 0) {
        list.innerHTML = '<p class="error-message">Mwanafunzi hajapatikana au hakuna matokeo</p>';
        return;
    }

    list.innerHTML = '<h3>Matokeo ya ' + (res.data[0].jinaKamili || namba) + '</h3>';
    res.data.forEach(m => {
        list.innerHTML += `
            <div class="admin-item">
                <strong>${m.code}</strong> - ${m.jinaLaKozi}
                <span class="admin-badge">${m.alama} (${m.grade})</span>
            </div>
        `;
    });
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
    const userNameEl = document.getElementById('userName');
    if (userNameEl) userNameEl.textContent = localStorage.getItem('jinaKamili') || 'Mwalimu';
    loadSelects();
});