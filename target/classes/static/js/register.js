// ============================================
// CBE RESULTS - REGISTER.JS
// ============================================

const API_URL = 'http://localhost:8082';

const registerForm = document.getElementById('registerForm');
const errorMessage = document.getElementById('errorMessage');
const successMessage = document.getElementById('successMessage');
const registerBtn = document.getElementById('registerBtn');

registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const jinaKamili = document.getElementById('jinaKamili').value.trim();
    const nambaYaMwanafunzi = document.getElementById('nambaYaMwanafunzi').value.trim();
    const username = document.getElementById('username').value.trim();
    const email = document.getElementById('email').value.trim();
    const jinsia = document.getElementById('jinsia').value;
    const course = document.getElementById('course').value;
    const mwakaWaKujiunga = parseInt(document.getElementById('mwakaWaKujiunga').value);
    const password = document.getElementById('password').value;
    const passwordRudia = document.getElementById('passwordRudia').value;

    errorMessage.style.display = 'none';
    successMessage.style.display = 'none';

    // Validation
    if (!jinaKamili || !nambaYaMwanafunzi || !username || !email || !jinsia || !course || !password) {
        showError('Tafadhali jaza sehemu zote');
        return;
    }

    if (password.length < 6) {
        showError('Password lazima iwe na herufi 6 au zaidi');
        return;
    }

    if (password !== passwordRudia) {
        showError('Password hazifanani');
        return;
    }

    if (!email.includes('@')) {
        showError('Email si sahihi');
        return;
    }

    registerBtn.disabled = true;
    registerBtn.textContent = 'Inasajili...';

    try {
        const body = {
            jinaKamili: jinaKamili,
            nambaYaMwanafunzi: nambaYaMwanafunzi,
            username: username,
            email: email,
            jinsia: jinsia,
            course: course,
            mwakaWaKujiunga: mwakaWaKujiunga,
            password: password
        };

        const res = await fetch(`${API_URL}/api/auth/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });

        const text = await res.text();
        let data;
        try { data = JSON.parse(text); } catch (e) { data = {}; }

        if (!res.ok) {
            throw new Error(data.message || 'Usajili umeshindwa');
        }

        successMessage.textContent = 'Usajili umefanikiwa! Unaelekezwa kwenye login...';
        successMessage.style.display = 'block';

        setTimeout(() => {
            window.location.href = '/login.html';
        }, 2000);

    } catch (error) {
        showError(error.message);
        registerBtn.disabled = false;
        registerBtn.textContent = 'Jisajili';
    }
});

function showError(message) {
    errorMessage.textContent = message;
    errorMessage.style.display = 'block';
}