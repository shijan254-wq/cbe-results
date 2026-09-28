// ============================================
// CBE RESULTS - BADILISHA-PASSWORD.JS
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

async function badilishaPassword() {
    const token = getToken();
    if (!token) return;

    const yaSasa = document.getElementById('passwordYaSasa').value;
    const mpya = document.getElementById('passwordMpya').value;
    const rudia = document.getElementById('passwordRudia').value;

    const errorMessage = document.getElementById('errorMessage');
    const successMessage = document.getElementById('successMessage');

    errorMessage.style.display = 'none';
    successMessage.style.display = 'none';

    if (!yaSasa || !mpya || !rudia) {
        errorMessage.textContent = 'Jaza sehemu zote';
        errorMessage.style.display = 'block';
        return;
    }

    if (mpya.length < 6) {
        errorMessage.textContent = 'Password mpya lazima iwe na herufi 6+';
        errorMessage.style.display = 'block';
        return;
    }

    if (mpya !== rudia) {
        errorMessage.textContent = 'Password mpya hazifanani';
        errorMessage.style.display = 'block';
        return;
    }

    try {
        const res = await fetch(`${API_URL}/api/user/badilisha-password`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                passwordYaSasa: yaSasa,
                passwordMpya: mpya
            })
        });

        const text = await res.text();

        if (!res.ok) {
            let msg = 'Imeshindwa kubadilisha password';
            try {
                const data = JSON.parse(text);
                msg = data.message || msg;
            } catch (e) {}
            errorMessage.textContent = msg;
            errorMessage.style.display = 'block';
            return;
        }

        successMessage.textContent = 'Password imebadilishwa! Ingia tena.';
        successMessage.style.display = 'block';

        setTimeout(() => {
            localStorage.clear();
            window.location.href = '/login.html';
        }, 2000);

    } catch (e) {
        errorMessage.textContent = e.message;
        errorMessage.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    setupLogout();
});