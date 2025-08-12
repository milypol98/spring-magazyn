// companies.js
(() => {
  // ===== Utils =====
  const $ = (sel, root = document) => root.querySelector(sel);
  const $$ = (sel, root = document) => Array.from(root.querySelectorAll(sel));
  const debounce = (fn, wait = 200) => { let t; return (...a) => { clearTimeout(t); t = setTimeout(() => fn(...a), wait); }; };

  // Normalizacja tekstu do porównań (bez polskich znaków, zredukowane spacje)
  const normalize = (s) =>
    (s || '')
      .toString()
      .toLowerCase()
      .normalize('NFD').replace(/[\u0300-\u036f]/g, '')
      .replace(/\s+/g, ' ')
      .trim();

  // ===== Lista firm: dynamiczne wyszukiwanie =====
  function filterTable(table, query) {
    if (!table) return;
    const q = normalize(query);
    const rows = $$('tbody tr', table);
    let visible = 0;

    rows.forEach((tr) => {
      // Pomijamy ostatnią kolumnę z akcjami
      const cells = $$('td', tr).slice(0, -1);
      const hay = normalize(cells.map((td) => td.textContent).join(' | '));
      const match = q === '' || hay.includes(q);
      tr.style.display = match ? '' : 'none';
      if (match) visible++;
    });

    const countEl = $('#companiesCount');
    if (countEl) {
      const total = rows.length;
      countEl.textContent = q ? `Widoczne: ${visible} / ${total}` : `Łącznie: ${total}`;
    }
  }

  function initList() {
    const table = $('#companiesTable');
    if (!table) return;

    // Inicjalny licznik
    filterTable(table, '');

    // Pole wyszukiwania
    const input = $('.table-search');
    if (input) {
      const run = debounce(() => filterTable(table, input.value), 150);
      input.addEventListener('input', run);
      input.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') { input.value = ''; run(); }
      });
    }

    // Wyczyść
    const clearBtn = $('#clearSearchBtn');
    if (clearBtn && input) {
      clearBtn.addEventListener('click', (e) => {
        e.preventDefault();
        input.value = '';
        filterTable(table, '');
        input.focus();
      });
    }

    // Potwierdzenie usuwania na liście
    $$('form[data-confirm]').forEach((form) => {
      form.addEventListener('submit', (e) => {
        const msg = form.getAttribute('data-confirm') || 'Czy na pewno?';
        if (!window.confirm(msg)) e.preventDefault();
      });
    });
  }

  // ===== Formularz edycji: UX (NIP, kod pocztowy) =====
  function initEditUX() {
    const nip = document.querySelector('#nip');
    if (nip) {
      // Lekkie formatowanie po blur: 123-456-32-18
      nip.addEventListener('blur', function () {
        const digits = nip.value.replace(/\D/g, '');
        if (digits.length === 10) {
          nip.value = digits.replace(/(\d{3})(\d{3})(\d{2})(\d{2})/, '$1-$2-$3-$4');
        }
      });
    }

    const postal = document.querySelector('#address\\.postalCode');
    if (postal) {
      // W trakcie wpisywania: 00-000
      postal.addEventListener('input', function () {
        let v = postal.value.replace(/\D/g, '').slice(0, 5);
        if (v.length > 2) v = v.slice(0, 2) + '-' + v.slice(2);
        postal.value = v;
      });
    }

    // Potwierdzenie usuwania, jeśli formularz z atrybutem data-confirm istnieje na stronie edycji
    $$('form[data-confirm]').forEach((f) => {
      f.addEventListener('submit', (e) => {
        const msg = f.getAttribute('data-confirm') || 'Czy na pewno?';
        if (!confirm(msg)) e.preventDefault();
      });
    });
  }

  // ===== Szczegóły: dodatkowe drobiazgi =====
  function initInfoUX() {
    // Linki mailto/URL już działają natywnie; dodajmy ewentualnie confirm dla usuń:
    $$('form[data-confirm]').forEach((f) => {
      f.addEventListener('submit', (e) => {
        const msg = f.getAttribute('data-confirm') || 'Czy na pewno?';
        if (!confirm(msg)) e.preventDefault();
      });
    });
  }

  // Init
  document.addEventListener('DOMContentLoaded', () => {
    initList();
    initEditUX();
    initInfoUX();
  });
})();