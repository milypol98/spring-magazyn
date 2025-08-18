// dashboard.js
(function () {
    const pad = (n) => String(n).padStart(2, "0");

    // Live clock + date
    function tickClock() {
        const now = new Date();
        const hh = pad(now.getHours());
        const mm = pad(now.getMinutes());
        const el = document.getElementById("liveClock");
        const dateEl = document.getElementById("todayDate");
        const yearEl = document.getElementById("yearNow");
        if (el) el.textContent = `${hh}:${mm}`;
        if (dateEl) dateEl.textContent = now.toLocaleDateString(undefined, { weekday: "long", year: "numeric", month: "long", day: "numeric" });
        if (yearEl) yearEl.textContent = now.getFullYear();
    }
    tickClock();
    setInterval(tickClock, 15_000);

    // Filter tiles by search
    const search = document.getElementById("moduleSearch");
    const tilesGrid = document.getElementById("tilesGrid");
    const emptyState = document.getElementById("emptyState");
    function applyFilter() {
        if (!search || !tilesGrid) return;
        const q = search.value.trim().toLowerCase();
        const tiles = tilesGrid.querySelectorAll(".tile");
        let visible = 0;
        tiles.forEach(t => {
            const txt = (t.textContent || "") + " " + (t.getAttribute("data-keywords") || "");
            const ok = txt.toLowerCase().includes(q);
            t.style.display = ok ? "" : "none";
            if (ok) visible++;
        });
        if (emptyState) emptyState.classList.toggle("hidden", visible > 0);
    }
    if (search) {
        search.addEventListener("input", applyFilter);
    }

    // Density toggle (compact grid)
    const densityBtn = document.getElementById("toggleDensity");
    const densityKey = "dashboard.tiles.compact";
    function setDensity(compact) {
        tilesGrid?.classList.toggle("compact", compact);
        if (densityBtn) {
            densityBtn.setAttribute("aria-pressed", String(compact));
            densityBtn.textContent = compact ? "Standardowy" : "Kompaktowy";
        }
        try { localStorage.setItem(densityKey, compact ? "1" : "0"); } catch {}
    }
    if (densityBtn) {
        densityBtn.addEventListener("click", () => setDensity(!(tilesGrid?.classList.contains("compact"))));
        const saved = typeof localStorage !== "undefined" ? localStorage.getItem(densityKey) : null;
        setDensity(saved === "1");
    }

    // Track recent clicks
    const recentKey = "dashboard.recent";
    function loadRecent() {
        try { return JSON.parse(localStorage.getItem(recentKey) || "[]"); } catch { return []; }
    }
    function saveRecent(list) {
        try { localStorage.setItem(recentKey, JSON.stringify(list.slice(0, 6))); } catch {}
    }
    function renderRecent() {
        const box = document.getElementById("recentList");
        if (!box) return;
        const list = loadRecent();
        box.innerHTML = "";
        if (list.length === 0) {
            const li = document.createElement("li");
            li.innerHTML = '<span class="muted">Brak historii. Kliknij dowolny moduł.</span>';
            box.appendChild(li);
            return;
        }
        list.forEach(item => {
            const li = document.createElement("li");
            li.innerHTML = `<a href="${item.href}">${item.label}</a><span class="muted">${item.time}</span>`;
            box.appendChild(li);
        });
    }
    function handleTileClick(e) {
        const a = e.currentTarget;
        const label = a.querySelector("h4")?.textContent?.trim() || a.textContent.trim();
        const href = a.getAttribute("href") || a.getAttribute("th:href") || "#";
        const time = new Date().toLocaleTimeString(undefined, { hour: "2-digit", minute: "2-digit" });
        const list = loadRecent().filter(x => x.href !== href);
        list.unshift({ label, href, time });
        saveRecent(list);
        renderRecent();
        // click ripple-ish glow
        a.style.transform = "translateY(-2px) scale(1.01)";
        a.style.boxShadow = "0 16px 32px rgba(17,24,39,.16)";
        setTimeout(() => {
            a.style.transform = "";
            a.style.boxShadow = "";
        }, 200);
    }
    document.querySelectorAll(".tile").forEach(t => t.addEventListener("click", handleTileClick));
    renderRecent();

    // Initial filter (if any saved by browser autofill)
    applyFilter();
})();