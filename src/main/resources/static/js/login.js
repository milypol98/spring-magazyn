// login.js — drobne UX: rok, pokaż/ukryj hasło, CapsLock, shake na błąd
(function(){
  const yearEl = document.getElementById("yearNow");
  if (yearEl) yearEl.textContent = new Date().getFullYear();

  // Toggle password visibility
  const pwd = document.getElementById("password");
  const btn = document.getElementById("togglePassword");
  if (pwd && btn) {
    btn.addEventListener("click", () => {
      const visible = pwd.getAttribute("type") === "text";
      pwd.setAttribute("type", visible ? "password" : "text");
      btn.setAttribute("aria-label", visible ? "Pokaż hasło" : "Ukryj hasło");
      btn.textContent = visible ? "👁️" : "🙈";
      pwd.focus();
    });
  }

  // CapsLock hint
  const capsHint = document.getElementById("capsHint");
  function capsHandler(e){
    if (!capsHint) return;
    // Jeśli zdarzenie udostępnia flagę
    if (e.getModifierState && typeof e.getModifierState === "function") {
      capsHint.classList.toggle("hidden", !e.getModifierState("CapsLock"));
    }
  }
  pwd?.addEventListener("keyup", capsHandler);
  pwd?.addEventListener("keydown", capsHandler);

  // Shake card on error
  const err = document.getElementById("loginError");
  if (err) {
    const card = document.querySelector(".login-card");
    card?.classList.add("shake");
    setTimeout(() => card?.classList.remove("shake"), 450);
  }
})();