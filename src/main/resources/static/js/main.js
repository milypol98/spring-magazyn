// JavaScript
(function () {
  const normalize = (s) =>
    (s || "")
      .toString()
      .toLowerCase()
      .normalize("NFD")
      .replace(/[\u0300-\u036f]/g, "")
      .trim();

  const parseDate = (val) => {
        // Obsługa najczęstszych formatów: YYYY-MM-DD, DD.MM.YYYY, DD/MM/YYYY
        if (!val) return NaN;
        const t = val.trim();
        // Spróbuj Date.parse dla ISO/rozpoznawalnych formatów:
        let d = Date.parse(t);
        if (!isNaN(d)) return d;

        // DD.MM.YYYY lub DD/MM/YYYY
        const m = t.match(/^(\d{1,2})[./\/](\d{1,2})[./\/](\d{2,4})$/);
        if (m) {
            const dd = parseInt(m[1], 10);
            const mm = parseInt(m[2], 10) - 1;
            let yyyy = parseInt(m[3], 10);
            if (yyyy < 100) yyyy += 2000;
            const dt = new Date(yyyy, mm, dd);
            return dt.getTime();
        }
        return NaN;
    };

  // Filtrowanie
  function filterTable(table, query) {
    const q = normalize(query);
    const rows = table.tBodies[0]?.rows || [];
    for (const row of rows) {
      const text = normalize(row.innerText);
      row.style.display = text.includes(q) ? "" : "none";
    }
  }

  // Sortowanie
    function sortTable(table, colIndex, type, dir) {
        const tbody = table.tBodies[0];
        const rows = Array.from(tbody.rows).filter(r => r.style.display !== "none"); // sortuj tylko widoczne

        const getVal = (cell) => {
            const t = (cell?.innerText ?? "").trim();
            switch (type) {
                case "number": {
                    const num = parseFloat(t.replace(/\s/g, "").replace(",", "."));
                    return isNaN(num) ? Number.NEGATIVE_INFINITY : num;
                }
                case "date": {
                    const ts = parseDate(t);
                    return isNaN(ts) ? Number.NEGATIVE_INFINITY : ts;
                }
                default:
                    return normalize(t);
            }
        };


    rows.sort((a, b) => {
      const av = getVal(a.cells[colIndex]);
      const bv = getVal(b.cells[colIndex]);
      if (av < bv) return dir === "asc" ? -1 : 1;
      if (av > bv) return dir === "asc" ? 1 : -1;
      return 0;
    });

    // Wstaw z powrotem
    for (const r of rows) tbody.appendChild(r);

    // Aktualizuj stan kierunku na th
    const ths = table.tHead.rows[0].cells;
    for (const th of ths) {
      th.removeAttribute("data-sort-dir");
      th.classList.remove("sorted-asc", "sorted-desc");
    }
    const th = ths[colIndex];
    th.setAttribute("data-sort-dir", dir);
    th.classList.add(dir === "asc" ? "sorted-asc" : "sorted-desc");
  }

  function attachSearch() {
    document.querySelectorAll(".table-search").forEach(input => {
      const table = document.querySelector(input.dataset.target);
      if (!table) return;
      input.addEventListener("input", () => filterTable(table, input.value));
    });
  }

  function attachSorting() {
    document.querySelectorAll("table").forEach(table => {
      const headRow = table.tHead?.rows?.[0];
      if (!headRow) return;

      Array.from(headRow.cells).forEach((th, idx) => {
        const type = th.getAttribute("data-sort") || "text";
        th.style.cursor = "pointer";
        th.addEventListener("click", (e) => {
          e.stopPropagation(); // na wszelki wypadek, by nie klikać wierszy
          const current = th.getAttribute("data-sort-dir") || "desc";
          const nextDir = current === "asc" ? "desc" : "asc";
          sortTable(table, idx, type, nextDir);
        });
      });
    });
  }

  // Inicjalizacja
  document.addEventListener("DOMContentLoaded", () => {
    attachSearch();
    attachSorting();
  });
})();