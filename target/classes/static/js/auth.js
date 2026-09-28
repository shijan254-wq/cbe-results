// ============================================
// CBE RESULTS - AUTH.JS (Login)
// ============================================

const API_URL = 'http://localhost:8082';

if (localStorage.getItem('token')) {
    const role = localStorage.getItem('role');
    if (role === 'ADMIN') window.location.href = '/admin.html';
    else if (role === 'MWALIMU') window.location.href = '/mwalimu.html';
    else window.location.href = '/dashboard.html';
}

const loginForm = document.getElementById('loginForm');
const errorMessage = document.getElementById('errorMessage');
const loginBtn = document.getElementById('loginBtn');

loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value;

    if (!username || !password) {
        showError('Tafadhali weka username na password');
        return;
    }

    loginBtn.disabled = true;
    loginBtn.textContent = 'Inaingia...';
    errorMessage.style.display = 'none';

    try {
        const response = await fetch(`${API_URL}/api/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        const text = await response.text();
        let data;
        try { data = JSON.parse(text); } catch (e) { data = {}; }

        if (!response.ok) {
            throw new Error(data.message || 'Kuingia kimeshindwa');
        }

        localStorage.setItem('token', data.token);
        localStorage.setItem('username', data.username);
        localStorage.setItem('role', data.role);
        localStorage.setItem('jinaKamili', data.jinaKamili);

        if (data.role === 'ADMIN') window.location.href = '/admin.html';
        else if (data.role === 'MWALIMU') window.location.href = '/mwalimu.html';
        else window.location.href = '/dashboard.html';

    } catch (error) {
        showError(error.message);
        loginBtn.disabled = false;
        loginBtn.textContent = 'Ingia';
    }
});

function showError(message) {
    errorMessage.textContent = message;
    errorMessage.style.display = 'block';
}