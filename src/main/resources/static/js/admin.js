// ============================================
// CBE RESULTS - ADMIN.JS
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
    if (tab === 'kozi') loadKozi();
    if (tab === 'matokeo') loadSelects();
    if (tab === 'malipo') loadSelects();
}

function showForm(form) {
    document.getElementById(form + 'Form').style.display = 'block';
}

function hideForm(form) {
    document.getElementById(form + 'Form').style.display = 'none';
}

async function safeFetch(url, token) {
    try {
        const res = await fetch(url, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        const text = await res.text();

        if (!text || text.trim() === '') {
            return { ok: false, status: res.status, data: null, error: 'Response tupu' };
        }

        try {
            const data = JSON.parse(text);
            return { ok: res.ok, status: res.status, data: data, error: null };
        } catch (e) {
            return { ok: false, status: res.status, data: null, error: 'JSON parse error' };
        }
    } catch (e) {
        return { ok: false, status: 0, data: null, error: e.message };
    }
}

async function loadWanafunzi() {
    const token = getToken();
    if (!token) return;

    const list = document.getElementById('wanafunziList');
    list.innerHTML = '<p>Inapakia...</p>';

    const res = await safeFetch(`${API_URL}/api/admin/wanafunzi`, token);

    if (!res.ok || !Array.isArray(res.data)) {
        list.innerHTML = '<p class="error-message">Imeshindwa kupata wanafunzi</p>';
        return;
    }

    list.innerHTML = '<h3>Wanafunzi (' + res.data.length + ')</h3>';
    res.data.forEach(w => {
        list.innerHTML += `
            <div class="admin-item">
                <strong>${w.jinaKamili || '-'}</strong> - ${w.nambaYaMwanafunzi || '-'}
                <span class="admin-badge">${w.course || '-'}</span>
            </div>
        `;
    });
}

async function loadKozi() {
    const token = getToken();
    if (!token) return;

    const list = document.getElementById('koziList');
    list.innerHTML = '<p>Inapakia...</p>';

    const res = await safeFetch(`${API_URL}/api/admin/kozi`, token);

    if (!res.ok || !Array.isArray(res.data)) {
        list.innerHTML = '<p class="error-message">Imeshindwa kupata kozi</p>';
        return;
    }

    list.innerHTML = '<h3>Kozi (' + res.data.length + ')</h3>';
    res.data.forEach(k => {
        list.innerHTML += `
            <div class="admin-item">
                <strong>${k.code}</strong> - ${k.jinaLaKozi}
                <span class="admin-badge">${k.credits} credits</span>
            </div>
        `;
    });
}

async function loadSelects() {
    const token = getToken();
    if (!token) return;

    const wRes = await safeFetch(`${API_URL}/api/admin/wanafunzi`, token);
    const kRes = await safeFetch(`${API_URL}/api/admin/kozi`, token);

    ['matMwanafunzi', 'malMwanafunzi'].forEach(id => {
        const sel = document.getElementById(id);
        if (!sel) return;
        sel.innerHTML = '<option value="">Chagua Mwanafunzi</option>';
        if (wRes.ok && Array.isArray(wRes.data)) {
            wRes.data.forEach(w => {
                sel.innerHTML += `<option value="${w.id}">${w.jinaKamili} (${w.nambaYaMwanafunzi})</option>`;
            });
        }
    });

    const matKozi = document.getElementById('matKozi');
    if (matKozi) {
        matKozi.innerHTML = '<option value="">Chagua Kozi</option>';
        if (kRes.ok && Array.isArray(kRes.data)) {
            kRes.data.forEach(k => {
                matKozi.innerHTML += `<option value="${k.id}">${k.code} - ${k.jinaLaKozi}</option>`;
            });
        }
    }
}

async function addMwanafunzi() {
    const token = getToken();
    if (!token) return;

    const body = {
        username: document.getElementById('regUsername').value.trim(),
        password: document.getElementById('regPassword').value,
        nambaYaMwanafunzi: document.getElementById('regNamba').value.trim(),
        jinaKamili: document.getElementById('regJina').value.trim(),
        jinsia: document.getElementById('regJinsia').value,
        course: document.getElementById('regCourse').value,
        mwakaWaKujiunga: parseInt(document.getElementById('regMwaka').value)
    };

    if (!body.username || !body.password || !body.nambaYaMwanafunzi || !body.jinaKamili) {
        alert('Jaza sehemu zote');
        return;
    }

    try {
        const res = await fetch(`${API_URL}/api/admin/mwanafunzi`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        });

        const text = await res.text();
        let data;
        try { data = JSON.parse(text); } catch (e) { data = {}; }

        if (!res.ok) {
            throw new Error(data.message || 'Imeshindwa kuongeza mwanafunzi');
        }

        alert('Mwanafunzi ameongezwa!');
        hideForm('mwanafunzi');
        loadWanafunzi();

    } catch (e) {
        alert(e.message);
    }
}

async function addKozi() {
    const token = getToken();
    const body = {
        code: document.getElementById('koziCode').value,
        jinaLaKozi: document.getElementById('koziJina').value,
        credits: parseInt(document.getElementById('koziCredits').value),
        semester: document.getElementById('koziSemester').value
    };

    try {
        const res = await fetch(`${API_URL}/api/admin/kozi`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        });
        if (!res.ok) throw new Error('Imeshindwa kuongeza kozi');
        alert('Kozi imeongezwa!');
        hideForm('kozi');
        loadKozi();
    } catch (e) {
        alert(e.message);
    }
}

async function addMatokeo() {
    const token = getToken();
    const params = new URLSearchParams({
        mwanafunziId: document.getElementById('matMwanafunzi').value,
        koziId: document.getElementById('matKozi').value,
        alama: document.getElementById('matAlama').value,
        semester: document.getElementById('matSemester').value,
        mwaka: document.getElementById('matMwaka').value
    });

    try {
        const res = await fetch(`${API_URL}/api/admin/matokeo?${params}`, {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${token}` }
        });
        if (!res.ok) throw new Error('Imeshindwa kuweka matokeo');
        alert('Matokeo yamewekwa!');
    } catch (e) {
        alert(e.message);
    }
}

async function addMalipo() {
    const token = getToken();
    const params = new URLSearchParams({
        mwanafunziId: document.getElementById('malMwanafunzi').value,
        kiasi: document.getElementById('malKiasi').value,
        aina: document.getElementById('malAina').value,
        risiti: document.getElementById('malRisiti').value,
        hali: document.getElementById('malHali').value
    });

    try {
        const res = await fetch(`${API_URL}/api/admin/malipo?${params}`, {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${token}` }
        });
        if (!res.ok) throw new Error('Imeshindwa kuweka malipo');
        alert('Malipo yamewekwa!');
    } catch (e) {
        alert(e.message);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
    const userNameEl = document.getElementById('userName');
    if (userNameEl) userNameEl.textContent = localStorage.getItem('jinaKamili') || 'Admin';
    loadWanafunzi();
});