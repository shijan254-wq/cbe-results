// ============================================
// CBE RESULTS - DOWNLOAD.JS (Shared)
// ============================================

async function downloadFile(url, filename) {
    const token = localStorage.getItem('token');
    if (!token) {
        window.location.href = '/login.html';
        return;
    }

    try {
        const res = await fetch(url, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        if (!res.ok) {
            alert('Imeshindwa kupakua faili: ' + res.status);
            return;
        }

        const blob = await res.blob();
        const link = document.createElement('a');
        link.href = URL.createObjectURL(blob);
        link.download = filename;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(link.href);
    } catch (e) {
        alert('Kosa: ' + e.message);
    }
}

function exportData(aina) {
    downloadFile(`http://localhost:8082/api/export/${aina}`, `${aina}.csv`);
}