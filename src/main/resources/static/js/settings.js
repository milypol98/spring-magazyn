// profile.js – logika UI dla zakładki profilu
(function () {
    const tbody = document.getElementById('tasksTbody');
    const search = document.getElementById('taskSearch');
    const onlyInProgress = document.getElementById('onlyInProgress');

    if (!tbody) return;

    const rows = Array.from(tbody.querySelectorAll('tr'));

    function matchesSearch(row, term) {
        if (!term) return true;
        const name = (row.getAttribute('data-name') || '').toLowerCase();
        const desc = (row.getAttribute('data-desc') || '').toLowerCase();
        return name.includes(term) || desc.includes(term);
    }

    function isInProgress(row) {
        return row.classList.contains('row--in-progress');
    }

    function applyFilters() {
        const term = (search?.value || '').trim().toLowerCase();
        const onlyIP = !!onlyInProgress?.checked;

        rows.forEach(row => {
            const okSearch = matchesSearch(row, term);
            const okIP = !onlyIP || isInProgress(row);
            row.style.display = (okSearch && okIP) ? '' : 'none';
        });
    }

    search?.addEventListener('input', applyFilters);
    onlyInProgress?.addEventListener('change', applyFilters);

    // Ulepszenie UX: Enter w polu wyszukiwania nie przeładowuje strony
    search?.addEventListener('keydown', (e) => {
        if (e.key === 'Enter') e.preventDefault();
    });

    // Prosta walidacja klienta dla formularza profilu
    const form = document.querySelector('form.profile-form');
    if (form) {
        form.addEventListener('submit', (e) => {
            const required = Array.from(form.querySelectorAll('[required]'));
            let valid = true;
            required.forEach(el => {
                if (!el.value || (el.type === 'email' && !/^\S+@\S+\.\S+$/.test(el.value))) {
                    el.classList.add('invalid');
                    valid = false;
                } else {
                    el.classList.remove('invalid');
                }
            });
            if (!valid) {
                e.preventDefault();
                // ewentualnie: przewinięcie do pierwszego błędu
                required.find(el => el.classList.contains('invalid'))?.focus();
            }
        });
    }
})();