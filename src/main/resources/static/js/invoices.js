// invoices.js - wspólny JS dla listy, edycji i szczegółów faktur
(() => {
  const $ = (sel, root = document) => root.querySelector(sel);
  const $$ = (sel, root = document) => Array.from(root.querySelectorAll(sel));
  const debounce = (fn, wait = 400) => { let t; return (...args) => { clearTimeout(t); t = setTimeout(() => fn.apply(null, args), wait); }; };

  function parseMoney(val) {
    if (val === null || val === undefined) return null;
    let s = String(val).trim();
    if (s === '') return null;
    s = s.replace(/\s+/g, '');
    s = s.replace(/,/g, '.');
    s = s.replace(/[^0-9.\-]/g, '');
    if (s === '' || s === '.' || s === '-' || s === '-.') return null;
    const n = Number(s);
    return Number.isFinite(n) ? n : null;
  }
  const fmt2 = (n) => (Number.isFinite(n) ? n.toFixed(2) : '');

  function markInvalid(input, invalid, msg) {
    if (!input) return;
    input.classList.toggle('is-invalid', !!invalid);
    let hint = input.nextElementSibling && input.nextElementSibling.classList?.contains('field-hint')
      ? input.nextElementSibling : null;
    if (invalid && msg) {
      if (!hint) {
        hint = document.createElement('div');
        hint.className = 'field-hint';
        input.insertAdjacentElement('afterend', hint);
      }
      hint.textContent = msg;
    } else if (hint) {
      hint.remove();
    }
  }

  // LISTA
  function initList() {
    const filtersForm = $('.filters-form');
    const invoicesTable = $('.list-container table');
    if (!filtersForm || !invoicesTable) return;

    const submitClosestForm = (el) => { const f = el.closest('form'); if (f) f.submit(); };

    $$('.filters-form select[name="size"], .filters-form input[type="date"], .filters-form select[name="companyId"]').forEach((ctrl) => {
      ctrl.addEventListener('change', () => submitClosestForm(ctrl));
    });

    const q = $('.filters-form input[name="q"]');
    if (q) {
      const run = debounce(() => submitClosestForm(q), 500);
      q.addEventListener('input', run);
      q.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') { q.value = ''; run(); }
        if (e.key === 'Enter') { e.preventDefault(); submitClosestForm(q); }
      });
    }

    $$('.pagination .page-btn.disabled').forEach((a) => a.addEventListener('click', (e) => e.preventDefault()));

    $$('.list-container form[method="post"]').forEach((f) => {
      if (!f.hasAttribute('onsubmit') && f.action.includes('/delete')) {
        f.addEventListener('submit', (e) => { if (!confirm('Usunąć tę fakturę?')) e.preventDefault(); });
      }
    });
  }

  // EDYCJA / DODAWANIE
  function initEdit() {
    const form = $('form[action$="/invoices/save"]');
    if (!form) return;

    const nr = $('input[name="nrFaktury"]');
    const nazwa = $('input[name="nazwa"]');
    const currency = $('input[name="currency"]');
    const net = $('input[name="totalNet"]');
    const vat = $('input[name="totalVat"]');
    const gross = $('input[name="totalGross"]');
    const file = $('input[name="plik"]');

    [nr, nazwa, currency, net, vat, gross, file].forEach(inp => {
      if (inp) inp.addEventListener('input', () => markInvalid(inp, false));
      if (inp) inp.addEventListener('change', () => markInvalid(inp, false));
    });

    function recalcGross() {
      const n = parseMoney(net?.value);
      const v = parseMoney(vat?.value);
      if (gross && n !== null && v !== null) {
        const sum = n + v;
        gross.value = fmt2(sum);
      }
    }
    if (net) net.addEventListener('input', recalcGross);
    if (vat) vat.addEventListener('input', recalcGross);
    [net, vat, gross].forEach(inp => {
      if (inp) inp.addEventListener('blur', () => {
        const val = parseMoney(inp.value);
        if (val !== null) inp.value = fmt2(val);
      });
    });
    document.addEventListener('DOMContentLoaded', recalcGross);

    const allowedExt = ['pdf', 'png', 'jpg', 'jpeg', 'webp', 'tiff'];
    function validateFile() {
      if (!file) return true;
      const f = file.files && file.files[0];
      if (!f) { markInvalid(file, true, 'Załącz plik z fakturą.'); return false; }
      const ext = (f.name.split('.').pop() || '').toLowerCase();
      if (!allowedExt.includes(ext)) {
        markInvalid(file, true, 'Dozwolone: PDF, PNG, JPG, WEBP, TIFF.');
        return false;
      }
      const max = 20 * 1024 * 1024;
      if (f.size > max) {
        markInvalid(file, true, 'Plik jest zbyt duży (limit 20MB).');
        return false;
      }
      return true;
    }

    form.addEventListener('submit', (e) => {
      let ok = true;

      if (!nr || nr.value.trim() === '') { markInvalid(nr, true, 'Podaj numer faktury.'); ok = false; }
      if (!nazwa || nazwa.value.trim() === '') { markInvalid(nazwa, true, 'Podaj nazwę/kontrahenta.'); ok = false; }

      if (currency && currency.value.trim() !== '') {
        const cur = currency.value.trim().toUpperCase();
        if (!/^[A-Z]{2,6}$/.test(cur)) { markInvalid(currency, true, 'Niepoprawny kod waluty.'); ok = false; }
        else currency.value = cur;
      }

      const n = parseMoney(net?.value);
      const v = parseMoney(vat?.value);
      const g = parseMoney(gross?.value);

      if (n !== null && n < 0) { markInvalid(net, true, 'Kwota nie może być ujemna.'); ok = false; }
      if (v !== null && v < 0) { markInvalid(vat, true, 'Kwota nie może być ujemna.'); ok = false; }
      if (g !== null && g < 0) { markInvalid(gross, true, 'Kwota nie może być ujemna.'); ok = false; }

      if (n !== null && v !== null) {
        const expected = +(n + v).toFixed(2);
        if (g !== null) {
          const diff = Math.abs(g - expected);
          if (diff > 0.01) {
            markInvalid(gross, true, 'Brutto musi równać się Netto + VAT.');
            ok = false;
          }
        } else {
          if (gross) gross.value = expected.toFixed(2);
        }
      }

      if (!validateFile()) ok = false;

      if (!ok) {
        e.preventDefault();
        const firstInvalid = $('.is-invalid', form);
        if (firstInvalid) firstInvalid.scrollIntoView({ behavior: 'smooth', block: 'center' });
      }
    });

    if (file) {
      file.addEventListener('change', () => {
        const f = file.files && file.files[0];
        if (f) file.title = f.name + ' (' + Math.round(f.size / 1024) + ' KB)';
      });
    }
  }

  // SZCZEGÓŁY
  function initInfo() {
    const details = $('dl.details-grid');
    if (!details) return;

    const labels = $$('dt', details);
    const dds = $$('dd', details);

    function detectCurrency() {
      for (let i = 0; i < labels.length; i++) {
        const name = (labels[i].textContent || '').toLowerCase();
        if (name.includes('waluta')) {
          const val = (dds[i]?.textContent || '').trim();
          return val || 'PLN';
        }
      }
      return 'PLN';
    }
    const currency = detectCurrency();

    function parseFromCell(text) {
      if (!text) return null;
      const s = String(text).replace(/\s/g, '').replace(',', '.');
      const n = Number(s);
      return Number.isFinite(n) ? n : null;
    }

    function formatMoneyCell(cell, cur) {
      if (!cell) return;
      const n = parseFromCell(cell.textContent.trim());
      if (n === null) return;
      try {
        cell.textContent = new Intl.NumberFormat('pl-PL', { style: 'currency', currency: cur || 'PLN' }).format(n);
      } catch {
        cell.textContent = n.toFixed(2) + ' ' + (cur || 'PLN');
      }
    }

    labels.forEach((dt, i) => {
      const name = (dt.textContent || '').toLowerCase();
      if (name.includes('netto') || name.includes('vat') || name.includes('brutto')) {
        formatMoneyCell(dds[i], currency);
      }
      if (name.includes('nr faktury')) {
        const valueEl = dds[i];
        valueEl.style.cursor = 'pointer';
        valueEl.title = 'Kliknij, aby skopiować numer faktury';
        valueEl.addEventListener('click', async () => {
          const txt = valueEl.textContent.trim();
          try {
            await navigator.clipboard.writeText(txt);
            const old = valueEl.textContent;
            valueEl.textContent = 'Skopiowano!';
            setTimeout(() => (valueEl.textContent = old), 900);
          } catch {
            alert('Nie udało się skopiować.');
          }
        });
      }
    });

    const delForm = $('form[action*="/delete"]');
    if (delForm && !delForm.hasAttribute('onsubmit')) {
      delForm.addEventListener('submit', (e) => {
        if (!confirm('Czy na pewno usunąć tę fakturę?')) e.preventDefault();
      });
    }
  }

  document.addEventListener('DOMContentLoaded', () => {
    initList();
    initEdit();
    initInfo();
  });
})();