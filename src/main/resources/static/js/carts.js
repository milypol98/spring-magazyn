// carts.js – interakcje listy i edycji koszyków
(function () {
    // Klik w wiersz listy → Szczegóły
    document.addEventListener('click', (e) => {
        const row = e.target.closest('tr.clickable-row');
        if (!row) return;
        // nie przechwytuj klików w link/przycisk/formularz
        const tag = e.target.tagName;
        const isButton = tag === 'BUTTON' || e.target.type === 'submit';
        const isLink = tag === 'A';
        if (isLink || isButton) return;
        const href = row.getAttribute('data-href');
        if (href) window.location.href = href;
    });

    // Filtr na liście (pole w belce tabeli)
    const search = document.getElementById('cartSearch');
    const tbody = document.getElementById('cartsTbody');
    if (search && tbody) {
        const rows = Array.from(tbody.querySelectorAll('tr.clickable-row'));
        let t;
        function apply() {
            const q = (search.value || '').trim().toLowerCase();
            rows.forEach(r => {
                const name = (r.getAttribute('data-name') || '').toLowerCase();
                const desc = (r.getAttribute('data-desc') || '').toLowerCase();
                r.style.display = !q || name.includes(q) || desc.includes(q) ? '' : 'none';
            });
        }
        search.addEventListener('input', () => {
            clearTimeout(t);
            t = setTimeout(apply, 120);
        });
        search.addEventListener('keydown', e => { if (e.key === 'Enter') e.preventDefault(); });
    }

    // Confirm dla usuwania
    document.querySelectorAll('form[data-confirm]').forEach(f => {
        f.addEventListener('submit', (e) => {
            const msg = f.getAttribute('data-confirm') || 'Na pewno?';
            if (!confirm(msg)) e.preventDefault();
        });
    });
})();