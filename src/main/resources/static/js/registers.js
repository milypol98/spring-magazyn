// registers.js — siła hasła, zgodność, pokaz/ukryj, CapsLock, UX
(function(){
    const byId = (id) => document.getElementById(id);
    const email = byId("email");
    const pwd = byId("password");
    const pwd2 = byId("confirmPassword");
    const bar = byId("strengthBar");
    const label = byId("strengthLabel");
    const submit = byId("submitBtn");
    const capsHint = byId("capsHint");
    const terms = byId("terms");

    const yearEl = byId("yearNow");
    if (yearEl) yearEl.textContent = new Date().getFullYear();

    // Toggle buttons
    function setupReveal(btnId, input) {
        const btn = byId(btnId);
        if (!btn || !input) return;
        btn.addEventListener("click", () => {
            const isText = input.type === "text";
            input.type = isText ? "password" : "text";
            btn.setAttribute("aria-label", isText ? "Pokaż hasło" : "Ukryj hasło");
            btn.textContent = isText ? "👁️" : "🙈";
            input.focus();
        });
    }
    setupReveal("togglePwd", pwd);
    setupReveal("togglePwd2", pwd2);

    // Strength meter
    function scorePassword(v){
        let score = 0;
        if (!v) return 0;
        // długość
        score += Math.min(10, v.length) * 5;
        // różnorodność
        if (/[a-z]/.test(v)) score += 10;
        if (/[A-Z]/.test(v)) score += 10;
        if (/\d/.test(v)) score += 10;
        if (/[^A-Za-z0-9]/.test(v)) score += 15;
        // bonus za złożoność
        if (/[A-Z]/.test(v) && /[a-z]/.test(v) && /\d/.test(v) && /[^A-Za-z0-9]/.test(v)) score += 20;
        return Math.max(0, Math.min(score, 100));
    }
    function updateStrength(){
        const v = pwd.value || "";
        const s = scorePassword(v);
        const insetRight = `${100 - s}%`;
        if (bar) bar.style.setProperty("inset", `0 ${insetRight} 0 0`);
        if (label) {
            let txt = "Słabe";
            if (s >= 70) txt = "Mocne";
            else if (s >= 40) txt = "Średnie";
            label.textContent = `Siła: ${txt}`;
        }
    }

    // Match info + submit enable
    function updateValidity(){
        const okMatch = pwd.value && pwd.value === pwd2.value;
        const okLen = (pwd.value || "").length >= 8;
        const okEmail = (email?.validity.valid) ?? false;
        const okTerms = terms?.checked ?? false;
        const can = okMatch && okLen && okEmail && okTerms;
        if (submit) submit.disabled = !can;
        const info = byId("matchInfo");
        if (info) {
            info.textContent = okMatch ? "Hasła zgodne." : "Hasła muszą być identyczne.";
            info.style.color = okMatch ? "#10b981" : "#6b7280";
        }
    }

    // CapsLock hint
    function capsHandler(e){
        if (!capsHint) return;
        if (e.getModifierState && typeof e.getModifierState === "function") {
            capsHint.classList.toggle("hidden", !e.getModifierState("CapsLock"));
        }
    }

    // Wire up
    ["input","change"].forEach(evt => {
        pwd?.addEventListener(evt, () => { updateStrength(); updateValidity(); });
        pwd2?.addEventListener(evt, updateValidity);
        email?.addEventListener(evt, updateValidity);
        terms?.addEventListener(evt, updateValidity);
    });
    pwd?.addEventListener("keyup", capsHandler);
    pwd?.addEventListener("keydown", capsHandler);

    // Initial
    updateStrength();
    updateValidity();

    // Shake on error
    const err = document.getElementById("regError");
    if (err) {
        const card = document.querySelector(".reg-card");
        card?.classList.add("shake");
        setTimeout(() => card?.classList.remove("shake"), 450);
    }
})();