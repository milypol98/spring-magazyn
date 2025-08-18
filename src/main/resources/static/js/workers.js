// users.js – filtrowanie listy użytkowników po stronie klienta
(function () {
    const search = document.getElementById('userSearch');
    const tbody = document.getElementById('usersTbody');
    if (!search || !tbody) return;

    const rows = Array.from(tbody.querySelectorAll('tr'));

    function applyFilter() {
        const q = (search.value || '').trim().toLowerCase();
        rows.forEach(r => {
            const name = (r.getAttribute('data-name') || '').toLowerCase();
            const email = (r.getAttribute('data-email') || '').toLowerCase();
            const match = !q || name.includes(q) || email.includes(q);
            r.style.display = match ? '' : 'none';
        });
    }

    // Debounce dla płynności
    let t = null;
    function debouncedApply() {
        clearTimeout(t);
        t = setTimeout(applyFilter, 120);
    }

    search.addEventListener('input', debouncedApply);
    search.addEventListener('keydown', (e) => { if (e.key === 'Enter') e.preventDefault(); });
})();