// cars.js – lista: wyszukiwarka, obliczenia statusów, pełne sortowanie
//          edycja: VIN/rejestracja uppercase + live "pozostało do..." (olej/rozrząd)
(function () {
  /* ====== Część wspólna dla LISTY ====== */
  const MS_PER_DAY = 24 * 60 * 60 * 1000;
  const today = new Date(); today.setHours(0, 0, 0, 0);
  const thresholds = { red: 0, orange: 7, yellow: 30 };

  const toNum = v => {
    if (v === null || v === undefined) return null;
    const s = String(v).trim().replace(/\s/g,'');
    if (!s) return null;
    const n = Number(s);
    return Number.isFinite(n) ? n : null;
  };
  const parseIsoDate = t => {
    if (!t) return null;
    const d = new Date(t);
    if (Number.isNaN(d.getTime())) return null;
    d.setHours(0,0,0,0);
    return d;
  };
  const computeDaysLeft = text => {
    const d = parseIsoDate(text);
    if (!d) return null;
    return Math.round((d - today) / MS_PER_DAY);
  };
  const formatDaysLeft = days => {
    if (days === null || days === undefined) return 'brak danych';
    if (days < 0) return `po terminie o ${Math.abs(days)} dni`;
    if (days === 0) return 'dzisiaj';
    return `za ${days} dni`;
  };
  const computeKmLeft = (current, last, interval) => {
    const cur = toNum(current), l = toNum(last), i = toNum(interval);
    if (cur === null || l === null || i === null) return null;
    return l + i - cur;
  };
  const classForValue = value => {
    if (value === null || value === undefined) return 'sev-neutral';
    if (value <= thresholds.red) return 'sev-red';
    if (value <= thresholds.orange) return 'sev-orange';
    if (value <= thresholds.yellow) return 'sev-yellow';
    return 'sev-green';
  };
  const applySeverity = (cell, value) => {
    if (!cell) return;
    cell.classList.remove('sev-green','sev-yellow','sev-orange','sev-red','sev-neutral');
    cell.classList.add(classForValue(value));
  };

  // Obliczenia statusów (ubezpieczenie/przegląd/olej/rozrząd) – LISTA
  const listTable = document.getElementById('carsTable');
  if (listTable) {
    document.querySelectorAll('#carsTable tbody tr').forEach(tr => {
      // Ubezpieczenie
      const insuredCell = tr.querySelector('.insured-date');
      const insuranceDaysCell = tr.querySelector('.insurance-days');
      if (insuredCell && insuranceDaysCell) {
        const dLeft = computeDaysLeft(insuredCell.textContent.trim());
        insuranceDaysCell.textContent = formatDaysLeft(dLeft);
        applySeverity(insuranceDaysCell, dLeft);
      }
      // Przegląd
      const reviewCell = tr.querySelector('.review-date');
      const reviewDaysCell = tr.querySelector('.review-days');
      if (reviewCell && reviewDaysCell) {
        const dLeft = computeDaysLeft(reviewCell.textContent.trim());
        reviewDaysCell.textContent = formatDaysLeft(dLeft);
        applySeverity(reviewDaysCell, dLeft);
      }
      // Olej
      const oilCell = tr.querySelector('.oil-remaining');
      if (oilCell) {
        const cur = tr.querySelector('.course')?.textContent;
        const lastOil = tr.getAttribute('data-oil-course');
        const intOil = tr.getAttribute('data-oil-int');
        const kmLeftOil = computeKmLeft(cur, lastOil, intOil);
        oilCell.textContent = (kmLeftOil === null) ? 'brak danych' :
          (kmLeftOil < 0 ? `po terminie o ${Math.abs(kmLeftOil)} km` :
           (kmLeftOil === 0 ? 'teraz' : `za ${kmLeftOil} km`));
        applySeverity(oilCell, kmLeftOil);
      }
      // Rozrząd
      const timingCell = tr.querySelector('.timing-remaining');
      if (timingCell) {
        const cur = tr.querySelector('.course')?.textContent;
        const last = tr.getAttribute('data-timing-course');
        const intTiming = tr.getAttribute('data-timing-int');
        const kmLeft = computeKmLeft(cur, last, intTiming);
        timingCell.textContent = (kmLeft === null) ? 'brak danych' :
          (kmLeft < 0 ? `po terminie o ${Math.abs(kmLeft)} km` :
           (kmLeft === 0 ? 'teraz' : `za ${kmLeft} km`));
        applySeverity(timingCell, kmLeft);
      }
    });

    // Wyszukiwarka (belka nad tabelą)
    const search = document.getElementById('carsSearch');
    const tbody = listTable.tBodies[0];
    if (search && tbody) {
      const rows = Array.from(tbody.querySelectorAll('tr'));
      let t;
      function applySearch() {
        const q = (search.value || '').trim().toLowerCase();
        rows.forEach(r => {
          const text = (r.textContent || '').toLowerCase();
          r.style.display = !q || text.includes(q) ? '' : 'none';
        });
      }
      search.addEventListener('input', () => { clearTimeout(t); t = setTimeout(applySearch, 120); });
      search.addEventListener('keydown', (e) => { if (e.key === 'Enter') e.preventDefault(); });
    }

    // Sortowanie – każda kolumna
    (function setupSorting(){
      const ths = Array.from(listTable.tHead?.rows[0]?.cells || []);
      const body = listTable.tBodies[0];
      const getCellText = (row, idx) => (row.children[idx]?.textContent || '').trim();
      const parseCell = (val, type) => {
        if (type === 'number') return toNum(val) ?? -Infinity;
        if (type === 'date') { const d = parseIsoDate(val); return d ? d.getTime() : -Infinity; }
        return (val || '').toLowerCase();
      };
      ths.forEach((th, idx) => {
        const type = th.getAttribute('data-sort') || 'text';
        th.addEventListener('click', () => {
          const rows = Array.from(body.querySelectorAll('tr'));
          const asc = !th.classList.contains('sort-asc');
          ths.forEach(h => h.classList.remove('sort-asc','sort-desc'));
          th.classList.add(asc ? 'sort-asc' : 'sort-desc');

          rows.sort((r1, r2) => {
            const a = parseCell(getCellText(r1, idx), type);
            const b = parseCell(getCellText(r2, idx), type);
            if (a < b) return asc ? -1 : 1;
            if (a > b) return asc ? 1 : -1;
            return 0;
          });

          const frag = document.createDocumentFragment();
          rows.forEach(r => frag.appendChild(r));
          body.appendChild(frag);
        });
      });
    })();
  }

  /* ====== Część dla EDYCJI (uruchomi się tylko jeśli formularz istnieje) ====== */
  const vin = document.getElementById('vin');
  const reg = document.getElementById('registration');
  const editForm = document.querySelector('form.edit-form') || document.querySelector('form[th\\:object], form[action*="/cars/save"]');

  if (editForm) {
    // VIN: uppercase, bez I/O/Q i innych znaków
    vin?.addEventListener('input', () => {
      let v = vin.value.toUpperCase().replace(/[^A-HJ-NPR-Z0-9]/g, '');
      v = v.replace(/[IOQ]/g, '');
      vin.value = v.slice(0, 17);
    });

    // Rejestracja: uppercase, A-Z 0-9 -
    reg?.addEventListener('input', () => {
      reg.value = reg.value.toUpperCase().replace(/[^A-Z0-9\-]/g, '').slice(0, 12);
    });

    // Live podpowiedź: pozostało km do rozrządu/oleju
    const q = sel => document.querySelector(sel);
    function numOf(sel) {
      const raw = q(sel)?.value ?? '';
      const n = Number(String(raw).replace(/\s/g,''));
      return Number.isFinite(n) ? n : null;
    }
    function fmt(km) {
      if (km === null) return '—';
      if (km < 0) return `po terminie o ${Math.abs(km)} km`;
      if (km === 0) return 'teraz';
      return `za ${km} km`;
    }

    const fieldsIds = ['#course','#timingSystemCourse','#timingSystemIntervalKm','#oilChangeCourse','#oilChangeIntervalKm'];
    function recalc() {
      const course = numOf('#course');
      const timingCourse = numOf('#timingSystemCourse');
      const timingInt = numOf('#timingSystemIntervalKm');
      const oilCourse = numOf('#oilChangeCourse');
      const oilInt = numOf('#oilChangeIntervalKm');

      const timingLeft = (course != null && timingCourse != null && timingInt != null)
        ? (timingCourse + timingInt - course) : null;
      const oilLeft = (course != null && oilCourse != null && oilInt != null)
        ? (oilCourse + oilInt - course) : null;

      const timingOut = document.getElementById('timingLeft');
      const oilOut = document.getElementById('oilLeft');
      if (timingOut) timingOut.textContent = fmt(timingLeft);
      if (oilOut) oilOut.textContent = fmt(oilLeft);
    }
    fieldsIds.forEach(id => q(id)?.addEventListener('input', recalc));
    recalc();

    // Prosta blokada wysłania z błędnymi patternami rejestracji/VIN (opcjonalnie)
    editForm.addEventListener('submit', (e) => {
      const invalids = editForm.querySelectorAll('input:invalid, textarea:invalid, select:invalid');
      if (invalids.length > 0) {
        // przewiń do pierwszego
        invalids[0].scrollIntoView({ behavior: 'smooth', block: 'center' });
      }
    });
  }
})();
// Na końcu istniejącego IIFE dopisz uniwersalne sortowanie dla dowolnych tabel .table-sortable
(function setupGenericSortable(){
  document.querySelectorAll('table.table-sortable').forEach(tbl => {
    if (!tbl.tHead) return;
    const ths = Array.from(tbl.tHead.rows[0].cells);
    const body = tbl.tBodies[0];
    const getCellText = (row, idx) => (row.children[idx]?.textContent || '').trim();

    const toNum = v => {
      if (v === null || v === undefined) return null;
      const s = String(v).trim().replace(/\s/g,'');
      if (!s) return null;
      const n = Number(s);
      return Number.isFinite(n) ? n : null;
    };
    const parseIsoDate = t => {
      if (!t) return null;
      const d = new Date(t);
      if (Number.isNaN(d.getTime())) return null;
      d.setHours(0,0,0,0);
      return d;
    };
    const parseCell = (val, type) => {
      if (type === 'number') return toNum(val) ?? -Infinity;
      if (type === 'date') { const d = parseIsoDate(val); return d ? d.getTime() : -Infinity; }
      return (val || '').toLowerCase();
    };

    ths.forEach((th, idx) => {
      const type = th.getAttribute('data-sort') || 'text';
      th.addEventListener('click', () => {
        const rows = Array.from(body.querySelectorAll('tr'));
        const asc = !th.classList.contains('sort-asc');
        ths.forEach(h => h.classList.remove('sort-asc','sort-desc'));
        th.classList.add(asc ? 'sort-asc' : 'sort-desc');

        rows.sort((r1, r2) => {
          const a = parseCell(getCellText(r1, idx), type);
          const b = parseCell(getCellText(r2, idx), type);
          if (a < b) return asc ? -1 : 1;
          if (a > b) return asc ? 1 : -1;
          return 0;
        });

        const frag = document.createDocumentFragment();
        rows.forEach(r => frag.appendChild(r));
        body.appendChild(frag);
      });
    });
  });
})();
// cars-cost.js – drobne ulepszenia UX formularza kosztu
(function () {
    const cost = document.getElementById('costInput');
    const from = document.getElementById('dateFrom');
    const to = document.getElementById('dateTo');
    const rangeHint = document.getElementById('rangeHint');

    // Formatowanie i walidacja kwoty w locie
    if (cost) {
        cost.addEventListener('input', () => {
            // wymuszamy punkt dziesiętny, brak spacji, tylko cyfry i kropka
            let v = (cost.value || '').replace(',', '.').replace(/[^\d.]/g, '');
            // tylko jedna kropka
            const parts = v.split('.');
            if (parts.length > 2) v = parts[0] + '.' + parts.slice(1).join('');
            // ograniczenie do 2 miejsc po przecinku
            if (v.includes('.')) {
                const [i, f] = v.split('.');
                v = i + '.' + (f ?? '').slice(0, 2);
            }
            cost.value = v;
        });
    }

    function parseDate(value) {
        if (!value) return null;
        const d = new Date(value);
        if (Number.isNaN(d.getTime())) return null;
        d.setHours(0, 0, 0, 0);
        return d;
    }

    function updateRangeHint() {
        if (!rangeHint) return;
        const fd = parseDate(from?.value);
        const td = parseDate(to?.value);
        if (!fd || !td) {
            rangeHint.textContent = 'Zakres dat (dni): —';
            rangeHint.classList.remove('sev-red');
            return;
        }
        const days = Math.round((td - fd) / (24 * 60 * 60 * 1000)) + 1;
        rangeHint.textContent = days >= 0
            ? `Zakres dat (dni): ${days}`
            : `Zakres dat (dni): błąd zakresu`;
        rangeHint.classList.toggle('sev-red', days < 0);
    }

    from?.addEventListener('change', updateRangeHint);
    to?.addEventListener('change', updateRangeHint);
    updateRangeHint();

    // Prosta ochrona przed wysłaniem z błędnym zakresem
    const form = document.querySelector('form.edit-form');
    form?.addEventListener('submit', (e) => {
        const fd = parseDate(from?.value);
        const td = parseDate(to?.value);
        if (fd && td && td < fd) {
            e.preventDefault();
            rangeHint?.scrollIntoView({ behavior: 'smooth', block: 'center' });
            rangeHint?.classList.add('sev-red');
        }
    });
})();