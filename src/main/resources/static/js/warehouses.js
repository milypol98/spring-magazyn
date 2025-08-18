// warehouses.js – interakcje listy magazynu (produkty + narzędzia)
(function () {
  // Klikalny wiersz: URL z Thymeleaf w data-href
  document.addEventListener('click', (e) => {
    const row = e.target.closest('tr.clickable-row');
    if (!row) return;
    const t = e.target;
    if (t.tagName === 'A' || t.tagName === 'BUTTON' || t.type === 'submit') return;
    const href = row.getAttribute('data-href');
    if (href) window.location.href = href;
  });

  // Filtrowanie: input.table-search ma data-target z selektorem TBODY/TABLE
  document.querySelectorAll('.table-search[data-target]').forEach(input => {
    const targetSel = input.getAttribute('data-target');
    const table = document.querySelector(targetSel);
    if (!table) return;
    const rows = Array.from(table.querySelectorAll('tbody tr.clickable-row'));
    let timer;
    function apply() {
      const q = (input.value || '').trim().toLowerCase();
      rows.forEach(r => {
        const text = (r.textContent || '').toLowerCase();
        r.style.display = !q || text.includes(q) ? '' : 'none';
      });
    }
    input.addEventListener('input', () => {
      clearTimeout(timer);
      timer = setTimeout(apply, 120);
    });
    input.addEventListener('keydown', (e) => { if (e.key === 'Enter') e.preventDefault(); });
  });
})();